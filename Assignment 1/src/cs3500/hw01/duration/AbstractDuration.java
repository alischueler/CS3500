package cs3500.hw01.duration;

/**
 * Abstract base class for implementations of {@link Duration}.
 */
abstract class AbstractDuration implements Duration {

  /**
   * Constructs a {@link Duration} in a manner selected by concrete subclasses of this class.
   *
   * @param inSeconds the length in seconds
   * @return the new {@code Duration}
   */
  protected abstract Duration fromSeconds(long inSeconds);

  @Override
  public String toString() {
    return asHms();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Duration)) {
      return false;
    }

    return ((Duration) that).inSeconds() == this.inSeconds();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(inSeconds());
  }

  @Override
  public int compareTo(Duration that) {
    return Long.compare(this.inSeconds(), that.inSeconds());
  }

  @Override
  public Duration plus(Duration that) {
    return fromSeconds(this.inSeconds() + that.inSeconds());
  }

  /**
   * Converts an unpacked hours-minutes-seconds duration to its length in seconds.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @return the duration in seconds
   */
  protected static long inSeconds(int hours, int minutes, int seconds) {
    return 3600 * hours + 60 * minutes + seconds;
  }

  /**
   * Formats an unpacked hours-minutes-seconds duration in the same {@code H:MM:SS} format that
   * {@link Duration#asHms()} returns. Assumes that
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @return formatted duration
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static String asHms(int hours, int minutes, int seconds) {
    return String.format("%d:%02d:%02d", hours, minutes, seconds);
  }

  /**
   * Ensures that the hours, minutes, and seconds are all non-negative. Is factoring this out
   * overkill? Or should we also factor out the {@code inSeconds < 0} check in the two unary
   * constructors? Discuss.
   *
   * @param hours   the number of hours
   * @param minutes the number of minutes
   * @param seconds the number of seconds
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static void ensureHms(int hours, int minutes, int seconds) {
    if (hours < 0 || minutes < 0 || seconds < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }
  }

  /**
   * Returns the number of whole hours in the given number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of hours
   * @throws ArithmeticException if the correct result cannot fit in an {@code int}.
   */
  protected static int hoursOf(long inSeconds) {
    if (inSeconds / 3600 > Integer.MAX_VALUE) {
      throw new ArithmeticException("result cannot fit in type");
    }

    return (int) (inSeconds / 3600);
  }

  /**
   * Returns the number of whole minutes in the given number of seconds, less the number of whole
   * hours.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining minutes
   */
  protected static int minutesOf(long inSeconds) {
    return (int) (inSeconds / 60 % 60);
  }

  /**
   * Returns the number of seconds remaining after all full minutes are removed from the given
   * number of seconds.
   *
   * @param inSeconds the total number of seconds
   * @return the number of remaining seconds
   */
  protected static int secondsOf(long inSeconds) {
    return (int) (inSeconds % 60);
  }

  /**
   * Checks if the given character is the second character of any format specifiers.
   *
   * @param c2 character that comes directly after any "%" in the template
   * @return the String corresponding to the format specifier identified
   * @throws IllegalArgumentException if the character is not part of a format specifier
   */
  private String checkSpecifiers(Character c2) {
    String s2 = Character.toString(c2);
    long sec = this.inSeconds();
    if ("t".equals(s2)) {
      return Long.toString(sec);
    }
    if ("h".equals(s2) || ("H".equals(s2) && this.hoursOf(sec) >= 10)) {
      return Integer.toString(this.hoursOf(sec));
    }
    if ("H".equals(s2) && this.hoursOf(sec) < 10) {
      return "0" + Integer.toString(this.hoursOf(sec));
    }
    if ("m".equals(s2) || ("M".equals(s2) && this.minutesOf(sec) >= 10)) {
      return Integer.toString(this.minutesOf(sec));
    }
    if ("M".equals(s2) && this.minutesOf(sec) < 10) {
      return "0" + Integer.toString(this.minutesOf(sec));
    }
    if ("s".equals(s2) || ("S".equals(s2) && this.secondsOf(sec) >= 10)) {
      return Integer.toString(this.secondsOf(sec));
    }
    if ("S".equals(s2) && this.secondsOf(sec) < 10) {
      return "0" + Integer.toString(this.secondsOf(sec));
    }
    if ("%".equals(s2)) {
      return "%";
    } else {
      throw new IllegalArgumentException("Malformed template");
    }
  }

  @Override
  public String format(String template) {
    String finalString = "";
    if (template == null) {
      throw new IllegalArgumentException("Malformed template");
    }
    if (template.length() == 0) {
      return finalString;
    }
    for (int i = 0; i < template.length(); i++) {
      if (template.length() == 1 && "%".equals(template)) {
        throw new IllegalArgumentException("Malformed template");
      }
      if (template.length() == 1 && !("%".equals(template))) {
        finalString += template;
      }
      if (template.substring(i, i + 1).equals("%")) {
        if ((template.length() - i) >= 2) {
          finalString += checkSpecifiers(template.charAt(i + 1));
          i = i + 1;
        } else {
          throw new IllegalArgumentException("Malformed template");
        }
      } else {
        finalString += Character.toString(template.charAt(i));
      }
    }
    return finalString;
  }

}


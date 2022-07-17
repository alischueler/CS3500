package cs3500.pyramidsolitaire.model.hw02;

/**
 * Enumerates 14 possible Values.
 */
public enum Value {
  A(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9),
  Ten(10), J(11), Q(12), K(13), Empty(0);
  private final Integer value;

  /**
   * Constructs a Value with the given Value.
   * @param value represents the value of the Value.
   */
  Value(Integer value) {
    this.value = value;
  }

  /**
   * Determines the value of this Value.
   * @return the value of this value.
   */
  public int getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    switch (this.value) {
      case 1: return "A";
      case 2: return "2";
      case 3: return "3";
      case 4: return "4";
      case 5: return "5";
      case 6: return "6";
      case 7: return "7";
      case 8: return "8";
      case 9: return "9";
      case 10: return "10";
      case 11: return "J";
      case 12: return "Q";
      case 13: return "K";
      default: return "";
    }
  }
}

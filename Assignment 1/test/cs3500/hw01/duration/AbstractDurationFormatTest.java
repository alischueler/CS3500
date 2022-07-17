package cs3500.hw01.duration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Tests for the format method of {@link Duration}s. 
    Add your tests to this class to assure that your format 
    method works properly
*/
public abstract class AbstractDurationFormatTest {

  @Test
  public void formatExample1() {
    assertEquals("4 hours, 0 minutes, and 9 seconds",
                  hms(4, 0, 9)
                    .format("%h hours, %m minutes, and %s seconds"));
  }

  @Test
  public void formatExample2() {
    assertEquals("4:05:17",
                  hms(4, 5, 17).format("%h:%M:%S"));
  }

  // ADD MORE TESTS HERE. 
  // THE ABOVE TEST NAMES ARE MERE PLACEHOLDERS. NAME YOUR TESTS MEANINGFULLY. 
  // IF YOU NAME YOUR TESTS SIMILAR TO ABOVE, YOU WILL LOSE POINTS!
  // Your tests must only use hms(...) and sec(...) to construct new Durations
  // and must *not* directly say "new CompactDuration(...)" or
  // "new HmsDuration(...)"

  //tests for an empty template string when given correct HMS
  @Test
  public void testEmptyHMS() {
    assertEquals("", hms(34, 25, 9).format(""));
  }

  //test for an empty template string when given a correct CompactDuration
  @Test
  public void testEmptySec() {
    assertEquals("", sec(900).format(""));
  }

  //test for no format specifiers when given a correct HMS
  @Test
  public void testNoSpecifiersHMS() {
    assertEquals("HelloWorld",
        hms(900, 45, 39).format("HelloWorld"));
  }

  //test for no format specifiers when given a correct CompactDuration
  @Test
  public void testNoSpecifiersSec() {
    assertEquals("Goodbye Moon", sec(567).format("Goodbye Moon"));
  }

  //tests for using every specifier in one template when given correct HMS
  @Test
  public void testAllSpecifiersHMS() {
    assertEquals("%320662894220489",
        hms(89, 4, 22).format("%%%t%h%m%S%M%H"));
  }

  //tests for using every specifier in one template when given correct CompactDuration
  @Test
  public void testAllSpecifiersSec() {
    assertEquals("0203%200032000",
        sec(200).format("%h%S%m%%%t%M%s%H"));
  }

  //tests for 0 hours when given correct HMS
  @Test
  public void test0hrsHMS() {
    assertEquals("0:00:2:02:32:32:152, hello%",
        hms(0, 2, 32).format("%h:%H:%m:%M:%s:%S:%t, hello%%"));
  }

  //tests for 0 hours when given correct CompactDuration
  @Test
  public void test0hrsSec() {
    assertEquals("hello%, 800 20 20 13 13 0 00",
        sec(800).format("hello%%, %t %s %S %m %M %h %H"));
  }

  //tests for 0 mins when given correct HMS
  @Test
  public void test0minHMS() {
    assertEquals("61210 10 10 !hello%, 0 00 17 17",
        hms(17, 0, 10).format("%t %s %S !hello%%, %m %M %h %H"));
  }

  //tests for 0 mins when given correct CompactDuration
  @Test
  public void test0minSec() {
    assertEquals("10804 04 4 ?hello%, 00 0 03 3",
        sec(10804).format("%t %S %s ?hello%%, %M %m %H %h"));
  }

  //tests for 0 seconds when given correct HMS
  @Test
  public void test0secHMS() {
    assertEquals("24240 00 0 <>^%, 44 44 06 6",
        hms(6, 44, 0).format("%t %S %s <>^%%, %M %m %H %h"));
  }

  //tests for 0 seconds when given correct CompactDuration
  @Test
  public void test0secSec() {
    assertEquals("3660 00 0 look, 01 1 01 1",
        sec(3660).format("%t %S %s look, %M %m %H %h"));
  }

  //tests for single digit hours when given correct HMS
  @Test
  public void testsinglehrsHMS() {
    assertEquals("09 vs 9",
        hms(9, 4, 22).format("%H vs %h"));
  }

  //tests for single digit hours when given correct CompactDuration
  @Test
  public void testsinglehrsSec() {
    assertEquals("1 vs 01",
        sec(3600).format("%h vs %H"));
  }

  //tests for single digit minutes when given correct HMS
  @Test
  public void testsingleminHMS() {
    assertEquals("4 then 04",
        hms(89, 4, 22).format("%m then %M"));
  }

  //tests for single digit minutes when given correct CompactDuration
  @Test
  public void testsingleminSec() {
    assertEquals("06 cool 6", sec(360).format("%M cool %m"));
  }

  //tests for single digit seconds when given correct HMS
  @Test
  public void testsinglesecHMS() {
    assertEquals("909",
        hms(89, 4, 9).format("%s%S"));
  }

  //tests for single digit seconds when given correct CompactDuration
  @Test
  public void testsinglesecSec() {
    assertEquals("099",
        sec(129).format("%S%s"));
  }


  //testing for more than 2 digit hours when given correct HMS
  @Test
  public void testManyHoursHMS() {
    assertEquals("367 367",
        hms(367, 4, 59).format("%h %H"));
  }

  //testing for more than 2 digit hours when given correct CompactDuration
  @Test
  public void testManyHoursSec() {
    assertEquals("1000 1000",
        sec(3600000).format("%h %H"));
  }

  //tests for nonstandard mins
  @Test
  public void testnonStandardMins() {
    assertEquals("10 10 14 14 55 55",
        hms(9, 74, 55).format("%h %H %m %M %s %S"));
  }

  //tests for nonstandard secs
  @Test
  public void testnonStandardSecs() {
    assertEquals("367 367 10 10 40 40",
        hms(367, 9, 100).format("%h %H %m %M %s %S"));
  }

  //tests for leftmost specifier when given HMS
  @Test
  public void testLeftmostHMS() {
    assertEquals("%t21%",
        hms(89, 11, 21).format("%%t%S%%"));
  }

  //test the leftmost specifier when given CompactDuration
  @Test
  public void testLeftMostDuration() {
    assertEquals("I will see you in 35 seconds, %%mt35",
        sec(35).format("I will see you in %s seconds, %%%%mt%S"));
  }

  //tests for malformed template with correct hms
  @Test(expected = IllegalArgumentException.class)
  public void MalformedTempHMS_thenExpectationSatisfied() {
    hms(89, 11, 21).format("hello%m%");
  }

  //tests for malformed template with correct sec
  @Test(expected = IllegalArgumentException.class)
  public void MalformedTempSec_thenExpectationSatisfied() {
    sec(29765).format("%%%");
  }

  //tests for null template with correct hms
  @Test(expected = IllegalArgumentException.class)
  public void NullTempHMS_thenExpectationSatisfied() {
    hms(89, 11, 21).format(null);
  }

  //tests for null template with correct sec
  @Test(expected = IllegalArgumentException.class)
  public void NullTempSec_thenExpectationSatisfied() {
    sec(29765).format(null);
  }
  

  /*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within 
    your tests.
   */
  /**
   * Constructs an instance of the class under test representing the duration
   * given in hours, minutes, and seconds.
   *
   * @param hours the hours in the duration
   * @param minutes the minutes in the duration
   * @param seconds the seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration hms(int hours, int minutes, int seconds);

  /**
   * Constructs an instance of the class under test representing the duration
   * given in seconds.
   *
   * @param inSeconds the total seconds in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration sec(long inSeconds);

  /**
   * Concrete class for testing HmsDuration implementation of Duration.
   */
  public static final class HmsDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new HmsDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new HmsDuration(inSeconds);
    }
  }

  /**
   * Concrete class for testing CompactDuration implementation of Duration.
   */
  public static final class CompactDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration hms(int hours, int minutes, int seconds) {
      return new CompactDuration(hours, minutes, seconds);
    }

    @Override
    protected Duration sec(long inSeconds) {
      return new CompactDuration(inSeconds);
    }
  }
}

package cs3500.hw01.publication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Webpage: unit tests to ensure that Webpages can be
 * cited correctly and otherwise behave correctly.
 */
public class WebpageTest {

  Publication cs3500 = new Webpage("CS3500: Object-Oriented Design",
      "http://www.ccs.neu.edu/course/cs3500/",
      "August 11, 2014");
  Publication wiki = new Webpage(
      "Wikipedia", "http://wiki.com", "September 15, 2020");
  Publication allNull = new Webpage(
      null, null, null);
  Publication nullName = new Webpage(
      null, "http://somecite.com", "Spetember 21, 2020");
  Publication nullCite = new Webpage(
      "Not Null", null, "September 24, 2020");
  Publication nullDate = new Webpage(
      "Also not null", "http://notNull.com", null);

  //tests citing cs3500 site in MLA format
  @Test
  public void testCiteMlaWebpageCS3500() {
    assertEquals(
        "\"CS3500: Object-Oriented Design.\" Web."
            + " August 11, 2014 <http://www.ccs.neu.edu/course/cs3500/>.",
        cs3500.citeMla());
  }

  //tests citing wiki site in MLA format
  @Test
  public void testCiteMlaWebpageWiki() {
    assertEquals("\"Wikipedia.\" Web. September 15, 2020 <http://wiki.com>.",
        wiki.citeMla());
  }

  //tests citing cs3500 site in APA format
  @Test
  public void testCiteApaWebpagecs3500() {
    assertEquals(
        "CS3500: Object-Oriented Design. Retrieved August 11, 2014, "
            + "from http://www.ccs.neu.edu/course/cs3500/.", cs3500.citeApa());
  }

  //tests citing wiki site in APA format
  @Test
  public void testCiteApaWebpageWiki() {
    assertEquals("Wikipedia. Retrieved September 15, 2020, from http://wiki.com.",
        wiki.citeApa());
  }

  //tests citing all null fields site in MLA format
  @Test(expected = IllegalArgumentException.class)
  public void testCiteMlaAllNull() {
    allNull.citeMla();
  }

  //tests citing all null fields site in APA format
  @Test(expected = IllegalArgumentException.class)
  public void testCiteApaAllNull() {
    allNull.citeApa();
  }

  //tests citing a null name site in MLA format
  @Test(expected = IllegalArgumentException.class)
  public void testCiteMlaNameNull() {
    nullName.citeMla();
  }

  //test citing a null name site in APA format
  @Test(expected = IllegalArgumentException.class)
  public void testCiteApaNameNull() {
    nullName.citeApa();
  }

  //test citing a null site in MLA format
  @Test(expected = IllegalArgumentException.class)
  public void testCiteMlaCiteNull() {
    nullCite.citeMla();
  }

  //tests citing a null site in APA format
  @Test(expected = IllegalArgumentException.class)
  public void testCiteApaCiteNull() {
    nullCite.citeApa();
  }

  //tests citing a null date in MLA format
  @Test(expected = IllegalArgumentException.class)
  public void testCiteMlaDateNull() {
    nullDate.citeApa();
  }

  //tests citing a null date in APA format
  @Test(expected = IllegalArgumentException.class)
  public void testCiteApaDateNull() {
    nullDate.citeApa();
  }
}
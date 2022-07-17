package cs3500.hw01.publication;

/**
 * Represents bibliographic information for webpages.
 */
public class Webpage implements Publication {
  private final String title;
  private final String url;
  private final String retrieved;

  /**
   * Constructs a {@code Webpage} object.
   *
   * @param title the title of the webpage
   * @param url the url of the webpage
   * @param retrieved the date the webpage was retrieved
   */
  public Webpage(String title, String url, String retrieved) {
    this.title = title;
    this.url = url;
    this.retrieved = retrieved;
  }

  /**
   * Cites a webpage in APA format.
   *
   * @return the string value of the APA citation
   */
  public String citeApa() {
    if (this.title == null || this.url == null || this.retrieved == null) {
      throw new IllegalArgumentException("Null");
    } else {
      return title + ". Retrieved " + retrieved + ", from " + url + ".";
    }
  }

  /**
   * Cites a webpage in MLA format.
   *
   * @return the string value of the MLA citation
   */
  public String citeMla() {
    if (this.title == null || this.url == null || this.retrieved == null) {
      throw new IllegalArgumentException("Null");
    } else {
      return "\"" + title + "." + "\"" + " Web. " + retrieved + " <" + url + ">.";
    }
  }
}

package cs3500.pyramidsolitaire.model.hw02;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A class to test the public methods of Suits.
 */
public class SuitTest {

  //tests that we are able to access Spades character
  @Test
  public void testSpades() {
    Card c = new Card(1, 4);
    assertEquals("A♠", c.toString());
  }

  //tests we are able to access Clubs character
  @Test
  public void testClubs() {
    Card c = new Card(11, 1);
    assertEquals("J♣", c.toString());
  }

  //tests we are able to access Diamonds Character
  @Test
  public void testDiamonds() {
    Card c = new Card(4, 2);
    assertEquals("4♦", c.toString());
  }

  //tests we are able to access Hearts Character
  @Test
  public void testHearts() {
    Card c = new Card(10, 3);
    assertEquals("10♥", c.toString());
  }


}
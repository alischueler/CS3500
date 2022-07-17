

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.Suit;
import org.junit.Test;

/**
 * A class to test the public methods of cards.
 */
public class CardTest {

  //tests to see if a empty card is empty
  @Test
  public void testEmptyforempty() {
    Card c = new Card();
    assertEquals(true, c.isEmpty());
  }

  //tests to see if a non empty card is empty
  @Test
  public void testNonEmptyforempty() {
    Card c = new Card(4, 8);
    assertEquals(false, c.isEmpty());
  }

  //finds the value of the given high card
  @Test
  public void testFindValueHigh() {
    Card c = new Card(13, 2);
    assertEquals(13, c.findValue());
  }

  //finds the value of the given low card
  @Test
  public void testFindValueLow() {
    Card c = new Card(1, 4);
    assertEquals(1, c.findValue());
  }

  //finds the exposed value of a newly created card
  @Test
  public void testFindExposedFalse() {
    Card c = new Card(12, 4);
    assertEquals(false, c.isExposed());
  }

  //tests toString for empty values and suits
  @Test
  public void toStringEmptyBoth() {
    List<Card> cardList = new ArrayList<>();
    cardList.add(new Card(3, 2));
    cardList.add(new Card());
    cardList.add(new Card(13, 2));
    assertEquals(". ", cardList.get(1).toString());
  }

  //tests to string for card with empty value
  @Test
  public void toStringEmptyValue() {
    Card c = new Card(15, 2);
    assertEquals(". ", c.toString());
  }

  //tests toString with card with empty suit
  @Test
  public void toStringEmptysuit() {
    Card c = new Card(2, 14);
    assertEquals(". ", c.toString());
  }

  //tests to string for ace
  @Test
  public void toStringA() {
    Card c = new Card(1, 2);
    assertEquals("A♦", c.toString());
  }

  //tests to string for 2
  @Test
  public void toString2() {
    Card c = new Card(2, 3);
    assertEquals("2♥", c.toString());
  }

  //tests toString for 3
  @Test
  public void toString3() {
    Card c = new Card(3, 4);
    assertEquals("3♠", c.toString());
  }

  //tests tostring for 4
  @Test
  public void toString4() {
    Card c = new Card(4, 1);
    assertEquals("4♣", c.toString());
  }

  //tests toString for 5
  @Test
  public void toString5() {
    Card c = new Card(5, 2);
    assertEquals("5♦", c.toString());
  }

  //tests toString for 6
  @Test
  public void toString6() {
    Card c = new Card(6, 3);
    assertEquals("6♥", c.toString());
  }

  //tests to String for 7
  @Test
  public void toString7() {
    Card c = new Card(7, 4);
    assertEquals("7♠", c.toString());
  }

  //tests to String for 8
  @Test
  public void toString8() {
    Card c = new Card(8, 1);
    assertEquals("8♣", c.toString());
  }

  //tests toString for 9
  @Test
  public void toString9() {
    Card c = new Card(9, 2);
    assertEquals("9♦", c.toString());
  }

  //tests toString for 10
  @Test
  public void toString10() {
    Card c = new Card(10, 3);
    assertEquals("10♥", c.toString());
  }

  //tests toString for 11
  @Test
  public void toString11() {
    Card c = new Card(11, 4);
    assertEquals("J♠", c.toString());
  }

  //tests toString for 12
  @Test
  public void toString12() {
    Card c = new Card(12, 1);
    assertEquals("Q♣", c.toString());
  }

  //tests toString for 13
  @Test
  public void toString13() {
    Card c = new Card(13, 2);
    assertEquals("K♦", c.toString());
  }

  //testing two cards same suit different value are not equal
  @Test
  public void SameSuitDiffValue() {
    Card c1 = new Card(13, 2);
    Card c2 = new Card(1, 2);
    assertEquals(false, c1.equals(c2));
    assertEquals(false, c2.equals(c1));
  }


  //testing two cards different suit same value are not equal
  @Test
  public void SameValDiffSuit() {
    Card c1 = new Card(4, 4);
    Card c2 = new Card(4, 2);
    assertEquals(false, c1.equals(c2));
    assertEquals(false, c2.equals(c1));
  }

  //testing two cards same suit same value are equal
  @Test
  public void SamSuitSameValue() {
    Card c1 = new Card(5, 1);
    Card c2 = new Card(5, 1);
    assertEquals(true, c1.equals(c2));
    assertEquals(true, c2.equals(c1));
  }

  //tests that we can determine clubs suit
  @Test
  public void testClubs() {
    Card c1 = new Card(5, 1);
    assertEquals(Suit.Clubs, c1.findSuit());
  }

  //test that we can determine diamonds suit
  @Test
  public void testDiamonds() {
    Card c1 = new Card(7, 2);
    assertEquals(Suit.Diamonds, c1.findSuit());
  }

  //tests that we can determine hearts suit
  @Test
  public void testHearts() {
    Card c1 = new Card(5, 3);
    assertEquals(Suit.Hearts, c1.findSuit());
  }

  //tests that we can determine clubs suit
  @Test
  public void testSpades() {
    Card c1 = new Card(5, 4);
    assertEquals(Suit.Spades, c1.findSuit());
  }

  //tests that we can determine empty suit
  @Test
  public void testEmptyOne() {
    Card c1 = new Card(8, 6);
    assertEquals(Suit.Empty, c1.findSuit());
  }

  @Test
  public void testEmptyBoth() {
    Card c1 = new Card(15, 6);
    assertEquals(Suit.Empty, c1.findSuit());
  }

  //tests that we can determine A value
  @Test
  public void testAce() {
    Card c1 = new Card(1, 4);
    assertEquals(1, c1.findValue());
  }

  //tests that we can determine 2 value
  @Test
  public void testTwo() {
    Card c1 = new Card(2, 2);
    assertEquals(2, c1.findValue());
  }

  //tests that we can determine 3 value
  @Test
  public void testThree() {
    Card c1 = new Card(3, 4);
    assertEquals(3, c1.findValue());
  }

  //tests that we can determine 4 value
  @Test
  public void testFour() {
    Card c1 = new Card(4, 1);
    assertEquals(4, c1.findValue());
  }

  //tests that we can determine 5 value
  @Test
  public void testFive() {
    Card c1 = new Card(5, 3);
    assertEquals(5, c1.findValue());
  }

  //tests that we can determine 6 value
  @Test
  public void testSix() {
    Card c1 = new Card(6, 4);
    assertEquals(6, c1.findValue());
  }

  //tests that we can determine 7 value
  @Test
  public void testSeven() {
    Card c1 = new Card(7, 2);
    assertEquals(7, c1.findValue());
  }

  //tests that we can determine 8 value
  @Test
  public void testEight() {
    Card c1 = new Card(8, 2);
    assertEquals(8, c1.findValue());
  }

  //tests that we can determine 9 value
  @Test
  public void testNine() {
    Card c1 = new Card(9, 4);
    assertEquals(9, c1.findValue());
  }

  //tests that we can determine 10 value
  @Test
  public void testTen() {
    Card c1 = new Card(10, 4);
    assertEquals(10, c1.findValue());
  }

  //tests that we can determine 11 value
  @Test
  public void testEleven() {
    Card c1 = new Card(11, 1);
    assertEquals(11, c1.findValue());
  }

  //tests that we can determine 12 value
  @Test
  public void testQueen() {
    Card c1 = new Card(12, 4);
    assertEquals(12, c1.findValue());
  }

  //tests that we can determine 13 value
  @Test
  public void testKing() {
    Card c1 = new Card(13, 3);
    assertEquals(13, c1.findValue());
  }

  //tests that an empty card has empty suit
  @Test
  public void testValueEmpty() {
    Card c1 = new Card();
    assertEquals(0, c1.findValue());
  }

  //tests that a card with real value but empty suit has empty suit
  @Test
  public void testValueAllEmpty() {
    Card c1 = new Card(25, 3);
    assertEquals(0, c1.findValue());
  }

}
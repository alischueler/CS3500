package cs3500.pyramidsolitaire.model.hw02;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * A class for testing the public methods of Value.
 */
public class ValueTest {

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
    List<Card> cardList = new ArrayList<>();
    cardList.add(new Card(15, 2));
    assertEquals(". ", cardList.get(0).toString());
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



}


import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * A class for testing public methods of MultiPyramidSolitaire.
 */
public class MultiPyramidSolitaireTest extends BasicPyramidSolitaireTest {
  

  //tests that start game throws exception with too many rows
  @Test (expected = IllegalArgumentException.class)
  public void TooLargeRowClose() {
    PyramidSolitaireModel<Card> basic = new MultiPyramidSolitaire();
    basic.startGame(basic.getDeck(), false, 9, 4);
  }


  //tests that the deck created is 104 cards
  @Test
  public void testDeckSize() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    assertEquals(104, model.getDeck().size());
  }

  //tests that start game throws exception with greater than 52 deck
  @Test (expected = IllegalArgumentException.class)
  public void LargeDeck() {
    PyramidSolitaireModel<Card> basic = new MultiPyramidSolitaire();
    List<Card> largeDeck = new ArrayList<>();
    for (int i = 0; i < 105; i++) {
      largeDeck.add(new Card(5, 2));
    }
    basic.startGame(largeDeck, false, 6, 2);
  }

  //tests that start game throws exception with duplicates in deck
  @Test (expected = IllegalArgumentException.class)
  public void DuplicateDeck() {
    PyramidSolitaireModel<Card> basic = new MultiPyramidSolitaire();
    List<Card> duplicate = new ArrayList<>();
    for (int i = 0; i < 104; i++) {
      duplicate.add(new Card(5, 2));
    }
    basic.startGame(duplicate, false, 90, -5);
  }

  //tests that start game throws exception with small amount of duplicates in deck
  @Test (expected = IllegalArgumentException.class)
  public void SmallDuplicateDeck() {
    PyramidSolitaireModel<Card> basic = new MultiPyramidSolitaire();
    List<Card> duplicate = new ArrayList<>();
    for (int i = 1; i < 13; i++) {
      for (int j = 1; j < 3; j++) {
        for (int l = 0; l < 2; l++) {
          duplicate.add(new Card(i, j));
        }
      }
    }
    for (int i = 1; i < 13; i++) {
      for (int j = 1; j < 2; j++) {
        for (int l = 0; l < 2; l++) {
          duplicate.add(new Card(i, j));
        }
      }
    }
    basic.startGame(duplicate, false, 6, 5);
  }

  //tests that start game throws exception with some null and duplicate in deck
  @Test (expected = IllegalArgumentException.class)
  public void SomeNullDeck() {
    PyramidSolitaireModel<Card> basic = new MultiPyramidSolitaire();
    List<Card> somenull = new ArrayList<>();
    somenull.add(null);
    for (int i = 0; i < 102; i++) {
      somenull.add(new Card(5, 2));
    }
    somenull.add(null);
    basic.startGame(somenull, false, 90, -5);
  }

  //tests that the double deck will still be a valid deck
  @Test
  public void testDeckValid() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    model.startGame(model.getDeck(), false, 7, 5);
    assertEquals(5, model.getNumDraw());
  }

  //tests the start of the clubs suit is where it should be in the deck
  @Test
  public void testStartClubs2() {
    PyramidSolitaireModel<Card> model1 =
        new MultiPyramidSolitaire();
    Card c = new Card(1,1);
    assertEquals(c, model1.getDeck().get(52));
  }

  //tests the start of the clubs suit is where it should be in the deck
  @Test
  public void testStartDiamonds2() {
    PyramidSolitaireModel<Card> model1 =
        new MultiPyramidSolitaire();
    Card c = new Card(1,2);
    assertEquals(c, model1.getDeck().get(65));
  }

  //tests the start of the clubs suit is where it should be in the deck
  @Test
  public void testStartHearts2() {
    PyramidSolitaireModel<Card> model1 =
        new MultiPyramidSolitaire();
    Card c = new Card(1,3);
    assertEquals(c, model1.getDeck().get(78));
  }

  //tests the start of the clubs suit is where it should be in the deck
  @Test
  public void testStartSpades2() {
    PyramidSolitaireModel<Card> model1 =
        new MultiPyramidSolitaire();
    Card c = new Card(1,4);
    assertEquals(c, model1.getDeck().get(91));
  }


  //tests that the correct row width is returned at 7 rows
  @Test
  public void testRowWidth7Rows() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    model.startGame(model.getDeck(), false, 7, 5);
    assertEquals(10, model.getRowWidth(3));
  }

  //tests that the correct row width is returned at 4 rows
  @Test
  public void testRowWidth4Rows() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    model.startGame(model.getDeck(), false, 4, 5);
    assertEquals(5, model.getRowWidth(0));
  }

  //tests that the correct row width is returned at 4 rows
  @Test
  public void testRowWidth1Row() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    model.startGame(model.getDeck(), false, 1, 5);
    assertEquals(1, model.getRowWidth(0));
  }

  //tests that the correct card is retreived
  @Test
  public void testGetCardAtCorrect() {
    PyramidSolitaireModel<Card> model1 =
        new MultiPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 2, 10);
    assertEquals(new Card(5, 1), model1.getCardAt(1, 1));
  }

  //tests that a null card is retrieved if trying to get a card in a place holder
  @Test
  public void testGetCardAtPlaceHolder() {
    PyramidSolitaireModel<Card> model1 =
        new MultiPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 5, 4);
    assertEquals(null, model1.getCardAt(0, 1));
  }

  //tests getting the score of a multipyramid
  @Test
  public void getScoreMulti() {
    PyramidSolitaireModel<Card> model1 =
        new MultiPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 5, 4);
    assertEquals(210, model1.getScore());
  }
}
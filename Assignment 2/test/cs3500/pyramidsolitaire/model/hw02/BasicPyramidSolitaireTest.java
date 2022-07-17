package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A class for testing public methods of BasicPyramidSolitaire.
 */
public class BasicPyramidSolitaireTest {


  //tests that start game throws exception with too many rows
  @Test (expected = IllegalArgumentException.class)
  public void TooLargeRow() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    basic.startGame(basic.getDeck(), false, 10, 4);
  }

  //tests that start game throws exception with too many draw cards
  @Test (expected = IllegalArgumentException.class)
  public void TooManyDraw() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    basic.startGame(basic.getDeck(), false, 5, 54);
  }

  //tests that start game throws exception with 0 pyramid rows
  @Test (expected = IllegalArgumentException.class)
  public void NotEnoughPyramid() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    basic.startGame(basic.getDeck(), false, 0, 24);
  }

  //tests that start game throws exception with neg draw cards
  @Test (expected = IllegalArgumentException.class)
  public void NegDraw() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    basic.startGame(basic.getDeck(), false, 90, -5);
  }

  //tests that start game throws exception with null deck
  @Test (expected = IllegalArgumentException.class)
  public void NullDeck() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    List<Card> nullCards = new ArrayList<>();
    basic.startGame(nullCards, false, 90, -5);
  }

  //tests that start game throws exception with deck with invalid cards
  @Test (expected = IllegalArgumentException.class)
  public void invalidDeck() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    List<Card> invalidCards = new ArrayList<>();
    invalidCards.add(new Card());
    invalidCards.add(new Card(3, 2));
    invalidCards.add(new Card());
    basic.startGame(invalidCards, false, 90, -5);
  }

  //tests that start game throws exception with less than 52 deck
  @Test (expected = IllegalArgumentException.class)
  public void SmallDeck() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    List<Card> smallDeck = new ArrayList<>();
    smallDeck.add(new Card(5, 3));
    smallDeck.add(new Card(2, 1));
    smallDeck.add(new Card(10, 4));
    basic.startGame(smallDeck, false, 90, -5);
  }

  //tests that start game throws exception with greater than 52 deck
  @Test (expected = IllegalArgumentException.class)
  public void LargeDeck() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    List<Card> largeDeck = new ArrayList<>();
    for (int i = 0; i < 70; i++) {
      largeDeck.add(new Card(5, 2));
    }
    basic.startGame(largeDeck, false, 90, -5);
  }

  //tests that start game throws exception with duplicates in deck
  @Test (expected = IllegalArgumentException.class)
  public void DuplicateDeck() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    List<Card> duplicate = new ArrayList<>();
    for (int i = 0; i < 50; i++) {
      duplicate.add(new Card(5, 2));
    }
    basic.startGame(duplicate, false, 90, -5);
  }

  //tests that start game throws exception with small amount of duplicates in deck
  @Test (expected = IllegalArgumentException.class)
  public void SmallDuplicateDeck() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    List<Card> duplicate = new ArrayList<>();
    for (int i = 1; i < 13; i++) {
      for (int j = 1; j < 3; j++) {
        duplicate.add(new Card(i, j));
      }
    }
    duplicate.add(new Card(1, 1));
    basic.startGame(duplicate, false, 90, -5);
  }

  //tests that start game throws exception with some null and duplicate in deck
  @Test (expected = IllegalArgumentException.class)
  public void SomeNullDeck() {
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire();
    List<Card> somenull = new ArrayList<>();
    somenull.add(null);
    for (int i = 0; i < 50; i++) {
      somenull.add(new Card(5, 2));
    }
    somenull.add(null);
    basic.startGame(somenull, false, 90, -5);
  }


  //testing to make sure the deck has been
  // shuffled is not equal to the deck that has not been shuffled
  @Test
  public void testDeckShuffleTrue() {
    BasicPyramidSolitaire shuffleTrue =
        new BasicPyramidSolitaire();
    BasicPyramidSolitaire shuffleFalse = new BasicPyramidSolitaire();
    List<Card> deck1 = shuffleTrue.getDeck();
    List<Card> deck2 = shuffleFalse.getDeck();
    shuffleTrue.startGame(deck1, true, 5, 3);
    shuffleFalse.startGame(deck2, false, 5, 3);
    assertFalse(shuffleTrue.getDrawCards() == (shuffleFalse.getDrawCards()));
  }

  //testing to make sure the deck that has not been shuffled is the same
  // as another deck that has not been shuffled
  @Test
  public void testDeckShuffleFalse() {
    BasicPyramidSolitaire shuffleFalse =
        new BasicPyramidSolitaire();
    BasicPyramidSolitaire shuffleFalse2 = new BasicPyramidSolitaire();
    List<Card> deck1 = shuffleFalse.getDeck();
    List<Card> deck2 = shuffleFalse2.getDeck();
    shuffleFalse.startGame(deck1, false, 5, 3);
    shuffleFalse2.startGame(deck2, false, 5, 3);
    assertEquals(true, shuffleFalse.getDrawCards().equals(shuffleFalse2.getDrawCards()));
  }


  //tests that two BasicPyramidModels with same numDraws have same getNumDraws()
  @Test
  public void sameNumDraw() {
    BasicPyramidSolitaire numDraw1 =
        new BasicPyramidSolitaire();
    BasicPyramidSolitaire numDraw2 = new BasicPyramidSolitaire();
    List<Card> deck1 = numDraw1.getDeck();
    List<Card> deck2 = numDraw2.getDeck();
    numDraw1.startGame(deck1, true, 5, 5);
    numDraw2.startGame(deck2, true, 5, 5);
    assertTrue(numDraw1.getNumDraw() == numDraw2.getNumDraw());
  }

  //tests that a BasicPyramidModel with a given numDraw has that number of draw cards
  @Test
  public void testNumDraw() {
    BasicPyramidSolitaire numDraw1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = numDraw1.getDeck();
    numDraw1.startGame(deck1, true, 5, 5);
    assertEquals(5, numDraw1.getNumRows());
  }

  //tests that a BasicPyramidModel with a given rows has that number of rows
  @Test
  public void testNumRows() {
    BasicPyramidSolitaire numDraw1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = numDraw1.getDeck();
    numDraw1.startGame(deck1, true, 7, 5);
    assertEquals(7, numDraw1.getNumRows());
  }

  //tests that a BasicPyramidModel can remove two valid pyramid cards
  @Test
  public void RemoveTwoValid() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    List<Card> deck = model1.getDeck();
    model1.startGame(deck, false, 5, 5);
    model1.remove(4, 0, 4, 4);
    assertEquals(null, model1.getCardAt(4, 4));
    assertEquals(null, model1.getCardAt(4, 0));
  }

  //tests that a BasicPyramidModel can remove one valid card
  @Test
  public void RemoveOneValid() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = model1.getDeck();
    model1.startGame(deck1, false, 7, 5);
    model1.remove(6, 4);
    assertEquals(null, model1.getCardAt(6, 4));
  }

  //tests that a BasicPyramidModel will not remove two cards if one is not valid
  @Test(expected = IllegalArgumentException.class)
  public void RemoveTwoOneNotValid() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = model1.getDeck();
    model1.startGame(deck1, false, 8, 5);
    model1.remove(7, 3, 0, 0);
  }

  //tests that a BasicPyramidModel will not remove a card if the position is not valid
  @Test(expected = IllegalStateException.class)
  public void removeOneNotValid() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = model1.getDeck();
    model1.startGame(deck1, false, 8, 5);
    model1.remove(8, 3);
  }

  //tests that a BasicPyramidModel will not remove a card if the card does not add to 13
  @Test(expected = IllegalArgumentException.class)
  public void removeOneNot13() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = model1.getDeck();
    model1.startGame(deck1, false, 8, 5);
    model1.remove(7, 0);
  }

  //tests that a BasicPyramidModel will not remove two cards if both are not valid
  @Test(expected = IllegalArgumentException.class)
  public void RemoveTwoBothNotValid() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = model1.getDeck();
    model1.startGame(deck1, false, 8, 5);
    model1.remove(0, 0, 4, 2);
  }

  //tests that a BasicPyramidModel will not remove two cards if do not add to 13
  @Test(expected = IllegalArgumentException.class)
  public void RemoveTwoNot13() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = model1.getDeck();
    model1.startGame(deck1, false, 8, 5);
    model1.remove(7, 4, 7, 5);
  }

  //tests that a BasicPyramidModel will remove a valid card with a valid draw card
  @Test
  public void RemoveOneWithDraw() {
    BasicPyramidSolitaire example1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = example1.getDeck();
    example1.startGame(deck1, false, 6, 5);
    example1.removeUsingDraw(1, 5, 0);
    assertEquals(null, example1.getCardAt(5, 0));
  }

  //tests that a BasicPyramidModel will not remove a card with a draw card if not =13
  @Test(expected = IllegalArgumentException.class)
  public void RemoveOneWithDrawNot13() {
    BasicPyramidSolitaire example =
        new BasicPyramidSolitaire();
    List<Card> deck1 = example.getDeck();
    example.startGame(deck1, false, 7, 5);
    example.removeUsingDraw(0, 3, 0);
  }


  //tests that a BasicPyramidModel will not remove a non valid card with a draw card
  @Test(expected = IllegalArgumentException.class)
  public void RemoveOneWithDrawNotValid() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    List<Card> deck1 = model1.getDeck();
    model1.startGame(deck1, false, 8, 5);
    model1.removeUsingDraw(0, 0, 0);
  }

  //tests to make sure discard draw throw exception with game not started
  @Test(expected = IllegalStateException.class)
  public void testDiscardNotStart() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire( 3, 3);
    model1.discardDraw(2);
  }

  //tests to make sure get numRows return -1 with game not started
  @Test
  public void testnumRowsNotStart() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire( 4, 5);
    assertEquals(-1, model1.getNumRows());
  }

  //tests to make sure getnumdraw return -1 with game not started
  @Test
  public void testnumDrawnotstart() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire( 3, 6);
    assertEquals(-1, model1.getNumDraw());
  }

  //tests to make sure getrowwidth throw exception with game not started
  @Test(expected = IllegalStateException.class)
  public void testGetRowWidthnotstart() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire( 6, 8);
    model1.getRowWidth(2);
  }

  //tests to make sure isgameover throw exception with game not started
  @Test(expected = IllegalStateException.class)
  public void testGameOverNotStart() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire(4, 2);
    model1.isGameOver();
  }

  //tests to make sure getscore throw exception with game not started
  @Test(expected = IllegalStateException.class)
  public void testGetScoreNoteStart() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire( 2, 5);
    model1.getScore();
  }

  //tests to make sure getcardat throw exception with game not started
  @Test(expected = IllegalStateException.class)
  public void testGetCardAtNotStart() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire( 8, 4);
    model1.getCardAt(6, 3);
  }

  //tests to make sure getdraw throw exception with game not started
  @Test(expected = IllegalStateException.class)
  public void testGetDrawNotStart() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire( 8, 4);
    model1.getDrawCards();
  }

  //tests the start of the clubs suit is where it should be in the deck
  @Test
  public void testStartClubs() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    Card c = new Card(1,1);
    assertEquals(c, model1.getDeck().get(0));
  }

  //tests the start of the clubs suit is where it should be in the deck
  @Test
  public void testStartDiamonds() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    Card c = new Card(1,2);
    assertEquals(c, model1.getDeck().get(13));
  }

  //tests the start of the clubs suit is where it should be in the deck
  @Test
  public void testStartHearts() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    Card c = new Card(1,3);
    assertEquals(c, model1.getDeck().get(26));
  }

  //tests the start of the clubs suit is where it should be in the deck
  @Test
  public void testStartSpades() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    Card c = new Card(1,4);
    assertEquals(c, model1.getDeck().get(39));
  }

  //discard draw throws illegalargument for invalid index close
  @Test(expected = IllegalArgumentException.class)
  public void testDeiscardClose() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 7);
    model1.discardDraw(7);
  }

  //discard draw throws illegalargument for invalid index far off
  @Test(expected = IllegalArgumentException.class)
  public void testDiscardFarOff() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 7);
    model1.discardDraw(10);
  }

  //discard draw throws illegalargument for invalid index neg
  @Test(expected = IllegalArgumentException.class)
  public void testDeiscardNeg() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 7);
    model1.discardDraw(-2);
  }

  //get row width returns correct width of large row
  @Test
  public void testCorrectRowwidthLarge() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 7);
    assertEquals(9, model1.getRowWidth(8));
  }

  //get row width returns correct width of small row
  @Test
  public void testCorrectRowwidthSmall() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 7);
    assertEquals(1, model1.getRowWidth(0));
  }

  //get row width returns illegal argument if row does not exist because too large
  @Test(expected = IllegalArgumentException.class)
  public void testRowWidthTooHigh() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 7);
    model1.getRowWidth(9);
  }

  //get row width returns illegal argument if row does not exist because too small
  @Test(expected = IllegalArgumentException.class)
  public void testRowWidthTooSmall() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 7);
    model1.getRowWidth(-1);
  }

  //is game over returns true if game is over
  @Test
  public void testGameOverTrue() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(3);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(4);
    assertEquals(true, model1.isGameOver());
  }

  //is game over returns false is game is not over
  @Test
  public void testGameOverFalse() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(3);
    assertEquals(false, model1.isGameOver());
  }

  //tests getNumDraw returns correct amount of draw cards when some are missing
  @Test
  public void testNumDrawNotFull() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(3);
    model1.discardDraw(2);
    model1.discardDraw(0);
    assertEquals(5, model1.getNumDraw());
  }

  //tests the size of the drawcards is correct when some are missing
  @Test
  public void testNumDrawCardsNotFull() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(3);
    model1.discardDraw(2);
    model1.discardDraw(0);
    assertEquals(5, model1.getNumDraw());
  }


  //tests restarting the game in the middle of the current game
  @Test
  public void testRestartGame() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(3);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.startGame(model1.getDeck(), false, 9, 5);
    List<Card> addTo = new ArrayList<>();
    addTo.add(new Card(7, 4));
    addTo.add(new Card(8, 4));
    addTo.add(new Card(9, 4));
    addTo.add(new Card(10, 4));
    addTo.add(new Card(11, 4));
    assertEquals(addTo, model1.getDrawCards());
  }

  //tests discarding one card from pyramid
  @Test
  public void testOneDiscard() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.discardDraw(0);
    List<Card> addTo = new ArrayList<>();
    addTo.add(new Card(12, 4));
    addTo.add(new Card(8, 4));
    addTo.add(new Card(9, 4));
    addTo.add(new Card(10, 4));
    addTo.add(new Card(11, 4));
    assertEquals(addTo, model1.getDrawCards());
  }


  //get score returns score of 0 if no cards left
  @Test
  public void testScore0() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 2, 10);
    model1.removeUsingDraw(6, 1, 1);
    model1.removeUsingDraw(7, 1, 0);
    model1.removeUsingDraw(8, 0, 0);
    assertEquals(0, model1.getScore());
  }


  //get card at returns the card at a given pos
  @Test
  public void testGetCardAtCorrect() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 2, 10);
    assertEquals(new Card(3, 1), model1.getCardAt(1, 1));
  }

  //get card at throws illegalarg if invald row
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtInvalidRow() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 8, 10);
    model1.getCardAt(10, 1);
  }

  //get card at throw illegalarg if invalid card pos
  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtInvalidCard() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 8, 10);
    model1.getCardAt(3, 10);
  }

  //get draw cards returns the list of draw cards (not full)
  @Test
  public void testGetDrawCardsNotFull() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 9, 7);
    List<Card> addTo = new ArrayList<>();
    addTo.add(new Card(7, 4));
    addTo.add(new Card(8, 4));
    addTo.add(new Card(9, 4));
    addTo.add(new Card(10, 4));
    addTo.add(null);
    addTo.add(new Card(12, 4));
    addTo.add(new Card(13, 4));
    model1.removeUsingDraw(4, 8, 4);
    assertEquals(addTo, model1.getDrawCards());
  }
}
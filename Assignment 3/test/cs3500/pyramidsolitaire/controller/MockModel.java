package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a mock implementation of PyramidSolitaireModel with the Card class.
 * Represents a mock model object for a game of solitaire.
 */
public class MockModel implements PyramidSolitaireModel<Card> {
  StringBuilder log;

  MockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public List<Card> getDeck() {
    List<Card> newDeck = new ArrayList<>();
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 13; j++) {
        Card addCard = new Card(j, i);
        newDeck.add(addCard);
      }
    }
    return newDeck;
  }

  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw)
      throws IllegalArgumentException {
    return;
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {
    this.log.append("remove: " + row1 + " " + card1 + " " + row2 + " " + card2 + "\n");
  }

  @Override
  public void remove(int row, int card) throws IllegalArgumentException, IllegalStateException {
    this.log.append("remove: " + row + " " + card + "\n");
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card)
      throws IllegalArgumentException, IllegalStateException {
    this.log.append("remove: " + drawIndex + " " + row + " " + card + "\n");
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {
    this.log.append("discard " + drawIndex + "\n");
  }

  @Override
  public int getNumRows() {
    return 0;
  }

  @Override
  public int getNumDraw() {
    return 0;
  }

  @Override
  public int getRowWidth(int row) {
    return 0;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    return false;
  }

  @Override
  public int getScore() throws IllegalStateException {
    return 0;
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    return null;
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    return null;
  }
}

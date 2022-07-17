package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import java.util.ArrayList;
import java.util.List;
import cs3500.pyramidsolitaire.model.hw02.Card;

/**
 * A class to represent an extension of the Super class BasicPyramidSolitaire with 3 peaks,
 * which is an implementation of the PyramidSolitaireModel interface.
 * Represents a model object for a game of solitaire.
 */
public class MultiPyramidSolitaire extends BasicPyramidSolitaire {

  /**
   * Constructs a MultiPyramidSolitaire that is ready to start the game.
   */
  public MultiPyramidSolitaire() {
    super();
  }

  @Override
  public List<Card> getDeck() {
    List<Card> newDeck = new ArrayList<>();
    int count = 0;
    while (count < 2) {
      for (int i = 1; i <= 4; i++) {
        for (int j = 1; j <= 13; j++) {
          Card addCard = new Card(j, i);
          newDeck.add(addCard);
        }
      }
      count++;
    }
    return newDeck;
  }

  /**
   * Checks if the number of rows is valid to start the game.
   * @param numRows represents the number of rows passed in to start the game
   * @return true if the rows are not valid, false if otherwise.
   */
  protected boolean checkRows(int numRows) {
    return numRows <= 0 || numRows > 8;
  }

  /**
   * Checks if the size of the deck is valid to start the game.
   * @param deck represents the deck passed in to start the game.
   * @return true if the deck size is not valid, false if otherwise.
   */
  protected boolean checkDeckSize(List<Card> deck) {
    return deck.size() != 104;
  }

  /**
   * Checks to see if the deck does not have 13 different cards in the four suits and null values.
   * @param deck represents the deck of cards passed in initially to start the game.
   * @return true if the deck is valid and false if there are duplicates or null cards.
   */
  protected boolean checkValid(List<Card> deck) {
    List<Card> newList = new ArrayList<>();
    List<Integer> cardCount = new ArrayList<>(52);
    for (int j = 0; j <= 52; j++) {
      cardCount.add(0);
    }
    for (int i = 0; i < deck.size(); i++) {
      if (deck.get(i) == null) {
        return false;
      }
      else if (deck.get(i).isEmpty()) {
        return false;
      }
      else if (newList.size() != 0) {
        int loc = deck.get(i).findValue();
        if (newList.contains(deck.get(i)) && cardCount.get(loc - 1) == 2) {
          return false;
        }
        else if (newList.contains(deck.get(i)) && cardCount.get(loc - 1) < 2) {
          newList.add(deck.get(i));
          cardCount.set(loc - 1, cardCount.get(loc - 1) + 1);
        }
      }
      else {
        int loc = deck.get(i).findValue();
        newList.add(deck.get(i));
        if (cardCount.get(loc - 1) == 0) {
          cardCount.set(loc - 1, 1);
        }
        else if (cardCount.get(loc - 1) == 1) {
          cardCount.set(loc - 1, 2);
        }
      }
    }
    return true;
  }

  /**
   * Calculates the number of Cards originally in the pyramid.
   * @param numRows represents the number of rows originally passed in for the pyramid.
   * @return the total number of cards originally in the pyramid.
   */
  protected int findNumberPyramid(int numRows) {
    int pyramidNum = 0;
    int overlap;
    int overlapInt = 0;
    if (numRows % 2 != 0) {
      overlap = (numRows / 2) + 1;
      while (numRows != 0) {
        pyramidNum += numRows;
        numRows -= 1;
      }
      while (overlap != 0) {
        overlapInt += overlap;
        overlap -= 1;
      }
    }
    else if (numRows % 2 == 0) {
      overlap = numRows / 2;
      while (numRows != 0) {
        pyramidNum += numRows;
        numRows -= 1;
      }
      while (overlap != 0) {
        overlapInt += overlap;
        overlap -= 1;
      }
    }
    return (pyramidNum * 3) - (overlapInt * 2);
  }


  /**
   * Assigns row and card to every Card in the pyramid.
   */
  protected void assignPyramidPos(List<Card> deck) {
    int cardIndex = 0;
    List<List<Card>> newPyramid = new ArrayList<>();
    for (int i = 0; i < super.numRows; i ++) {
      List<Card> inner = new ArrayList<>(this.getRowWidth(i));
      for (int j = 0; j < this.getRowWidth(i); j++) {
        inner.add(new Card());
      }
      newPyramid.add(inner);
    }
    for (int i = 0; i < super.numRows; i++) {
      for (int j = 0; j < this.getRowWidth(i); j++) {
        if (numRows <= 3) {
          setPos(newPyramid, i, j, cardIndex);
          cardIndex += 1;
        }
        else if (numRows == 4 || numRows == 5) {
          if (i == 0) {
            if (j == 0 || j == 2) {
              setPos(newPyramid, i, j, cardIndex);
              cardIndex += 1;
              j += 1;
            }
            else if (j != 0 || j != 2) {
              setPos(newPyramid, i, j, cardIndex);
              cardIndex += 1;
            }
          } else if (i != 0) {
            setPos(newPyramid, i, j, cardIndex);
            cardIndex += 1;
          }
        }
        else if (numRows == 6 || numRows == 7) {
          if (i == 0) {
            setPos(newPyramid, i, j, cardIndex);
            cardIndex += 1;
            j += 2;
          }
          else if (i == 1) {
            setPos(newPyramid, i, j, cardIndex);
            cardIndex += 1;
            if (j == 1 || j == 4) {
              j += 1;
            }
          }
          else if (i != 0 && i != 1) {
            setPos(newPyramid, i, j, cardIndex);
            cardIndex += 1;
          }
        }
        else if (numRows == 8) {
          if (i == 0) {
            setPos(newPyramid, i, j, cardIndex);
            cardIndex += 1;
            j += 3;
          }
          else if (i == 1) {
            setPos(newPyramid, i, j, cardIndex);
            cardIndex += 1;
            if (j == 1 || j == 5) {
              j += 2;
            }
          }
          else if (i == 2) {
            setPos(newPyramid, i, j, cardIndex);
            cardIndex += 1;
            if (j == 2 || j == 6) {
              j += 1;
            }
          }
          else {
            setPos(newPyramid, i, j, cardIndex);
            cardIndex += 1;
          }
        }
      }
    }
    super.pyramid = newPyramid;
    for (int i = 0; i < this.getRowWidth(super.numRows - 1); i ++) {
      super.pyramid.get(super.numRows - 1).get(i).changeExposed();
    }
  }


  private void setPos(List<List<Card>> newPyramid, int row, int card, int cardIndex) {
    newPyramid.get(row).set(card, deck.get(cardIndex));
  }


  @Override
  public int getRowWidth(int row) {
    if (!super.gameStart) {
      throw new IllegalStateException("Game has not started");
    }
    else if (row < super.numRows && row >= 0) {
      if (numRows == 1) {
        return 1;
      }
      else if (super.numRows < 4) {
        return 3 + row;
      }
      else if (super.numRows < 6) {
        return 5 + row;
      }
      else if (super.numRows < 8) {
        return 7 + row;
      }
      else {
        return 9 + row;
      }
    }
    else {
      throw new IllegalArgumentException("Invalid Row");
    }
  }
}

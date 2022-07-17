package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;

/**
 * A class to represent an extension of the Super class BasicPyramidSolitaire with relaxed rules
 * which is an implementation of the PyramidSolitaireModel interface.
 * Represents a model object for a game of solitaire.
 */
public class RelaxedPyramidSolitaire extends BasicPyramidSolitaire {

  /**
   * Constructs a RelaxedPyramidSolitaire that is ready to start the game.
   */
  public RelaxedPyramidSolitaire() {
    super();
  }


  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {
    if (!super.gameStart) {
      throw new IllegalStateException("Game has not been started");
    } else {
      boolean exists1 = findCard(row1, card1);
      boolean exists2 = findCard(row2, card2);
      if (exists1 && exists2) {
        Card c1 = findCardinPyramid(row1, card1);
        Card c2 = findCardinPyramid(row2, card2);

        int val1 = c1.findValue();
        boolean exposed1 = c1.isExposed();

        int val2 = c2.findValue();
        boolean exposed2 = c2.isExposed();


        if (val1 + val2 == 13 && ((exposed1 && exposed2)
            || checkOverlap(row1, card1, row2, card2))) {
          updateEmpty(c1);
          updateEmpty(c2);
          updateExposed();
        }
        else {
          throw new IllegalArgumentException("Invalid remove");
        }
      }
      else {
        throw new IllegalStateException("Cards do not exist");
      }
    }
  }


  /**
   * Determines if the upper card is only missing one child, if the lower card is the other child.
   * @param row1 represents the row of one card.
   * @param card1 represents the card of the same card.
   * @param row2 represents the row of the other card.
   * @param card2 represents the card of the other card.
   * @return true if upper card is missing a child and lower card is other child, false otherwise.
   */
  private boolean checkOverlap(int row1, int card1, int row2, int card2) {
    if (row1 == row2 || 1 !=  Math.abs(row1 - row2)) {
      return false;
    } else {
      int rowAbove = Math.min(row1, row2);
      int cardAbove;
      int rowBelow = Math.max(row1, row2);
      int cardBelow;
      if (rowAbove == row1) {
        cardAbove = card1;
        cardBelow = card2;
      } else {
        cardAbove = card2;
        cardBelow = card1;
      }
      if (cardBelow == 0) {
        if (cardAbove == cardBelow) {
          return super.pyramid.get(rowBelow).get(cardBelow + 1).isEmpty();
        }
        else {
          return false;
        }
      }
      else if (1 == Math.abs(cardAbove - cardBelow)) {
        return super.pyramid.get(rowBelow).get(cardBelow - 1).isEmpty();
      }
      else {
        return false;
      }
    }
  }

  /**
   * Checks if there are any remaining moves left.
   * @return true if there are moves, false if otherwise.
   */
  protected boolean checkForMoves() {
    if (getScore() == 0) {
      return false;
    } else if (getScore() != 0) {
      for (int i = 0; i < super.numRows; i++) {
        for (int j = 0; j < super.getRowWidth(i); j++) {
          Card c1 = super.pyramid.get(i).get(j);
          if (!c1.isEmpty()) {
            if (c1.isExposed() && c1.findValue() == 13) {
              return true;
            }
            if (c1.isExposed() && c1.findValue() != 13) {
              for (int l = 0; l < super.numRows; l++) {
                for (int m = 0; m < super.getRowWidth(l); m++) {
                  Card c2 = super.pyramid.get(l).get(m);
                  if (!c2.isEmpty()) {
                    if (c2.isExposed() && c1.findValue() + c2.findValue() == 13) {
                      return true;
                    }
                    else if (!c2.isExposed() && c1.findValue() + c2.findValue() == 13
                        && checkOverlap(i, j, l, m)) {
                      return true;
                    }
                  }
                }
              }
            }
            for (Card draw : super.draw) {
              if (!draw.isEmpty()) {
                return true;
              }
            }
          }
        }
      }
    }
    return super.stock.size() >= 1;
  }

}

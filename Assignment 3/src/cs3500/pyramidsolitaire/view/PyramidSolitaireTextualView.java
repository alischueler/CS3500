package cs3500.pyramidsolitaire.view;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.io.IOException;

/**
 * Represents the Textual View of the PyramidSolitaire Game.
 * Represents the view object of a solitaire game.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {
  private final PyramidSolitaireModel<?> model;
  private Appendable out;

  /**
   * Constructs a PyramidSolitaireTextualView with the corresponding model.
   * @param model represents the PyramidSolitaireModel associated.
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
  }

  /**
   * Constructs a PyramidSolitaireTextualView with appendables and readables.
   * @param out Appendable putting together outputs
   * @param model represents the model asscoaited with the view
   */
  public PyramidSolitaireTextualView(Appendable out, PyramidSolitaireModel<?> model) {
    this.out = out;
    this.model = model;
  }

  @Override
  public String toString() {
    if (this.model.getNumDraw() == -1 && this.model.getNumRows() == -1) {
      return "";
    }
    else if (this.model.getScore() == 0) {
      return "You win!";
    }
    else if (this.model.isGameOver()) {
      return "Game over. Score: " + this.model.getScore();
    }
    else {
      String result = "";
      String[][] board = drawBoard();
      for (int i = 0; i < this.model.getNumRows(); i++) { //row
        for (int j = 0; j < this.model.getRowWidth(i); j++) { //card
          String totalSpaces = "";
          int spacingThisRow = 2
              * ((this.model.getRowWidth(this.model.getNumRows() - 1)) - this.model.getRowWidth(i));
          if (i == 0 && j == 0) { //first row first card
            for (int m = 1; m <= spacingThisRow; m++) {
              totalSpaces += " ";
            }
            result = result + totalSpaces + board[i][j] + "\n";
          }
          if (i == this.model.getNumRows() - 1) { //last row
            if (j == this.model.getRowWidth(i) - 1) { //last card
              result = result + board[i][j] + "\n";
            }
            if (j == 0) { //first card
              if (board[i][j].length() == 1) {
                result = result + totalSpaces + board[i][j] + "   ";
              }
              else if (board[i][j].length() == 2) {
                result = result + totalSpaces + board[i][j] + "  ";
              }
              else {
                result = result + totalSpaces + board[i][j] + " ";
              }
            }
            else if (j != 0 && j != this.model.getRowWidth(i) - 1) { //middle card
              if (board[i][j].length() == 1) {
                result = result + board[i][j] + "   ";
              }
              else if (board[i][j].length() == 2) {
                result = result + board[i][j] + "  ";
              }
              else {
                result = result + board[i][j] + " ";
              }
            }
          }
          if (i != this.model.getNumRows() - 1 && i != 0) { //middle rows
            if (j == 0) { //first card
              for (int m = 1; m <= spacingThisRow; m++) {
                totalSpaces += " ";
              }
              if (board[i][j].length() == 1) {
                result = result + totalSpaces + board[i][j] + "   ";
              }
              else if (board[i][j].length() == 2) {
                result = result + totalSpaces + board[i][j] + "  ";
              }
              else {
                result = result + totalSpaces + board[i][j] + " ";
              }
            }
            if (j != 0 && j != this.model.getRowWidth(i) - 1) { //middle card
              if (board[i][j].length() == 1) {
                result = result + board[i][j] + "   ";
              }
              else if (board[i][j].length() == 2) {
                result = result + board[i][j] + "  ";
              }
              else {
                result = result + board[i][j] + " ";
              }
            }
            else if (j == this.model.getRowWidth(i) - 1) { //last card
              result = result + board[i][j] + "\n";
            }
          }
        }
      }
      if (isDrawEmpty()) {
        result = result + "Draw:";
      }
      else {
        result = result + "Draw: ";
        for (int i = 0; i < this.model.getDrawCards().size(); i++) {
          if (i != this.model.getDrawCards().size() - 1) {
            result = result + board[this.model.getNumRows()][i] + ", ";
          }
          else {
            result = result + board[this.model.getNumRows()][i];
          }
        }
      }
      return result;
    }
  }

  /**
   * Creates a 2d array of the board by [row][card].
   * @return a 2d array representing the current board.
   */
  private String[][] drawBoard() {
    String[][] board = new String[this.model.getNumRows() + 1]
        [Math.max(this.model.getRowWidth(this.model.getNumRows() - 1),
        this.model.getNumDraw() + 1)];
    for (int i = 0; i < this.model.getNumRows(); i++) {
      for (int j = 0; j < this.model.getRowWidth(i); j++) {
        if (this.model.getCardAt(i, j) == null) {
          board[i][j] = ".";
        }
        else if (this.model.getCardAt(i, j) != null) {
          board[i][j] = this.model.getCardAt(i, j).toString();
        }
      }
    }
    for (int i = 0; i < this.model.getDrawCards().size(); i++) {
      if (this.model.getDrawCards().get(i) == null) {
        board[this.model.getNumRows()][i] = ". ";
      }
      else {
        board[this.model.getNumRows()][i] =
            this.model.getDrawCards().get(i).toString();
      }
    }
    return board;
  }

  /**
   * Determines if the draw cards of the model have all been removed.
   * @return true if there are cards available for use and false if otherwise.
   */
  private boolean isDrawEmpty() {
    for (int i = 0; i < this.model.getNumDraw(); i++) {
      if (this.model.getDrawCards().get(i) != null) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void render() throws IOException {
    this.out.append(this.toString() + "\n");
  }

}

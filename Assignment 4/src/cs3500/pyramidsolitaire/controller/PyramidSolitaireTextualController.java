package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * A class to represent a textual controller implementation of PyramisSolitaireController.
 * Represents the controller object for a game of Solitaire.
 * Reacts to user input to play the game.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {
  private final Readable in;
  private final Appendable out;
  private Boolean gameQuit;

  /**
   * Constructs a PyramisSolitaireTextualController.
   * @param in represents the appendable.
   * @param out represents the readable.
   * @throws IllegalArgumentException if the appendable or the readbale are null.
   */
  public PyramidSolitaireTextualController(Readable in, Appendable out)
      throws IllegalArgumentException {
    if (in == null) {
      throw new IllegalArgumentException("readable is null");
    }
    if (out == null) {
      throw new IllegalArgumentException("appendable is null");
    }
    this.in = in;
    this.out = out;
    this.gameQuit = false;
  }

  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck, boolean shuffle,
      int numRows, int numDraw) {

    //checks if the model is null
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }

    //checks if the deck is null
    if (deck == null) {
      throw new IllegalArgumentException("Deck is null");
    }

    //starts the game
    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("Game could not be started");
    }

    Scanner scan = new Scanner(this.in);
    PyramidSolitaireView view = new PyramidSolitaireTextualView(this.out, model);
    String op;

    //appending the original game state
    try {
      view.render();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot transmit game state and score to output");
    }
    tryAppend("");
    tryAppend("Score: " + model.getScore());

    //continue as long as the game is not over
    while (!model.isGameOver()) {

      if (gameQuit) {
        return;
      }

      //continue scanning if there is a next element in the input
      else if (scan.hasNext()) {
        op = scan.next();

        try {
          //figuring out which move to make
          switch (op) {
            case "q":
            case "Q":
              appendQuit(model, view);
              break;
            case "rm1":
              appendRemove1(model, scan, view);
              break;
            case "rm2":
              appendRemove2(model, scan, view);
              break;
            case "rmwd":
              appendRemoveDraw(model, scan, view);
              break;
            case "dd":
              appendDiscardDraw(model, scan, view);
              break;
            default:
              tryAppend("Did not recognize move " + op);
              break;
          }
        } catch (NullPointerException e) {
          throw new IllegalStateException("Input is null");
        }
      }
      else {
        throw new IllegalStateException("no more inputs");
      }
    }
    //if the game is over
    scan.close();
    if (model.isGameOver()) {
      try {
        view.render();
      } catch (IOException e) {
        throw new IllegalStateException("Cannot transmit output");
      }
      tryAppend("");
    }
  }


  /**
   * Appends the quitted game state to the appendable.
   * @param model represents the games ongoing model.
   * @param view represents the view that will append the game state to the appendable.
   */
  private <K> void appendQuit(PyramidSolitaireModel<K> model, PyramidSolitaireView view) {
    this.gameQuit = true;
    tryAppend("Game quit!");
    tryAppend("State of game when quit:");
    try {
      view.render();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot transmit the output");
    }
    tryAppend("");
    tryAppend("Score: " + model.getScore());
  }


  /**
   * Returns the int of the next input if there is one.
   * @param scan represents the scannable object.
   * @return the int representing the next input.
   */
  private int readInput(Scanner scan) {
    if (scan.hasNext()) {
      String str = scan.next();
      try {
        Integer.parseInt(str);
      }
      catch (NumberFormatException nfe) {
        if (str.equals("q") || str.equals("Q")) {
          return -1000;
        } else {
          return this.readInput(scan);
        }
      }
      return Integer.parseInt(str);
    }
    else {
      return readInput(scan);
    }
  }

  /**
   * Determines if the given int is a valid input.
   * @param i represents the integer being examined.
   * @return true if the Integer is not null, false if otherwise.
   */
  private Boolean haveInput(Integer i) {
    return (i != null);
  }



  /**
   * Updates the appendble with the removed card if it is a valid remove.
   * or the message to put in more input.
   * @param model the given model associated with this game.
   * @param scan the scanner object being used to read inputs.
   * @param view the view that will append the game state to the appendable.
   */
  private <K> void appendRemove1(PyramidSolitaireModel<K> model, Scanner scan,
      PyramidSolitaireView view) {
    int int1;
    int int2;

    int1 = readInput(scan);
    if (int1 == -1000) {
      appendQuit(model, view);
    } else if (haveInput(int1)) {
      int2 = readInput(scan);
      if (int2 == -1000) {
        appendQuit(model, view);
      } else if (haveInput(int2)) {
        try {
          model.remove(int1 - 1, int2 - 1);
          appendGame(model, view);
        } catch (IllegalArgumentException iae) {
          tryAppend("Invalid move. Play again. Invalid remove");
        } catch (IllegalStateException ise) {
          tryAppend("Invalid move. Play again. No card exists here");
        }
      }
    }
  }

  /**
   * Updates the appendable with the two removed cards if they are valid removes.
   * @param model the given model associated with this game.
   * @param scan the scanner object being used to read inputs.
   * @param view the view that will append the game state to the appendable.
   */
  private <K> void appendRemove2(PyramidSolitaireModel<K> model, Scanner scan,
      PyramidSolitaireView view) {
    int int1;
    int int2;
    int int3;
    int int4;
    int1 = readInput(scan);
    if (int1 == -1000) {
      appendQuit(model, view);
    } else if (haveInput(int1)) {
      int2 = readInput(scan);
      if (int2 == -1000) {
        appendQuit(model, view);
      } else if (haveInput(int2)) {
        int3 = readInput(scan);
        if (int3 == -1000) {
          appendQuit(model, view);
        } else if (haveInput(int3)) {
          int4 = readInput(scan);
          if (int4 == -1000) {
            appendQuit(model, view);
          } else if (haveInput(int4)) {
            try {
              model.remove(int1 - 1, int2 - 1,
                  int3 - 1, int4 - 1);
              appendGame(model, view);
            } catch (NumberFormatException nfe) {
              return;
            } catch (IllegalArgumentException iae) {
              tryAppend("Invalid move. Play again. Invalid remove");
            } catch (IllegalStateException ise) {
              tryAppend("Invalid move. Play again. Not valid cards");
            }
          }
        }
      }
    }
  }


  /**
   * Updates the appendable with the removed card and draw card if it is valid.
   * @param model the given model associated with this game.
   * @param scan the scanner object being used to read inputs.
   * @param view the view that will append the game state to the appendable.
   */
  private <K> void appendRemoveDraw(PyramidSolitaireModel<K> model, Scanner scan,
      PyramidSolitaireView view) {
    int draw;
    int int1;
    int int2;
    draw = readInput(scan);
    if (draw == -1000) {
      appendQuit(model, view);
    } else if (haveInput(draw)) {
      int1 = readInput(scan);
      if (int1 == -1000) {
        appendQuit(model, view);
      } else if (haveInput(int1)) {
        int2 = readInput(scan);
        if (int2 == -1000) {
          appendQuit(model, view);
        } else if (haveInput(int2)) {
          try {
            model.removeUsingDraw(draw - 1,
                int1 - 1, int2 - 1);
            appendGame(model, view);
          } catch (NumberFormatException nfe) {
            return;
          } catch (IllegalArgumentException iae) {
            tryAppend("Invalid move. Play again. Invalid remove");
          } catch (IllegalStateException ise) {
            tryAppend("Invalid move. Play again. Not valid cards");
          }
        }
      }
    }
  }


  /**
   * Updates the appendable with the discarded draw card if it is valid.
   * @param model the given model associated with this game.
   * @param scan the scanner object being used to read inputs.
   * @param view the view that will append the game state to the appendable.
   */
  private <K> void appendDiscardDraw(PyramidSolitaireModel<K> model, Scanner scan,
      PyramidSolitaireView view) {
    int draw;
    draw = readInput(scan);
    if (draw == -1000) {
      appendQuit(model, view);
    } else {
      try {
        model.discardDraw(draw - 1);
        appendGame(model, view);
      } catch (NumberFormatException nfe) {
        return;
      } catch (IllegalArgumentException iae) {
        tryAppend("Invalid move. Play again. Invalid discard");
      }
    }
  }

  /**
   * Appends the game state and score to the appendable.
   * @param model the given model associated with this game.
   * @param view the view that will append the game state to the appendable.
   */
  private <K> void appendGame(PyramidSolitaireModel<K> model, PyramidSolitaireView view) {
    if (!model.isGameOver() && !this.gameQuit) {
      try {
        view.render();
      }
      catch (IOException io) {
        throw new IllegalStateException("Cannot transmit output");
      }
      tryAppend("");
      tryAppend("Score: " + model.getScore());
    }
    else if (this.gameQuit) {
      return;
    }
  }

  /**
   * Appends the given message to the appendable, throws an IllegalStateError if it cannot.
   * @param msg represents the meddage to try to append to the appendable.
   */
  private void tryAppend(String msg) {
    try {
      this.out.append(msg + "\n");
    }
    catch (IOException io) {
      throw new IllegalStateException("Could not transmit output");
    }
  }

}


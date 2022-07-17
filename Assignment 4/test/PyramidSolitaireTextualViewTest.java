

import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;
import java.io.IOException;
import org.junit.Test;

/**
 * A class for testing the public methods of PyramidSolitaireTextualView.
 */
public class PyramidSolitaireTextualViewTest {


  //tests that the view deck can properly display a small pyramid
  @Test
  public void testSmallPyramid() {
    PyramidSolitaireModel<Card> smallModel =
        new BasicPyramidSolitaire();
    PyramidSolitaireView smallView = new PyramidSolitaireTextualView(smallModel);
    smallModel.startGame(smallModel.getDeck(), false, 3, 3);
    assertEquals("    A♣\n  2♣  3♣\n4♣  5♣  6♣\nDraw: 7♣, 8♣, 9♣", smallView.toString());
  }

  @Test
  public void testFullDeck() {
    PyramidSolitaireModel<Card> fullModel =
        new BasicPyramidSolitaire();
    PyramidSolitaireView fullView = new PyramidSolitaireTextualView(fullModel);
    fullModel.startGame(fullModel.getDeck(), false, 9, 4);
    assertEquals("                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + "J♥  Q♥  K♥  A♠  2♠  3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠", fullView.toString());
  }

  //tests that toString will return "" if game has not been started
  @Test
  public void toStringNotStarted() {
    PyramidSolitaireModel<Card> basic = new BasicPyramidSolitaire(4, 6);
    PyramidSolitaireView view  = new PyramidSolitaireTextualView(basic);
    assertEquals("", view.toString());
  }

  //tests that when someone wins it is properly displayed
  @Test
  public void testGameOverDraw() {
    PyramidSolitaireModel<Card> model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 2, 10);
    model1.removeUsingDraw(6, 1, 1);
    model1.removeUsingDraw(7, 1, 0);
    model1.removeUsingDraw(8, 0, 0);
    assertEquals("You win!", view.toString());
  }

  //tests that game is not over with more in stock
  @Test
  public void testGameOver() {
    PyramidSolitaireModel<Card> fullModel =
        new BasicPyramidSolitaire();
    PyramidSolitaireView fullView = new PyramidSolitaireTextualView(fullModel);
    fullModel.startGame(fullModel.getDeck(), false, 2, 1);
    assertEquals("  A♣\n2♣  3♣\nDraw: 4♣", fullView.toString());
  }


  //tests for the correct board after many operations
  @Test
  public void testDrawingOperations() {
    PyramidSolitaireModel<Card> model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    assertEquals("                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  .   6♠\n"
        + "Draw: . , K♠, . , 10♠, J♠", view.toString());
  }

  //tests restarting the game in the middle of another game
  @Test
  public void testreStartGame() {
    PyramidSolitaireModel<Card> model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(3);
    model1.removeUsingDraw(2, 8, 6);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(4);
    model1.startGame(model1.getDeck(), false, 8, 5);
    assertEquals("              A♣\n"
        + "            2♣  3♣\n"
        + "          4♣  5♣  6♣\n"
        + "        7♣  8♣  9♣  10♣\n"
        + "      J♣  Q♣  K♣  A♦  2♦\n"
        + "    3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "  9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + "Draw: J♥, Q♥, K♥, A♠, 2♠", view.toString());
  }

  //tests drawing game with empty draw
  @Test
  public void testEmptyDraw() {
    PyramidSolitaireModel<Card> model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(3);
    model1.removeUsingDraw(2, 8, 6);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(4);
    assertEquals("                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  .   .   6♠\n"
        + "Draw:", view.toString());
  }

  //tests that when game is over correct score is displayed
  @Test
  public void GameIsOverScore() {
    PyramidSolitaireModel<Card> model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(3);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(4);
    assertEquals("Game over. Score: 250", view.toString());
  }

  //tests that render renders the correct gamestate to the appendable
  @Test
  public void testRenderGameState() {
    PyramidSolitaireModel<Card> model1 =
        new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(out, model1);
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(3);
    try {
      view.render();
    }
    catch (IOException io) {
      throw new IllegalStateException("Cannot transmit output");
    }
    assertEquals("                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  .   6♠\n"
        + "Draw: 7♠, K♠, . , . , J♠", out.toString());
  }

  //tests that render renders the correct game over to the appendable
  @Test
  public void testGameOverRender() {
    PyramidSolitaireModel<Card> model1 =
        new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(out, model1);
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(3);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(4);
    try {
      view.render();
    }
    catch (IOException io) {
      throw new IllegalStateException("could not transmit output");
    }
    assertEquals("Game over. Score: 250", out.toString());
  }

  //tests that render renders the correct you win to the appendable
  @Test
  public void testWinDraw() {
    PyramidSolitaireModel<Card> model1 =
        new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(out, model1);
    model1.startGame(model1.getDeck(), false, 2, 10);
    model1.removeUsingDraw(6, 1, 1);
    model1.removeUsingDraw(7, 1, 0);
    model1.removeUsingDraw(8, 0, 0);
    try {
      view.render();
    }
    catch (IOException io) {
      throw new IllegalStateException("could not transmit output");
    }
    assertEquals("You win!", out.toString());
  }

  //tests that both cards are removed in relaxed lower first
  @Test
  public void testRemoveOverlap1() {
    PyramidSolitaireModel<Card> model1 =
        new RelaxedPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 7, 3);
    model1.removeUsingDraw(1, 6, 0);
    model1.remove(6, 1, 5, 0);
    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  .   4♦  5♦  6♦  7♦  8♦\n"
        + ".   .   J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 6♥, 5♥", view.toString());
  }

  //tests that both cards are removed in relaxed upper first
  @Test
  public void testRemoveOverlap2() {
    PyramidSolitaireModel<Card> model1 =
        new RelaxedPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 7, 3);
    model1.removeUsingDraw(1, 6, 0);
    model1.remove(5, 0, 6, 1);
    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  .   4♦  5♦  6♦  7♦  8♦\n"
        + ".   .   J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 6♥, 5♥", view.toString());
  }

  //tests that relaxed allows "normal" moves as well
  @Test
  public void testRemoveOverlapNormal() {
    PyramidSolitaireModel<Card> model1 =
        new RelaxedPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 7, 3);
    model1.remove(6, 2, 6, 6);
    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ .   Q♦  K♦  A♥  .\n"
        + "Draw: 3♥, 4♥, 5♥", view.toString());
  }

  //tests drawing multipyramid one row
  @Test
  public void testMulliOne() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    model.startGame(model.getDeck(), false, 1, 3);
    assertEquals("A♣\nDraw: 2♣, 3♣, 4♣", view.toString());
  }

  //tests drawing multipyramid two row
  @Test
  public void testMulliTwo() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    model.startGame(model.getDeck(), false, 2, 3);
    assertEquals("  A♣  2♣  3♣\n4♣  5♣  6♣  7♣\nDraw: 8♣, 9♣, 10♣", view.toString());
  }

  //tests drawing multipyramid three row
  @Test
  public void testMulliThree() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    model.startGame(model.getDeck(), false, 3, 3);
    assertEquals("    A♣  2♣  3♣\n  4♣  5♣  6♣  7♣\n8♣  9♣  10♣ J♣  Q♣\nDraw: K♣, A♦, 2♦",
        view.toString());
  }

  //tests drawing multipyramid four row
  @Test
  public void testMulliFour() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    model.startGame(model.getDeck(), false, 4, 3);
    assertEquals("      A♣  .   2♣  .   3♣\n    4♣  5♣  6♣  7♣  8♣  9♣\n  "
            + "10♣ J♣  Q♣  K♣  A♦  2♦  3♦\n4♦  5♦  6♦  7♦  8♦  9♦  10♦ J♦\nDraw: Q♦, K♦, A♥",
        view.toString());
  }

  //tests drawing multipyramid five row
  @Test
  public void testMulliFive() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    model.startGame(model.getDeck(), false, 5, 3);
    assertEquals("        A♣  .   2♣  .   3♣\n      4♣  5♣  6♣  7♣  8♣  9♣\n    "
            + "10♣ J♣  Q♣  K♣  A♦  2♦  3♦\n  4♦  5♦  6♦  7♦  8♦  9♦  10♦ J♦\n"
            + "Q♦  K♦  A♥  2♥  3♥  4♥  5♥  6♥  7♥\nDraw: 8♥, 9♥, 10♥",
        view.toString());
  }

  //tests drawing multipyramid six row
  @Test
  public void testMulliSix() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    model.startGame(model.getDeck(), false, 6, 3);
    assertEquals("          A♣  .   .   2♣  .   .   3♣\n        4♣  5♣  .   6♣  7♣  "
            + ".   8♣  9♣\n      10♣ J♣  Q♣  K♣  A♦  2♦  3♦  4♦  5♦\n    6♦  7♦  8♦  9♦  10♦ J♦  Q♦"
            + "  K♦  A♥  2♥\n  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥\nA♠  2♠  3♠  4♠  5♠  6♠  "
            + "7♠  8♠  9♠  10♠ J♠  Q♠\nDraw: K♠, A♣, 2♣",
        view.toString());
  }

  //tests drawing multipyramid seven row
  @Test
  public void testMulliSeven() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    model.startGame(model.getDeck(), false, 7, 3);
    assertEquals("            A♣  .   .   2♣  .   .   3♣\n          4♣  5♣  .   6♣  7♣  "
            + ".   8♣  9♣\n        10♣ J♣  Q♣  K♣  A♦  2♦  3♦  4♦  5♦\n      6♦  7♦  8♦  9♦  10♦ J♦"
            + "  Q♦  K♦  A♥  2♥\n    3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥\n  A♠  2♠  3♠  4♠  "
            + "5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠\nK♠  A♣  2♣  3♣  4♣  5♣  6♣  7♣  8♣  9♣  10♣ J♣  Q♣\n"
            + "Draw: K♣, A♦, 2♦",
        view.toString());
  }

  //tests drawing multipyramid eight row
  @Test
  public void testMulliEight() {
    PyramidSolitaireModel<Card> model = new MultiPyramidSolitaire();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    model.startGame(model.getDeck(), false, 8, 3);
    assertEquals("              A♣  .   .   .   2♣  .   .   .   3♣\n            4♣  5♣  . "
            + "  .   6♣  7♣  .   .   8♣  9♣\n          10♣ J♣  Q♣  .   K♣  A♦  2♦  .   3♦  4♦  5♦\n"
            + "        6♦  7♦  8♦  9♦  10♦ J♦  Q♦  K♦  A♥  2♥  3♥  4♥\n      5♥  6♥  7♥  8♥  9♥  "
            + "10♥ J♥  Q♥  K♥  A♠  2♠  3♠  4♠\n    5♠  6♠  7♠  8♠  9♠  10♠ J♠  Q♠  K♠  A♣  2♣  3♣  "
            + "4♣  5♣\n  6♣  7♣  8♣  9♣  10♣ J♣  Q♣  K♣  A♦  2♦  3♦  4♦  5♦  6♦  7♦\n8♦  9♦  10♦ "
            + "J♦  Q♦  K♦  A♥  2♥  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\nDraw: J♥, Q♥, K♥",
        view.toString());
  }

  //tests rendering multipyramid
  @Test
  public void testRenderMulti() {
    PyramidSolitaireModel<Card> model1 =
        new MultiPyramidSolitaire();
    Appendable out = new StringBuilder();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(out, model1);
    model1.startGame(model1.getDeck(), false, 6, 5);
    try {
      view.render();
    }
    catch (IOException io) {
      throw new IllegalStateException("cannot transmit");
    }
    assertEquals("          A♣  .   .   2♣  .   .   3♣\n        4♣  5♣  .   6♣  7♣  "
            + ".   8♣  9♣\n      10♣ J♣  Q♣  K♣  A♦  2♦  3♦  4♦  5♦\n    6♦  7♦  8♦  9♦  10♦ J♦  Q♦"
            + "  K♦  A♥  2♥\n  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥ J♥  Q♥  K♥\nA♠  2♠  3♠  4♠  5♠  6♠  "
            + "7♠  8♠  9♠  10♠ J♠  Q♠\nDraw: K♠, A♣, 2♣, 3♣, 4♣",
        view.toString());
  }

  //tests rendering a relaxed pyramid
  @Test
  public void testRenderRelaxed() {
    PyramidSolitaireModel<Card> model1 =
        new RelaxedPyramidSolitaire();
    Appendable out = new StringBuilder();
    PyramidSolitaireView view = new PyramidSolitaireTextualView(out, model1);
    model1.startGame(model1.getDeck(), false, 9, 5);
    try {
      view.render();
    }
    catch (IOException io) {
      throw new IllegalStateException("cannot transmit");
    }
    assertEquals("                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + "J♥  Q♥  K♥  A♠  2♠  3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠, J♠", view.toString());
  }

}
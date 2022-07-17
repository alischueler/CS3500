package cs3500.pyramidsolitaire.view;

import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import org.junit.Test;

/**
 * A class for testing the public methods of PyramidSolitaireTextualView.
 */
public class PyramidSolitaireTextualViewTest {


  //tests that the view deck can properly display a small pyramid
  @Test
  public void testSmallPyramid() {
    BasicPyramidSolitaire smallModel =
        new BasicPyramidSolitaire();
    PyramidSolitaireTextualView smallView = new PyramidSolitaireTextualView(smallModel);
    smallModel.startGame(smallModel.getDeck(), false, 3, 3);
    assertEquals("    A♣\n  2♣  3♣\n4♣  5♣  6♣\nDraw: 7♣, 8♣, 9♣", smallView.toString());
  }

  @Test
  public void testFullDeck() {
    BasicPyramidSolitaire fullModel =
        new BasicPyramidSolitaire();
    PyramidSolitaireTextualView fullView = new PyramidSolitaireTextualView(fullModel);
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
    BasicPyramidSolitaire basic = new BasicPyramidSolitaire(4, 6);
    PyramidSolitaireTextualView view  = new PyramidSolitaireTextualView(basic);
    assertEquals("", view.toString());
  }

  //tests that when someone wins it is properly displayed
  @Test
  public void testGameOverDraw() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 2, 10);
    model1.removeUsingDraw(6, 1, 1);
    model1.removeUsingDraw(7, 1, 0);
    model1.removeUsingDraw(8, 0, 0);
    assertEquals("You win!", view.toString());
  }

  //tests that game is not over with more in stock
  @Test
  public void testGameOver() {
    BasicPyramidSolitaire fullModel =
        new BasicPyramidSolitaire();
    PyramidSolitaireTextualView fullView = new PyramidSolitaireTextualView(fullModel);
    fullModel.startGame(fullModel.getDeck(), false, 2, 1);
    assertEquals("  A♣\n2♣  3♣\nDraw: 4♣", fullView.toString());
  }


  //tests for the correct board after many operations
  @Test
  public void testDrawingOperations() {
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model1);
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
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(3);
    model1.removeUsingDraw(2, 8, 6);
    model1.discardDraw(2);
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
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model1);
    model1.startGame(model1.getDeck(), false, 9, 5);
    model1.remove(8, 2);
    model1.remove(8, 1, 8, 3);
    model1.remove(8, 0, 8, 4);
    model1.removeUsingDraw(1, 8, 7);
    model1.discardDraw(1);
    model1.discardDraw(3);
    model1.removeUsingDraw(2, 8, 6);
    model1.discardDraw(2);
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
    BasicPyramidSolitaire model1 =
        new BasicPyramidSolitaire();
    PyramidSolitaireTextualView view = new PyramidSolitaireTextualView(model1);
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
    assertEquals("Game over. Score: 250", view.toString());
  }

}
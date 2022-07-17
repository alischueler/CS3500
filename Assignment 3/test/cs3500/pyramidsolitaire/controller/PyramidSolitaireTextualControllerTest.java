package cs3500.pyramidsolitaire.controller;

import cs3500.pyramidsolitaire.model.hw02.Card;
import static org.junit.Assert.assertEquals;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


/**
 * A class for testing public methods in PyramidSolitaireTextualController.
 */
public class PyramidSolitaireTextualControllerTest {

  //tests that game will not be started with a null model
  @Test (expected = IllegalArgumentException.class)
  public void testNullModel() {
    PyramidSolitaireModel<Card> model = null;
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    deck.add(new Card(4, 2));

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 5, 2);
  }

  //tests that the game will not be started with less than 52 cards
  @Test (expected = IllegalStateException.class)
  public void testLessThan52() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    deck.add(new Card(4, 2));

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 5, 2);
  }

  //tests that the game will not be started with greater than cards
  @Test (expected = IllegalStateException.class)
  public void testGreaterThan52() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    for (int i = 0; i < 70; i++) {
      deck.add(new Card(5, 2));
    }
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 5, 2);
  }

  //tests that the game will not be started with duplicates in the deck
  @Test (expected = IllegalStateException.class)
  public void testDuplicateDeck() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    for (int i = 0; i < 52; i++) {
      deck.add(new Card(5, 2));
    }
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 5, 2);
  }

  //tests that the game will not be started with too many rows
  @Test (expected = IllegalStateException.class)
  public void testTooManyRows() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    for (int i = 1; i <= 13; i++) {
      for (int j = 1; j <= 4; j++) {
        deck.add(new Card(i, j));
      }
    }
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 10, 2);
  }

  //tests that the game will not be started with not enough rows
  @Test (expected = IllegalStateException.class)
  public void testNotEnoughRows() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    for (int i = 1; i <= 13; i++) {
      for (int j = 1; j <= 4; j++) {
        deck.add(new Card(i, j));
      }
    }
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 0, 2);
  }

  //tests that the game will not be started with neg rows
  @Test (expected = IllegalStateException.class)
  public void testNegRows() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    for (int i = 1; i <= 13; i++) {
      for (int j = 1; j <= 4; j++) {
        deck.add(new Card(i, j));
      }
    }
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, -3, 2);
  }

  //tests that the game will not be started with too many draw cards
  @Test (expected = IllegalStateException.class)
  public void testTooManyDrawCards() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    for (int i = 1; i <= 13; i++) {
      for (int j = 1; j <= 4; j++) {
        deck.add(new Card(i, j));
      }
    }
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 7, 25);
  }

  //tests that the game will not be started with neg draw cards
  @Test (expected = IllegalStateException.class)
  public void testNegDrawCards() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    for (int i = 1; i <= 13; i++) {
      for (int j = 1; j <= 4; j++) {
        deck.add(new Card(i, j));
      }
    }
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, false, 6, -2);
  }

  //tests that game will not be started with nulls in deck
  @Test (expected = IllegalStateException.class)
  public void testNullDeck() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    deck.add(null);
    for (int i = 1; i <= 12; i++) {
      for (int j = 1; j <= 4; j++) {
        deck.add(new Card(i, j));
      }
    }
    deck.add(null);
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, true, 5, 2);
  }

  //tests that game will not be started with invalid cards in deck
  @Test (expected = IllegalStateException.class)
  public void testInvalidDeck() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable out = new StringBuilder();
    Readable in = new StringReader("rm1 7 5 q");
    List<Card> deck = new ArrayList<>();
    deck.add(new Card(0, 0));
    for (int i = 1; i <= 12; i++) {
      for (int j = 1; j <= 4; j++) {
        deck.add(new Card(i, j));
      }
    }
    deck.add(new Card(15, 5));
    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(in, out);
    controller.playGame(model, deck, true, 5, 2);
  }


  //tests removing a vaid card
  @Test
  public void testRemoveOneValid() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm1 7 5");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  .   A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 172\n", app.toString());
  }

  //tests that removing a non valid card returns the correct string
  @Test
  public void testErrorRemoveOneInvalid() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm1 7 -1");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Invalid move. Play again. No card exists here\n", app.toString());
  }

  //tests that removing a non 13 card returns the correct string
  @Test
  public void testErrorRemoveOneNot13() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm1 7 7");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Invalid move. Play again. Invalid remove\n", app.toString());

  }


  //tests removing two valid cards
  @Test
  public void testRemoveTwoValid() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm2 7 3 7 7");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ .   Q♦  K♦  A♥  .\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 172\n", app.toString());
  }

  //tests that removing a non valid card returns the correct string
  @Test
  public void testRemoveTwoNot13() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm2 7 3 2 1");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Invalid move. Play again. Invalid remove\n", app.toString());
  }

  //tests that removing two cards that do not add to 13 returns the correct string
  @Test
  public void testRemoveTwoNotValidCard() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm2 7 3 -2 9");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Invalid move. Play again. Not valid cards\n", app.toString());
  }

  //tests removing two two valid cards in a row
  @Test
  public void testRemoveTwoValidX2() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm2 7 3 7 7 rm2               7 4 7 6");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ .   Q♦  K♦  A♥  .\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 172\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ .   .   K♦  .   .\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 159\n", app.toString());
  }


  //tests removing a card and a draw card
  @Test
  public void testRemoveWithDrawValid() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rmwd 1 7 2");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  .   J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 6♥, 4♥, 5♥\n"
        + "Score: 175\n", app.toString());
  }

  //tests that removing a non valid remove using draw throws an error
  @Test
  public void testRemoveWithDrawNotValid() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rmwd 0 7 2");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Invalid move. Play again. Invalid remove\n", app.toString());
  }

  //tests that removing a draw and pyramid card that do not add to 13 throws an error
  @Test
  public void testRemoveWithDrawNot13() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rmwd 8 7 1");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Invalid move. Play again. Invalid remove\n", app.toString());
  }

  //tests removing two card and a draw card in a row
  @Test
  public void testRemoveWithDrawValidX2() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rmwd 1 7 2      \n rmwd 2 7 1");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  .   J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 6♥, 4♥, 5♥\n"
        + "Score: 175\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + ".   .   J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 6♥, 7♥, 5♥\n"
        + "Score: 166\n", app.toString());
  }

  //tests discarding a valid draw
  @Test
  public void testDiscardDrawValid() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("dd 2");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 6♥, 5♥\n"
        + "Score: 185\n", app.toString());
  }


  //tests discarding two valid draw in a row
  @Test
  public void testDiscardDrawValidX2() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("dd 2\ndd 1");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 6♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 7♥, 6♥, 5♥\n"
        + "Score: 185\n", app.toString());
  }


  //tests that discarding a non valid draw will throw an error
  @Test
  public void testDiscardDrawNotValid() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("dd 0");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Invalid move. Play again. Invalid discard\n", app.toString());
  }

  //tests with q ad first entry
  @Test
  public void testFirstq() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("q");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController( read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n", app.toString());
  }

  //tests with q as last entry
  @Test
  public void testLastq() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rmwd 1 7 2 rm1 7\n5 Q");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  .   J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 6♥, 4♥, 5♥\n"
        + "Score: 175\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  .   J♦  Q♦  .   A♥  2♥\n"
        + "Draw: 6♥, 4♥, 5♥\n"
        + "Score: 162\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  .   J♦  Q♦  .   A♥  2♥\n"
        + "Draw: 6♥, 4♥, 5♥\n"
        + "Score: 162\n", app.toString());
  }

  //tests with q as middle of one card remove
  @Test
  public void testqMiddleRemove() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm1 1   Q");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n", app.toString());
  }

  //tests with q as middle of two card remove
  @Test
  public void testqMiddleRemoveTwo() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm1 7 5 dd 2 rm2 q 3 4 6 8");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  .   A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 172\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  .   A♥  2♥\n"
        + "Draw: 3♥, 6♥, 5♥\n"
        + "Score: 172\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  .   A♥  2♥\n"
        + "Draw: 3♥, 6♥, 5♥\n"
        + "Score: 172\n", app.toString());
  }

  //tests with q as middle of remove with draw
  @Test
  public void testqMiddleRemoveWD() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("h h bogus rmwd 2 Q f 7 2");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Did not recognize move h\n"
        + "Did not recognize move h\n"
        + "Did not recognize move bogus\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n", app.toString());
  }

  //tests with q as middle of discard draw
  @Test
  public void testqMiddleRemoveDD() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("h h bogus dd Q");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Did not recognize move h\n"
        + "Did not recognize move h\n"
        + "Did not recognize move bogus\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n", app.toString());
  }

  //tests with q in between two removes
  @Test
  public void testqMiddleTwoRemoves() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("h h bogus rm1 7 5 \n q rm2 7 3 7 7");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Did not recognize move h\n"
        + "Did not recognize move h\n"
        + "Did not recognize move bogus\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  .   A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 172\n"
        + "Game quit!\n"
        + "State of game when quit:\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  .   A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 172\n", app.toString());
  }

  //tests an invalid command
  @Test
  public void testInvalidCommand() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("dd1 3");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Did not recognize move dd1\n"
        + "Did not recognize move 3\n", app.toString());
  }


  //tests one remove with invalid inputs before correct one
  @Test
  public void testRemove1InvalidCommand() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("wwww hi bye rm1 d s  s o 7 a hello 5");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Did not recognize move wwww\n"
        + "Did not recognize move hi\n"
        + "Did not recognize move bye\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  .   A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 172\n", app.toString());
  }

  //tests two remove with invalid inputs before correct one
  @Test
  public void testRemove2InvalidCommand() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("wwww ! ? rm2 d s  s o 7 a bc 3 i 7 7");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController( read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
            + "Draw: 3♥, 4♥, 5♥\n"
            + "Score: 185\n"
            + "Did not recognize move wwww\n"
            + "Did not recognize move !\n"
            + "Did not recognize move ?\n"
            + "            A♣\n"
            + "          2♣  3♣\n"
            + "        4♣  5♣  6♣\n"
            + "      7♣  8♣  9♣  10♣\n"
            + "    J♣  Q♣  K♣  A♦  2♦\n"
            + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
            + "9♦  10♦ .   Q♦  K♦  A♥  .\n"
            + "Draw: 3♥, 4♥, 5♥\n"
            + "Score: 172\n", app.toString());
  }


  //tests remove with draw with invalid inputs before correct one
  @Test
  public void testRemoveWDInvalidCommand() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("wwww . , rmwd d s  s o 1 a bc 7 i 2");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Did not recognize move wwww\n"
        + "Did not recognize move .\n"
        + "Did not recognize move ,\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  .   J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 6♥, 4♥, 5♥\n"
        + "Score: 175\n", app.toString());
  }

  //tests discard draw with invalid inputs before correct one
  @Test
  public void testDDInvalidCommand() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("hyr 6 , dd d s  s o 3");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);

    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Did not recognize move hyr\n"
        + "Did not recognize move 6\n"
        + "Did not recognize move ,\n"
        + "            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 6♥\n"
        + "Score: 185\n", app.toString());
  }

  //tests losing the game
  @Test
  public void GameIsOverScore() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm1 9 3 rm2 9 2 9 4       rm2 9 1   9 5\n  rmwd 1 9 9 \n"
        + "dd 2 dd 3     dd 4 dd       2 dd\n1 dd 5");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController( read, app);
    controller.playGame(model, model.getDeck(),
        false, 9, 5);

    assertEquals("                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + "J♥  Q♥  K♥  A♠  2♠  3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "Score: 294\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + "J♥  Q♥  .   A♠  2♠  3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "Score: 281\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + "J♥  .   .   .   2♠  3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "Score: 268\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "Score: 255\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  5♠  .\n"
        + "Draw: Q♠, 8♠, 9♠, 10♠, J♠\n"
        + "Score: 249\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  5♠  .\n"
        + "Draw: Q♠, K♠, 9♠, 10♠, J♠\n"
        + "Score: 249\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  5♠  .\n"
        + "Draw: Q♠, K♠, . , 10♠, J♠\n"
        + "Score: 249\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  5♠  .\n"
        + "Draw: Q♠, K♠, . , . , J♠\n"
        + "Score: 249\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  5♠  .\n"
        + "Draw: Q♠, . , . , . , J♠\n"
        + "Score: 249\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  5♠  .\n"
        + "Draw: . , . , . , . , J♠\n"
        + "Score: 249\n"
        + "Game over. Score: 249\n", app.toString());
  }

  //tests winning the game
  @Test
  public void GameIsOverWin() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rmwd 7 2 2\nrmwd\n8\n2\n1\nrmwd 9 1 1");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 2, 10);

    assertEquals("  A♣\n2♣  3♣\nDraw: 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "Score: 6\n"
        + "  A♣\n2♣  .\nDraw: 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, A♦, J♣, Q♣, K♣\n"
        + "Score: 3\n"
        + "  A♣\n.   .\nDraw: 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, A♦, 2♦, Q♣, K♣\n"
        + "Score: 1\n"
        + "You win!\n", app.toString());
  }

  //tests removing cards that have already been removed
  @Test
  public void RemoveAlreadygone() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm1 9 3\n rm2 9 1 9 5 \n rm2 9 2 9 4\n rmwd 4 b 9 s 6\n"
        + "rm2 8 5 9 9\nrmwd 2 9 w 8\nrm2 8 8 8 1\n rmwd 3 9 7 rmwd 1 8 4 dd \n 2\n rm2 8 3 8 6"
        + "  rm2 8 7 8 2\n rm1\n 7 5\n\n rm2 7 3 7 7 \n rm2 7 4 7 6 \n rm2 6 3 6 6 "
        + "rm2\n6 \n5 \n6 \n4 rm1 5 3\n rmwd 4 5 4 rm1 9 2 rm2 9 2 8 3 rmwd 1 8 2 dd 1");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 9, 5);
    assertEquals("                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + "J♥  Q♥  K♥  A♠  2♠  3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "Score: 294\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + "J♥  Q♥  .   A♠  2♠  3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "Score: 281\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   Q♥  .   A♠  .   3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "Score: 268\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   3♠  4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, 10♠, J♠\n"
        + "Score: 255\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  7♥  8♥  9♥  10♥\n"
        + ".   .   .   .   .   .   4♠  5♠  6♠\n"
        + "Draw: 7♠, 8♠, 9♠, Q♠, J♠\n"
        + "Score: 252\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  .   8♥  9♥  10♥\n"
        + ".   .   .   .   .   .   4♠  5♠  .\n"
        + "Draw: 7♠, 8♠, 9♠, Q♠, J♠\n"
        + "Score: 239\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  3♥  4♥  5♥  6♥  .   8♥  9♥  10♥\n"
        + ".   .   .   .   .   .   4♠  .   .\n"
        + "Draw: 7♠, K♠, 9♠, Q♠, J♠\n"
        + "Score: 234\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  .   4♥  5♥  6♥  .   8♥  9♥  .\n"
        + ".   .   .   .   .   .   4♠  .   .\n"
        + "Draw: 7♠, K♠, 9♠, Q♠, J♠\n"
        + "Score: 221\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  .   4♥  5♥  6♥  .   8♥  9♥  .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: 7♠, K♠, . , Q♠, J♠\n"
        + "Score: 217\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  .   4♥  5♥  .   .   8♥  9♥  .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , K♠, . , Q♠, J♠\n"
        + "Score: 211\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  .   4♥  5♥  .   .   8♥  9♥  .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , Q♠, J♠\n"
        + "Score: 211\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  .   4♥  .   .   .   .   9♥  .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , Q♠, J♠\n"
        + "Score: 198\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "  .   .   .   .   .   .   .   .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , Q♠, J♠\n"
        + "Score: 185\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ J♦  Q♦  .   A♥  2♥\n"
        + "  .   .   .   .   .   .   .   .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , Q♠, J♠\n"
        + "Score: 172\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ .   Q♦  .   A♥  .\n"
        + "  .   .   .   .   .   .   .   .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , Q♠, J♠\n"
        + "Score: 159\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "    9♦  10♦ .   .   .   .   .\n"
        + "  .   .   .   .   .   .   .   .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , Q♠, J♠\n"
        + "Score: 146\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  .   6♦  7♦  .\n"
        + "    9♦  10♦ .   .   .   .   .\n"
        + "  .   .   .   .   .   .   .   .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , Q♠, J♠\n"
        + "Score: 133\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  K♣  A♦  2♦\n"
        + "      3♦  4♦  .   .   .   .\n"
        + "    9♦  10♦ .   .   .   .   .\n"
        + "  .   .   .   .   .   .   .   .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , Q♠, J♠\n"
        + "Score: 120\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  .   A♦  2♦\n"
        + "      3♦  4♦  .   .   .   .\n"
        + "    9♦  10♦ .   .   .   .   .\n"
        + "  .   .   .   .   .   .   .   .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , Q♠, J♠\n"
        + "Score: 107\n"
        + "                A♣\n"
        + "              2♣  3♣\n"
        + "            4♣  5♣  6♣\n"
        + "          7♣  8♣  9♣  10♣\n"
        + "        J♣  Q♣  .   .   2♦\n"
        + "      3♦  4♦  .   .   .   .\n"
        + "    9♦  10♦ .   .   .   .   .\n"
        + "  .   .   .   .   .   .   .   .\n"
        + ".   .   .   .   .   .   .   .   .\n"
        + "Draw: . , . , . , . , J♠\n"
        + "Score: 106\n"
        + "Invalid move. Play again. No card exists here\n"
        + "Invalid move. Play again. Not valid cards\n"
        + "Invalid move. Play again. Invalid remove\n"
        + "Invalid move. Play again. Invalid discard\n", app.toString());
  }

  //tests that spaces are needed to read commands correctly
  @Test
  public void testNoSpace() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm175rm27476dd1 rm175 rm27476 dd1");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);
    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n"
        + "Did not recognize move rm175rm27476dd1\n"
        + "Did not recognize move rm175\n"
        + "Did not recognize move rm27476\n"
        + "Did not recognize move dd1\n", app.toString());
  }

  //tests that the controller is unable to transmit output
  @Test(expected = IllegalStateException.class)
  public void testNoOutput() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new MockAppendable();
    Readable read = new StringReader("hh");
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(), true, 4, 6);
  }

  //tests that the controller is unable to recieve input
  @Test(expected = IllegalStateException.class)
  public void testNoInputError() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new MockReadable();
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(), true, 7, 4);
  }

  //tests controller with mock model and a q
  @Test
  public void testMockQ() {
    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel<Card> mockModel = new MockModel(log);
    Appendable app = new StringBuilder();
    Readable read = new StringReader("dd 1 rm2 5 2 100 4 rm1 3 -4 q rmwd 6 400 -10");
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(read, app);
    controller.playGame(mockModel, mockModel.getDeck(), false, 7, 4);
    assertEquals("discard 0\nremove: 4 1 99 3\nremove: 2 -5\n", log.toString());
  }

  //tests controller with no input will only display first screen
  @Test
  public void testNoInput() {
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    Appendable app = new StringBuilder();
    Readable read = new StringReader("");

    PyramidSolitaireController controller =
        new PyramidSolitaireTextualController(read, app);
    controller.playGame(model, model.getDeck(),
        false, 7, 3);
    assertEquals("            A♣\n"
        + "          2♣  3♣\n"
        + "        4♣  5♣  6♣\n"
        + "      7♣  8♣  9♣  10♣\n"
        + "    J♣  Q♣  K♣  A♦  2♦\n"
        + "  3♦  4♦  5♦  6♦  7♦  8♦\n"
        + "9♦  10♦ J♦  Q♦  K♦  A♥  2♥\n"
        + "Draw: 3♥, 4♥, 5♥\n"
        + "Score: 185\n", app.toString());
  }

  //tests that the controller is not created with null app
  @Test(expected = IllegalArgumentException.class)
  public void testNullApp() {
    Appendable app = null;
    Readable read = new StringReader("H");
    new PyramidSolitaireTextualController(read, app);
  }

  //tests that the controller is not created with null read
  @Test(expected = IllegalArgumentException.class)
  public void testNullRead() {
    Appendable app = new StringBuilder();
    Readable read = null;
    new PyramidSolitaireTextualController(read, app);
  }

  //tests that remove one is sending right info to model
  @Test
  public void testRemoveOneInput() {
    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel<Card> mockModel = new MockModel(log);
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm1 6 4");
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(read, app);
    controller.playGame(mockModel, mockModel.getDeck(), false, 7, 4);
    assertEquals("remove: 5 3\n", log.toString());
  }


  //tests that remove two is sending right info to model
  @Test
  public void testRemoveTwoInput() {
    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel<Card> mockModel = new MockModel(log);
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rm2 6 4 7 2");
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(read, app);
    controller.playGame(mockModel, mockModel.getDeck(), false, 7, 4);
    assertEquals("remove: 5 3 6 1\n", log.toString());
  }

  //tests that remove with draw is sending right info to model
  @Test
  public void testRemoveWDInput() {
    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel<Card> mockModel = new MockModel(log);
    Appendable app = new StringBuilder();
    Readable read = new StringReader("rmwd 6 4 7");
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(read, app);
    controller.playGame(mockModel, mockModel.getDeck(), false, 7, 4);
    assertEquals("remove: 5 3 6\n", log.toString());
  }

  //tests that discard draw is sending right info to model
  @Test
  public void testDiscardInput() {
    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel<Card> mockModel = new MockModel(log);
    Appendable app = new StringBuilder();
    Readable read = new StringReader("dd -2");
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(read, app);
    controller.playGame(mockModel, mockModel.getDeck(), false, 7, 4);
    assertEquals("discard -3\n", log.toString());
  }

  //test that the model is receiving the correct input for a series of moves
  @Test
  public void testSeriesInput() {
    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel<Card> mockModel = new MockModel(log);
    Appendable app = new StringBuilder();
    Readable read = new StringReader("dd 1 rm2 5 2 100 4 rm1 3 -4 rmwd 6 400 -10");
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(read, app);
    controller.playGame(mockModel, mockModel.getDeck(), false, 7, 4);
    assertEquals("discard 0\nremove: 4 1 99 3\nremove: 2 -5\nremove: 5 399 -11\n",
        log.toString());
  }
}
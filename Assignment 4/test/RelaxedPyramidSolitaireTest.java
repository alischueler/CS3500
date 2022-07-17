

import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * A class for testing public methods of RelaxedPyramidSolitaire.
 */
public class RelaxedPyramidSolitaireTest extends BasicPyramidSolitaireTest {

  //tests that relaxed still does not allow moves if they do not add to 13
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveOverlapNot13() {
    RelaxedPyramidSolitaire model1 =
        new RelaxedPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 7, 3);
    model1.removeUsingDraw(1, 6, 0);
    model1.remove(5, 0, 6, 2);
  }


  //tests that relax does not allow to remove using draw if it is still covered by one card
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveUDOverlap() {
    RelaxedPyramidSolitaire model1 =
        new RelaxedPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 7, 8);
    model1.removeUsingDraw(1, 6, 0);
    model1.removeUsingDraw(7, 5, 0);
  }

  //tests that will not remove card that adds to 13 if it has two children
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveOverlap() {
    RelaxedPyramidSolitaire model1 =
        new RelaxedPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 7, 3);
    model1.remove(5, 0, 6, 1);
  }

  //tests that will remove card that adds to 13 if it has only one child
  @Test
  public void testRemoveOverlap2() {
    PyramidSolitaireModel<Card> model1 =
        new RelaxedPyramidSolitaire();
    model1.startGame(model1.getDeck(), false, 7, 3);
    model1.removeUsingDraw(1, 6, 0);
    model1.remove(5, 0, 6, 1);
    assertEquals(null, model1.getCardAt(5, 0));
    assertEquals(null, model1.getCardAt(6, 1));
  }

  //checks that game is not over yet if there are two cards that can be removed in relaxed style
  @Test
  public void gameNotOverRelaxed() {
    PyramidSolitaireModel<Card> model1 =
        new RelaxedPyramidSolitaire();
    List<Card> deck = new ArrayList<>();
    deck.add(new Card(1, 4));
    deck.add(new Card(12, 4));
    for (int i = 1; i <= 13; i ++) {
      for (int j = 1; j <= 3; j ++) {
        deck.add(new Card(i, j));
      }
    }
    for (int i = 2; i <= 11; i ++) {
      deck.add(new Card(i, 4));
    }
    deck.add(new Card(13, 4));
    model1.startGame(deck, false, 2, 3);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.removeUsingDraw(2, 1, 1);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    model1.discardDraw(0);
    model1.discardDraw(1);
    model1.discardDraw(2);
    assertEquals(false, model1.isGameOver());
  }
}
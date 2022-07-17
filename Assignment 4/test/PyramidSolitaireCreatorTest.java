

import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator.GameType;
import org.junit.Test;

/**
 * A class to test public methoods of PyramidSolitaire Creator.
 */
public class PyramidSolitaireCreatorTest {

  //tests that a basicpyramid model is created from BASIC enum
  @Test
  public void createBASICtest() {
    GameType basic = GameType.BASIC;
    PyramidSolitaireCreator basicCreate = new PyramidSolitaireCreator();
    assertEquals(cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire.class,
        basicCreate.create(basic).getClass());
  }

  //tests that a relaxed pyramid model is created from RELAXED enum
  @Test
  public void createREALXEDtest() {
    GameType relaxed = GameType.RELAXED;
    PyramidSolitaireCreator relaxedcreate = new PyramidSolitaireCreator();
    assertEquals(cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire.class,
        relaxedcreate.create(relaxed).getClass());
  }

  //tests that a multi pyramid model is created from MULTIPYRAMID enum
  @Test
  public void createMULTItest() {
    GameType multi = GameType.MULTIPYRAMID;
    PyramidSolitaireCreator multiCreate = new PyramidSolitaireCreator();
    assertEquals(cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire.class,
        multiCreate.create(multi).getClass());
  }
}
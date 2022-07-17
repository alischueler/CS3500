package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Represents a Factory class to intialize the correct model using the three possible enums.
 */
public class PyramidSolitaireCreator {

  /**
   * Represent three possible game states for pyramid solitiare : basic, relaxed, or multipyramid.
   */
  public enum GameType {
    BASIC(), RELAXED(), MULTIPYRAMID();
  }

  /**
   * Determines the correct version of the model based on the gametype passed in.
   * @param type represents the enum representing the game state.
   * @return the correct version of the model.
   */
  public static PyramidSolitaireModel<Card> create(GameType type) {
    PyramidSolitaireModel<Card> model;
    switch (type) {
      case BASIC: model = new BasicPyramidSolitaire();
        break;
      case RELAXED: model = new RelaxedPyramidSolitaire();
      break;
      case MULTIPYRAMID: model = new MultiPyramidSolitaire();
      break;
      default: model = null;
        break;
    }
    return model;
  }

}


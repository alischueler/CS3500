import cs3500.pyramidsolitaire.model.hw02.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent an extenson of MockModel which is a mock implementation of
 * PyramidSolitaireModel with the Card class.
 * Represents a mock model object for a multipyramid game of solitaire.
 */
public class MockMulti extends MockModel {
  StringBuilder log;

  /**
   * Consructs a MockModel to play the game.
   * @param log represents tString outputs will be apended to.
   */
  MockMulti(StringBuilder log) {
    super(log);
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

}

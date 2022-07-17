/**
 * A class to represent an extenson of MockModel which is a mock implementation of
 * PyramidSolitaireModel with the Card class.
 * Represents a mock model object for a relaxed game of solitaire.
 */
public class MockRelaxed extends MockModel {
  StringBuilder log;

  /**
   * Consructs a MockModel to play the game.
   * @param log represents tString outputs will be apended to.
   */
  MockRelaxed(StringBuilder log) {
    super(log);
  }

}

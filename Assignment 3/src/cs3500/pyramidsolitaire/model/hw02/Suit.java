package cs3500.pyramidsolitaire.model.hw02;

/**
 * Enumerates five possible types of Suits: Clubs, Diamonds, Hearts, Spades, or Empty.
 */
public enum Suit {
  Clubs('♣'), Diamonds('♦'), Hearts('♥'), Spades('♠'), Empty(' ');
  private final Character type;

  /**
   * Constructs a new Suit with the given character.
   * @param type represents the Character of the Suit.
   */
  Suit(Character type) {
    this.type = type;
  }

  /**
   * Determines the Character representation of this Suit.
   * @return the Character type of this Suit.
   */
  public Character getSuitChar() {
    return this.type;
  }
}

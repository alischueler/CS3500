package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;


/**
 * A class to represent Cards.
 */
public class Card {
  private Value value;
  private int row;
  private int card;
  private boolean exposed;
  private Suit suit;


  /**
   * Constructs a card with empty suit and value fields.
   */
  Card() {
    this.suit = Suit.Empty;
    this.value = Value.Empty;
    this.exposed = false;
  }

  /**
   * Constructs a new Card.
   * @param value the value of the card.
   * @param suit the suit of the card..
   */
  public Card(int value, int suit) {
    switch (value) {
      case 1: this.value = Value.A;
      break;
      case 2: this.value = Value.Two;
        break;
      case 3: this.value = Value.Three;
        break;
      case 4: this.value = Value.Four;
        break;
      case 5: this.value = Value.Five;
        break;
      case 6: this.value = Value.Six;
        break;
      case 7: this.value = Value.Seven;
        break;
      case 8: this.value = Value.Eight;
        break;
      case 9: this.value = Value.Nine;
        break;
      case 10: this.value = Value.Ten;
        break;
      case 11: this.value = Value.J;
        break;
      case 12: this.value = Value.Q;
        break;
      case 13: this.value = Value.K;
        break;
      default: this.value = Value.Empty;
    }
    switch (suit) {
      case 1: this.suit = Suit.Clubs;
        break;
      case 2: this.suit = Suit.Diamonds;
        break;
      case 3: this.suit = Suit.Hearts;
        break;
      case 4: this.suit = Suit.Spades;
        break;
      default: this.suit = Suit.Empty;
    }
    this.exposed = false;
  }



  /**
   * Updates the position of the given Card to a row and card in the pyramid.
   * @param row represents the row to put the card in
   * @param card represents the position in the row to put the card in
   * @param numRows represents the total number of rows originally in the pyramid
   */
  public void assignPyramidCard(int row, int card, int numRows) {
    this.row = row;
    this.card = card;
    if (row == numRows - 1) {
      this.exposed = true;
    }
    else {
      this.exposed = false;
    }
  }

  /**
   * Determines this Card's value.
   * @return this Card's value
   */
  public int findValue() {
    return this.value.getValue();
  }

  /**
   * Determines if this Card is exposed or not.
   * @return true if this Card is exposed and false if not
   */
  public boolean isExposed() {
    return this.exposed;
  }

  /**
   * Determines the row of this Card.
   * @return the row of this Card in the pyramid.
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Determines the card position of this Card in the pyramid.
   * @return the card position of this Card in the Pyramid.
   */
  public int getCard() {
    return this.card;
  }

  /**
   * Determines is this card has value.
   * @return true is no value, false if otherwise.
   */
  public boolean isEmpty() {
    return this.value == Value.Empty && this.suit == Suit.Empty;
  }

  /**
   * Updates the exposed value to true.
   */
  public void changeExposed() {
    this.exposed = true;
  }

  /**
   * Determines if this Card has the given row and card cooridinates in the pyramid.
   * @param row represents a row in the pyramid.
   * @param card represents a card position in the row in the pyramid.
   * @return true if this Card has the same row and card position in the pyramid and false if not.
   */
  public boolean checkCoordinates(int row, int card) {
    return (this.row == row) && (this.card == card);
  }

  /**
   * Determines the suit of this card.
   * @return the suit of this card.
   */
  public Suit findSuit() {
    return this.suit;
  }

  @Override
  public String toString() {
    if (this.value == Value.Empty || this.suit == Suit.Empty) {
      return ". ";
    }
    else {
      return this.value.toString() + this.suit.getSuitChar();
    }
  }


  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (! (other instanceof Card)) {
      return false;
    }

    Card newOther = (Card) other;
    return this.value == newOther.value
        && this.suit.getSuitChar() == newOther.suit.getSuitChar();

  }

  @Override
  public int hashCode() {
    return Objects.hash(value, suit);
  }
}

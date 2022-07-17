package cs3500.pyramidsolitaire.model.hw02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class to represent an implementation of PyramidSolitaireModel with the Card class.
 * Represents a model object for a game of solitaire.
 */
public class BasicPyramidSolitaire implements PyramidSolitaireModel<Card> {
  protected boolean gameStart;
  protected List<Card> stock;
  protected List<Card> draw;
  protected List<List<Card>> pyramid;
  protected int numRows;
  protected int numDraw;
  protected List<Card> deck;

  /**
   * Constructs a BasicPyramidSolitaire that is ready to start the game.
   */
  public BasicPyramidSolitaire() {
    this.gameStart = false;
    this.deck = getDeck();
  }

  /**
   * Constructus a BasicPyramidSolitaire that is not ready to start the game.
   */
  public BasicPyramidSolitaire(int numRow, int numDraw) {
    this.gameStart = false;
    this.numRows = numRow;
    this.numDraw = numDraw;
  }



  @Override
  public List<Card> getDeck() {
    List<Card> newDeck = new ArrayList<>();
    for (int i = 1; i <= 4; i++) {
      for (int j = 1; j <= 13; j++) {
        Card addCard = new Card(j, i);
        newDeck.add(addCard);
      }
    }
    return newDeck;
  }

  @Override
  public void startGame(List<Card> deck, boolean shuffle, int numRows, int numDraw)
      throws IllegalArgumentException {
    this.gameStart = true;
    if (deck == null) {
      this.gameStart = false;
      throw new IllegalArgumentException("Deck is null");
    }
    if (checkDeckSize(deck)) {
      this.gameStart = false;
      throw new IllegalArgumentException("Deck is not correct size");
    }
    if (!checkValid(deck)) {
      this.gameStart = false;
      throw new IllegalArgumentException("Deck is not Valid");
    }
    if (checkRows(numRows)) {
      this.gameStart = false;
      throw new IllegalArgumentException("numRows is invalid");
    }
    int numPyramid = findNumberPyramid(numRows);
    if (numDraw < 0 || numDraw > (deck.size() - numPyramid)) {
      this.gameStart = false;
      throw new IllegalArgumentException("numDraw is invalid");
    }
    this.deck = deck;
    if (shuffle) {
      List<Card> newDeck = new ArrayList<>();
      newDeck.addAll(deck);
      Collections.shuffle(newDeck);
      this.deck = newDeck;
    }
    this.numRows = numRows;
    this.numDraw = numDraw;
    assignPyramidPos(this.deck.subList(0, numPyramid));
    this.draw = this.deck.subList(numPyramid, numPyramid + this.numDraw);
    this.stock = this.deck.subList(numPyramid + this.numDraw, deck.size());
    assignDraw();
  }

  /**
   * Checks if the number of rows is valid to start the game.
   * @param numRows represents the number of rows passed in to start the game
   * @return true if the rows are not valid, false if otherwise.
   */
  protected boolean checkRows(int numRows) {
    return numRows <= 0 || numRows > 9;
  }

  /**
   * Checks if the size of the deck is valid to start the game.
   * @param deck represents the deck passed in to start the game.
   * @return true if the deck size is not valid, false if otherwise.
   */
  protected boolean checkDeckSize(List<Card> deck) {
    return deck.size() != 52;
  }

  /**
   * Changes the exposed value for every card in the draw pile that is also in stock.
   */
  protected void assignDraw() {
    for (Card c1 : this.draw) {
      for (Card c2 : this.stock) {
        if (c1.equals(c2)) {
          c1.changeExposed();
        }
      }
    }
  }

  /**
   * Checks to see if the deck does not have 13 different cards in the four suits and null values.
   * @param deck represents the deck of cards passed in initially to start the game.
   * @return true if the deck is valid and false if there are duplicates or null cards.
   */
  protected boolean checkValid(List<Card> deck) {
    List<Card> newList = new ArrayList<>();
    for (int i = 0; i < deck.size(); i++) {
      if (deck.get(i) == null) {
        return false;
      }
      else if (deck.get(i).isEmpty()) {
        return false;
      }
      else if (newList.size() != 0) {
        if (newList.contains(deck.get(i))) {
          return false;
        }
      }
      else {
        newList.add(deck.get(i));
      }
    }
    return true;
  }


  /**
   * Calculates the number of Cards originally in the pyramid.
   * @param numRows represents the number of rows originally passed in for the pyramid.
   * @return the total number of cards originally in the pyramid.
   */
  protected int findNumberPyramid(int numRows) {
    int pyramidNum = 0;
    while (numRows != 0) {
      pyramidNum += numRows;
      numRows -= 1;
    }
    return pyramidNum;
  }


  /**
   * Assigns row and card to every Card in the pyramid.
   */
  protected void assignPyramidPos(List<Card> deck) {
    int cardIndex = 0;
    List<List<Card>> newPyramid = new ArrayList<>();
    for (int i = 0; i < this.numRows; i ++) {
      List<Card> inner = new ArrayList<>(this.getRowWidth(i));
      for (int j = 0; j < this.getRowWidth(i); j++) {
        inner.add(new Card());
      }
      newPyramid.add(inner);
    }
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.getRowWidth(i); j++) {
        newPyramid.get(i).set(j, deck.get(cardIndex));
        cardIndex += 1;
      }
    }
    this.pyramid = newPyramid;
    for (int i = 0; i < this.getRowWidth(this.numRows - 1); i ++) {
      this.pyramid.get(this.numRows - 1).get(i).changeExposed();
    }
  }

  /**
   * Updates the exposed value of all cards in the pyramid to true only if both cards under it have
   * been removed, otherwise stays as false.
   */
  protected void updateExposed() {
    for (int i = 0; i < this.numRows - 1; i++) {
      for (int j = 0; j < this.getRowWidth(i); j++) {
        Card c = this.pyramid.get(i).get(j);
        boolean exposed = c.isExposed();
        if (!c.isEmpty()) {
          if (!exposed) {
            Card belowLeft = this.pyramid.get(i + 1).get(j);
            Card belowRight = this.pyramid.get(i + 1).get(j + 1);
            if (belowLeft.isEmpty() && belowRight.isEmpty()) {
              c.changeExposed();
            }
          }
        }
      }
    }
  }

  /**
   *  Updates the position of the given card in pyramid to null.
   * @param card represents a card in th pyramid.
   */
  protected void updateEmpty(Card card) {
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.getRowWidth(i); j++) {
        if (this.pyramid.get(i).get(j).equals(card)) {
          this.pyramid.get(i).set(j, new Card());
        }
      }
    }
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2)
      throws IllegalArgumentException, IllegalStateException {
    if (!this.gameStart) {
      throw new IllegalStateException("Game has not been started");
    } else {
      boolean exists1 = findCard(row1, card1);
      boolean exists2 = findCard(row2, card2);
      if (exists1 && exists2) {
        Card c1 = findCardinPyramid(row1, card1);
        Card c2 = findCardinPyramid(row2, card2);
        int val1 = c1.findValue();
        boolean exposed1 = c1.isExposed();
        int val2 = c2.findValue();
        boolean exposed2 = c2.isExposed();
        if ((val1 + val2 == 13) && (exposed1 && exposed2)) {
          updateEmpty(c1);
          updateEmpty(c2);
          updateExposed();
        }
        else {
          throw new IllegalArgumentException("Invalid remove");
        }
      }
      else {
        throw new IllegalStateException("Cards do not exist");
      }
    }
  }

  @Override
  public void remove(int row, int card) throws IllegalArgumentException, IllegalStateException {
    if (!this.gameStart) {
      throw new IllegalStateException("Game has not been started");
    }
    else {
      boolean exists = findCard(row, card);
      if (exists) {
        Card c = findCardinPyramid(row, card);
        int val = c.findValue();
        boolean exposed = c.isExposed();
        if ((val == 13) && exposed) {
          updateEmpty(c);
          updateExposed();
        }
        else {
          throw new IllegalArgumentException("Invalid remove");
        }
      }
      else {
        throw new IllegalStateException("Card does not exist");
      }
    }
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card)
      throws IllegalArgumentException, IllegalStateException {
    if (!this.gameStart) {
      throw new IllegalStateException("Game has not been started");
    }
    if (drawIndex < numDraw && drawIndex >= 0) {
      boolean exists = findCard(row, card);
      if (exists) {
        Card c = findCardinPyramid(row, card);
        Card draw = this.draw.get(drawIndex);
        int val = c.findValue();
        boolean exposed = c.isExposed();
        int valDraw = draw.findValue();
        if ((val + valDraw == 13) && exposed && !draw.isEmpty()) {
          updateEmpty(c);
          updateExposed();
          discardDraw(drawIndex);
        } else {
          throw new IllegalArgumentException("Cards do not add to 13");
        }
      }
      else {
        throw new IllegalArgumentException("Card has already been removed");
      }
    }
    else {
      throw new IllegalArgumentException("Card does not exist");
    }
  }

  /**
   * Finds the card with the given position in the pyramid.
   * @param row the row in the pyramid to look for the card
   * @param card the card position in the pyramid to look for the card
   * @return the card with the given coordinates in the pyramid
   */
  protected Card findCardinPyramid(int row, int card) {
    return this.pyramid.get(row).get(card);
  }

  /**
   * Determines if a card with the given coordinates exists in the pyramid.
   * @param row the row in the pyramid to look for the card
   * @param card the card position in the pyramid to look for the card
   * @return true if the card exists in the pyramid, false if not.
   */
  protected boolean findCard(int row, int card) {
    if (this.pyramid == null) {
      return false;
    }
    for (int i = 0; i < this.numRows; i++) {
      for (int j = 0; j < this.getRowWidth(i); j++) {
        Card c = this.pyramid.get(i).get(j);
        if (c != null) {
          if (!c.isEmpty()) {
            if (row == i && card == j) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }



  /**
   * Updates the draw field by adding a new card and removing the correct one
   * and stock field with a new exposed card and removing the correct one.
   * @param drawIndex represents the index of the draw card that was just removed.
   */
  private void replaceDraw(int drawIndex) {
    List<Card> newStock = new ArrayList<>();
    newStock.addAll(this.stock);
    this.stock = newStock;
    Card nextCard = this.stock.get(0);
    nextCard.changeExposed();
    this.draw.set(drawIndex, nextCard);
    this.stock.remove(0);
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalArgumentException, IllegalStateException {
    if (!this.gameStart) {
      throw new IllegalStateException("Game has not been started");
    }
    if (drawIndex < this.numDraw && drawIndex >= 0) {
      if (!this.draw.get(drawIndex).isEmpty()) {
        if (!this.stock.isEmpty()) {
          replaceDraw(drawIndex);
        }
        else if (this.stock.isEmpty()) {
          discardNotReplaceDraw(drawIndex);
        }
      }
      else {
        throw new IllegalArgumentException("Card has already been removed");
      }
    }
    else {
      throw new IllegalArgumentException("No card present here");
    }
  }


  /**
   * Discards the card at the given index without replace it in the draw pile.
   * @param drawIndex the index of the card to be discarded in both decks
   */
  private void discardNotReplaceDraw(int drawIndex) {
    this.draw.set(drawIndex, new Card());
  }

  @Override
  public int getNumRows() {
    if (!this.gameStart) {
      return -1;
    }
    return this.numRows;
  }

  @Override
  public int getNumDraw() {
    if (!this.gameStart) {
      return -1;
    }
    return this.numDraw;
  }

  @Override
  public int getRowWidth(int row) {
    if (!this.gameStart) {
      throw new IllegalStateException("Game has not started");
    }
    else if (row < this.numRows && row >= 0) {
      return row + 1;
    } else {
      throw new IllegalArgumentException("Invalid Row");
    }
  }

  /**
   * Checks if there are any remaining moves left.
   * @return true if there are moves, false if otherwise.
   */
  protected boolean checkForMoves() {
    if (getScore() == 0) {
      return false;
    } else if (getScore() != 0) {
      for (int i = 0; i < this.numRows; i++) {
        for (int j = 0; j < this.getRowWidth(i); j++) {
          Card c1 = this.pyramid.get(i).get(j);
          if (!c1.isEmpty()) {
            if (c1.isExposed() && c1.findValue() == 13) {
              return true;
            }
            if (c1.isExposed() && c1.findValue() != 13) {
              for (int l = 0; l < this.numRows; l++) {
                for (int m = 0; m < this.getRowWidth(l); m++) {
                  Card c2 = this.pyramid.get(l).get(m);
                  if (!c2.isEmpty()) {
                    if (c2.isExposed() && c1.findValue() + c2.findValue() == 13) {
                      return true;
                    }
                  }
                }
              }
            }
            for (Card draw : this.draw) {
              if (!draw.isEmpty()) {
                return true;
              }
            }
          }
        }
      }
    }
    return this.stock.size() >= 1;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    if (!this.gameStart) {
      throw new IllegalStateException("Game has not been started");
    }
    else {
      return !checkForMoves();
    }
  }

  @Override
  public int getScore() throws IllegalStateException {
    if (!this.gameStart) {
      throw new IllegalStateException("Game has not started");
    }
    else {
      int value = 0;
      for (int i = 0; i < this.numRows; i++) {
        for (int j = 0; j < this.getRowWidth(i); j++) {
          Card c = this.pyramid.get(i).get(j);
          value += c.findValue();
        }
      }
      return value;
    }
  }

  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException {
    if (!this.gameStart) {
      throw new IllegalStateException("Game has not been started");
    }
    if (row >= this.getNumRows() || row < 0) {
      throw new IllegalArgumentException("Invalid Row");
    }
    if (card >= this.getRowWidth(row) || card < 0) {
      throw new IllegalArgumentException("Invalid card");
    }
    else {
      if (findCard(row, card)) {
        return findCardinPyramid(row, card);
      }
      else {
        return null;
      }
    }
  }

  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    if (!this.gameStart) {
      throw new IllegalStateException("Game has not started");
    }
    else {
      List<Card> newDraw = new ArrayList<>();
      for (Card c : this.draw) {
        if (!c.isEmpty()) {
          newDraw.add(c);
        }
        else {
          newDraw.add(null);
        }
      }
      return newDraw;
    }
  }
}

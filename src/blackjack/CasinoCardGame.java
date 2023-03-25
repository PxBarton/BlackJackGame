/**
 * blackjack.CasinoCardGame.java
 * @author Paul Barton
 * CSC243 Assignment 4
 */


package blackjack;

/**
 * abstract class for a casino card game
 */
public abstract class CasinoCardGame {
  /**
   * the card shoe for the game
   */
  protected Shoe shoe;

  /**
   * the dealer for the game
   */
  protected Dealer dealer;

  /**
   * constructor creates a new dealer
   */
  public CasinoCardGame() {
    dealer = new Dealer();   
  }
  
  /**
   * plays a card game
   */
  public abstract void play(); 
}

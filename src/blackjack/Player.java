/**
 * blackjack.Player.java
 * @author Paul Barton
 * CSC243 Assignment4
 */

package blackjack;

/**
 * inherits class Hand, represents a player's hand and wagers
 */
public class Player extends Hand {

  /**
   * the player's starting amount
   */
  private int startStake;

  /**
   * the player's starting amount plus or minus winnings
   */
  private int stake;

  /**
   * the player's bet for a round
   */
  private int bet;

  /**
   * 
   * @param start
   */
  public Player(int start) {
    startStake = start;
    stake = start;
  }

  /**
   * sets the current bet to the amount input by user
   * @param amount
   */
  public void placeBet(int amount) {
    bet = amount;
  }

  /**
   * adds or subtracts the bet amount from the player's stake 
   * @param dealer
   * @return int 
   */
  public int collectWinnings(Dealer dealer) {
    //if (getPoints() > 21)
      
    if (getPoints() < 22 && 
          (dealer.getPoints() > 21 || compareTo(dealer) == 1 )) {
      if (hasBlackjack() && !dealer.hasBlackjack())
        stake += 2*bet;
      else
        stake += bet;
    return bet;
    }
    else if (getPoints() > 21 || compareTo(dealer) == -1) {
      stake -= bet;
      return bet;
    }
    else return 0;
  }

  /** 
   * resets the bet amount to zero
   */ 
  public void resetBet() {
    bet = 0;
  }

  /**
   * gets the player's stake amount
   * @return int
   */
  public int currentStake() {
    return stake;
  }

  /**
   * calculates the player's total winnings or losses
   * @return int 
   */  
  public int winnings() {
    return stake - startStake;
  }

}



    



          

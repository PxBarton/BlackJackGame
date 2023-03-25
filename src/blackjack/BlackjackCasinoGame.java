/**
 * blackjack.Card.java
 * CSC243
 * @author John Carelli
 */

package blackjack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Generate and play a hand of Blackjack
 */
class BlackjackCasinoGame extends CasinoCardGame {
  /**
   * represents the player's hand
   */  
  private Player player;

  /**
   * Create an new game 
   * @param nDecks the number of card decks in the shoe
   * @param rsCount maximum number of cards to deal per deck
   */
  public BlackjackCasinoGame(int nDecks, int rsCount) {
    player = new Player(100);
    try {
      shoe = new Shoe(nDecks, rsCount);
    }
    catch (BlackjackException bjex) {
      System.out.println(bjex);
      System.exit(0);
    }
    shoe.shuffle();
  }

  /**
   * Start a new round of the game
   * @param uin user input
   */
  public void newRound(ConsoleInput uin) {
    dealer = new Dealer();
    player.newHand();
    player.placeBet(uin.getBet("Your wager: "));
    try {
      dealer.hitHand(shoe.deal());
    }
    catch (BlackjackException bjex) {
      System.out.println(bjex);
      System.exit(0);
    }
    dealer.getPoints();
    System.out.println("Dealer's card: " + dealer.toString());
    System.out.println("");
  }
  
  /**
    * Starts the game fromm the GUI by creating a dealer 
    * dealing a card to the dealer and dealing 2 cards to the player
    * @param bet the player's wager from the GUI textfield
    */   
  public void newRoundGUI(int bet) {
    dealer = new Dealer();
    player.newHand();
    player.placeBet(bet);
    try {
      dealer.hitHand(shoe.deal()); 
    }
    catch (BlackjackException bjex) {
      System.out.println(bjex);
      System.exit(0);
    }
    try {
      player.hitHand(shoe.deal());
      player.hitHand(shoe.deal());
    }
    catch (BlackjackException bjex) {
      System.out.println(bjex);
      System.exit(0);
    }

  }

 
  /**
   * Play the player rounds
   * @param uin keyboard input from user
   * @return boolean false if player busts, true otherwise 
   */
  public boolean playPlayerRounds(ConsoleInput uin) {
    try {
      player.hitHand(shoe.deal());
      player.hitHand(shoe.deal());
    }
    catch (BlackjackException bjex) {
      System.out.println(bjex);
      System.exit(0);
    }
    player.displayHand();
    if (player.hasBlackjack()) { 
      System.out.println("Blackjack!");
      return true;
    }
    while (player.getPoints() < 22) {
      if (uin.playerStands(player.getPoints()) == false) {
        try {
          player.hitHand(shoe.deal());
        }
        catch (BlackjackException bjex) {
          System.out.println(bjex);
          System.exit(0);
        }
        player.displayHand();
      }
      else
        return true;
      }
    System.out.println("Bust!");
    return false;
  }

  /**
   * Deals the player a card when GUI 'Hit' button pressed
   */
  public void playerHitGUI() { 
    try {
      player.hitHand(shoe.deal());
    }
    catch (BlackjackException bjex) {
      System.out.println(bjex);
      System.exit(0);
    }
  }



  /**
   * Play the dealer round
   */
  public void playDealerRound() {
    System.out.println("");
    System.out.println("Dealer's Hand:");
    while (dealer.getPoints() < 17)
      try {
        dealer.hitHand(shoe.deal());
      }
      catch (BlackjackException bjex) {
        System.out.println(bjex);
        System.exit(0);
      }
      System.out.println(dealer.toString());
      System.out.println("Dealer's score: " + dealer.getPoints());

    if (dealer.hasBlackjack() == true)
      System.out.println("Dealer Has Blackjack!");
    else if (dealer.getPoints() == 21)
      System.out.println("Dealer Has 21!");
    if (dealer.getPoints() > 21)
      System.out.println("Dealer Busts...You Win!");
    System.out.println("");
  }

  /**
   * Deals the dealer a card
   */
  public void dealerHitGUI() {
    try {
      dealer.hitHand(shoe.deal());
    }
    catch (BlackjackException bjex) {
      System.out.println(bjex);
      System.exit(0);
    }
  }

  /**
    * Determine the winners
    */
  public void determineWinner() {
    if (player.compareTo(dealer) == 0
        && !((player.hasBlackjack() & !dealer.hasBlackjack()) ||
             (!player.hasBlackjack() & dealer.hasBlackjack())))
      System.out.println("Push!");
    if ((player.getPoints() < 22 && player.compareTo(dealer) == 1)
        || dealer.getPoints() > 21)
      System.out.println("You Won $" + player.collectWinnings(dealer) + "!");
    else if ((dealer.getPoints() < 22 && player.compareTo(dealer) == -1) 
        || player.getPoints() > 21) {
      System.out.println("Dealer Wins!");
      System.out.println("You Lost $" + player.collectWinnings(dealer));
    }
    System.out.println("Current Stake: $" + player.currentStake());
    System.out.println("Total Winnings: $" + player.winnings());
    player.resetBet();
  }

  /**
   * gets the players score for the hand
   * @return int the number of points
   */    
  public int playerPoints() {
    return player.getPoints();
  }
  /**
   * gets the players current hand
   * @return String a string representation of the players hand
   */ 
  public String playerHand() {
    return player.toString();
  }
 
  /**
   * determines if the player won the round
   * @return int 1 if player won, -1 if player lost, 0 for push
   * @param dealer the dealers hand
   */
  public int playerWon(Dealer dealer) {
    return player.compareTo(dealer);
  }
  
  /**
   * the amount the player won or lost for the round
   * @return int the number of dollars won or lost
   * @param dealer the hand to compare to
   */ 
  public int playerWinnings(Dealer dealer) {
    return player.collectWinnings(dealer);
  }

  /**
   * the players total current stake
   * @return int dollar amount
   */
  public int playerStake() {
    return player.currentStake();
  }
 
  /**
   * the players total winnings for the session
   * @param int dollar amount
   */ 
  public int playerWinnings() {
    return player.winnings();
  }
 
  /** 
   * determines if a player has blackjack
   * @param boolean true if player has blackjack, false otherwise
   */ 
  public boolean playerBlackjack() {
    return player.hasBlackjack();
  }
  
  /**
   * Play the game
   *  
   */
  public void play() {
    ConsoleInput userIn= new ConsoleInput();
    do {
      System.out.println("\n--------------------------");
      newRound(userIn);
      if(playPlayerRounds(userIn))
        playDealerRound();
      determineWinner();
    } while(userIn.getYesNo("\nQuit (y or n)? ") == 'n');
  }

  /**
   * Play blackjack
   * @param args command line arguments
   */
  public static void main(String[] args) throws BlackjackException {
    // get command line arguments
    int nDecks= 6;
    int rsCount= 26;
    if(args.length == 1) {
      rsCount= Integer.valueOf(args[0]);
    }

    BlackjackCasinoGame bj= new BlackjackCasinoGame(nDecks, rsCount);
    bj.play();
  }
}

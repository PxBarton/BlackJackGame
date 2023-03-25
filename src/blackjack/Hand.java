/**
 * blackjack.Hand.java
 * CSC243
 * @author John Carelli
 */

package blackjack;

import java.util.ArrayList;

/**
 * Represents a player's Blackjack hand.
 */
class Hand implements Comparable<Hand> {
  /**
   * Declare an ArrayList of Card objects
   */
  private ArrayList<Card> hand;

  /**
   * Create an empty hand.
   */
  public Hand() {
    hand = new ArrayList<Card>();
  }
  
  /**
   * compares hands to determine which has the highest score
   * @param h the right-operand
   * @return int 1, -1, or 0 as per compareTo() format
   */
  @Override 
  public int compareTo(Hand h) {
    if (getPoints() > h.getPoints())
      return 1;
    else if (getPoints() < h.getPoints())
      return -1;
    else 
      return 0;
  }
        

  /** Adds a card to the hand
   * @param card should be the next card in the shoe
   */
  void hitHand(Card card) {
      hand.add(card);
  }
    
  /**
   * Finds the score, checks for Aces and adjusts score accordingly
   * @return int the number of points in the hand
   */
  int getPoints() {
    int score = 0;
    int numAces = 0;
    for (int i = 0; i < hand.size(); i++) {
      score += hand.get(i).getPoints();
      if (hand.get(i).getPoints() == 11)
        numAces++;
    }
    //check for aces if score > 21
    while (score > 21 && numAces > 0) {
      score -= 10;
      numAces--;
    }
    return score; 
  }  

  /**
   * A string representation of the hand
   * @return String 
   */
  public String toString() {
    String handString = "";
    for (int i = 0; i < hand.size(); i++)
      handString += (hand.get(i).toString() + " ");
      return handString;
  }

  /**
   * Clears the cards from the existing hand
   */
  void newHand() {
    hand.clear();
  }

  /**
   * determines if a player has blackjack
   * @return true if the hand is blackjack, false otherwise
   */
  boolean hasBlackjack() {
    if (getPoints() == 21 && hand.size() == 2)
      return true;
    else return false;
  }
  
  /**
   * Prints the hand and the score
   */  
  public void displayHand() {
    System.out.println("Your Hand:");
    System.out.println(toString());
    System.out.println("Your Score:" + getPoints());
    System.out.println("");
  }

}



/**
 * blackjack.Card.java
 * CSC243
 * @author John Carelli
 */

package blackjack;

/**
 * Card class containing an individual card from a standard
 * deck of playing cards.
 */
class Card {

  /**
   * Internal numerical identifier for the card
   */
  private int id;

  /**
   *  Construct a card from the value of int id
   *  @param id a numeric value from 0 to 51 
   */
  Card(int id) {
    this.id = id;
  }
  
  /** 
   * Return the point value for the given Card
   * @return int
   */
  int getPoints() {
    int points = 0;
    switch(id % 13) {
    case 0: points = 11; break;
    case 1: points = 2; break;
    case 2: points = 3; break;
    case 3: points = 4; break;
    case 4: points = 5; break;
    case 5: points = 6; break;
    case 6: points = 7; break;
    case 7: points = 8; break;
    case 8: points = 9; break;
    case 9: points = 10; break;
    case 10: points = 10; break;
    case 11: points = 10; break;
    case 12: points = 10; break; 
    }
    return points;
  }

  /**
   * Converts the numeric id of a card to its String representation
   * @return String describes the card 
   */
  public String toString() {
    String[] rankNames = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    String[] suitNames = {"S","H","C","D"};
    int rank = id % 13;
    int suit = id / 13;
    return rankNames[rank] + suitNames[suit]; 
  }    
}    


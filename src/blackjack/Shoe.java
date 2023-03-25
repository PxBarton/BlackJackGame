/**
 * blackjack.Shoe.java
 * CSC243
 * @author John Carelli
 */

package blackjack;

/**
 * Represents a dealer's "Shoe" containing a specified
 * number of decks of standard playing cards.
 */
class Shoe {
  /**
   * cards in the shoe
   */ 
  private Card[] shoe;

  /**
   * top of the shoe (next card to deal)
   */
  private int top;     

  /**
   * maximum number of cards dealt before reshuffle
   */ 
  private int reshuffleCount;

  /**
   * Constructor
   * @param numDecks the number of decks in the shoe
   * @param maxNumPerDeck number of cards per deck determines reshuffleCount
   * @throws BlackjackException maxNumPerDeck must be less than 52
   */
  public Shoe(int numDecks, int maxNumPerDeck) throws BlackjackException {
    if (maxNumPerDeck <= 52)
      reshuffleCount = numDecks * maxNumPerDeck;
    else {
      throw new BlackjackException("Cards per Deck Exceeds Max of 52");
    }
    shoe = new Card[numDecks * 52];
    for (int i = 0; i < shoe.length; i++)
      shoe[i] = new Card(i % 52);
  }

  /**
   * Return all cards to the shoe and shuffle
   * using the Fisher-Yates shuffling algorithm
   */
  public void shuffle() {
    top= 0; // reset to the first card
    for (int i=0; i < shoe.length-1; i++) {
      int rindx = i + (int)(Math.random()*(shoe.length - i));
      Card temp = shoe[i];
      shoe[i] = shoe[rindx];
      shoe[rindx] = temp;
    }
  }

  /**
   * Deals a card to a Hand 
   * @return Card the next card in the array
   * @throws BlackjackException if the shoe limit has been reached  
   */
  Card deal() throws BlackjackException {
    top++;
    if (top-1 == reshuffleCount)
      throw new BlackjackException("Shoe Limit Reached...Reshuffling");
    else
      return shoe[top-1];
  }


  /** 
   * Prints the cards in the shoe, four rows per deck
   */ 
  public void displayShoe() {
    int num = shoe.length / 52;
    for (int i = 0; i < num; i++) {
      String shoeRow1 = "";
      for (int j = 0 + (i * 52); j < 13 + (i * 52); j++)
        shoeRow1 += shoe[j] + " "; 
      System.out.println(shoeRow1);
      String shoeRow2 = "";
      for (int j = 13 + (i * 52); j < 26 + (i * 52); j++)
        shoeRow2 += shoe[j] + " ";
      System.out.println(shoeRow2);
      String shoeRow3 = "";
      for (int j = 26 + (i * 52); j < 39 + (i * 52); j++)
        shoeRow3 += shoe[j] + " ";
      System.out.println(shoeRow3);
      String shoeRow4 = "";
      for (int j = 39 + (i * 52); j < 52 + (i * 52); j++)
        shoeRow4 += shoe[j] + " ";
      System.out.println(shoeRow4);
      }
  }

}

/**
 * blackjack.BlackjackException.java
 * CSC243
 * @Author: Paul Barton
 */

package blackjack;

/**
 * exceptions for the blackjack game
 * for when Shoe Limit reached and if cards per deck exceeds 52
 */
public class BlackjackException extends Exception {
  
  /**
   * constructor for exception
   * @param message
   */
  public BlackjackException(String message) {
    super(message);
  }

}

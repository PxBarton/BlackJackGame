/**
 * blackjack.ConsoleInput.java
 * CSC243
 * @author John Carelli
 */

package blackjack;

import java.util.Scanner;

/**
 * Receive Console input from the user
 */
public class ConsoleInput {
  private Scanner input;

  /**
   * Constructor: initialize Scanner to accept stdin
   */
  public ConsoleInput() {
    input = new Scanner(System.in);
  }

  /**
   * Get a yes/no answer from the user.
   * @param prompt a prompt for the user
   * @return 'y' for yes, 'n' for no
   */
  public char getYesNo(String prompt) {
    char result = ' ';
    while (true) {
      System.out.print(prompt);
      String s = input.next();
      s = s.toLowerCase();
      if(s.matches("[yY]([eE][sS])*")) {
        result = 'y';
        break;
      }
      else if(s.matches("[nN]([oO])*")) {
        result = 'n';
        break;
      }
      else if(s.matches("q")) { // this is a cheat, also accept 'q'
        result = 'q';
        break;
      }
      else {
        System.out.println("Invalid response. Please respond with \"y(es)\" or \"n(o)\"");
      }
    }
    return result;
  }

  /**
   * Does the player stand with the current hand?
   * @param score
   * @return true if standing with hand
   */
  public boolean playerStands(int score) {
    boolean result= false;
    while (true) {
      System.out.print("Stand with: " + score + "? (y or n): ");
      String s = input.next();
      s = s.toLowerCase();
      if(s.matches("[yY]([eE][sS])*")) {
        result = true;
        break;
      }
      else if(s.matches("[nN]([oO])*")) {
        result = false;
        break;
      }
      else {
        System.out.println("Invalid response. Please respond with \"y(es)\" or \"n(o)\"");
      }
    }
    return result;
  }

  /**
   * Get a non-negative integer representing a wager in chips from the user.
   * @param prompt a prompt for the user
   * @return an integer
   */
  public int getBet(String prompt) {
    int result = 0;
    while (true) {
      System.out.print(prompt);
      if (input.hasNextInt()) {
        result = input.nextInt();
        if (result >= 0) {
          break;
        }
      }
      else {
        String throw_away = input.next();
      }
      System.out.println("The bet must be a non-negative integer");
    }
    return result;
  }
}



package blackjack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.geometry.HPos;

public class BlackjackApp extends Application {

  BlackjackCasinoGame game = new BlackjackCasinoGame(6, 26);
  static int bet = 0;

  Text txt1 = new Text();   // player's score
  Text txt2 = new Text();   // dealer's score
  Text txt3 = new Text();   // player's hand
  Text txt4 = new Text();   // dealer's hand
  Text txt5 = new Text();   // player's stake
  Text txt6 = new Text();   // player's winnings
  Text txt7 = new Text();   // game status
  Text txt8 = new Text();   // game status

  public void setBet(int bet) {
    this.bet = bet;
  }

  TextField field1 = new TextField();
  //field1.setEditable(true);

  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    
    GridPane pane = new GridPane();  
    Label title = new Label("Blackjack");
    title.getStyleClass().add("title");
    pane.setAlignment(Pos.CENTER);
    pane.setHgap(50);
    pane.setVgap(20);

    Label lab2 = new Label("Player Score:");
    Label lab3 = new Label("Dealer Score:");
    Label lab4 = new Label("Wager:");
    Label lab5 = new Label("Stake:");
    Label lab6 = new Label("Winnings:");

    Button bt1 = new Button("Play");
    Button bt2 = new Button("Hit");
    Button bt3 = new Button("Stand");
 
    field1.setEditable(true);

    for (int i = 1; i < 9; i++)
      pane.add(new Label(""), i, 0);

    pane.add(title, 3, 0);
    GridPane.setColumnSpan(title, 3);
    GridPane.setHalignment(title, HPos.CENTER);

    pane.add(lab2, 0, 1);
    GridPane.setColumnSpan(lab2, 3);
    pane.add(lab3, 4, 1);
    GridPane.setColumnSpan(lab2, 3);
    pane.add(lab4, 5, 3);
    GridPane.setColumnSpan(lab4, 2);
    pane.add(lab5, 2, 4);
    GridPane.setColumnSpan(lab5, 2);
    pane.add(lab6, 5, 4);
    GridPane.setColumnSpan(lab6, 2);
    pane.add(bt1, 0, 3);
    GridPane.setColumnSpan(bt1, 2);
    pane.add(bt2, 2, 3);
    pane.add(bt3, 3, 3);
    GridPane.setColumnSpan(bt3, 2);

    pane.add(txt1, 3, 1);             //player's score
    pane.add(txt2, 7, 1);             //dealer's score
    pane.add(txt3, 1, 2);             //player's hand
    GridPane.setColumnSpan(txt3, 3);  
    pane.add(txt4, 5, 2);             //dealer's hand
    GridPane.setColumnSpan(txt4, 3);
    pane.add(txt5, 3, 4);             //player stake
    pane.add(txt6, 7, 4);             //player winnings
    pane.add(txt7, 2, 5);             //game status
    GridPane.setColumnSpan(txt7, 5);
    pane.add(txt8, 2, 6);             //game status
    GridPane.setColumnSpan(txt8, 6);

   // GridPane.setHalignment(lab8, HPos.CENTER);

    pane.add(field1, 7, 3);
    GridPane.setColumnSpan(field1, 2);

    bt1.setOnAction(new PlayHandler());

    bt2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if (game.playerPoints() < 22) {
          game.playerHitGUI();
          playerRound();
        }
        else 
          txt7.setText("Bust!"); 
      }
    });

    bt3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        dealerRound();
      }   
    });

    Scene scene = new Scene(pane);
    String css = getClass().getResource("Style.css").toExternalForm();
    scene.getStylesheets().add(css);

    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.setHeight(500);
    primaryStage.setWidth(800);
    primaryStage.setTitle("Blackjack Game");
    primaryStage.show(); // Display the stage

  }
  /*
   * Allows the player to be dealt card while score < 22
   * Proceeds to determineWinnerGUI() if player busts
   */ 
  private void playerRound() {
    if (game.playerPoints() >= 22) {
      txt3.setText(game.playerHand());
      txt1.setText(String.valueOf(game.playerPoints()));
      txt7.setText("Bust!");
      determineWinnerGUI();
    }
    else {
      txt3.setText(game.playerHand());
      txt1.setText(String.valueOf(game.playerPoints()));
      txt7.setText("Hit or Stand?");
    }
  }
  /*
   * Deals the remaining dealer hand
   * Proceeds to determineWinnerGUI()
   */ 
  private void dealerRound() {
    while (game.dealer.getPoints() < 17) {
      game.dealerHitGUI();
      txt4.setText(String.valueOf(game.dealer.getPoints()));
      txt2.setText(String.valueOf(game.dealer.getPoints()));
    }
    if (game.dealer.hasBlackjack() == true)
      txt7.setText("Dealer Has Blackjack!");
    else if (game.dealer.getPoints() == 21)
      txt7.setText("Dealer Has 21!");
    if (game.dealer.getPoints() > 21)
      txt7.setText("Dealer Busts...You Win!");
    determineWinnerGUI();
  }

 /*
  * Determine the winners, GUI version
  * Compares hands to determine the winner and resets the wager to 0
  */
  private void determineWinnerGUI() {
    if (game.playerWon(game.dealer) == 0
        && !((game.playerBlackjack() & !game.dealer.hasBlackjack()) ||
             (!game.playerBlackjack() & game.dealer.hasBlackjack())))
      txt8.setText("Push!");
    if ((game.playerPoints() < 22 && game.playerWon(game.dealer) == 1)
        || game.dealer.getPoints() > 21) {
      txt8.setText("You Won!");
      game.playerWinnings(game.dealer);
    }
    else if ((game.dealer.getPoints() < 22 && game.playerWon(game.dealer) == -1)
        || game.playerPoints() > 21) {
      txt8.setText("Dealer Wins!");
      game.playerWinnings(game.dealer);
    }
    txt5.setText(String.valueOf(game.playerStake()));
    txt6.setText(String.valueOf(game.playerWinnings()));
    //game.playerResetBet();
    bet = 0;
  }
  
  /*
   * Event handler for the Start button
   * Starts game by calling game.newRoundGUI(bet)
   * Proceeds to dealerRound() if player has Blackjack
   * Prompts player for positive integer wager if necessary
   */
  class PlayHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
      txt5.setText(String.valueOf(game.playerStake()));
      txt6.setText(String.valueOf(game.playerWinnings()));
      txt8.setText("");
      bet = Integer.parseInt(field1.getText()); 
      if (bet > 0) {
        game.newRoundGUI(bet);
        txt4.setText(game.dealer.toString());
        txt2.setText(String.valueOf(game.dealer.getPoints()));
        txt3.setText(game.playerHand());
        txt1.setText(String.valueOf(game.playerPoints()));
        if (game.playerBlackjack()) {
          txt7.setText("Blackjack!");
          dealerRound();
        }
        else
          txt7.setText("Hit or Stand?");
      }
      else if (bet < 0)
        txt7.setText("Wager must be a positive integer!");
      else
        txt7.setText("Place a bet to start a game!");
    }
  }
/*
  public static void main(String[] args) throws BlackjackException {
    
    BlackjackCasinoGame game = new BlackjackCasinoGame(6, 26);
  } 
*/

}

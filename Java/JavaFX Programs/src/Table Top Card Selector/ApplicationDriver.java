package selectingcards;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import selectingcards.CardIdentity.Suit;
import javafx.scene.image.*;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 * Driver that tests GUICard class by giving the player and computer a hand of
 * cards, then playing them onto the field.
 */
public class ApplicationDriver extends Application {
   private final int NUM_CARDS_PER_HAND = 7;
   private final int NUM_PLAYERS = 2;
   private Image[] humanImages = new Image[NUM_CARDS_PER_HAND];
   private Button[] humanButtons = new Button[NUM_CARDS_PER_HAND];
   private ImageView[] humanViews = new ImageView[NUM_CARDS_PER_HAND];
   private Image[] computerImages = new Image[NUM_CARDS_PER_HAND];
   private ImageView[] computerViews = new ImageView[NUM_CARDS_PER_HAND];
   private Image[] playedImages = new Image[NUM_CARDS_PER_HAND];
   private ImageView[] playedViews = new ImageView[NUM_CARDS_PER_HAND];
   private Label[] playLabelText = new Label[NUM_PLAYERS];

   public static void main(String[] args) {
      launch(args);
   }

   public void start(Stage primaryStage) {
      // Create the scene and place it in the stage
      BorderPane pane = new BorderPane();
      Scene scene = new Scene(pane, 800, 600);
      primaryStage.setTitle("Card Table");
      primaryStage.setScene(scene);
      HBox humanPane = new HBox(15);
      HBox computerPane = new HBox(15);
      humanPane.setPadding(new Insets(15, 15, 15, 50)); // up, right, down, left
      computerPane.setPadding(new Insets(15, 15, 15, 100));
      FlowPane playedPane = new FlowPane(150, 15);
      playedPane.setPadding(new Insets(100, 200, 100, 200));
      playedPane.setAlignment(Pos.CENTER);
      playedPane.setStyle("-fx-border-color: blue");

      // CREATE IMAGES AND BUTTONS ------------------------------------------
      for (int cardIndex = 0; cardIndex < NUM_CARDS_PER_HAND; cardIndex++) {
         Card card = generateRandomCard();
         humanImages[cardIndex] = GUICard.getImage(card);
         humanViews[cardIndex] = new ImageView(humanImages[cardIndex]);
         humanButtons[cardIndex] = new Button();
         humanButtons[cardIndex].setGraphic(humanViews[cardIndex]);

         final Image hTemp = humanImages[cardIndex];
         humanButtons[cardIndex].setOnAction(e -> {
            final Image cTemp = GUICard.getImage(generateRandomCard());
            moveCard(cTemp, hTemp, playedPane);
         });

         computerImages[cardIndex] = GUICard.getBackCardImage();
         computerViews[cardIndex] = new ImageView(computerImages[cardIndex]);
      }

      // ADD BUTTONS AND IMAGEVIEWS TO PANES --------------------------------
      for (int cardIndex = 0; cardIndex < NUM_CARDS_PER_HAND; cardIndex++) {
         humanPane.getChildren().add(humanButtons[cardIndex]);
         computerPane.getChildren().add(computerViews[cardIndex]);
      }

      // Sets initial playing pane to Jokers
      moveCard(GUICard.getImage(new Card('X', Suit.CLUBS)),
            GUICard.getImage(new Card('X', Suit.DIAMONDS)), playedPane);

      pane.setTop(computerPane);
      pane.setCenter(playedPane);
      pane.setBottom(humanPane);
      primaryStage.show(); // show everything to the user
   }

   public Card generateRandomCard() {
      char randomValue = GUICard
            .turnIntIntoCardValueChar((int) (Math.random() * 13));
      Card.Suit randomSuit = GUICard.turnIntIntoSuit((int) (Math.random() * 4));
      return new Card(randomValue, randomSuit);
   }

   private void moveCard(Image cTemp, Image hTemp, Pane pPane) {
      pPane.getChildren().clear();
      playedImages[0] = cTemp;
      playedImages[1] = hTemp;
      playedViews[0] = new ImageView(playedImages[0]);
      playedViews[1] = new ImageView(playedImages[1]);
      playLabelText[0] = new Label("Computer      "); // spacing to align text
      playLabelText[1] = new Label("      You     "); // to card
      pPane.getChildren().addAll(playedViews[0], playedViews[1],
            playLabelText[0], playLabelText[1]);
   }
}
package pointcardgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import pointcardgame.CardIdentity.Suit;
import javafx.scene.image.*;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.EmptyStackException;

/**
 * Driver that launched the 24-Point Card Game application. Uses the the
 * EvaluateExpression class algorithm to implement game mechanics.
 */
public class ApplicationDriver extends Application {
   private final int NUM_CARDS_PER_GAME = 4;
   private Image[] cardImages = new Image[NUM_CARDS_PER_GAME];
   private ImageView[] cardViews = new ImageView[NUM_CARDS_PER_GAME];
   private Image[] tempImages = new Image[NUM_CARDS_PER_GAME];
   private ImageView[] tempViews = new ImageView[NUM_CARDS_PER_GAME];
   private List<Card> currentCards = new ArrayList<>();

   public static void main(String[] args) {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) {
      // Create the scene and place it in the stage
      BorderPane pane = new BorderPane();
      Scene scene = new Scene(pane, 375, 215);
      primaryStage.setTitle("24 Point Card Game");
      primaryStage.setScene(scene);

      HBox shufflePane = new HBox(15);
      shufflePane.setAlignment(Pos.CENTER_RIGHT);
      shufflePane.setPadding(new Insets(15, 20, 15, 0)); // up, right, down, left
      HBox cardsPane = new HBox(15);
      cardsPane.setPadding(new Insets(0, 20, 0, 20));
      cardsPane.setAlignment(Pos.CENTER);
      HBox expressionInputPane = new HBox(15);
      expressionInputPane.setAlignment(Pos.CENTER_RIGHT);
      expressionInputPane.setPadding(new Insets(15, 20, 15, 15));

      // CREATE SHUFFLE PANE ------------------------------------------------
      Label answerText = new Label();
      answerText.setPrefWidth(194);
      answerText.setWrapText(true);
      Button shuffleBtn = new Button("Shuffle");

      shuffleBtn.setOnAction(event -> {
         currentCards.clear();
         for (int cardIndex = 0; cardIndex < NUM_CARDS_PER_GAME; cardIndex++) {
            Card randomCard;
            do {
               randomCard = generateRandomCard();
               System.out.println(randomCard.toString());
            } while (containsCard(currentCards, randomCard));

            currentCards.add(randomCard);
            cardImages[cardIndex] = GUICard.getImage(randomCard);
            cardViews[cardIndex] = new ImageView(cardImages[cardIndex]);
         }
         System.out.println();
         cardsPane.getChildren().clear();
         cardsPane.getChildren().addAll(cardViews);
      });

      shufflePane.getChildren().addAll(answerText, shuffleBtn);

      // CREATE CARDS PANE --------------------------------------------------
      Suit[] suits = Suit.values();
      for (int cardIndex = 0; cardIndex < NUM_CARDS_PER_GAME; cardIndex++) {
         Suit currentSuit = suits[cardIndex % suits.length];
         Card jokerCard = new Card('X', currentSuit);
         tempImages[cardIndex] = GUICard.getImage(jokerCard);
         tempViews[cardIndex] = new ImageView(tempImages[cardIndex]);
         cardsPane.getChildren().add(tempViews[cardIndex]);
      }

      // CREATE EXPRESSIONS PANE --------------------------------------------
      Label expressionText = new Label("Enter an Expression:");
      TextField textField = new TextField();
      Button verifyBtn = new Button("Verify");

      verifyBtn.setOnAction(event -> {
         String expression = textField.getText();
         String message;
         Double evaluatedExpression = 0.0;
         try {
            evaluatedExpression = EvaluateExpression
                  .evaluateExpression(expression);
         } catch (EmptyStackException e) {
            System.out.println("Empty Stack Detected");
         } catch (NumberFormatException e) {
            System.out.println("Letter in Expression Detected");
         }

         if (!expressionContainsCards(expression, currentCards)) {
            message = "The numbers in the expression don't match the numbers in "
                  + "the set";
         } else if (evaluatedExpression == 24) {
            message = "Correct!";
         } else {
            message = "Incorrect Result";
         }
         answerText.setText(message);
      });

      expressionInputPane.getChildren().addAll(expressionText, textField,
            verifyBtn);

      pane.setTop(shufflePane);
      pane.setCenter(cardsPane);
      pane.setBottom(expressionInputPane);
      primaryStage.show();
   }

   public Card generateRandomCard() {
      char randomValue = GUICard
            .turnIntIntoCardValueChar((int) (Math.random() * 13));
      Card.Suit randomSuit = GUICard.turnIntIntoSuit((int) (Math.random() * 4));
      return new Card(randomValue, randomSuit);
   }

   private boolean containsCard(List<Card> cardList, Card card) {
      for (Card existingCard : cardList) {
         if (existingCard.getSuit() == card.getSuit()
               && existingCard.getValue() == card.getValue()) {
            return true;
         }
      }
      return false;
   }

   private boolean expressionContainsCards(String expression, List<Card> cards) {
      List<Double> expressionNumbers = new ArrayList<>();
      String number = "";
      for (int i = 0; i < expression.length(); i++) {
         char c = expression.charAt(i);
         if (Character.isDigit(c)) {
            number += c;
         } else if (!number.isEmpty()) {
            expressionNumbers.add(Double.parseDouble(number));
            number = "";
         }
      }
      // Add the last number if it exists
      if (!number.isEmpty()) {
         expressionNumbers.add(Double.parseDouble(number));
      }

      List<Double> cardValues = cards.stream()
            .map(card -> cardValueToDouble(card.getValue()))
            .collect(Collectors.toList());

      System.out.println("Before sort:");
      System.out.println("Expression Numbers: " + expressionNumbers);
      System.out.println("Card Values: " + cardValues);

      Collections.sort(expressionNumbers);
      Collections.sort(cardValues);

      System.out.println("After sort:");
      System.out.println("Expression Numbers: " + expressionNumbers);
      System.out.println("Card Values: " + cardValues + "\n");

      return expressionNumbers.equals(cardValues);
   }

   public double cardValueToDouble(char cardValue) {
      if (Character.isDigit(cardValue)) {
         return Character.getNumericValue(cardValue);
      } else if (cardValue == 'A') {
         return 1;
      } else if (cardValue == 'T') {
         return 10;
      } else if (cardValue == 'J') {
         return 11;
      } else if (cardValue == 'Q') {
         return 12;
      } else if (cardValue == 'K') {
         return 13;
      } else {
         return -1;
      }
   }

}
package selectingcards;

import javafx.scene.layout.*;
import javafx.scene.image.*;

/**
 * GUICard class is responsible for loading card images and providing
 * methods to convert card values and suits to their corresponding images.
 */
public class GUICard {
   private static Pane pane = new FlowPane();
   private static Image[][] imageCards = new Image[14][4]; // 14th = Joker
   private static ImageView[][] imageCardViews = new ImageView[14][4];
   private static Image imageBack;
   private static ImageView imageCardBack;
   private static boolean imagesLoaded = false;

   private static String cardlValsConvertAssist = "23456789TJQKAX";
   private static String suitValsConvertAssist = "CDHS";
   private static Card.Suit suitConvertAssist[] = { Card.Suit.SPADES,
         Card.Suit.HEARTS, Card.Suit.CLUBS, Card.Suit.DIAMONDS };

   /* === Mutators & Accessors === */
   static public Image getImage(Card card) {
      loadCardImages(); // will not load twice, so no worries.
      return imageCards[valueAsInt(card)][suitAsInt(card)];
   }

   static public Image getBackCardImage() {
      return imageBack;
   }

   /* === Main Method === */
   static void loadCardImages() {
      if (!imagesLoaded) {
         String imageFileName;
         int intSuit, intVal;
         for (intSuit = 0; intSuit < 4; intSuit++) {
            for (intVal = 0; intVal < 14; intVal++) {
               imageFileName = "file:images/" + turnIntIntoCardValueChar(intVal)
                     + turnIntIntoCardSuitChar(intSuit) + ".gif";
               imageCards[intVal][intSuit] = new Image(imageFileName);
               imageCardViews[intVal][intSuit] = new ImageView(
                     imageCards[intVal][intSuit]);
               pane.getChildren().add(imageCardViews[intVal][intSuit]);
            }
         }
         imageBack = new Image("file:images/BK.gif");
         imageCardBack = new ImageView(imageBack);
         pane.getChildren().add(imageCardBack);
         imagesLoaded = true;
      }
   }

   /* === Support Methods === */
   static char turnIntIntoCardValueChar(int valueIndex) {
      if (valueIndex < 0 || valueIndex > 13) {
         return '?';
      }
      return cardlValsConvertAssist.charAt(valueIndex);
   }

   static char turnIntIntoCardSuitChar(int suitIndex) {
      if (suitIndex < 0 || suitIndex > 3) {
         return '?';
      }
      return suitValsConvertAssist.charAt(suitIndex);
   }

   static Card.Suit turnIntIntoSuit(int suitIndex) {
      if (suitIndex < 0 || suitIndex > 3) {
         return null;
      }
      return suitConvertAssist[suitIndex];
   }

   static int valueAsInt(Card card) {
      return cardlValsConvertAssist.indexOf(card.getValue());
   }

   static int suitAsInt(Card card) {
      return suitValsConvertAssist.indexOf(card.getSuit().toString().charAt(0));
   }
}
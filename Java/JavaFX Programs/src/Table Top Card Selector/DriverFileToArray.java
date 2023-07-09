package selectingcards;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;

/**
 * Driver tests loading cards from file to array
 */
public class DriverFileToArray extends Application {
   // Variables must be static to allow us to display all cards using labels
   static final int NUM_CARD_IMAGES = 57; // 52 + 4 jokers + 1 cardback image
   static Pane pane = new FlowPane();
   static Image[] image = new Image[NUM_CARD_IMAGES];
   static ImageView[] views = new ImageView[NUM_CARD_IMAGES];

   // For assisting with conversions:
   static String cardlValsConvertAssist = "23456789TJQKAX";
   static String suitValsConvertAssist = "CDHS";

   public static void main(String[] args) {
      launch(args);
   }

   /* === Methods === */
   public void start(Stage primaryStage) {
      loadCardImages(); // prepare the image array
      Scene scene = new Scene(pane, 800, 600); // create scene, place in stage
      primaryStage.setTitle("Card Room");
      primaryStage.setScene(scene);
      primaryStage.show(); // show everything to the user
   }

   static void loadCardImages() {
      String imageFileName;
      int intSuit, intVal;
      int imageIndex = 0;
      // Card image files named like "AS.gif", "3H.gif", "XD.gif", etc.
      for (intSuit = 0; intSuit < 4; intSuit++) {
         for (intVal = 0; intVal < 14; intVal++) {
            imageFileName = "File:images/" + turnIntIntoCardValueChar(intVal)
                  + turnIntIntoCardSuitChar(intSuit) + ".gif";
            image[imageIndex] = new Image(imageFileName);
            views[imageIndex] = new ImageView(image[imageIndex]);
            pane.getChildren().add(views[imageIndex]);
            imageIndex++;
         }
      }
      imageFileName = "File:images/BK.gif";
      image[image.length - 1] = new Image(imageFileName);
      views[image.length - 1] = new ImageView(image[image.length - 1]);
      pane.getChildren().add(views[image.length - 1]);
   }

   // Turns 0 - 13 into 'A', '2', '3', ... 'Q', 'K', 'X'
   static char turnIntIntoCardValueChar(int k) {
      if (k < 0 || k > 13)
         return '?';
      return cardlValsConvertAssist.charAt(k);
   }

   // Turns 0 - 3 into 'C', 'D', 'H', 'S'
   static char turnIntIntoCardSuitChar(int k) {
      if (k < 0 || k > 3)
         return '?';
      return suitValsConvertAssist.charAt(k);
   }
}
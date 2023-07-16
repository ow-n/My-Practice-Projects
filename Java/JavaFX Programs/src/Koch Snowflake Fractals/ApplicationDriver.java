package fractal;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/*
 * Program that displays the Koch Snowflake fractals
 */
public class ApplicationDriver extends Application {
   @Override
   public void start(Stage primaryStage) {
      KochSnowFlakePane trianglePane = new KochSnowFlakePane();
      TextField tfOrder = new TextField();
      tfOrder.setOnAction(
            e -> trianglePane.setOrder(Integer.parseInt(tfOrder.getText())));
      tfOrder.setPrefColumnCount(4);
      tfOrder.setAlignment(Pos.BOTTOM_RIGHT);

      // Pane to hold label, text field, and a button
      HBox inputPane = new HBox(10);
      inputPane.getChildren().addAll(new Label("Enter an order: "), tfOrder);
      inputPane.setAlignment(Pos.CENTER);

      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(trianglePane);
      borderPane.setBottom(inputPane);

      // Create a scene and place it in the stage
      Scene scene = new Scene(borderPane, 250, 250);
      primaryStage.setTitle("Koch Snowflake Fractal");
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   /**
    * Embedded class for displaying fractals
    */
   static class KochSnowFlakePane extends Pane {
      private int order;

      public KochSnowFlakePane() {
         this.order = 0;
      }

   public static void main(String[] args) {
      launch(args);
   }
}
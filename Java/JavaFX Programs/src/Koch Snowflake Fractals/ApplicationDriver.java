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

      public boolean setOrder(int order) {
         if (order >= 0 && order < 9) { // increase max order at own risk
            this.order = order;
            paint();
            return true;
         } else {
            return false;
         }
      }

      protected void paint() {
         Point2D p1 = new Point2D(getWidth() / 2, 0);
         Point2D p2 = new Point2D(30, getHeight() - 60);
         Point2D p3 = new Point2D(getWidth() - 30, getHeight() - 60);

         this.getChildren().clear(); // Clear the pane before redisplay

         displayKochSnowFlake(order, p1, p2);
         displayKochSnowFlake(order, p2, p3);
         displayKochSnowFlake(order, p3, p1);
      }

      private void displayKochSnowFlake(int order, Point2D p1, Point2D p2) {
         if (order == 0) {
            Line line = new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            this.getChildren().add(line);
         } else {
            double deltaX = p2.getX() - p1.getX();
            double deltaY = p2.getY() - p1.getY();

            Point2D x = new Point2D(p1.getX() + deltaX / 3,
                  p1.getY() + deltaY / 3);
            Point2D y = new Point2D(p1.getX() + deltaX * 2 / 3,
                  p1.getY() + deltaY * 2 / 3);
            Point2D z = new Point2D(
                  (p1.getX() + p2.getX()) / 2 + Math.cos(Math.toRadians(30))
                        * (p1.getY() - p2.getY()) / 3,
                  (p1.getY() + p2.getY()) / 2 + Math.cos(Math.toRadians(30))
                        * (p2.getX() - p1.getX()) / 3);

            // Recursively display snow flakes on lines
            displayKochSnowFlake(order - 1, p1, x);
            displayKochSnowFlake(order - 1, x, z);
            displayKochSnowFlake(order - 1, z, y);
            displayKochSnowFlake(order - 1, y, p2);
         }
      }
   }

   public static void main(String[] args) {
      launch(args);
   }
}
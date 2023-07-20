module GUICards {
	requires javafx.controls;
	requires javafx.graphics;
	
	opens selectingcards to javafx.graphics, javafx.fxml;
	opens fractal to javafx.graphics;
   opens pointcardgame;
}

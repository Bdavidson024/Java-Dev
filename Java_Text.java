package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Java_Text extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox pane = new HBox();
		pane.setAlignment(Pos.CENTER);
		
		Font font = Font.font("Times New Roman", FontWeight.BOLD,FontPosture.ITALIC,22);
		
		for (int i =0;i<5;i++) {
			Text txt = new Text("Java");
			txt.setRotate(90);
			txt.setFont(font);
			txt.setFill(new Color(Math.random(),Math.random(),Math.random(),Math.random()));
			pane.getChildren().add(txt);
		}
		
		Scene scene = new Scene(pane, 200, 100);
		primaryStage.setTitle("Chapter 14 Lab 1");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}


}

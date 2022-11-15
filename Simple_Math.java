/*
 *Author: Brad Davidson
 *Date: 10/07/2022
 *Purpose: Program will take user input of 2 Numbers and will calculate a result of either add,multiply,subtract, or divide the 2 numbers and display the result
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Simple_Math extends Application {
	
	public void start(Stage primaryStage) {
		HBox hbox = new HBox();
		FlowPane pane = new FlowPane(hbox);
		pane.setHgap(2);
		pane.setAlignment(Pos.CENTER);

		TextField tfNum1 = new TextField();
		TextField tfNum2 = new TextField();
		TextField tfResult = new TextField();
		Button btAdd = new Button("Add");
		Button btSub = new Button("Subtract");
		Button btMult = new Button("Multiply");
		Button btDiv = new Button("Divide");
		
		tfNum1.setPrefColumnCount(3);
		tfNum2.setPrefColumnCount(3);
		tfResult.setPrefColumnCount(10);
		pane.getChildren().addAll(new Label("Number 1: "), tfNum1,new Label("Number 2: "), tfNum2,new Label("Result: "), tfResult);
		
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(btAdd,btSub,btMult,btDiv);
		
		BorderPane bPane = new BorderPane();
		bPane.setCenter(pane);
		bPane.setBottom(hbox);
		
		Scene scene = new Scene(bPane,400,50);
		primaryStage.setTitle("Chapter 15 Lab 1");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		btAdd.setOnAction(e -> {
			tfResult.setText(Double.parseDouble(tfNum1.getText()) + Double.parseDouble(tfNum2.getText()) + " ");
		});
		
		btSub.setOnAction(e -> {
			tfResult.setText(Double.parseDouble(tfNum1.getText()) - Double.parseDouble(tfNum2.getText()) + " ");
		});
		
		btMult.setOnAction(e -> {
			tfResult.setText(Double.parseDouble(tfNum1.getText()) * Double.parseDouble(tfNum2.getText()) + " ");
		});
		
		btDiv.setOnAction(e -> {
			tfResult.setText(Double.parseDouble(tfNum1.getText()) / Double.parseDouble(tfNum2.getText()) + " ");
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}
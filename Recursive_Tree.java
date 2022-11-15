/*
 * Author: Brad Davidson
 * Date: 11/15/2022
 * Purpose: Program will take user input for the recursion order and draw the appropriate recursive tree
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Recursive_Tree extends Application {
	
	public void start(Stage primaryStage) {
		
		HBox hbox = new HBox(10);
		TextField tfOrder = new TextField();
		tfOrder.setPrefColumnCount(4);
		tfOrder.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().addAll(new Label("Enter an order: "), tfOrder);
		hbox.setAlignment(Pos.CENTER);
		
		TreePane pane = new TreePane();
		tfOrder.setOnAction(e -> {
			pane.setDepth(Integer.parseInt(tfOrder.getText()));
		});
		
		BorderPane bpane = new BorderPane();
		bpane.setBottom(hbox);
		bpane.setCenter(pane);
		
		Scene scene = new Scene(bpane, 200, 210);
		primaryStage.setTitle("Recursive Tree");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	static class TreePane extends Pane {
		
		private int depth = 0;
		private double angleFactor = Math.PI / 5;
		private double sizeFactor = 0.58;
		
		public void setDepth(int depth) {
			this.depth = depth;
			paint();
		}
		
		public void paint() {
			getChildren().clear();
			paintBranch(depth, getWidth() / 2, getHeight() - 150, getHeight() / 3, Math.PI / 2);
		}
		
		public void paintBranch(int depth, double x1, double y1, double length, double angle) {
			
			if (depth >= 0) {
			
				double x2 = x1 + Math.cos(angle) * length;
				double y2 = y1 + Math.sin(angle) * length;
				
				getChildren().add(new Line(x1, y1, x2, y2));
				
				paintBranch(depth - 1, x2, y2, length * sizeFactor, angle + angleFactor);
				
				paintBranch(depth - 1, x2, y2, length * sizeFactor, angle - angleFactor);
			}
		}
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}

}

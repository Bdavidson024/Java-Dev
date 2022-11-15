/*
 * Author: Brad Davidson
 * Date: 11/1/2022
 * Purpose: Program will take user input via +/- buttons, left/right mouse click, and UP/DOWN arrow keys to increase and decrease the Sierpinski Triangle order
 */

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Sierpinski_Triangle extends Application {
	
	private int order = -1;
	
	public void start(Stage primaryStage) {
		
		SierpinskiTrianglePane trianglePane = new SierpinskiTrianglePane();
		Button btDown = new Button("-");
		Button btUp = new Button("+");
		HBox hbox = new HBox(5);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(btDown, btUp);
		
		//set action for buttons to increase/ decrease order
		btUp.setOnAction(e -> trianglePane.setOrder(trianglePane.getOrder() + 1));
		btDown.setOnAction(e -> trianglePane.setOrder(trianglePane.getOrder() - 1));
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(trianglePane);
		borderPane.setBottom(hbox);
		
		//create the scene and place in the stage
		Scene scene = new Scene(borderPane, 400,310);
		primaryStage.setTitle("Sierpinski Triangles");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		trianglePane.widthProperty().addListener(ov -> trianglePane.paint());
		trianglePane.heightProperty().addListener(ov -> trianglePane.paint());
		
		//set action to increase order on primary click and decrease on secondary click
		scene.setOnMouseClicked(e -> {
			if(e.getButton() == MouseButton.PRIMARY) {
				trianglePane.setOrder(trianglePane.getOrder() + 1);
			}
			else if(e.getButton() == MouseButton.SECONDARY) {
				trianglePane.setOrder(trianglePane.getOrder() - 1);
			}
		});
		
		//set action to increase order if UP arrow is pressed and decrease if 	DOWN arrow is pressed 
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.UP) {
				trianglePane.setOrder(trianglePane.getOrder() + 1);
			}
			else if(e.getCode() == KeyCode.DOWN) {
				trianglePane.setOrder(trianglePane.getOrder() - 1);
			}
		});
	}
	
	//class for pane for displaying the triangles
	static class SierpinskiTrianglePane extends Pane {
		
		private int order = 0;
		
		public int getOrder() {
			return order;
		}
		
		public void setOrder(int order) {
			if (order < 0)
				order = 0;
			this.order = order;
			
			paint();
		}
		
		SierpinskiTrianglePane() {}
		
		protected void paint() {
			
			//select three points in proportion to the panel size
			Point2D p1 = new Point2D(getWidth() / 2, 10);
			Point2D p2 = new Point2D(10, getHeight() - 10);
			Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);
			
			this.getChildren().clear();//clears the pane for display
			
			displayTriangles(order, p1, p2, p3);
		}
		
		private void displayTriangles(int order, Point2D p1, Point2D p2, Point2D p3) {
			
			if(order < 0)
				return;
			
			if(order == 0) {
				//draw a triangle to connect 3 points
				Polygon triangle = new Polygon();
				triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
				triangle.setStroke(Color.BLACK);
				triangle.setFill(Color.WHITE);
				this.getChildren().add(triangle);
			}
			else {
				//get the midpoint on each edge in the triangle
				Point2D p12 = p1.midpoint(p2);
				Point2D p23 = p2.midpoint(p3);
				Point2D p31 = p3.midpoint(p1);
				
				//recursively display 3 triangles
				displayTriangles(order - 1, p1, p12, p31);
				displayTriangles(order - 1, p12, p2, p23);
				displayTriangles(order - 1, p31, p23, p3);
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

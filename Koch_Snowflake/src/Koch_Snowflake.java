/*
 * Author: Brad Davidson
 * Date: 11/24/2022
 * Purpose: program will take user input for the order of recursion and then draw the koch snowflake accordingly
 */
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class Koch_Snowflake extends Application {
	
	TextField tfOrder = new TextField();
	
	public void start(Stage primaryStage) {
		
		BorderPane bpane = new BorderPane();
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll( new Label("Enter order here: "), tfOrder);
		hbox.setAlignment(Pos.CENTER);
		bpane.setBottom(hbox);

		
		SnowflakePane pane = new SnowflakePane();
		tfOrder.setOnAction(e -> {
			pane.setOrder(Integer.parseInt(tfOrder.getText()));
		});
		bpane.setCenter(pane);
		
		Scene scene = new Scene(bpane,200,210);
		primaryStage.setTitle("Koch Triangle");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	    scene.widthProperty().addListener(ov -> pane.paint());
	    scene.heightProperty().addListener(ov -> pane.paint());
		
	}
	
	static class SnowflakePane extends Pane {
		
		private int order = 0;
		
		public void setOrder(int order) {
			this.order = order;
			paint();
		}
		
		SnowflakePane(){}
		
		protected void paint() {
			
			double side = Math.min(getWidth(), getHeight()) * 0.8;
			double triangleHeight = side * Math.sin(Math.toRadians(60));

			Point2D p1 = new Point2D(getWidth() / 2, 10);
			Point2D p2 = new Point2D(10, getHeight() - 10);
			Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);
			
			this.getChildren().clear();
			
			displaySnowflake(order,p1,p2);
			displaySnowflake(order,p2,p3);
			displaySnowflake(order,p3,p1);
			
		}
		
		private void displaySnowflake(int order, Point2D p1, Point2D p2) {
			
			if (order == 0)
				this.getChildren().add(new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
			
			else { //order > 0
				//get the points for new segments
				double x2 = p2.getX() - p1.getX();
				double y2 = p2.getY() - p1.getY();
				
				Point2D x = new Point2D(p1.getX() + x2/3, p1.getY() + y2/3);
				Point2D y = new Point2D(p1.getX() + x2 * 2/3, p1.getY() + y2 * 2/3);
				Point2D z = new Point2D(
						(p1.getX() + p2.getX()) / 2 + Math.cos(Math.toRadians(30)) * (p1.getY() - p2.getY()) / 3,
				        (p1.getY() + p2.getY()) / 2 + Math.cos(Math.toRadians(30)) * (p2.getX() - p1.getX()) / 3);
				
				displaySnowflake(order - 1, p1, x);
				displaySnowflake(order - 1, x, z);
				displaySnowflake(order - 1, z, y);
				displaySnowflake(order - 1, y, p2);
				
			}
		}

	public static void main(String[] args) {
		launch(args);
	}
}
}
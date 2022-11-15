/*
 * Author: Brad Davidson
 * Date: 09/30/2022
 * Purpose: Programming Assignment # 25- Program should choose 5 points on a circle and connect those points with lines, creating a polygon
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.Pane;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import java.util.ArrayList;


public class Polygon_from_Circle_Points extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		//pane.setPadding(10,10,10,10);
		
		Circle circle = new Circle(70,70,50);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		pane.getChildren().add(circle);
		
		Polygon polygon = new Polygon();
		pane.getChildren().add(polygon);
		polygon.setFill(Color.WHITE);
		polygon.setStroke(Color.BLACK);
		ObservableList<Double>list = polygon.getPoints();
		
		ArrayList<Double>angles = new ArrayList<>();
		
		for(int i=0;angles.size()<5;i++) {
			double angle = (Math.random() * (2*Math.PI));
			if(!angles.contains(angle)) {
				angles.add(angle);
			}
		}
		
		java.util.Collections.sort(angles);
		
		for(int i=0;i<angles.size();i++) {
			list.add(circle.getCenterX() + circle.getRadius()*Math.cos(angles.get(i)));
			list.add(circle.getCenterY() + circle.getRadius()*Math.sin(angles.get(i)));
		}
		
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Chapter 14 Programming Assignment 1");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

}

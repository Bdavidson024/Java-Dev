/*
 * Author: Bradley Davidson
 * Date: 10/07/2022
 * Purpose: Program runs animation of a fan. Has 3 buttons to resume fan, stop fan, and reverse the direction of the fan.
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Fan_Animation extends Application {
	
	public void start(Stage primaryStage) {
		
		HBox hbox = new HBox(10);
		Button btStart = new Button("Resume");
		Button btPause = new Button("Pause");
		Button btReverse = new Button("Reverse");
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(btStart, btPause, btReverse);
		
		BorderPane pane = new BorderPane();
		pane.setBottom(hbox);
		
		FanPane fan = new FanPane();
		pane.setCenter(fan);
		
		Timeline animation = new Timeline(
				new KeyFrame(Duration.millis(100), e -> fan.move()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
		
		btPause.setOnAction(e -> animation.pause());
		btStart.setOnAction(e -> animation.play());
		btReverse.setOnAction(e -> fan.reverse());
		
		Scene scene = new Scene(pane,250,250);
		primaryStage.setTitle("Fan Animation");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		scene.widthProperty().addListener(e -> fan.setW(scene.getWidth()));
		scene.heightProperty().addListener(e -> fan.setH(scene.getHeight()));
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}

class FanPane extends Pane {
	private double w = 200;
	private double h = 200;
	private double radius = Math.min(w,h)*0.45;
	
	private Arc arc[] = new Arc[4];
	private double startAngle = 30;
	private Circle circle = new Circle(w/2,h/2,radius);
	
	public FanPane() {
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.WHITE);
		getChildren().add(circle);
		
		for(int i =0;i < 4; i++) {
			arc[i] = new Arc(w/2,h/2,radius*0.9,radius*0.9,startAngle+i*90, 35);
			arc[i].setFill(Color.BLUE);
			arc[i].setType(ArcType.ROUND);
			getChildren().addAll(arc[i]);
		}
	}
	
	public void setValues() {
		radius = Math.min(w, h) * 0.45;
		circle.setRadius(radius);
		circle.setCenterX(w/2);
		circle.setCenterY(h/2);
		
		for(int i = 0; i < 4; i++) {
			arc[i].setRadiusX(radius * 0.9);
			arc[i].setRadiusY(radius * 0.9);
			arc[i].setCenterX(w/2);
			arc[i].setCenterY(h/2);
			arc[i].setStartAngle(startAngle + i*90);
		}
	}
	
	private double increment = 5;
	
	public void move() {
		setStartAngle(startAngle + increment);
	}
	
	public void reverse() {
		increment = -increment;
	}
	
	public void setStartAngle(double angle) {
		this.startAngle = angle;
		setValues();
		
	}
	public void setW(double w) {
		this.w = w;
		setValues();
	}
	
	public void setH(double h) {
		this.h = h;
		setValues();
	}
	
	
}

/*
 * Author: Brad Davidson
 * Date: 10/07/2022
 * Purpose: Program will create a pendulum that swings. Pressing the up arrow will increase pendulum swing speed, down will decrease, S will stop pendulum, and R will resume swing
 */

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pendulum_Swings extends Application {
	
	public void start(Stage primaryStage) {
		
		BorderPane pane = new BorderPane();
		VBox vbox = new VBox();
		pane.getChildren().add(vbox);
		
		Pendulum pendulum = new Pendulum(500, 300);
		pane.setCenter(pendulum);	
		
		Scene scene = new Scene(pane,500,500);
		primaryStage.setTitle("Chapter 15 Programming Assignemnt 2");
		primaryStage.setScene(scene);
		pendulum.resume();
		primaryStage.show();
		
		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case UP: pendulum.increase(); break;
			case DOWN: pendulum.decrease(); break;
			case R: pendulum.resume(); break;
			case S: pendulum.stop(); break;
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}

class Pendulum extends Pane {
	private double w=300;
	private double h;
	
	PathTransition pt;
	Line line;
	Arc arc;
	
	public Pendulum(double width,double height) {
		w = width;
		h = height;

		arc = new Arc(w/2,h*.8,w*.15,w*.15,180,180);
		Circle topCircle = new Circle(arc.getCenterX()-arc.getRadiusX(),arc.getCenterY(),10);
		Circle bottomCircle = new Circle(arc.getCenterX(),arc.getCenterY()-h/2,20);
		arc = new Arc(topCircle.getCenterX(),topCircle.getCenterY(),w/2,h/2,240,60);
		arc.setFill(Color.TRANSPARENT);
		line = new Line(topCircle.getCenterX(),topCircle.getCenterY(),bottomCircle.getCenterX(),bottomCircle.getCenterY());
		pt = new PathTransition();
		pt.setDuration(Duration.seconds(2.0));
		pt.setPath(arc);
		pt.setNode(bottomCircle);
		pt.setOrientation(PathTransition.OrientationType.NONE);
		pt.setCycleCount(PathTransition.INDEFINITE);
		pt.setAutoReverse(true);
		topCircle.setStroke(Color.BLACK);
		topCircle.setFill(Color.BLACK);
		bottomCircle.setStroke(Color.BLACK);
		bottomCircle.setFill(Color.BLACK);
		line.setStroke(Color.BLACK);
		line.endXProperty().bind(bottomCircle.translateXProperty().add(bottomCircle.getCenterX()));
		line.endYProperty().bind(bottomCircle.translateYProperty().add(bottomCircle.getCenterY()));

		getChildren().add(line);
		getChildren().add(bottomCircle);
		getChildren().add(topCircle);
		getChildren().add(arc);
		
	}
	
	public void increase() {
		pt.setRate(pt.getRate()+1);
	}
	
	public void decrease() {
		pt.setRate(pt.getRate()-1);
	}
	
	public void resume() {
		pt.play();
	}
	
	public void stop() {
		pt.stop();
	}
}

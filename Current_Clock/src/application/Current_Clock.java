package application;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Current_Clock extends Application {
	
	public void start(Stage primaryStage) {
				ClockPane clock = new ClockPane();
				HBox hbox = new HBox(5);
				Button btnStop = new Button("Stop");
				Button btnStart = new Button("Start");
				hbox.getChildren().addAll(btnStop, btnStart);
				hbox.setAlignment(Pos.CENTER);
				BorderPane bpane = new BorderPane();
				bpane.setCenter(clock);
				bpane.setBottom(hbox);
				
				Scene scene = new Scene(bpane,250, 300);
				primaryStage.setTitle("Chapter 32 Lab 1");
				primaryStage.setScene(scene);
				primaryStage.show();
				
				btnStart.setOnAction(e -> clock.play());
				btnStop.setOnAction(e -> clock.pause());
				clock.widthProperty().addListener(ov -> {
					clock.setW(bpane.getWidth());
				});
				clock.heightProperty().addListener(ov -> {
					clock.setH(bpane.getHeight());
				});
	}

	public static void main(String[] args) {
		launch(args);
	}
class ClockPane extends Pane {
	private int hour;
	private int minute;
	private int second;
	
	//clock panes width and height
	private double w = 250, h = 250;
	private int sleepTime = 50;
	
	private Thread thread = new Thread(() -> {
		try {
			while (true) {
				Platform.runLater(() -> setCurrentTime());
				Thread.sleep(sleepTime);
			}
		}
		catch (InterruptedException ex) { }
	});
	
	public void pause() {
		thread.suspend();
	}
	
	public void play() {
		thread.resume();
	}
	
	//Construct a default clock with the current time
	public ClockPane() {
		setCurrentTime();
		thread.start();
	}
	
	//Construct a clock with a specified hour, minute and second
	public ClockPane(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		paintClock();
		thread.start();
	}
	
	//getters and setters
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
		paintClock();
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
		paintClock();
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
		paintClock();
	}
	
	public double getW() {
		return w;
	}
	
	 public void setW(double w) {
		 this.w = w;
		 paintClock();
	 }
	 
	public double getH() {
		return h;
	}
	
	public void setH(double h) {
		this.h = h;
		paintClock();
	}
	
	//set the current time for the clock
	public void setCurrentTime() {
		//construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar();
		//set current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
		paintClock();//repaint the clock
	}
	
	//method to paint the clock
	private void paintClock() {
		//intialize clock parameters
		double clockRadius = Math.min(w, h) * 0.8 * 0.5;
		double centerX = w/2;
		double centerY = h/2;
		
		//draw the circle
		Circle circle = new Circle(centerX, centerY, clockRadius);
		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		Text t1 = new Text(centerX - 5, centerY - clockRadius + 12, "12");
		Text t2 = new Text(centerX - clockRadius + 3, centerY + 5, "9");
		Text t3 = new Text(centerX + clockRadius - 10, centerY + 3, "3");
		Text t4 = new Text(centerX - 3, centerY + clockRadius - 3 , "6");
		
		//draw the second hand
		double sLength = clockRadius * 0.8;
		double secondX = centerX + sLength * Math.sin(second * (2 * Math.PI/60));
		double secondY = centerY - sLength * Math.cos(second * (2 * Math.PI/60));
		Line sLine = new Line(centerX, centerY, secondX, secondY);
		sLine.setStroke(Color.RED);
		
		//draw the minute hand
		double mLength = clockRadius * 0.65;
		double minuteX = centerX + mLength * Math.sin(minute * (2 * Math.PI/60));
		double minuteY = centerY - mLength * Math.cos(minute * (2 * Math.PI/60));
		Line mLine = new Line(centerX, centerY, minuteX, minuteY);
		mLine.setStroke(Color.BLUE);
		
		//draw the hour hand
		double hLength = clockRadius * 0.5;
		double hourX = centerX + hLength * Math.sin((hour % 12 + minute/60.0) * (2 * Math.PI/12));
		double hourY = centerY - hLength * Math.cos((hour % 12 + minute/60.0) * (2 * Math.PI/12));
		Line hLine = new Line(centerX, centerY, hourX, hourY);
		mLine.setStroke(Color.GREEN);
		
		getChildren().clear();
		getChildren().addAll(circle, t1, t2, t3, t4, sLine, mLine, hLine);
	}
}
}

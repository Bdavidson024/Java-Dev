package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Audio_Countdown extends Application {
	
	//declare object variables
	private MenuBar menuBar = new MenuBar();
	Media audioClip = new Media("https://liveexample.pearsoncmg.com/common/audio/anthem/anthem6.mp3");
	MediaPlayer mediaPlayer = new MediaPlayer(audioClip);
	private Menu fileMenu = new Menu("File");
	private Menu helpMenu = new Menu("Help/Info");
	private MenuItem closeMenu = new MenuItem("Close Program");
	private MenuItem infoMenu = new MenuItem("Press for Information");
	TextField text = new TextField();
	Timeline animation;
	
	public void start(Stage primaryStage) {
		
		//set up menus
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(helpMenu);
		fileMenu.getItems().add(closeMenu);
		helpMenu.getItems().add(infoMenu);
		
		//set menu item closeMenu to close the program when clicked
		closeMenu.setOnAction(e -> { 
			System.exit(0);	
		});
				
		//set width of menu bar
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		
		BorderPane pane = new BorderPane();
		 pane.setTop(menuBar);
		 
		 BorderPane pane2 = new BorderPane();
		 Alert alert = new Alert(AlertType.INFORMATION);
		//setup alert
		 alert.setTitle("Program Information");
		 alert.setContentText("Click in the text field and type the number of seconds before the music plays");
		 //set text field and add it to pane2 and add pane2 to pane
		 text.setMaxWidth(50);
		 text.setText(" ");
		 pane2.setCenter(text);
		 pane.setCenter(pane2);
		 
		 //set infoMenu to show information alert
		 infoMenu.setOnAction(e -> {
			 alert.show();
		 });
		 
		 //create animation for countdown
		 animation = new Timeline(new KeyFrame(Duration.millis(1000), e -> startCountdown()));
		 animation.setCycleCount(Timeline.INDEFINITE);
		 //set for text countdown to start when 'Enter' key is pressed 
		 text.setOnKeyPressed(e -> {
			 if(e.getCode() == KeyCode.ENTER) {
				 animation.play();
			 }
		 });
		 
		 Scene scene = new Scene(pane, 500, 300);
		 primaryStage.setTitle("Chapter 16 Programming Assignment 1");
		 primaryStage.setScene(scene);
		 primaryStage.show();
		
	}
	
	public void startCountdown() {
		if(Integer.parseInt(text.getText()) > 0) {
			mediaPlayer.pause();
			mediaPlayer.seek(Duration.ZERO);
			text.setText(String.valueOf(Integer.parseInt(text.getText()) - 1));
		}
		if(text.getText().equals("0")) {
			animation.pause();
			mediaPlayer.play();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

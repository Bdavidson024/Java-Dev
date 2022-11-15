package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.animation.Timeline;


public class Audio_Clip extends Application {
	//declare and initailize variables for audio clip and menus
	AudioClip audioClip = new AudioClip("https://liveexample.pearsoncmg.com/common/audio/anthem/anthem2.mp3");
	private MenuBar menuBar = new MenuBar();
	private Menu fileMenu = new Menu("File");
	private Menu editMenu = new Menu("Edit");
	private MenuItem exitMenu = new MenuItem("Exit Program");
	private MenuItem playMenu = new MenuItem("Play");
	private MenuItem loopMenu = new MenuItem("Loop");
	private MenuItem stopMenu = new MenuItem("Stop");
	
	public void start(Stage primaryStage) {
		//set up menus
		menuBar.getMenus().add(fileMenu);
		menuBar.getMenus().add(editMenu);
		fileMenu.getItems().add(exitMenu);
		editMenu.getItems().add(playMenu);
		editMenu.getItems().add(loopMenu);
		editMenu.getItems().add(stopMenu);
		//set listeners for action for menu items
		exitMenu.setOnAction(new Listener());
		playMenu.setOnAction(new Listener());
		loopMenu.setOnAction(new Listener());
		stopMenu.setOnAction(new Listener());
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		
		Button btPlay = new Button("Play");
		Button btLoop = new Button("Loop");
		Button btStop = new Button("Stop");
		
		BorderPane pane = new BorderPane();
		pane.setTop(menuBar);
		
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(btPlay, btLoop, btStop);
		
		btPlay.setOnAction(e -> {
			audioClip.setCycleCount(1);
			audioClip.play();
		});

		btLoop.setOnAction(e -> {
			audioClip.setCycleCount(Timeline.INDEFINITE);
			audioClip.play();
		});
		
		btStop.setOnAction(e -> {
			audioClip.stop();
		});
		
		pane.setCenter(hbox);
		Scene scene = new Scene(pane, 200, 300);
		primaryStage.setTitle("Chapter 16 Lab 4");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
class Listener implements EventHandler<ActionEvent> {
	
		public void handle(ActionEvent e) {
			if(e.getSource() == playMenu) {
				audioClip.setCycleCount(1);
				audioClip.play();
			}
			
			if(e.getSource() == loopMenu) {
				audioClip.setCycleCount(Timeline.INDEFINITE);
				audioClip.play();
			}
			
			if(e.getSource() == stopMenu) {
				audioClip.stop();
			}
			
			if(e.getSource() == exitMenu) {
				System.exit(0);
			}
		}
}
}

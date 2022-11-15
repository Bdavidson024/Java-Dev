/*
 * Author: Brad Davidson
 * Date: 11/07/2022
 * Purpose: take a file and split it into multiple files
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Split_Files extends Application {
	
	private TextField tfInputFile = new TextField();
	private TextField tfNumberOfFiles = new TextField();
	private Button btBrowse = new Button("Browse");
	private Button btStart = new Button("Start");
	private MenuBar menuBar;
	private Menu fileMenu;
	private Menu inputMenu;
	private MenuItem exitMenu;
	private MenuItem fileInput;
	private MenuItem fileNumberInput;
	private MenuItem startMenu;
	
	public void start(Stage stage) {
		
		GridPane gridPane = new GridPane();
		gridPane.setVgap(10);
		gridPane.add(new Label("Enter a file: "),	 0, 0);
		gridPane.add(tfInputFile, 1, 0);
		gridPane.add(new Label("Specify the number of smaller files: "), 0, 1);
		gridPane.add(tfNumberOfFiles, 1, 1);
		
		HBox hbox = new HBox(5);
		hbox.getChildren().add(btStart);
		hbox.setAlignment(Pos.CENTER);
		createMenu();
		
		VBox vbox = new VBox(6);
		vbox.getChildren().addAll(menuBar, new Label("If you split a file named temp.txt into 3 smaller files, \nthe three smaller files are temp.txt.1, temp.txt.2, and temp.txt.3."), gridPane, hbox);
		
		Scene scene = new Scene(vbox, 400, 200);
		stage.setTitle("Chapter 17 Lab 3");
		stage.setScene(scene);
		stage.show();
		
	}

	public void splitFile(String filename, int numberOfPieces) {
		try(BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File(filename)));){
			System.out.println("File size: " + input.available() + " bytes");
			long fileSize = input.available();
			int splitFileSize = (int) Math.ceil(1.0 * fileSize / numberOfPieces);
			
			for(int i = 1; i <= numberOfPieces; i++) {
				try(BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(new File(filename + "." + i)));) {
					int value;
					int count = 0;
					
					while(count++ < splitFileSize && (value = input.read()) != -1) {
						output.write(value);
					}
				}
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();			
		}
	}
	
	public void createMenu() {
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		exitMenu = new MenuItem("Close Program");
		inputMenu = new Menu("Input");
		fileInput = new MenuItem("Click to enter a file name");				
		fileNumberInput = new MenuItem("Click to specify the number of smaller files ");
		startMenu = new MenuItem("Click to start splitting the file");
		
		menuBar.getMenus().addAll(fileMenu, inputMenu);
		fileMenu.getItems().add(exitMenu);
		inputMenu.getItems().addAll(fileInput, fileNumberInput, startMenu);
		
		exitMenu.setOnAction(new Listener());
		fileInput.setOnAction(new Listener());
		fileNumberInput.setOnAction(new Listener());
		startMenu.setOnAction(new Listener());
		
		exitMenu.setAccelerator(KeyCombination.keyCombination("shortcut + O"));
		fileInput.setAccelerator(KeyCombination.keyCombination("shortcut + I"));
		fileNumberInput.setAccelerator(KeyCombination.keyCombination("shortcut + N"));
		startMenu.setAccelerator(KeyCombination.keyCombination("shortcut + S"));
		
		btStart.setOnAction(e -> {
			splitFile(tfInputFile.getText(), Integer.parseInt(tfNumberOfFiles.getText()));
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
class Listener implements EventHandler<ActionEvent> {
	
	public void handle(ActionEvent e) {
	
		if(e.getSource() == exitMenu) {
			System.exit(0);
		}
		
		if(e.getSource() == fileInput) {
			tfInputFile.setText(JOptionPane.showInputDialog("Enter file name: "));
		}
		
		if(e.getSource() == fileNumberInput) {
			tfNumberOfFiles.setText(JOptionPane.showInputDialog("Enter number of files to split into: "));
		}
		
		if(e.getSource() == startMenu) {
			splitFile(tfInputFile.getText(), Integer.parseInt(tfNumberOfFiles.getText()));
		}
		
	}
}
}

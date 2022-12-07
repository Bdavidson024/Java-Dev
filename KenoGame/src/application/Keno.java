/*
 * Author: Brad Davidson
 * Date: 10/10/2022
 * Purpose: Program will be a workable Keno game with GUI
 * */
package application;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Keno extends Application {
	//declare variables
	private int count = 0;
	private int correct = 0;
	private int numbersBet = 0;
	private int amountBet = 0;
	private double amountWon = 0;
	private int counter = 0;
	private int i = 0;
	
	NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
	
	private ArrayList<String> picks = new ArrayList<String>();
	private ArrayList<Integer> shuffleNumbers = new ArrayList<Integer>();
	private ArrayList<Integer> winningNumbers = new ArrayList<Integer>();
	
	//declare and initialize objects
    MenuBar menuBar = new MenuBar();
    Menu file = new Menu("File");
    Menu help = new Menu("Help");
    Menu payScale = new Menu("Payoffs");
    Menu betMaxMenu = new Menu("Choose Max Bet Amount");
    Menu eraseMenu = new Menu("Clear Board");
    Menu startMenu = new Menu("Determine Winning Numbers");
    
    MenuItem exit = new MenuItem("Exit Program");
    MenuItem info = new MenuItem("How to Play Keno");
    MenuItem generateMenu = new MenuItem("Generate Numbers");
    MenuItem maxMenuItem = new MenuItem("Bet Max Amount");
    MenuItem clearMenuItem = new MenuItem("Reset Board");
    MenuItem[] payoffs = new MenuItem[12];
    
    Timer timer = new Timer();
	
	Button[]buttons=new Button[80];

	TopVBox buttonPanel1 = new TopVBox();
	BottomVBox buttonPanel2 = new BottomVBox();
	MiddleVBox middlePanel = new MiddleVBox();
	GridPane pane = new GridPane();
	ToggleGroup dollarGroup;
	RadioButton dollars[];
	ToggleGroup spotsGroup;
	RadioButton spots[];
	Scene scene;
		
	public void start(Stage primaryStage) throws Exception {
		//call create menu method to add the menu bar then add to grid pane
		createMenus();
		pane.getChildren().add(menuBar);
		pane.addRow(1, buttonPanel1);
		pane.addRow(2, middlePanel);
		pane.addRow(3, buttonPanel2);
		
		scene = new Scene(pane);
		//set external style sheet, adding it to the scene
		scene.getStylesheets().add("css/styles.css");
		primaryStage.setScene(scene);
		
		//set the width and height of the stage so that if you move the window it will remain a certain size
		primaryStage.minWidthProperty().bind(scene.heightProperty().multiply(1));
		primaryStage.minWidthProperty().bind(scene.widthProperty().divide(1.5));
		primaryStage.setTitle("Let's Play Keno");
		primaryStage.show();
	}
	//adds the top rows of buttons
	void createTopButtons() {
		
		for(int i = 0;i < 10;i++) {
			buttons[i]=new Button(String.valueOf(i + 1));
			buttons[i].setOnAction(new ButtonListener());
			buttons[i].setMinWidth(80);	
		}
		
		for(int i = 9;i < 40;i++) {
			buttons[i]= new Button( String.valueOf(i + 1));
			buttons[i].setOnAction(new ButtonListener());
			buttons[i].setMinWidth(80);
		
		}
	}
	//adds the bottom rows of buttons
	void createBottomButtons() {
		
		for(int i = 40;i < 80;i++) {
			buttons[i]=new Button(String.valueOf(i+1));
			buttons[i].setOnAction(new ButtonListener());
			buttons[i].setMinWidth(80);
			buttons[i].setStyle("-fx-font-size:30;-fx-font-weight:bold;-fx-font-family:sans-serif;-fx-background-color:#0000ff;-fx-text-fill:#ffff00;-fx-border-style:solid outside;-fx-border-width:2;-fx-border-color:#000");
		}
	}
	//create options to play the game with radio buttons
	void createRadioButtons() {
		
		dollarGroup = new ToggleGroup();
		dollars = new RadioButton[6];
		
		dollars[0] = new RadioButton("1");
		dollars[1] = new RadioButton("2");
		dollars[2] = new RadioButton("3");
		dollars[3] = new RadioButton("5");
		dollars[4] = new RadioButton("10");		
		dollars[5] = new RadioButton("20");

		for(int i =0; i < 6; i++) {
			dollars[i].setToggleGroup(dollarGroup);
		}
		
		spotsGroup = new ToggleGroup();
		spots = new RadioButton[12];
		
		for(int i = 0; i < 12; i++) {
			spots[i] = new RadioButton(String.valueOf(i + 1));
			spots[i].setToggleGroup(spotsGroup);
		}
	}
	//method for creating the menu bar
	void createMenus() {
		
		menuBar.getMenus().addAll(file, betMaxMenu, eraseMenu, startMenu, payScale, help);
		file.getItems().add(exit);
		help.getItems().add(info);
		betMaxMenu.getItems().add(maxMenuItem);
		eraseMenu.getItems().add(clearMenuItem);
		startMenu.getItems().add(generateMenu);
		
		for(int i = 0; i < payoffs.length; i++) {
			payoffs[i] = new MenuItem(String.valueOf(i + 1) + " Spots Selected");
			payScale.getItems().add(payoffs[i]);
			payoffs[i].setOnAction(new Listener());
		}
		
		maxMenuItem.setOnAction(new Listener());
		generateMenu.setOnAction(new Listener());
		clearMenuItem.setOnAction(new Listener());
		startMenu.setOnAction(new Listener());
		info.setOnAction(new Listener());
		exit.setOnAction(new Listener());
		
		
		exit.setAccelerator(KeyCombination.keyCombination("shortcut+O"));
		info.setAccelerator(KeyCombination.keyCombination("shortcut+K"));
		maxMenuItem.setAccelerator(KeyCombination.keyCombination("shortcut+B"));
		clearMenuItem.setAccelerator(KeyCombination.keyCombination("shortcut+C"));
		generateMenu.setAccelerator(KeyCombination.keyCombination("shortcut+G"));
	}
	
	void clear() {
		
		winningNumbers.clear();
		winningNumbers.trimToSize();
		count = 0;
		correct = 0;
		amountWon = 0;
		i = 0;
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(String.valueOf(i+ 1));
			buttons[i].setStyle("-fx-font-size:30;-fx-font-weight:bold;-fx-font-family:sans-serif;-fx-background-color:#0000ff;-fx-text-fill:#ffff00;-fx-border-style:solid outside;-fx-border-width: 2; -fx-border-color: #000");
			buttons[i].setGraphic(null);
		}
		
		for (Toggle t : dollarGroup.getToggles()) {
			if (t instanceof RadioButton) {
				((RadioButton) t).setSelected(false);
			}
		}
		
		for (Toggle t : spotsGroup.getToggles()) {
			if (t instanceof RadioButton) {
				((RadioButton) t).setSelected(false);
			}
		}
		
		numbersBet = 0;
		amountBet = 0;
	}
	
	void generateNumbers() {
		
		populateArrayList();
		winningNumbers.clear();
		winningNumbers.trimToSize();
		Collections.shuffle(shuffleNumbers);
		
		if (numbersBet <= 0) {
			JOptionPane.showMessageDialog(null, "Choose numbers to play");
			clear();
		}
		else if (count != numbersBet) {
			JOptionPane.showMessageDialog(null, "You must select " + numbersBet + " numbers");
			clear();
		}
		
		else {
			for(int i = 0; i < 20; i++) {
				winningNumbers.add(shuffleNumbers.get(i));
			}
			for(int k = 0; k <20; k++) {
				TimerTask task = new TimerTask() {
					public void run() {
						if(buttons[winningNumbers.get(i)].getGraphic() != null) {
							correct++;
							buttons[winningNumbers.get(i)].setStyle(null);
							buttons[winningNumbers.get(i)].setStyle("-fx-font-size:30;-fx-font-weight:bold;-fx-font-family:sans-serif;-fx-background-color:#00ff00;-fx-text-fill:#ffff00;-fx-border-style:solid outside;-fx-border-width:2;-fx-border-color:#000");;
						}
						else {
							buttons[winningNumbers.get(i)].setStyle(null);
							buttons[winningNumbers.get(i)].setStyle("-fx-font-size:30;-fx-font-weight:bold;-fx-font-family:sans-serif;-fx-background-color:#ff0000;-fx-text-fill:#ffff00;-fx-border-style:solid outside;-fx-border-width:2;-fx-border-color:#000");;
						}
						if(i < 19)
							i++;
						else {
							JOptionPane.showMessageDialog(null, "The numbers you have selected and the numbers generated, have been successfully downloaded");
							displayWinnings();
						}
						try {
							Thread.sleep(1000);
						}
						catch(InterruptedException ie) {Thread.currentThread().interrupt();}
					}
				};
				timer.schedule(task, 75);
			}
			try {
				//construct a Formatter object that uses the FileOutputStream class to link to the text file to be downloaded to
				Formatter output = new Formatter(new FileOutputStream("Final.txt", true));
				
				for (int i = 0; i < 20; i++)
					output.format("%d", (winningNumbers.get(i)));
				output.close();
			}
			catch(IOException e) {
				JOptionPane.showMessageDialog(null, "Error creating file");
			}
		}
	}
	
	void displayWinnings() {
		
		switch(numbersBet) {
		
			case 1:
				switch(correct) {
				
					case 1:
						amountWon = 2.50 * amountBet;
					break;
						
					case 0:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match the number to win.");
					break;
						
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry you must match the number to win.");
					break;						
				}
			break;
			
			case 2:
				switch(correct) {
				
					case 1: 
						amountWon = 1 * amountBet;
					break;
					
					case 2:
						amountWon = 5 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match at least one number to win.");
					break;
				}
			break;
			
			case 3:
				switch(correct) {

					case 2: 
						amountWon = 2.50 * amountBet;
					break;
					
					case 3:
						amountWon = 25 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match at least two numbers to win.");
					break;
				}
			break;
				
			case 4:
				switch(correct) {
					
					case 2: 
						amountWon = 1 * amountBet;
					break;
					
					case 3:
						amountWon = 4 * amountBet;
					break;
					
					case 4:
						amountWon = 100 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match at least two numbers to win.");
					break;
				}
			break;
				
			case 5:
				switch(correct) {

					case 3: 
						amountWon = 2 * amountBet;
					break;
					
					case 4:
						amountWon = 20 * amountBet;
					break;
					
					case 5:
						amountWon = 450 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match at least three numbers to win.");
					break;
				}
			break;
				
			case 6:
				switch(correct) {
					
					case 3: 
						amountWon = 1 * amountBet;
					break;
					
					case 4:
						amountWon = 7 * amountBet;
					break;
					
					case 5:
						amountWon = 50 * amountBet;
					break;
					
					case 6:
						amountWon = 1600 *amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match at least three numbers to win.");
					break;
				}
			break;
				
			case 7:
				switch(correct) {

					case 3: 
						amountWon = 1 * amountBet;
					break;
					
					case 4:
						amountWon = 3 * amountBet;
					break;
					
					case 5:
						amountWon = 20 * amountBet;
					break;
					
					case 6:
						amountWon = 100 *amountBet;
					break;
					
					case 7:
						amountWon = 5000 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match at least three numbers to win.");
					break;
				}
			break;
				
			case 8:
				switch(correct) {
					
					case 4: 
						amountWon = 2 * amountBet;
					break;
					
					case 5:
						amountWon = 10 * amountBet;
					break;
					
					case 6:
						amountWon = 50 * amountBet;
					break;
					
					case 7:
						amountWon = 1000 *amountBet;
					break;
					
					case 8:
						amountWon = 15000 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match at least four numbers to win.");
					break;	
				}
			break;
				
			case 9:
				switch(correct) {
					
					case 4: 
						amountWon = 1 * amountBet;
					break;
					
					case 5:
						amountWon = 5 * amountBet;
					break;
					
					case 6:
						amountWon = 25 * amountBet;
					break;
					
					case 7:
						amountWon = 200 *amountBet;
					break;
					
					case 8:
						amountWon = 4000 * amountBet;
					break;
					
					case 9:
						amountWon = 40000 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match at least four numbers to win.");
					break;
				}
			break;
				
			case 10:
				switch(correct) {
			
				case 0: 
						amountWon = 2 * amountBet;
					break;
					
					case 5:
						amountWon = 2 * amountBet;
					break;
					
					case 6:
						amountWon = 20 * amountBet;
					break;
					
					case 7:
						amountWon = 80 *amountBet;
					break;
					
					case 8:
						amountWon = 500 * amountBet;
					break;
					
					case 9:
						amountWon = 10000 * amountBet;
					break;
					
					case 10:
						amountWon = 100000 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match zero or five numbers to win.");
					break;					
				}
			break;
				
			case 11:
				switch(correct) {
				
					case 0: 
						amountWon = 2 * amountBet;
					break;
					
					case 5:
						amountWon = 1 * amountBet;
					break;
					
					case 6:
						amountWon = 10 * amountBet;
					break;
					
					case 7:
						amountWon = 50 *amountBet;
					break;
					
					case 8:
						amountWon = 250 * amountBet;
					break;
					
					case 9:
						amountWon = 1500 * amountBet;
					break;
					
					case 10:
						amountWon = 15000 * amountBet;
					break;
					
					case 11:
						amountWon = 500000 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match zero or five numbers to win.");
					break;					
				}
			break;
				
			case 12:
				switch(correct) {
				
					case 0: 
						amountWon = 4 * amountBet;
					break;
					
					case 6:
						amountWon = 5 * amountBet;
					break;
					
					case 7:
						amountWon = 25 * amountBet;
					break;
					
					case 8:
						amountWon = 150 *amountBet;
					break;
					
					case 9:
						amountWon = 1000 * amountBet;
					break;
					
					case 10:
						amountWon = 2500 * amountBet;
					break;
					
					case 11:
						amountWon = 25000 * amountBet;
					break;
					
					case 12:
						amountWon = 1000000 * amountBet;
					break;
					
					default:
						JOptionPane.showMessageDialog(null, "You matched " + correct + " numbers. Sorry, but you must match zero or six numbers to win.");
					break;
				}
			break;
		}
		
		if (amountWon > 0)
			JOptionPane.showMessageDialog(null, "Congratulations! You matched " + correct + " number(s) and won " + currency.format(amountWon));
	}
	
	void populateArrayList() {
		
		for (int i = 0; i <= 79; i++) {
			shuffleNumbers.add(i);
		}
	}
	
	
	//this class creates the top 40 buttons and puts 10 buttons into each of 4 HBoxes
	class TopVBox extends VBox {
		HBox boxes[] = new HBox[4];
		
			public TopVBox() {
			createTopButtons();
			for(int i =0; i < 4; i++) {
				boxes[i] = new HBox();
			}
			for(int i = 0; i < 10; i++) {
				boxes[0].getChildren().addAll(buttons[i]);
			}
				
			for(int i = 10; i < 20; i++) {
				boxes[1].getChildren().addAll(buttons[i]);
			}
				
			for(int i = 20; i < 30; i++) {
				boxes[2].getChildren().addAll(buttons[i]);
			}
				
			for(int i = 30; i < 40; i++) {
				boxes[3].getChildren().addAll(buttons[i]);
			}
				
			getChildren().addAll(boxes);
		}
			
	}
	//this class creates the bottom 40 buttons and puts 10 buttons into each of 4 HBoxes
	class BottomVBox extends VBox{
		HBox boxes[] = new HBox[4];
			
		public BottomVBox() {
			createBottomButtons();
			for(int i =0; i < 4; i++) {
				boxes[i] = new HBox();
			}
			for(int i = 40; i < 50; i++) {
				boxes[0].getChildren().addAll(buttons[i]);
			}
				
			for(int i = 50; i < 60; i++) {
				boxes[1].getChildren().addAll(buttons[i]);
			}
				
			for(int i = 60; i < 70; i++) {
				boxes[2].getChildren().addAll(buttons[i]);
			}
				
			for(int i = 70; i < 80; i++) {
				boxes[3].getChildren().addAll(buttons[i]);
			}
				
			getChildren().addAll(boxes);
		}
	}
	//this class creates the gui in between the row of buttons on the top and the rows on the bottom
	class MiddleVBox extends HBox {
		Image luckyImage = new Image("images/LuckySmall.png");
		ImageView lucky = new ImageView(luckyImage);
		Label numberLabel = new Label(" Choose Numbers to Play ");
		Label dollarLabel = new Label(" Choose Bet Amount ($) ");
		HBox spotsHBox[] = new HBox[6];
		HBox middleBox[] = new HBox[3];
		VBox spotsVBox = new VBox();
		VBox dollarsVBox = new VBox();
			
		public MiddleVBox() {
			createRadioButtons();
				
			for(int i = 0; i < middleBox.length; i++) {
				middleBox[i] = new HBox();
			}
				
			for(int i = 0; i < spotsHBox.length; i++) {
				spotsHBox[i] = new HBox();
				spotsHBox[i].setSpacing(30);
				spotsHBox[i].setPadding(new Insets(10));
			}
				
			spotsHBox[0].getChildren().add(spots[0]);
			spotsHBox[0].getChildren().add(spots[6]);
			spotsHBox[1].getChildren().add(spots[1]);
			spotsHBox[1].getChildren().add(spots[7]);
			spotsHBox[2].getChildren().add(spots[2]);
			spotsHBox[2].getChildren().add(spots[8]);
			spotsHBox[3].getChildren().add(spots[3]);
			spotsHBox[3].getChildren().add(spots[9]);
			spotsHBox[4].getChildren().add(spots[4]);
			spotsHBox[4].getChildren().add(spots[10]);
			spotsHBox[5].getChildren().add(spots[5]);
			spotsHBox[5].getChildren().add(spots[11]);
				
			spotsVBox.getChildren().add(numberLabel);
				//add all elements of spotsHBox to spotsVBox
			for(int i = 0; i < spotsHBox.length; i++) {
				spotsVBox.getChildren().add(spotsHBox[i]);
			}
				
				dollarsVBox.getChildren().add(dollarLabel);	
				//add all elements of dollars to dollarsVBox
			for(int i = 0; i < dollars.length; i++ ) {
				dollarsVBox.getChildren().add(dollars[i]);
			}	
				//set spacing and padding for dollarsVBox
			dollarsVBox.setSpacing(15);
			dollarsVBox.setPadding(new Insets(10));
				
			for(int i = 0; i < middleBox.length; i++) {
				middleBox[i].setPadding(new Insets(10));
			}
				//add both VBoxes with radio buttons and the main image to middleBox
			middleBox[0].getChildren().add(spotsVBox);
			middleBox[1].getChildren().add(lucky);	
			middleBox[2].getChildren().add(dollarsVBox);
				
			setAlignment(Pos.CENTER);
			getChildren().addAll(middleBox);			
			}		
		}
	//button listener class enforcing rules for selecting the buttons
	class ButtonListener implements EventHandler<ActionEvent>{
		
		private Image img2 = new Image("images/checkmarkSmall.gif");
		private ImageView icon = new ImageView(img2);
			
		public void handle(ActionEvent e) {
			
			for(int i =0; i <spots.length; i++) {
				if(spots[i].isSelected()) {
					numbersBet = Integer.parseInt(spots[i].getText());
				}
			}
			
			if(count < numbersBet) {
				count++;
				if(e.getSource() instanceof Button) {
					((Button) e.getSource()).setGraphic(icon);
					String numberLabel = ((Button)e.getSource()).getText();
					picks.add(numberLabel);
					((Button)e.getSource()).setText(" ");
				}
			}
			else if(count == 0) {
				JOptionPane.showMessageDialog(null, "You have to choose to bet at least 1 (one) number.");
				numbersBet = 0;
			}
			else
				JOptionPane.showMessageDialog(null, "You have already chosen your " + numbersBet + " number(s).\nYou cannot choose any additional numbers.");
		}
	}
	//listener class for menu items
	class Listener implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent e) {
			
			if(e.getSource().equals(maxMenuItem)) {
				dollars[5].setSelected(true);
			}
			
			if(e.getSource().equals(clearMenuItem)) {
				clear();
			}
			
			if(e.getSource() == info) {
				JOptionPane.showMessageDialog(null, "Click the number(s) you wish to bet (1-12).\n\nClick 'Generate Numbers' menu under the 'Start Menu'.\n\n20 Random numbers will be chosen and highlighted in Red.\n\nA message will display as to whether you have won.\n\nSee 'Help' > 'Payoffs' for a list of payoffs.");
			}
			
			if(e.getSource() == exit) {
				System.exit(0);
			}
			
			if(e.getSource() == payoffs[0]) {
				JOptionPane.showMessageDialog(null, "Match\n1: $2.50");
			}
				
			if(e.getSource() == payoffs[1]) {
				JOptionPane.showMessageDialog(null, "Match\n1: $1.00\n2: $5");
			}
				
			if(e.getSource() == payoffs[2]) {
				JOptionPane.showMessageDialog(null, "Match\n2: $2.50\n3: $25");
			}
				
			if(e.getSource() == payoffs[3]) {
				JOptionPane.showMessageDialog(null, "Match\n2: $1.00\n3: $4\n4: $100");
			}
				
			if(e.getSource() == payoffs[4]) {
				JOptionPane.showMessageDialog(null, "Match\n3: $2.00\n4: $20\n5: $450");
			}
				
			if(e.getSource() == payoffs[5]) {
				JOptionPane.showMessageDialog(null, "Match\n3: $1.00\n4: $7\n5: $50\n6: $1,600");
			}
				
			if(e.getSource() == payoffs[6]) {
				JOptionPane.showMessageDialog(null, "Match\n3: $1.00\n4: $3\n5: $20\n6: $100\n7: $5,000");
			}
				
			if(e.getSource() == payoffs[7]) {
				JOptionPane.showMessageDialog(null, "Match\n4: $2.00\n5: $10\n6: $50\n7: $1,000\n8: $15,000");
			}
				
			if(e.getSource() == payoffs[8]) {
				JOptionPane.showMessageDialog(null, "Match\n4: $1.00\n5: $5\n6: $25\n7: $200\n8: $4,000\n9: $40,000");
			}
				
			if(e.getSource() == payoffs[9]) {
				JOptionPane.showMessageDialog(null, "Match\n0: $2.00\n5: $2.00\n6: $20\n7: $80\n8: $500\n9: $10,000\n10: $ 100,000");
			}
				
			if(e.getSource() == payoffs[10]) {
				JOptionPane.showMessageDialog(null, "Match\n0: $2.00\n5: $1.00\n6: $10\n7: $50\n8: $250\n9: $1,500\n10: $ 15,000\n11: $500,000");
			}
				
			if(e.getSource() == payoffs[11]) {
				JOptionPane.showMessageDialog(null, "Match\n0: $4.00\n6: $5\n7: $25\n8: $150\n9: $1,000\n10: $ 2,500\n11: $25,000\n12: $1,000,000");
			}
			
			if(e.getSource() == generateMenu) {
				generateNumbers();
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
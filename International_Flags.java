package application;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class International_Flags extends Application {
//	declare an array of strings for the flag titles
	private String[] flagTitles = {"Canada", "China", "Denmark", "France", "Germany", "India", "Norway", "United Kingdom", "United States of America"};
//	declare an array of image views for the flags of the countries
	private ImageView[] flagImage = {new ImageView("image/ca.gif"), new ImageView("image/china.gif"), new ImageView("image/denmark.gif"), new ImageView("image/fr.gif"), new ImageView("image/germany.gif"), new ImageView("image/india.gif"), new ImageView("image/norway.gif"), new ImageView("image/uk.gif"), new ImageView("image/us.gif")};
//	declare an array of strings for the flag descriptions
	private String[] flagDescription = new String[9];
//	declare and  create a description pane
	private DescriptionPane descriptionPane = new DescriptionPane();
//	create a combo box for selecting countries
	private ComboBox<String> cbo = new ComboBox<>();
	
	public void start(Stage primaryStage) {
		
		for(int i = 0; i < 9; i++) {
			flagDescription[i] = getDescription(i);
		}
		
		setDisplay(0);
		
		BorderPane pane = new BorderPane();
		BorderPane paneForComboBox = new BorderPane();
		paneForComboBox.setLeft(new Label("Select a country: "));
		paneForComboBox.setCenter(cbo);
		pane.setTop(paneForComboBox);
		cbo.setPrefWidth(400);
		cbo.setValue("Canada");
		
		ObservableList<String> items = FXCollections.observableArrayList(flagTitles);
		cbo.getItems().addAll(items);
		pane.setCenter(descriptionPane);
		
//		display the selected country
		cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));
//		create scene and configure stage
		Scene scene = new Scene(pane, 650,200);
		primaryStage.setTitle("Flags of the world");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void setDisplay(int index) {
		descriptionPane.setTitle(flagTitles[index]);
		descriptionPane.setImageView(flagImage[index]);
		descriptionPane.setDescription(flagDescription[index]);
	}
	
	private String getDescription(int i) {
		StringBuilder result = new StringBuilder();
		
		try {
			Scanner input = new Scanner(new File("text/description" + i + ".txt"));
			
			while (input.hasNext()) {
				result.append(input.nextLine() + '\n');
			}
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

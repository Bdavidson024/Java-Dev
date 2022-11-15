package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Img_Txt_Alignment extends Application {
	
	private double paneWidth = 480;
	private double paneHeight = 250;
	private ImageView imageView = new ImageView("image/grapes.gif");
	private Label label = new Label("Grapes", imageView);
	private ComboBox<String>cboContentDisplay = new ComboBox<>();
	private TextField tfGraphicTextGap = new TextField();
	
	public void start(Stage primaryStage) {
		
		BorderPane pane = new BorderPane();
		pane.setCenter(label);;
		label.setContentDisplay(ContentDisplay.TOP);
		cboContentDisplay.getItems().addAll("TOP", "BOTTOM", "LEFT", "RIGHT");
		cboContentDisplay.setValue("TOP");
		tfGraphicTextGap.setPrefColumnCount(3);
		
		HBox hbox = new HBox(10);
		hbox.getChildren().addAll(new Label("contentDisplay: "), cboContentDisplay, new Label("graphicTextGap:"), tfGraphicTextGap);
		hbox.setAlignment(Pos.CENTER);
		pane.setTop(hbox);
		
		Scene scene = new Scene(pane, paneWidth, paneHeight);
		primaryStage.setTitle("Chapter 16 Lab 3");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		cboContentDisplay.setOnAction(e -> {
			if(cboContentDisplay.getValue().equals("TOP")) {
				label.setContentDisplay(ContentDisplay.TOP);
			}
			else if(cboContentDisplay.getValue().equals("BOTTOM")) {
				label.setContentDisplay(ContentDisplay.BOTTOM);
			}
			else if(cboContentDisplay.getValue().equals("LEFT")) {
				label.setContentDisplay(ContentDisplay.LEFT);
			}
			else if(cboContentDisplay.getValue().equals("RIGHT")) {
				label.setContentDisplay(ContentDisplay.RIGHT);
			}
		});
		
		tfGraphicTextGap.setOnAction(e -> {
			label.setGraphicTextGap(Double.parseDouble(tfGraphicTextGap.getText()));
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

}

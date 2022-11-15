package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.event.ActionEvent;

public class Intersecting_Rectangles extends Application {
	
	private double paneWidth = 300;
	private double paneHeight = 400;
	private Label status = new Label("Two Rectangles intersect? NO");
	private Rectangle r1 = new Rectangle(50,50,40,50);
	private Rectangle r2 = new Rectangle(160,50,50,20);
	
	public void start(Stage primaryStage) {
		
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		MenuItem exitMenu = new MenuItem("Exit Program");
		fileMenu.getItems().add(exitMenu);
		menuBar.getMenus().add(fileMenu);
		
		exitMenu.setOnAction((ActionEvent t) -> {
			System.exit(0);
		});
		
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		
		BorderPane pane1 = new BorderPane();
		pane1.setTop(new Label("Enter rectangle 1 info: "));
		
		GridPane pane11 = new GridPane();
		pane11.setHgap(5);
		pane11.add(new Label("X: "), 0, 0);
		pane11.add(new Label("Y: "), 0, 1);
		pane11.add(new Label("Width: "), 0, 2);
		pane11.add(new Label("Height: "), 0, 3);
		
		TextField tfX1 = new TextField(r1.getX() + "");
		TextField tfY1 = new TextField(r1.getY() + "");
		TextField tfWidth1 = new TextField(r1.getWidth() + "");
		TextField tfHeight1 = new TextField(r1.getHeight() + "");
		pane11.add(tfX1, 1, 0);
		pane11.add(tfY1, 1, 1);
		pane11.add(tfWidth1, 1, 2);
		pane11.add(tfHeight1, 1, 3);
		tfX1.setPrefColumnCount(3);
		tfY1.setPrefColumnCount(3);
		tfWidth1.setPrefColumnCount(3);
		tfHeight1.setPrefColumnCount(3);
		tfX1.setAlignment(Pos.BOTTOM_RIGHT);
		tfY1.setAlignment(Pos.BOTTOM_RIGHT);
		tfWidth1.setAlignment(Pos.BOTTOM_RIGHT);
		tfHeight1.setAlignment(Pos.BOTTOM_RIGHT);
		
		pane1.setStyle("-fx-border-color:black");
		pane1.setCenter(pane11);
		
		BorderPane pane2 = new BorderPane();
		pane2.setTop(new Label("Enter rectangle 2 info: "));
		
		GridPane pane21 = new GridPane();
		pane21.setHgap(5);
		pane21.add(new Label("X: "), 0, 0);
		pane21.add(new Label("Y: "), 0, 1);
		pane21.add(new Label("Width: "), 0, 2);
		pane21.add(new Label("Height: "), 0, 3);
		
		TextField tfX2 = new TextField(r2.getX() + "");
		TextField tfY2 = new TextField(r2.getY() + "");
		TextField tfWidth2 = new TextField(r2.getWidth() + "");
		TextField tfHeight2 = new TextField(r2.getHeight() + "");
		pane21.add(tfX2, 1, 0);
		pane21.add(tfY2, 1, 1);
		pane21.add(tfWidth2, 1, 2);
		pane21.add(tfHeight2, 1, 3);
		tfX2.setPrefColumnCount(3);
		tfY2.setPrefColumnCount(3);
		tfWidth2.setPrefColumnCount(3);
		tfHeight2.setPrefColumnCount(3);
		tfX2.setAlignment(Pos.BOTTOM_RIGHT);
		tfY2.setAlignment(Pos.BOTTOM_RIGHT);
		tfWidth2.setAlignment(Pos.BOTTOM_RIGHT);
		tfHeight2.setAlignment(Pos.BOTTOM_RIGHT);
		
		pane2.setStyle("-fx-border-color:black");
		pane2.setCenter(pane21);
		
		HBox hbox = new HBox(5);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(pane1, pane2);
		
		GridPane pane3 = new GridPane();
		pane3.add(menuBar, 0, 0);
		pane3.add(status, 0, 1);
		pane3.setVgap(25);
		
		BorderPane pane = new BorderPane();
		pane.setTop(pane3);
		BorderPane.setAlignment(status, Pos.CENTER);
		Pane paneForRectangles = new Pane();
		r1.setFill(new Color(1, 1, 1, 0));
		r1.setStroke(Color.BLACK);
		r2.setFill(new Color(1, 1, 1, 0));
		r2.setStroke(Color.BLACK);
		
		paneForRectangles.getChildren().addAll(r1, r2);
		pane.setCenter(paneForRectangles);
		pane.setBottom(hbox);
		
		BorderPane bigPane = new BorderPane();
		bigPane.setCenter(pane);
		
		Button btRedraw = new Button("Redraw Rectangles");
		bigPane.setBottom(btRedraw);
		BorderPane.setAlignment(btRedraw, Pos.CENTER);
		
		Scene scene = new Scene(bigPane, paneWidth, paneHeight);
		primaryStage.setTitle("Exercise16_09");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		btRedraw.setOnAction(e -> {
			r1.setX(Double.parseDouble(tfX1.getText()));
			r1.setY(Double.parseDouble(tfY1.getText()));
			r1.setWidth(Double.parseDouble(tfWidth1.getText()));
			r1.setHeight(Double.parseDouble(tfHeight1.getText()));
			r2.setX(Double.parseDouble(tfX1.getText()));
			r2.setY(Double.parseDouble(tfY1.getText()));
			r2.setWidth(Double.parseDouble(tfWidth1.getText()));
			r2.setHeight(Double.parseDouble(tfHeight1.getText()));
			updateStatus();
		});
		
		r1.setOnMouseDragged(e -> {
			if(r1.contains(e.getX(), r1.getY())) {
				r1.setX(e.getX()-r1.getWidth()/2);
				r1.setY(e.getY()-r1.getHeight()/2);
				tfX1.setText(e.getX()-r1.getWidth()/2 + " ");
				tfY1.setText(e.getY()-r1.getHeight()/2 + " ");
			}
		});
		
		r2.setOnMouseDragged(e -> {
			if(r2.contains(e.getX(), e.getY())) {
				r2.setX(e.getX()-r2.getWidth()/2);
				r2.setY(e.getY()-r2.getHeight()/2);
				tfX2.setText(e.getX()-r2.getWidth()/2 + " ");
				tfY2.setText(e.getY()-r2.getHeight()/2 + " ");
			}
		});
		
	}
	
	private void updateStatus() {
		double distanceX = Math.abs(r1.getX() + r1.getWidth() / 2 - r2.getX() - r2.getWidth() / 2);
		double distanceY = Math.abs(r1.getY() + r1.getHeight() / 2 - r2.getY() - r2.getHeight() / 2);
		
		if(distanceX <= (r1.getWidth() + r2.getWidth()) / 2 && distanceY <= (r1.getHeight() + r2.getHeight()) / 2) {
			status.setText("Two rectangles intersect? Yes");
		}
		else {
			status.setText("Two rectangles intersect? No");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}

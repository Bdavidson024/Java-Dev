import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import java.util.ArrayList;
import java.util.Scanner;

public class User_Rectangles extends Application {
	
	Rectangle r1,r2;

	public static void main(String[] args){		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
	
		//add scanner for user input
		Scanner input = new Scanner(System.in);
		System.out.println("Rectangle 1: x and y are center coordinates: Please enter: x, y, width, height: ");
		double x1 = input.nextDouble();
		double y1 = input.nextDouble();
		double w1 = input.nextDouble();
		double h1 = input.nextDouble();
		System.out.println("Rectangle 2: x and y are center coordinates: Please enter: x, y, width, height: ");
		double x2 =input.nextDouble();
		double y2 = input.nextDouble();
		double w2 = input.nextDouble();
		double h2 = input.nextDouble();
		
		r1 = new Rectangle(x1,y1,w1,h1);
		r1.setFill(Color.TRANSPARENT);
		r1.setStroke(Color.BLACK);
		
		r2 = new Rectangle(x2,y2,w2,h2);
		r2.setFill(Color.TRANSPARENT);
		r2.setStroke(Color.BLACK);
		
		//create pane and vbox
		Pane pane = new Pane();
		VBox vBox = new VBox(20);
		vBox.setPadding(new Insets(30,25,25,30));
		
		//test whether the rectangles overlap, contained inside one another, or neither
		String output = " ";
		if(contains(r1,r2)||contains(r2,r1))
			output = "One rectangle is contained in another";
		else if(overlaps(r1,r2))
			output = "The rectangles overlap";
		else 
			output = "The rectangles are seperate";
		
		pane.getChildren().addAll(r1,r2);
		
		vBox.getChildren().addAll(pane,new Text(20,0,output));
		
		Scene scene = new Scene(vBox);
		primaryStage.setTitle("Chapter 14 PA 2");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public boolean contains(Rectangle r1,Rectangle r2) {
		return r2.getY() + r2.getHeight() <= r1.getY() + r1.getHeight() && r2.getX() + r2.getWidth() <= r1.getX() + r1.getWidth() && r2.getX()>r1.getX() && r2.getY() > r1.getY();
	}
	
	public boolean overlaps(Rectangle r1, Rectangle r2) {
		return getDistance(r1.getX(), r2.getX()+r2.getWidth()) < r1.getWidth() + r2.getWidth() && getDistance(r1.getY(), r2.getY()+r2.getHeight()) < r1.getHeight() + r2.getHeight();
	}
	
	private double getDistance(double d1, double d2) {
		return Math.sqrt(Math.pow(d2-d1,2));
	}

}



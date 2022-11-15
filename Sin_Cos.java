/*
 * Author: Brad Davidson
 * Date: 09/30/2022
 * Purpose: Programming Excercise #19-Plotting the sine and cosine functions on a graph
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Sin_Cos extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		Polyline polyline1 = new Polyline();
		
		ObservableList<Double>list1 = polyline1.getPoints();
		polyline1.setStroke(Color.RED);
		
		Polyline polyline2 = new Polyline();
		ObservableList<Double>list2 = polyline2.getPoints();
		polyline2.setStroke(Color.BLUE);
		double scaleFactor = 50;
		
		for(int x =-170;x<=170;x++) {
			list1.add(x+200.0);
			list1.add(100-scaleFactor * Math.sin(x/100.0) *2 * Math.PI);
			list2.add(x+200.0);
			list2.add(100-scaleFactor * Math.cos(x/100.0) *2 * Math.PI);
		}
		
		Line line1 = new Line(10,100,390,100);
		Line line2 = new Line(200,10,200,200);
		Line line3 = new Line(390,100,370,90);
		Line line4 = new Line(390,100,370,110);
		Line line5 = new Line(200,10,190,30);
		Line line6 = new Line(200,10,210,30);
		
		Text text1 = new Text(380,70,"X");
		Text text2 = new Text(220,20,"Y");
		Text text3 = new Text(140,115,"-/u03c0");
		Text text4 = new Text(250,115,"/u03c0");
		Text text5 = new Text(85,115,"-2/u03c0");
		Text text6 = new Text(295,115,"2/u03c0");
		Text text7 = new Text(200,115,"0");
		
		Group group = new Group();
		group.getChildren().addAll(polyline1,polyline2,line1,line2,line3,line4,line5,line6,text1,text2,text3,text4,text5,text6,text7);
		
		Scene scene = new Scene(new BorderPane(group),400,250);
		primaryStage.setTitle("Chapter 14 Lab 4");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}

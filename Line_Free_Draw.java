import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Line_Free_Draw extends Application {
	
	private double x = 100;
	private double y = 100;
	
	
	public void start(Stage primaryStage) {
		
		Pane pane = new Pane();
		pane.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.UP) {
				pane.getChildren().add(new Line(x,y,x,y-10));
				y-=10;
			}
			
			else if (e.getCode() == KeyCode.DOWN) {
				pane.getChildren().add(new Line(x,y,x,y+10));
				y+=10;
			}
			
			else if (e.getCode() == KeyCode.LEFT) {
				pane.getChildren().add(new Line(x,y,x-10,y));
				x-=10;
			}
			
			else if (e.getCode() == KeyCode.RIGHT) {
				pane.getChildren().add(new Line(x,y,x+10,y));
				x+=10;
			}
		});
		
		Scene scene = new Scene(pane,400,500);
		primaryStage.setTitle("Chapter 15 Lab 2");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		pane.requestFocus();
		x = pane.getWidth()/2;
		y = pane.getHeight()/2;
	}

	public static void main(String[] args) {
		launch(args);
	}

}

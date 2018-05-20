import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application{
	static final double canvasWidth = 400;
	static final double canvasHeight = 600;
	static final double frameRate = 60;
		
	private GraphicsContext gc;
	
	private Canvas canvas;
	private UpdateFrame updateFrame;
		
	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage theStage) throws Exception {
		canvas = new Canvas(canvasWidth,canvasHeight);
		
		Group canvasGroup = new Group(canvas);
		Scene theScene = new Scene(canvasGroup);
				
		gc = canvas.getGraphicsContext2D();
		
		updateFrame = new UpdateFrame(this);
		
		KeyFrame updateGame = new KeyFrame(Duration.seconds(1f/frameRate) , updateFrame);
		
		Timeline gameLoop = new Timeline(updateGame);
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		gameLoop.play();
		
		theStage.setScene(theScene);
		theStage.sizeToScene();
		theStage.setResizable(false);
		theStage.show();	
	}
	
	public GraphicsContext getGC() {
		return gc;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
}

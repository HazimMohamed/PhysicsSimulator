import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application{
	static final double canvasWidth = 800;
	static final double canvasHeight = 600;
		
	private GraphicsContext gc;
	private Canvas canvas;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	public void start(Stage theStage) throws Exception {
		canvas = new Canvas(canvasWidth,canvasHeight);
		
		Group canvasGroup = new Group(canvas);
		Scene theScene = new Scene(canvasGroup);
				
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.RED);
		
		UpdateFrame updateFrame = new UpdateFrame(this);
		DragEvent dragEvent = new DragEvent(updateFrame);
		
		canvas.setOnMousePressed((e) -> {
			dragEvent.startDragging(e);
		});
		
		canvas.setOnMouseDragged((e) -> {
			dragEvent.dragAction(e);
		});

		canvas.setOnMouseReleased((e) -> {
			dragEvent.stopDragging(e);
		});
		
		
		KeyFrame updateGame = new KeyFrame(Duration.seconds(1f/60f) , updateFrame);
		
		Timeline gameLoop = new Timeline(updateGame);
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		gameLoop.play();
		
		theStage.setScene(theScene);
		theStage.sizeToScene();
		theStage.show();	
	}
	
	public GraphicsContext getGC() {
		return gc;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
}

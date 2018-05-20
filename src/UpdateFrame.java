import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class UpdateFrame implements EventHandler<ActionEvent>{

	private Canvas canvas;
	private GraphicsContext gc;
		
	private PhysicsManager physicsManager;
	private GraphicsManager graphicsManager;
	
	UpdateFrame(Driver driver) {
		this.canvas = driver.getCanvas();
		this.gc = driver.getGC();
		
		setHandlers();
		
		physicsManager = new PhysicsManager();
		graphicsManager = new GraphicsManager(physicsManager);
	}
	
	//The game loop
	public void handle(ActionEvent irrelevantArgument) {		
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		for(Physical item: physicsManager.getObjects()) {
			if(item instanceof Drawable) {
				Drawable toDraw = (Drawable) item;
				toDraw.draw(gc);
			}
		}
		
		physicsManager.updatePositions();
		
		for(Drawable item: graphicsManager.getObjects()) {
			item.draw(gc);
		}
	}
	
	private void setHandlers() {
		canvas.setOnMousePressed((e) -> {
			graphicsManager.startDragging(e);
		});
		
		canvas.setOnMouseDragged((e) -> {
			graphicsManager.dragAction(e);
		});

		canvas.setOnMouseReleased((e) -> {
			graphicsManager.stopDragging(e);
		});
	}
}

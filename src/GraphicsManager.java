import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GraphicsManager {
	private PhysicsManager physicsManager;
	
	private ArrayList<Drawable> objectList;

	public GraphicsManager(PhysicsManager physicsManager) {
		this.physicsManager = physicsManager;
		objectList = new ArrayList<Drawable>(0);
	}
	
	public ArrayList<Drawable> getObjects() {
		return objectList;
	}

	void addToObjects (Drawable item) {
		objectList.add(item);
	}
	
	public void startDragging(MouseEvent mouseEvent) {
		AimingLine aimingLine = new AimingLine();
		objectList.add(aimingLine);
		aimingLine.start = new Vector2(mouseEvent.getX(),mouseEvent.getY());		
	}
	
	public void dragAction(MouseEvent mouseEvent) {
		for(Drawable item : objectList) {
			if(item instanceof AimingLine) {
				AimingLine aimingLine = (AimingLine) item;
				aimingLine.end = (new Vector2(mouseEvent.getX(), mouseEvent.getY()));
			}	
		}
	}
	
	public void stopDragging(MouseEvent mouseEvent) {
		for(Drawable item : objectList) {
			if(item instanceof AimingLine) {
				AimingLine aimingLine = (AimingLine) item;
				Vector2 launchVector = aimingLine.start.add(aimingLine.end.multiply(-1)).multiply(AimingLine.throwStrength);
				CannonBall addedBall = new CannonBall(new Vector2(aimingLine.end.x, aimingLine.end.y));
				physicsManager.addToObjects(addedBall);
				addedBall.addForce(launchVector);
			}	
		}
		objectList.removeIf(e -> {
			return (e instanceof AimingLine);
		});
	}
	
	public class AimingLine implements Drawable{
		static final double throwStrength = 0.5;
		
		private Vector2 start;
		private Vector2 end;
		
		public void draw(GraphicsContext gc) {
			if(start != null && end != null) {
				gc.setStroke(Color.BLACK);
				gc.setLineWidth(3);
				gc.strokeLine(start.x, start.y, end.x, end.y);
			}
		}
	}
}

import javafx.scene.input.MouseEvent;

public class DragEvent{
	static final double throwStrength = 5;

	private Vector2 launchVector;
	
	private UpdateFrame updateFrame;
	private AimingLine aimingLine;
		
	public DragEvent(UpdateFrame updateFrame) {
		this.updateFrame = updateFrame;	
		this.aimingLine = updateFrame.getAimingLine();
	}
	
	public void startDragging(MouseEvent e) {
		aimingLine.setStart(new Vector2(e.getX(),e.getY()));
	}

	public void dragAction(MouseEvent mouseEvent) {
		aimingLine.setVisibility(true);
		aimingLine.setEnd(new Vector2(mouseEvent.getX(), mouseEvent.getY()));
	}
	
	public void stopDragging(MouseEvent mouseEvent) {
		aimingLine.setVisibility(false);
		CannonBall addedBall = updateFrame.addCannonBall(new Vector2(aimingLine.getEnd().x, aimingLine.getEnd().y));
		addedBall.addImpulseForce(getLaunchVector());
	}
	
	public Vector2 getLaunchVector() {
		launchVector = aimingLine.getStart().add(aimingLine.getEnd().multiply(-1));
		launchVector  = launchVector.multiply(0.5);
		return launchVector;
	}
}

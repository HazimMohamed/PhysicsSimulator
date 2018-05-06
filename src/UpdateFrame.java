import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class UpdateFrame implements EventHandler<ActionEvent>{
	static final double gravityConstant = 3;

	private Canvas canvas;
	private GraphicsContext gc;
	private AimingLine aimingLine;
	private EdgeWall edgeWall;
	
	private ArrayList<CannonBall> ballList = new ArrayList<CannonBall>(0);
	
	public UpdateFrame(Driver driver) {
		this.canvas = driver.getCanvas();
		this.gc = driver.getGC();
		
		aimingLine = new AimingLine();
		edgeWall = new EdgeWall();

	}
	
	public CannonBall addCannonBall(Vector2 startPosition) {
		CannonBall newCannonBall = new CannonBall(startPosition);
		ballList.add(newCannonBall);
		return newCannonBall;
	}
	
	//The game loop
	public void handle(ActionEvent irrelevantArgument) {		
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		if(aimingLine.isVisible() && aimingLine.getStart() != null && aimingLine.getEnd() != null) {
			gc.setStroke(Color.BLACK);
			gc.setLineWidth(3);
			gc.strokeLine(aimingLine.getStart().x, aimingLine.getStart().y, 
				aimingLine.getEnd().x, aimingLine.getEnd().y);
		}
		
		for(CannonBall cannonBall:ballList) {
			cannonBall.addConstantForce(new Vector2(0,UpdateFrame.gravityConstant));
		}
		
		collisionChecking();

		for(CannonBall cannonBall:ballList) {
			cannonBall.updatePosition();
			gc.fillOval(cannonBall.getPosition().x, 
					cannonBall.getPosition().y,
					CannonBall.cannonBallRadius, 
					CannonBall.cannonBallRadius);
		}
	}
	
	private void collisionChecking() {
		for(CannonBall ball: ballList) {
			if(ball.getPosition().x + CannonBall.cannonBallRadius > Driver.canvasWidth) {
				new Collision<EdgeWall>(new Vector2(-1,0), ball, edgeWall);
			}
			
			if(ball.getPosition().x - CannonBall.cannonBallRadius < 0) {
				new Collision<EdgeWall>(new Vector2(1,0), ball, edgeWall);
			}
			
			if(ball.getPosition().y + CannonBall.cannonBallRadius > Driver.canvasHeight) {
				new Collision<EdgeWall>(new Vector2(0,-1), ball, edgeWall);
			}
		}
	}
	
	public AimingLine getAimingLine() {
		return aimingLine;
	}
}

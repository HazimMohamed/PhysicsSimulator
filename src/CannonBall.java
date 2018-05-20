import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CannonBall implements Physical, Collidable, Drawable{
	private static final double cannonBallRadius = 20;
	private static final double cannonBallBounciness = 0.8;
	private static final double cannonBallFrictionCoeficient = 1;
	private static final double cannonBallMass = 5;
		
	private Vector2 previousPosition;
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private ArrayList<Vector2> forces;
	
	GraphicsContext gc;
	
	CircleCollider collider;

	public CannonBall(Vector2 startPosition) {
		position = startPosition;
		velocity = Vector2.zero;
		acceleration = Vector2.zero;
		
		collider = new CircleCollider(this, Vector2.zero, cannonBallRadius);
		
		forces = new ArrayList<Vector2>(0);
	}
	
	public void addForce(Vector2 force) {
		forces.add(force);
	}
	
	public void updatePosition() {		
		Vector2 netForce = Vector2.zero;
		
		for(Vector2 force: forces) {
			netForce = netForce.add(force);
		}
		
		acceleration = netForce.multiply(1f/cannonBallMass);
		
		velocity = velocity.add(acceleration);
		
		previousPosition = position;
		
		position = position.add(velocity);
		
		forces.clear();	
	}

	public void setPosition(Vector2 a) {
		this.position = a;
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		gc.fillOval(position.x,
					position.y, 
					cannonBallRadius, 
					cannonBallRadius
		);
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public Vector2 getAcceleration() {
		return acceleration;
	}
	
	public ArrayList<Vector2> getForces() {
		return forces;
	}

	public double getMass() {
		return cannonBallMass;
	}
	
	
	public double getCoeficientOfFriction() {
		return cannonBallFrictionCoeficient;
	}

	@Override
	public Collider getCollider() {
		return collider;
	}

	@Override
	public Vector2 getPreviousPosition() {
		return previousPosition;
	}

	public boolean isImmobile() {
		return false;
	}

	@Override
	public double getBounciness() {
		return cannonBallBounciness;
	}
}

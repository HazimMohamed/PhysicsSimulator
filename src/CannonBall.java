import java.util.ArrayList;

public class CannonBall implements Collidable{
	static final double cannonBallRadius = 20;
	static final double cannonBallBounciness = 0.8;
	static final double cannonBallFrictionCoeficient = 0.2;
	static final double cannonBallMass = 5;
	static final double velocityBounceThreshold = 2.5;
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private ArrayList<Vector2> constantForces;
	private ArrayList<Vector2> impulseForces;
	
	UpdateFrame updateFrame;
	
	public CannonBall(Vector2 startPosition) {
		position = startPosition;
		velocity = Vector2.zero;
		acceleration = Vector2.zero;
		
		constantForces = new ArrayList<Vector2>(0);
		impulseForces = new ArrayList<Vector2>(0);
	}
	
	public void addImpulseForce(Vector2 force) {
		impulseForces.add(force);
	}
	
	public void addConstantForce(Vector2 force) {
		constantForces.add(force);
	}
	
	public Vector2 updatePosition() {		
		Vector2 netForce = Vector2.zero;
		
		for(Vector2 force: constantForces) {
			netForce = netForce.add(force);
		}
		
		for(Vector2 force: impulseForces) {
			netForce = netForce.add(force);
		}
		
		acceleration = netForce.multiply(1f/cannonBallMass);
		
		velocity = velocity.add(acceleration);
		
		this.position = position.add(velocity);
		
		stayInBounds();
		
		impulseForces.clear();
		constantForces.clear();	
		
		return position;
	}
	
	private void stayInBounds() {
		if(position.x + CannonBall.cannonBallRadius > Driver.canvasWidth) {
			this.position.x = Driver.canvasWidth + 0.01 - CannonBall.cannonBallRadius;
		}
		if(position.x - CannonBall.cannonBallRadius < 0){
			this.position.x = CannonBall.cannonBallRadius - 0.01;
		}
		if(position.y + CannonBall.cannonBallRadius > Driver.canvasHeight) {
			this.position.y = Driver.canvasHeight + 0.01 - CannonBall.cannonBallRadius;
		}
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
	
	public ArrayList<Vector2> getConstantForces() {
		return constantForces;
	}

	public double getMass() {
		return cannonBallMass;
	}
	
	public void translate(Vector2 v) {
		position = position.add(v);
	}

	public double getCoeficientOfFriction() {
		return cannonBallFrictionCoeficient;
	}
}

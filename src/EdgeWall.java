import java.util.ArrayList;

public class EdgeWall implements Collidable{
	private static final double mass = Double.MAX_VALUE;
	private static final double coeficentOfFriction = 0.05;
	
	WorldBorderCollider collider;
	
	EdgeWall() {
		collider = new WorldBorderCollider(this);
	}
	
	public double getMass() {
		return mass;
	}
	
	public void addForce(Vector2 force) {
		return;
	}

	@Override
	public double getCoeficientOfFriction() {
		return coeficentOfFriction;
	}

	@Override
	public Vector2 getPosition() {
		return Vector2.zero;
	}

	@Override
	public Vector2 getVelocity() {
		return Vector2.zero;
	}
	
	@Override
	public Vector2 getAcceleration() {
		return  Vector2.zero;
	}

	@Override
	public void updatePosition() {
		return;
	}

	@Override
	public Collider getCollider() {
		return collider;
	}

	@Override
	public ArrayList<Vector2> getForces() {
		return new ArrayList<Vector2>(0);
	}

	@Override
	public void setPosition(Vector2 previousPosition) {
		return;
	}

	@Override
	public Vector2 getPreviousPosition() {
		return Vector2.zero;
	}

	@Override
	public boolean isImmobile() {
		return true;
	}

	@Override
	public double getBounciness() {
		return 0;
	}	
	
}

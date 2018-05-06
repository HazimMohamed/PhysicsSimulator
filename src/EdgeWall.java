public class EdgeWall implements Collidable{
	private static final double mass = Double.MAX_VALUE;
	private static final double coeficentOfFriction = 0.2;
	
	public double getMass() {
		return mass;
	}
	
	public Vector2 getVelocity() {
		return Vector2.zero;
	}
	
	public void addImpulseForce(Vector2 force) {
		return;
	}

	@Override
	public double getCoeficientOfFriction() {
		return coeficentOfFriction;
	}
	
	
}

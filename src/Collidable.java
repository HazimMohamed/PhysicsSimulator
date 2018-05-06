public interface Collidable {
	public double getMass();
	public double getCoeficientOfFriction();
	public void addImpulseForce(Vector2 force);
	public Vector2 getVelocity();
}

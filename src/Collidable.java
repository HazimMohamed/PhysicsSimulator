public interface Collidable extends Physical{	
	double getMass();
	
	double getCoeficientOfFriction();
	
	Vector2 getPreviousPosition();
	
	Collider getCollider();

	double getBounciness();
	
	boolean isImmobile();
}

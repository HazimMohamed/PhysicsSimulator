import java.util.ArrayList;

public interface Physical {
	Vector2 getVelocity();
	
	Vector2 getPosition();

	Vector2 getAcceleration();
		
	void setPosition(Vector2 previousPosition);
	
	void addForce(Vector2 force);

	ArrayList<Vector2> getForces();
	
	void updatePosition();
}

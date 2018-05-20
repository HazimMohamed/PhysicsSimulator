public class CircleCollider extends Collider{	
	private double radius;
	
	CircleCollider(Collidable attachedObject, Vector2 offset, double radius){
		super(attachedObject, offset);
		this.radius = radius;
	}
	
	boolean intersects(Collider collider) {
		if(collider instanceof CircleCollider) {
			CircleCollider circleCollider = (CircleCollider) collider;
			double distanceBetween = getDistance(circleCollider.getCenter(), this.getCenter());
			if(distanceBetween <= (circleCollider.getRadius() + this.getRadius()*0.9)) {
				return true;
			}
		}
		
		if(collider instanceof WorldBorderCollider) {
			return collider.intersects(this);
		}
		
		return false;
	}
	
	double getRadius() {
		return radius;
	}
	
	private double getDistance(Vector2 a, Vector2 b) {
		double deltaX = a.x - b.x;
		double deltaY = a.y - b.y;
		
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
	}

	public String toString() {
		return "Circle collider at " + (attachedObject.getPosition().add(offset)) + " with a radius of "  + radius;
	}

	Vector2 getCollisionVector(Collider other) {
		if(other instanceof CircleCollider) {
			System.out.println(other + " and " + this + "collided");
			return other.getCenter().subtract(this.getCenter()).getComponentVector();
		}
		if(other instanceof WorldBorderCollider) {
			return other.getCollisionVector(this);
		}
		return null;
	}
}

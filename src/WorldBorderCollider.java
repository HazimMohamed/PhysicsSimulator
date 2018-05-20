public class WorldBorderCollider extends Collider{
	WorldBorderCollider(Collidable attachedObject) {
		super(attachedObject, Vector2.zero);
	}

	boolean intersects(Collider other) {
		if(other instanceof CircleCollider) {
			CircleCollider circleCollider = (CircleCollider) other;
			
			if(( circleCollider.getCenter().y + circleCollider.getRadius() ) > Driver.canvasHeight) {
				return true;
			}
			if(( circleCollider.getCenter().x + circleCollider.getRadius() ) > Driver.canvasWidth) {
				return true;
			}
			if(circleCollider.getCenter().x < 0) {
				return true;
			}
		}
				
		return false;
	}

	@Override
	public String toString() {
		return "Edge Collider";
	}

	Vector2 getCollisionVector(Collider other) {
		if(other instanceof CircleCollider) {				
			CircleCollider circleCollider = (CircleCollider) other;
							
			if(circleCollider.getCenter().y + circleCollider.getRadius() > Driver.canvasHeight) {
				return new Vector2(0, -1);
			}
			if(circleCollider.getCenter().x + circleCollider.getRadius() > Driver.canvasWidth) {
				return new Vector2(-1, 0);
			}
			if(circleCollider.getCenter().x < 0) {
				return new Vector2(1, 0);
			}
		}
		return null;
	}
}

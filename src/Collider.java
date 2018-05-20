public abstract class Collider {
	Vector2 offset;

	Collidable attachedObject;
	
	Collider(Collidable attachedObject, Vector2 offset){
		this.attachedObject = attachedObject;
		this.offset = offset;
	}

	Vector2 getCenter() {
		return attachedObject.getPosition().add(offset);
	}
	
	public Collidable getAttachedObject() {
		return attachedObject;
	}
	
	public abstract String toString();
	
	abstract boolean intersects(Collider collider);

	abstract Vector2 getCollisionVector(Collider other);
	
}

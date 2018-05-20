public class Collision {
	
	private Collider c1;
	private Collider c2;
	private Vector2 collisionVector;
	
	public Collision(Collider c1, Collider c2) {
		this.c1 = c1;
		this.c2 = c2;
		if(!(c1 instanceof WorldBorderCollider)) {
			collisionVector = c1.getCollisionVector(c2);
		} else {
			collisionVector = c2.getCollisionVector(c1);
		}
	}

	public Vector2 getCollisionVector() {
		return collisionVector;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Collision) {
			Collision o2 = (Collision) o;
			return 
					((this.c1.equals(o2.c1)) && (this.c2.equals(o2.c2))) ||
					((this.c2.equals(o2.c1)) && (this.c1.equals(o2.c2)))
			;
		}
		return false;		
	}

	public Collidable[] getObjects() {
		Collidable[] out = new Collidable[2];
		out[0] = c1.getAttachedObject();
		out[1] = c2.getAttachedObject();
		return out;
	}
	
	@Override
	public String toString() {
		return "Collision between " + c1.toString() + " and " + c2.toString();
	}
}
import java.util.ArrayList;

public class PhysicsManager {
	static final double gravityConstant = 3;
	
	private ArrayList<Physical> objectList = new ArrayList<Physical>(0);
	
	CollisionField collisionField;
	
	PhysicsManager() {
		collisionField = new CollisionField();
	}

	void updatePositions() {
		for(Physical item: objectList) {
			item.addForce(new Vector2(0,PhysicsManager.gravityConstant));
			item.updatePosition();
		}
		
		collisionField.collisionCheck();
		
		while(collisionField.collisionCheck()) {
			for(Collision col: collisionField.getCollions()) {
				onCollision(col);
			}
		}
		
		collisionField.getCollions().clear();
	}
	
	private void onCollision(Collision col) {
		Collidable c1 = col.getObjects()[0];
		Collidable c2 = col.getObjects()[1];
		
		if(c1.isImmobile()){
			Collidable temp = c1;
			c1 = c2;
			c2 = temp;
		}
		
		Vector2 collisionVector = col.getCollisionVector();
				
		Vector2 bounceForce = Vector2.zero;
		
		Vector2 relaventBounceVelocity = c1.getVelocity().getParallelComponent(collisionVector);

		bounceForce = relaventBounceVelocity.multiply(-1 - c1.getBounciness()).multiply(c1.getMass());
		
		c1.setPosition(c1.getPreviousPosition());
		c2.setPosition(c2.getPreviousPosition());
		
		c1.addForce(bounceForce);
		c2.addForce(bounceForce.invert());
		
	}
	
	void addToObjects (Physical item) {
		objectList.add(item);
		if(item instanceof Collidable) {
			if(((Collidable) item).getCollider() != null) {
				collisionField.addCollider(((Collidable) item).getCollider());
			}
		}
	}

	public ArrayList<Physical> getObjects() {
		return objectList;
	}
}

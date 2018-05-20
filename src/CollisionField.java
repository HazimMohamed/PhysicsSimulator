import java.util.ArrayList;

public class CollisionField {
	ArrayList<Collider> colliders;
	ArrayList<Collision> collisions;
	
	EdgeWall edgeWall;
	
	CollisionField(){
		EdgeWall edgeWall = new EdgeWall();
		
		colliders = new ArrayList<Collider>(0);
		colliders.add(edgeWall.getCollider());

		collisions = new ArrayList<Collision>(0);
	}

	public boolean collisionCheck() {
		collisions.clear();
		
		for(Collider c1: colliders) {
			for(Collider c2: colliders) {
				if(c1 == c2) {
					continue;
				} else if(c1.intersects(c2)) {
					collisions.add(new Collision(c1, c2));
				}
			}
		}
		
		collisions = removeDuplicates(collisions);
		
		return collisions.size() > 0;
	}

	private <T> ArrayList<T> removeDuplicates(ArrayList<T> in){
		ArrayList<T> out = new ArrayList<>(0);
		for(T o1: in) {
			boolean toAdd = true;
			for(T o2: out) {
				if(o1.equals(o2)) {
					toAdd = false;
				}
			}
			if(toAdd) {
				out.add(o1);
			}
		}
		return out;
	}
	
	void addCollider(Collider collider) {
		colliders.add(collider);
	}

	public ArrayList<Collision> getCollions() {
		return collisions;
	}

}

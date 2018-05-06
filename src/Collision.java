public class Collision<T extends Collidable> {
	private Vector2 collisionVector;
	private CannonBall cannonBall;
	private T collisionObject;
	
	public Collision(Vector2 collisionVector, CannonBall cannonBall, T collisionObject) {
		this.collisionVector = collisionVector;
		this.cannonBall = cannonBall;
		this.collisionObject = collisionObject;
		onCollision();
	}
	
	private void onCollision() {
		Vector2 normalForce = Vector2.zero;
		
		for(Vector2 constantForce: cannonBall.getConstantForces()) {
			normalForce = normalForce.add(constantForce.getParallelComponent(collisionVector).multiply(-1));
		}
		
		Vector2 relaventBounceVelocity = cannonBall.getVelocity().getParallelComponent(collisionVector);
		
		Vector2 bounceForce = Vector2.zero;
		Vector2 frictionForce = Vector2.zero;
				
		if(relaventBounceVelocity.getMagnitude() > CannonBall.velocityBounceThreshold) {
			bounceForce = relaventBounceVelocity.multiply(-1 - CannonBall.cannonBallBounciness).multiply(CannonBall.cannonBallMass);
			cannonBall.addImpulseForce(bounceForce);
		} else {
			Vector2 relevantFrictionVelocity = cannonBall.getVelocity().getPerpendicularComponent(collisionVector);
			frictionForce  = relevantFrictionVelocity.multiply(-collisionObject.getCoeficientOfFriction()).multiply(CannonBall.cannonBallMass);
			System.out.println("Velocity " + cannonBall.getVelocity());
			System.out.println("Perpendicular velocity: " + cannonBall.getVelocity().getPerpendicularComponent(collisionVector));
			System.out.println("Friction force " + frictionForce);
			System.out.println();
			cannonBall.addConstantForce(frictionForce);
		}

		cannonBall.addConstantForce(normalForce);	
	}
}
	
public class Vector2 {
	public double x;
	public double y;
	
	static final Vector2 zero = new Vector2(0,0);
	
	Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	double getMagnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	double getAngle() {
		double inRad = Math.atan(y/x);
		double degrees = Math.toDegrees(inRad);
		if(degrees < 0) {
			degrees += 360;
		}
		return degrees;
	}
	
	Vector2 getComponentVector() {
		return new Vector2(
				Math.cos(getAngle()),
				Math.sin(getAngle())
		);
	}
	
	Vector2 add(Vector2 v2) {
		return new Vector2(x + v2.x, y + v2.y);
	}
	
	Vector2 multiply(double s) {
		return new Vector2(x * s, y * s);
	}
	
	double dotProduct(Vector2 v2) {
		return x*v2.x + y*v2.y;
	}
	
	boolean isParallel(Vector2 v2) {
		return v2.getComponentVector().equals(this.getComponentVector());
	}
	
	Vector2 getParallelComponent(Vector2 v2) {
		double magnitudeMultiplier = (this.dotProduct(v2)/Math.pow(v2.getMagnitude(),2));
		return v2.multiply(magnitudeMultiplier);
	}
	
	Vector2 getPerpendicularComponent(Vector2 v2) {
		return(this.add(this.getParallelComponent(v2).multiply(-1)));
	}
	
	Vector2 matchQuadrant(Vector2 v2) {
		Vector2 v1 = this.clone();
		if(v2.x > 0) {
			v1 = new Vector2(Math.abs(v1.x),v1.y);
		} else if(v2.x < 0 && v1.x > 0) {
			v1 = new Vector2(-v1.x,v1.y);
		} 
		if(v2.y > 0) {
			v1 = new Vector2(v1.x,Math.abs(v1.x));
		} else if(v2.y < 0 && v1.y > 0) {
			v1 = new Vector2(v1.x,-v1.y);
		}
		return v1;
	}
	
	Vector2 invert() {
		return this.multiply(-1);
	}
	
	@Override
	public boolean equals(Object in) {
		if(in instanceof Vector2) {
			Vector2 v2 = (Vector2) in;
			return v2.x == this.x && v2.y == this.y;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "<"  + x + ", " + y + ">";
	}
	
	@Override
	public Vector2 clone() {
		return new Vector2(this.x, this.y);
	}

	public Vector2 subtract(Vector2 v2) {
		return this.add(v2.multiply(-1));
	}
}

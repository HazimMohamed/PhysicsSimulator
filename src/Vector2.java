public class Vector2 {
	public double x;
	public double y;
	
	static Vector2 zero = new Vector2(0,0);
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getMagnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public double getAngle() {
		double inRad = Math.atan(y/x);
		double degrees = Math.toDegrees(inRad);
		if(degrees < 0) {
			degrees += 360;
		}
		return degrees;
	}
	
	public Vector2 getComponentVector() {
		return new Vector2(
				Math.cos(getAngle()),
				Math.sin(getAngle())
		);
	}
	
	public Vector2 add(Vector2 v2) {
		return new Vector2(x + v2.x, y + v2.y);
	}
	
	public Vector2 multiply(double s) {
		return new Vector2(x * s, y * s);
	}
	
	public double dotProduct(Vector2 v2) {
		return x*v2.x + y*v2.y;
	}
	
	public boolean isParallel(Vector2 v2) {
		return v2.getComponentVector().equals(this.getComponentVector());
	}
	
	public Vector2 getParallelComponent(Vector2 v2) {
		double magnitudeMultiplier = (this.dotProduct(v2)/Math.pow(v2.getMagnitude(),2));
		return v2.multiply(magnitudeMultiplier);
	}
	
	public Vector2 getPerpendicularComponent(Vector2 v2) {
		return(this.add(this.getParallelComponent(v2).multiply(-1)));
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
}

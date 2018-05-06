public class AimingLine {
	private Vector2 start;
	private Vector2 end;
	private boolean isVisible;
	
	public AimingLine() {
		isVisible = false;
	}

	public void setStart(Vector2 start) {
		this.start = start;
	}
	
	public void setEnd(Vector2 end) {
		this.end = end;
	}
	
	public Vector2 getStart() {
		return start;
	}
	
	public Vector2 getEnd(){
		return end;
	}

	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisibility(boolean visibility) {
		isVisible = visibility;
	}
}

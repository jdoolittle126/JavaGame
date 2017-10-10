package jon.game.utils;

public class Point2 {
	public float x, y;
	
	public Point2() {
		x = y = 0;	
	}
	
	public Point2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void transform(Point2 point) {
		this.x += point.x;
		this.y += point.y;
	}
	
	public void transform(Point3 point) {
		this.x += point.x;
		this.y += point.y;
	}
	
	public void transform(float x, float y) {
		this.x += x;
		this.y += y;
	}

}

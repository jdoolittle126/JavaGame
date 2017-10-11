package jon.game.utils;

public class Point2 {
	public float x, y;
	
	public Point2() {
		x = y = 0;	
	}
	
	public Point2(Point2 point) {
		this.x = point.x;
		this.y = point.y;
	}
	
	public Point2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Point2 transform(Point2 point) {
		this.x += point.x;
		this.y += point.y;
		return this;
	}
	
	public Point2 transform(Point3 point) {
		this.x += point.x;
		this.y += point.y;
		return this;
	}
	
	public Point2 transform(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Point2 scale(float scale) {
		this.x *= scale;
		this.y *= scale;
		return this;
	}
	
	public Point2 cpy() {
		return new Point2(this);
	}

}

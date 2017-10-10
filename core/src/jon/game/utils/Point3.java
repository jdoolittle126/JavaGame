package jon.game.utils;

public class Point3 {
	public float x, y, z;
	
	public Point3() {
		x = y = z = 0;	
	}
	
	public Point3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3(Point2 point, float z) {
		this.x = point.x;
		this.y = point.y;
		this.z = z;
	}
	
	
	public void transform(Point3 point) {
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
	}
	
	public void transform(Point2 point) {
		this.x += point.x;
		this.y += point.y;
	}
	
	public void transform(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}
}
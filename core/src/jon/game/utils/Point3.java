package jon.game.utils;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;

public class Point3 {
	public float x, y, z;
	
	public Point3() {
		x = y = z = 0;	
	}
	
	public Point3(Point3 point) {
		this.x = point.x;
		this.y = point.y;
		this.z = point.z;
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
	
	
	public Point3 transform(Point3 point) {
		this.x += point.x;
		this.y += point.y;
		this.z += point.z;
		return this;
	}
	
	public Point3 transform(Point2 point) {
		this.x += point.x;
		this.y += point.y;
		return this;
	}
	
	public Point3 transform(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public Point3 scale(float scale) {
		this.x *= scale;
		this.y *= scale;
		this.z *= scale;
		return this;
	}
	
	public Point3 cpy() {
		return new Point3(this);
	}
	
	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Point3 other = (Point3)obj;
		if (NumberUtils.floatToIntBits(x) != NumberUtils.floatToIntBits(other.x)) return false;
		if (NumberUtils.floatToIntBits(y) != NumberUtils.floatToIntBits(other.y)) return false;
		if (NumberUtils.floatToIntBits(z) != NumberUtils.floatToIntBits(other.z)) return false;
		return true;
	}
	
	@Override
	public String toString () {
		return "(" + x + "," + y + "," + z + ")";
	}
	
}

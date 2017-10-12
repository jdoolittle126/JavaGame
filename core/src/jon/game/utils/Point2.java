package jon.game.utils;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;

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
	
	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Point2 other = (Point2)obj;
		if (NumberUtils.floatToIntBits(x) != NumberUtils.floatToIntBits(other.x)) return false;
		if (NumberUtils.floatToIntBits(y) != NumberUtils.floatToIntBits(other.y)) return false;
		return true;
	}
	
	@Override
	public String toString () {
		return "(" + x + "," + y + ")";
	}

}

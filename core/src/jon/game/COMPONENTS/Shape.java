package jon.game.COMPONENTS;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Shape {
	
	public enum Type {
		Circle, Polygon;
	}
	
	public Vector2 origin, bounda = new Vector2(), boundb = new Vector2();
	public ArrayList<Vector2> data;
	public Type type;
	
	public Shape(Type type, Vector2 origin, Vector2... args) {
		this.type = type;
		this.origin = origin;
		for(Vector2 x : args) {
			if(x.x > bounda.x) bounda.x = x.x;
			if(x.y > bounda.y) bounda.y = x.y;
			if(x.x < boundb.x) boundb.x = x.x;
			if(x.y < boundb.y) boundb.y = x.y;
			
			data.add(x);
		}
		
	}
	
	public void transform(Vector2 v) {
		origin.add(v);
		data.forEach(item->item.add(v));
	}
	
	public void transform(Vector3 v) {
		Vector2 value = new Vector2(v.x, v.y);
		origin.add(value);
		data.forEach(item->item.add(value));
	}
	
	public boolean hasCollision(Vector2 v) {
		if(type == Type.Circle) {
			
		} else {
			
		}
	}
	

}

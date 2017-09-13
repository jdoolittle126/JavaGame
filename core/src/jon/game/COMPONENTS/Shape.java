package jon.game.COMPONENTS;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.CORE.Debugger;
import jon.game.SCREENS.GameScreen;

public class Shape {
	
	public enum Type {
		Circle, Ellipse, Polygon;
	}
	
	public Vector2 origin, bounda = new Vector2(), boundb = new Vector2();
	public ArrayList<Vector2> data =  new ArrayList<Vector2>();
	public Type type;
	public Color color = Color.CYAN;
	
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
	
	public void update() {
		
		Vector2 last = origin.cpy();
		for(Vector2 p : data) {
			Debugger.DrawDebugLine(new Vector3(last.cpy(), 0f), new Vector3(p.cpy(), 0f), 2, color, GameScreen.camera.projection.cpy());
			last = p.cpy();
		}
		Debugger.DrawDebugLine(new Vector3(last.cpy(), 0f), new Vector3(origin.cpy(), 0f), 2, color, GameScreen.camera.projection.cpy());
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
		if(inBoundingBox(bounda.cpy(), boundb.cpy(), v)) {
			if(type == Type.Polygon) {
				boolean test = hasCollisionPoly(v);
				if(test) {
					color = Color.RED;
				} else {
					color = Color.CYAN;
				}
				return test;
			} else if(type == Type.Circle) {
				
			} else if(type == Type.Ellipse) {
				
			}
		}
		
		return false;
	}
	
	public static boolean inBoundingBox(Vector2 point_a, Vector2 point_b, Vector2 point) {
		return ((point.x <= point_a.x && point.y <= point_a.y) && (point.x >= point_b.x && point.y >= point_b.y)) || ((point.x >= point_a.x && point.y >= point_a.y) && (point.x <= point_b.x && point.y <= point_b.y));
	}
	
	private boolean hasCollisionPoly(Vector2 v) {
		Vector2 last = origin.cpy();
		int counter = 0;
		for(Vector2 o : data) {
			if(o.equals(v)) return true;
			Vector2 p = o.cpy();
			Vector2 q = v.cpy();
			Vector2 r = o.cpy().sub(last.cpy());
			Vector2 s = new Vector2((float) -Math.abs(bounda.cpy().sub(boundb.cpy()).x + 10), 0f);
			
			Debugger.DrawDebugLine(new Vector3(bounda.cpy(), 0f), new Vector3(boundb.cpy(), 0f), 2, Color.YELLOW, GameScreen.camera.projection.cpy());
			Debugger.DrawDebugLine(new Vector3(v.cpy(), 0f), new Vector3(v.cpy().add(s.cpy()), 0f), 2, Color.YELLOW, GameScreen.camera.projection.cpy());
			
			float t = ((q.cpy().sub(p.cpy())).crs(s.cpy())) / (r.cpy().crs(s.cpy()));
			float u = ((q.cpy().sub(p.cpy())).crs(r.cpy())) / (r.cpy().crs(s.cpy()));
			
			if(r.cpy().crs(s.cpy()) != 0 && 0 <= t && 0 <= u && t <= 1 && u <= 1) counter++;
			
			last = o.cpy();
		}
		if(counter != 0 && counter % 2 != 0) return true;
		return false;
	}
	
	private boolean hasCollisionEllipse(Vector2 v) {
		return false;
	}
	
	

}

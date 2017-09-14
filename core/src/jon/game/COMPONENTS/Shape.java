package jon.game.COMPONENTS;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.CORE.Debugger;
import jon.game.CORE.MyGdxGame;
import jon.game.SCREENS.GameScreen;

public class Shape {
	
	public boolean debug_draw = true;
	public static boolean debug_draw_bounding = true;
	
	public enum Type {
		Ellipse, Polygon;
	}
	
	public Vector2 origin, bounda = new Vector2(), boundb = new Vector2();
	public ArrayList<Vector2> data =  new ArrayList<Vector2>();
	public Type type;
	public Color color = Color.CYAN;
	
	public Shape(Type type, Vector2 origin, Vector2... args) {
		this.type = type;
		this.origin = origin;
		bounda = origin.cpy();
		boundb = origin.cpy();
				
		
		if(origin.x > bounda.x) bounda.x = origin.x;
		if(origin.y > bounda.y) bounda.y = origin.y;
		if(origin.x < boundb.x) boundb.x = origin.x;
		if(origin.y < boundb.y) boundb.y = origin.y;
		
		for(Vector2 x : args) {
			if(x.x > bounda.x) bounda.x = x.x;
			if(x.y > bounda.y) bounda.y = x.y;
			if(x.x < boundb.x) boundb.x = x.x;
			if(x.y < boundb.y) boundb.y = x.y;
			
			data.add(x);
		}
		
		data.add(origin);
		
		bounda.x += 5;
		bounda.y += 5;
		boundb.x -= 5;
		boundb.y -= 5;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void update() {
		
		Vector2 last = origin.cpy();
		for(Vector2 p : data) {
			if(debug_draw) Debugger.DrawDebugLine(new Vector3(last.cpy(), 0f), new Vector3(p.cpy(), 0f), 2, color, GameScreen.camera.projection);
			last = p.cpy();
		}
		
	}
	
	public void transform(Vector2 v) {
		data.forEach(item->item.add(v));
		bounda.add(v);
		boundb.add(v);
	}
	
	public void transform(Vector3 v) {
		Vector2 value = new Vector2(v.x, v.y);
		data.forEach(item->item.add(value));
		bounda.add(value);
		boundb.add(value);
		
	}
	
	public boolean hasCollision(Vector2 v) {
		if(inBoundingBox(bounda.cpy(), boundb.cpy(), v)) {
			if(type == Type.Polygon) {
				return hasCollisionPoly(v);
				
			} else {
				//TODO add elipse calc
			}
		} else {
			color = Color.CYAN;
		}
		
		return false;
	}
	
	
	public boolean hasCollision(Shape s) {
		//TODO add case where no verticies touch, add shape elipse
		if(inBoundingBox(s.bounda, s.boundb, this.bounda, this.boundb)) {
			color = Color.PINK;
			for(Vector2 item : data){
				if(s.hasCollision(item)) return true;
			}
			for(Vector2 item2 : s.data){
				if(hasCollision(item2))return true;
			}
			
			Vector2 la = s.origin.cpy();
			Vector2 lb = origin.cpy();
			
			System.out.println(s.data.size() + " " + data.size());
			for(int a = 0; a < data.size(); a++){
				System.out.println("test");
				for(int b = 0; b < s.data.size(); b++){
					System.out.println("al " + b);
					if(Shape.linesIntersectNonCollinear(s.data.get(a), data.get(b), la, lb)) return true;
					lb = data.get(b);
				}
				la = s.data.get(a);
			}




			color = Color.CYAN;
			
		} else {
			color = Color.CYAN;
		}
		
		return false;
	}
	
	public static boolean inBoundingBox(Vector2 point_a, Vector2 point_b, Vector2 point) {
		return ((point.x <= point_a.x && point.y <= point_a.y) && (point.x >= point_b.x && point.y >= point_b.y)) || ((point.x >= point_a.x && point.y >= point_a.y) && (point.x <= point_b.x && point.y <= point_b.y));
	}
	
	public static boolean inBoundingBox(Vector2 point_a, Vector2 point_b, Vector2 point_e, Vector2 point_f) {
		Vector2 point_c = new Vector2(point_a.x, point_b.y);
		Vector2 point_d = new Vector2(point_b.x, point_a.y);
		
		Vector2 point_g = new Vector2(point_e.x, point_f.y);
		Vector2 point_h = new Vector2(point_f.x, point_e.y);
		
		if(debug_draw_bounding){
			Debugger.DrawDebugLine(new Vector3(point_a,  0f), new Vector3(point_c,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_a,  0f), new Vector3(point_d,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_b,  0f), new Vector3(point_c,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_b,  0f), new Vector3(point_d,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			
			Debugger.DrawDebugLine(new Vector3(point_e,  0f), new Vector3(point_g,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_e,  0f), new Vector3(point_h,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_f,  0f), new Vector3(point_g,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_f,  0f), new Vector3(point_h,  0f), 3, Color.GREEN, GameScreen.camera.combined);
		}
		
		return (
				Shape.inBoundingBox(point_a, point_b, point_e) || 
				Shape.inBoundingBox(point_a, point_b, point_f) || 
				Shape.inBoundingBox(point_a, point_b, point_g) || 
				Shape.inBoundingBox(point_a, point_b, point_h) || 
				
				Shape.inBoundingBox(point_c, point_d, point_e) || 
				Shape.inBoundingBox(point_c, point_d, point_f) || 
				Shape.inBoundingBox(point_c, point_d, point_g) || 
				Shape.inBoundingBox(point_c, point_d, point_h)
				);
	}
	
	private boolean hasCollisionPoly(Vector2 v) {
		//TODO clean?
		Vector2 last = origin.cpy();
		int counter = 0;
		for(Vector2 o : data) {
			if(o.equals(v)) return true;
			if(Shape.linesIntersectNonCollinearSlope34(o, v, o.cpy().sub(last).scl(-1f), new Vector2((float) -Math.abs(bounda.cpy().sub(boundb.cpy()).x + 100), 0f))) counter++;	
			last = o.cpy();
		}
		if(counter != 0 && counter % 2 != 0) return true;
		return false;
	}
	
	public static boolean linesIntersectNonCollinear(Vector2 p1, Vector2 q1, Vector2 p2, Vector2 q2) {
		
		Vector2 r = p1.cpy().sub(p2).scl(-1f);
		Vector2 s = q1.cpy().sub(q2).scl(-1f);
		
		float t = ((q1.cpy().sub(p1.cpy())).crs(s)) / (r.cpy().crs(s));
		float u = ((q1.cpy().sub(p1)).crs(r)) / (r.cpy().crs(s));
		
		if(r.cpy().crs(s) != 0 && 0 <= t && 0 <= u && t <= 1 && u <= 1) return true;
		return false;
	
	}
	
	public static boolean linesIntersectNonCollinearSlope3(Vector2 p1, Vector2 q1, Vector2 r, Vector2 q2) {
		
		Vector2 s = q1.cpy().sub(q2);
		
		float t = ((q1.cpy().sub(p1.cpy())).crs(s)) / (r.cpy().crs(s));
		float u = ((q1.cpy().sub(p1)).crs(r)) / (r.cpy().crs(s));
		
		if(r.cpy().crs(s) != 0 && 0 <= t && 0 <= u && t <= 1 && u <= 1) return true;
		return false;
	
	}
	
	public static boolean linesIntersectNonCollinearSlope4(Vector2 p1, Vector2 q1, Vector2 p2, Vector2 s) {
		
		Vector2 r = p1.cpy().sub(p2);
		
		float t = ((q1.cpy().sub(p1.cpy())).crs(s)) / (r.cpy().crs(s));
		float u = ((q1.cpy().sub(p1)).crs(r)) / (r.cpy().crs(s));
		
		if(r.cpy().crs(s) != 0 && 0 <= t && 0 <= u && t <= 1 && u <= 1) return true;
		return false;
	
	}
	
	public static boolean linesIntersectNonCollinearSlope34(Vector2 p1, Vector2 q1, Vector2 r, Vector2 s) {
		
		float t = ((q1.cpy().sub(p1.cpy())).crs(s)) / (r.cpy().crs(s));
		float u = ((q1.cpy().sub(p1)).crs(r)) / (r.cpy().crs(s));
		
		if(r.cpy().crs(s) != 0 && 0 <= t && 0 <= u && t <= 1 && u <= 1) return true;
		return false;
	
	}
	
	public static boolean linesIntersectCollinear(Vector2 p1, Vector2 q1, Vector2 p2, Vector2 q2) {
		
		//TODO FINISH
		
		Vector2 r = p1.cpy().sub(p2);
		Vector2 s = q1.cpy().sub(q2);
		
		float t = ((q1.cpy().sub(p1.cpy())).crs(s)) / (r.cpy().crs(s));
		float u = ((q1.cpy().sub(p1)).crs(r)) / (r.cpy().crs(s));
		
		if(r.cpy().crs(s) != 0 && 0 <= t && 0 <= u && t <= 1 && u <= 1) return true;
		return false;
	
	}
	
	public static Vector2 linesIntersect(Vector2 p1, Vector2 q1, Vector2 p2, Vector2 q2) {
		
		Vector2 r = p1.cpy().sub(p2);
		Vector2 s = q1.cpy().sub(q2);
		
		float t = ((q1.cpy().sub(p1.cpy())).crs(s)) / (r.cpy().crs(s));
		float u = ((q1.cpy().sub(p1)).crs(r)) / (r.cpy().crs(s));
		
		if(r.cpy().crs(s) != 0 && 0 <= t && 0 <= u && t <= 1 && u <= 1) return p1.cpy().add(r.scl(t));
		return null;
	
	}
	
	private boolean hasCollisionEllipse(Vector2 v) {
		//TODO finish
		// 0 = (x^2 / factor_x^2) + (y^2 / factor_y^2)
		
		
		return false;
	}
	
	

}
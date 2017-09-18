package jon.game.PHYSICS;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.BASE.GameScreen;
import jon.game.BASE.MyGdxGame;
import jon.game.DEBUG.Debugger;
/* TODO
 * Finish rotation methods
 * Finish Ellipse Methods
 * Check and fix intersect methods
 */

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
	
	public Shape(Shape s) {
		this.debug_draw = s.debug_draw;
		this.type = s.type;
		this.origin = s.origin;
		this.bounda = s.bounda;
		this.boundb = s.boundb;
		this.data = s.data;
		this.color = s.color;
	}
	
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void update() {
		
		if(debug_draw) {
			Vector2 last = origin.cpy();
			for(Vector2 p : data) {
				 Debugger.DrawDebugLine(new Vector3(last.cpy(), 0f), new Vector3(p.cpy(), 0f), 2, color, GameScreen.camera.projection);
				last = p.cpy();
			}
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
				
			}
		}
		
		return false;
	}
	
	
	
	public boolean hasCollision(Shape s) {
		
		if(inBoundingBox(s.bounda, s.boundb, this.bounda, this.boundb)) {
			for(Vector2 item : data){
				if(s.hasCollision(item)) return true;
			}
			for(Vector2 item2 : s.data){
				if(hasCollision(item2)) return true;
			}
			
			Vector2 la = s.origin.cpy();
			Vector2 lb = origin.cpy();
			
			for(int a = 0; a < s.data.size(); a++){
				for(int b = 0; b < data.size(); b++){
					if(Shape.linesIntersectNonCollinear(s.data.get(a), data.get(b), la, lb)) return true;
					lb = data.get(b);
				}
				la = s.data.get(a);
			}




			
		}
		return false;
	}
	
	public static boolean inBoundingBox(Vector2 point_a, Vector2 point_b, Vector2 point) {
		Vector2 a_min = getBoxMin(point_a, point_b);
		Vector2 a_max = getBoxMax(point_a, point_b);
		
		if(a_max.x < point.x) return false;
		if(a_min.x > point.x) return false;
		if(a_max.y < point.y) return false;
		if(a_min.y > point.y) return false;
		return true;
	}
	
	public static boolean inBoundingBox(Vector2 point_a, Vector2 point_b, Vector2 point_e, Vector2 point_f) {
		Vector2 a_min = getBoxMin(point_a, point_b);
		Vector2 a_max = getBoxMax(point_a, point_b);
		
		Vector2 e_min = getBoxMin(point_e, point_f);
		Vector2 e_max = getBoxMax(point_e, point_f);
		
		if(debug_draw_bounding){
			Vector2 point_c = new Vector2(point_a.x, point_b.y);
			Vector2 point_d = new Vector2(point_b.x, point_a.y);
			
			Vector2 point_g = new Vector2(point_e.x, point_f.y);
			Vector2 point_h = new Vector2(point_f.x, point_e.y);
			Debugger.DrawDebugLine(new Vector3(point_a,  0f), new Vector3(point_c,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_a,  0f), new Vector3(point_d,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_b,  0f), new Vector3(point_c,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_b,  0f), new Vector3(point_d,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			
			Debugger.DrawDebugLine(new Vector3(point_e,  0f), new Vector3(point_g,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_e,  0f), new Vector3(point_h,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_f,  0f), new Vector3(point_g,  0f), 3, Color.GREEN, GameScreen.camera.combined);
			Debugger.DrawDebugLine(new Vector3(point_f,  0f), new Vector3(point_h,  0f), 3, Color.GREEN, GameScreen.camera.combined);
		}
		
		if(a_max.x < e_min.x) return false;
		if(a_min.x > e_max.x) return false;
		if(a_max.y < e_min.y) return false;
		if(a_min.y > e_max.y) return false;
		return true;
	}
	
	public static Vector2 getBoxMin(Vector2 point_a, Vector2 point_b){
		Vector2 min = new Vector2();
		if(point_a.x < point_b.x) min.x = point_a.x;
		else min.x = point_b.x;
		
		if(point_a.y < point_b.y) min.y = point_a.y;
		else min.y = point_b.y;
		
		return min;
	}
	
	public static Vector2 getBoxMax(Vector2 point_a, Vector2 point_b){
		Vector2 max = new Vector2();
		if(point_a.x < point_b.x) max.x = point_b.x;
		else max.x = point_a.x;
		
		if(point_a.y < point_b.y) max.y = point_b.y;
		else max.y = point_a.y;
		
		return max;
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
	
	private boolean hasCollisionEllipse(Vector2 v) {
		data.get(0); //top
		data.get(1); //right
		data.get(3); //bot
		data.get(4); //left
		
		// 1 = (x^2 / factor_x^2) + (y^2 / factor_y^2)
		
		
		return false;
	}
	
	public static boolean linesIntersectNonCollinear(Vector2 p1, Vector2 q1, Vector2 p2, Vector2 q2) {
		
		Vector2 r = p2.cpy().sub(p1);
		Vector2 s = q2.cpy().sub(q1);
		
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
	
	public void setDirection(Vector2 rot){
		
	}
	
	public void changeDirection(Vector2 rot){
		
	}
	
	

}

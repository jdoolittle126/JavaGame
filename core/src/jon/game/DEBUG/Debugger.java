package jon.game.DEBUG;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.DEBUG.UTIL.DebugArc;
import jon.game.DEBUG.UTIL.DebugCurve;
import jon.game.DEBUG.UTIL.DebugLine;

public class Debugger {
	//TODO clean and finish
	public static boolean debugging;
	private static boolean whitelist = false;
	private static ArrayList<Object> list =  new ArrayList<Object>();
	
	public static ShapeRenderer debugRenderer = new ShapeRenderer();
	private static ArrayList<DebugLine> lines = new ArrayList<DebugLine>();
	private static ArrayList<DebugCurve> curves = new ArrayList<DebugCurve>();
	private static ArrayList<DebugArc> arcs = new ArrayList<DebugArc>();
	
	private static String level1 = "INFO: ", level2 = "WARNING: ", level3 = "CRITICAL: ";
	
	private static void log_default(int verbose){
		System.out.print("\nTIME: " + System.currentTimeMillis() + "\t|\t");
		switch(verbose){
			case 1:
				System.out.print(level1);
				break;
			case 2:
				System.out.print(level2);
				break;
			case 3:
				System.out.print(level3);
				break;
			default:
				System.out.print(level1);
		}
	}
	
	public static void log(int verbose, String log){
		if(debugging && !whitelist) {
			log_default(verbose);
			System.out.print(log);
		}
	}
	
	public static void log(int verbose, String log, Object object){
		if(debugging && !whitelist) {
			log_default(verbose);
			System.out.print("OBJECT: " + object.toString());
			System.out.print("\t" + log);
		} else if(debugging && whitelist) {
			if(list.contains(object)){
				log_default(verbose);
				System.out.print("OBJECT: " + object.toString());
				System.out.print("\t" + log);
			}
		}
	}
	
	public static void addToList(Object object){
		list.add(object);
	}
	
	public static void remFromList(Object object){
		list.remove(object);
	}
	
	public static void mute(){
		whitelist = true;
	}
	
	public static void unmute(){
		whitelist = false;
	}
	
	public static void DrawDebugLine(Vector3 start, Vector3 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(start, end, lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugLine(Vector2 start, Vector2 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(new Vector3(start, 0f), new Vector3(end, 0f), lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugCurve(Vector3 start, Vector3 end, Vector3 c1, Vector3 c2, int smoothness, int lineWidth, Color color, Matrix4 projectionMatrix) {
		curves.add(new DebugCurve(start, end, c1, c2, smoothness, lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugCurve(Vector2 start, Vector2 end, Vector2 c1, Vector2 c2, int smoothness, int lineWidth, Color color, Matrix4 projectionMatrix) {
		curves.add(new DebugCurve(new Vector3(start, 0f), new Vector3(end, 0f), new Vector3(c1, 0f), new Vector3(c2, 0f), smoothness, lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugArc(Vector3 start, Vector3 end, int smoothness, int lineWidth, Color color, Matrix4 projectionMatrix) {
		arcs.add(new DebugArc(start, end, smoothness, lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugArc(Vector2 start, Vector2 end, int smoothness, int lineWidth, Color color, Matrix4 projectionMatrix) {
		arcs.add(new DebugArc(new Vector3(start, 0f), new Vector3(end, 0f), smoothness, lineWidth, color, projectionMatrix));
	 }
	
	
	public static void draw(){
		
		debugRenderer.begin(ShapeRenderer.ShapeType.Line);
		for(DebugLine x : lines){
	        Gdx.gl.glLineWidth(x.lineWidth);
	        debugRenderer.setProjectionMatrix(x.projectionMatrix);
	        debugRenderer.setColor(x.color);
	        debugRenderer.line(x.start, x.end);
		}
		
		for(DebugCurve x : curves){
	        Gdx.gl.glLineWidth(x.lineWidth);
	        debugRenderer.setProjectionMatrix(x.projectionMatrix);
	        debugRenderer.setColor(x.color);
	        debugRenderer.curve(x.start.x, x.start.y, x.c1.x, x.c1.y, x.c2.x, x.c2.y, x.end.x, x.end.y, x.smoothness);
		}
		
		for(DebugArc x : arcs){
	        Gdx.gl.glLineWidth(x.lineWidth);
	        debugRenderer.setProjectionMatrix(x.projectionMatrix);
	        debugRenderer.setColor(x.color);
	        debugRenderer.arc(x.start.x, x.start.y, x.radius, 0f, (float) Math.toDegrees(x.rot), x.smoothness);
		}
		
        debugRenderer.end();
        
        Gdx.gl.glLineWidth(1);
        lines.clear();
	}
	
	
}

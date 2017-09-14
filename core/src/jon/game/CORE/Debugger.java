package jon.game.CORE;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import jon.game.UTIL.DebugLine;

public class Debugger {
	public static boolean debugging;
	private static boolean whitelist = false;
	private static ArrayList<Object> list =  new ArrayList<Object>();
	public static ShapeRenderer debugRenderer = new ShapeRenderer();
	private static ArrayList<DebugLine> lines = new ArrayList<DebugLine>();
	
	
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
	
	public static void draw(){
		
		debugRenderer.begin(ShapeRenderer.ShapeType.Line);
		for(DebugLine x : lines){
	        Gdx.gl.glLineWidth(x.lineWidth);
	        debugRenderer.setProjectionMatrix(x.projectionMatrix);
	        debugRenderer.setColor(x.color);
	        debugRenderer.line(x.start, x.end);
		}
		
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);
        lines.clear();
	}
	
	
}

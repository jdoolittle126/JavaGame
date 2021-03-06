package jon.game.debug;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.debug.utility.DebugArc;
import jon.game.debug.utility.DebugCurve;
import jon.game.debug.utility.DebugLine;
import jon.game.utils.Point2;
import jon.game.utils.Point3;

public class Debugger {
	//TODO clean and finish
	public static final int MAX_LOGS_PER_SECOND_INFO = 1, MAX_LOGS_PER_SECOND_WARNING = 5, MAX_LOGS_PER_SECOND_CRITICAL = 100;
	public static boolean debugging_graphic, debugging_verbose;
	private static boolean whitelist = false, limit_output = true;
	private static ArrayList<Object> list =  new ArrayList<Object>();
	
	public static ShapeRenderer debugRenderer = new ShapeRenderer();
	private static ArrayList<DebugLine> lines = new ArrayList<DebugLine>();
	private static ArrayList<DebugCurve> curves = new ArrayList<DebugCurve>();
	private static ArrayList<DebugArc> arcs = new ArrayList<DebugArc>();
	
	private static ArrayList<String> logs = new ArrayList<String>();
	private static ArrayList<String> ids = new ArrayList<String>();
	private static ArrayList<Integer> max_limits = new ArrayList<Integer>();
	private static HashMap<String, Integer> limits = new HashMap<String, Integer>();
	
	private static String level1 = "INFO: ", level2 = "WARNING: ", level3 = "CRITICAL: ";
	
	private static float lapsetime = 0f;
	
	private static String log_default(int verbose){
		String time = "\nTIME: " + System.currentTimeMillis() + "\t|\t";
		switch(verbose){
			case 1:
				return time+level1;
			case 2:
				return time+level2;
			case 3:
				return time+level3;
			default:
				return time+level1;
		}
	}
	
	private static int getLimit(int verbose) {
		switch(verbose){
		case 1:
			return MAX_LOGS_PER_SECOND_INFO;
		case 2:
			return MAX_LOGS_PER_SECOND_WARNING;
		case 3:
			return MAX_LOGS_PER_SECOND_CRITICAL;
		default:
			return MAX_LOGS_PER_SECOND_INFO;
	}
	}
	
	public static void log(int verbose, String log, String logid){
		if(debugging_verbose && !whitelist) {
			logs.add(log_default(verbose) + log);
			ids.add(logid);
			max_limits.add(getLimit(verbose));	
		}
	}
	
	public static void log(int verbose, String log, Object object, String logid){
		if(debugging_verbose) {
			if(whitelist) {
				if(list.contains(object)){
					logs.add(log_default(verbose) + "OBJECT: " + object.toString() + "\t" + log);
					ids.add(logid);
					max_limits.add(getLimit(verbose));
				}
			} else  {
				logs.add(log_default(verbose) + "OBJECT: " + object.toString() + "\t" + log);
				ids.add(logid);
				max_limits.add(getLimit(verbose));
			}
		}
	}
	
	public static void log(int verbose, String log, String logid, int limit){
		if(debugging_verbose && !whitelist) {
			logs.add(log_default(verbose) + log);
			ids.add(logid);
			max_limits.add(limit);
		}
	}
	
	public static void log(int verbose, String log, Object object, String logid, int limit){
		if(debugging_verbose) {
			if(whitelist) {
				if(list.contains(object)){
					logs.add(log_default(verbose) + "OBJECT: " + object.toString() + "\t" + log);
					ids.add(logid);
					max_limits.add(limit);
				}
			} else  {
				logs.add(log_default(verbose) + "OBJECT: " + object.toString() + "\t" + log);
				ids.add(logid);
				max_limits.add(limit);
			}
		}
	}
	
	public static void outputLogs(float delta){
		if(limit_output) {
		lapsetime += delta;
		for(int i = 0; i < ids.size(); i++) {
			if(limits.containsKey(ids.get(i))) limits.put(ids.get(i), limits.get(ids.get(i)) + 1);
			else limits.put(ids.get(i), 1);
			
			if((limits.get(ids.get(i)) <= max_limits.get(i).intValue())) printLog(logs.get(i));
		
		}
		
		if(lapsetime >= 1) {
			limits.clear();
			lapsetime = 0;
		}
		
		logs.clear();
		ids.clear();
		max_limits.clear();
		} else {
			for(int i = 0; i < logs.size(); i++) {
				printLog(logs.get(i));
			}
		}
	}
	
	private static void printLog(String log) {
		System.out.print(log);
	}
	
	public static void addToList(Object object){
		list.add(object);
	}
	
	public static void remFromList(Object object){
		list.remove(object);
	}
	
	public static void whitelistMode(boolean mode){
		whitelist = mode;
	}
	
	public static void DrawDebugLine(Vector3 start, Vector3 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(start, end, lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugLine(Vector2 start, Vector2 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(new Vector3(start, 0f), new Vector3(end, 0f), lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugLine(Point3 start, Point3 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(start, end, lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugLine(Point2 start, Point2 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(new Point3(start, 0f), new Point3(end, 0f), lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugLine(Vector2 start, Vector3 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(new Vector3(start, 0), end, lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugLine(Vector3 start, Vector2 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(start, new Vector3(end, 0f), lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugLine(Point2 start, Point3 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(new Point3(start, 0), end, lineWidth, color, projectionMatrix));
	 }
	
	public static void DrawDebugLine(Point3 start, Point2 end, int lineWidth, Color color, Matrix4 projectionMatrix) {
		lines.add(new DebugLine(start, new Point3(end, 0f), lineWidth, color, projectionMatrix));
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
		//font.draw(batch, String.valueOf("FPS: " + Gdx.graphics.getFramesPerSecond()), GameScreen.camera.position.x - ((((GameScreen.camera.viewportWidth * 95) / 100) * GameScreen.camera.zoom) / 2), GameScreen.camera.position.y + ((((GameScreen.camera.viewportHeight * 95) / 100) * GameScreen.camera.zoom) / 2)); 
		if(debugging_graphic) {
			debugRenderer.begin(ShapeRenderer.ShapeType.Line);
			
			for(DebugLine x : lines){
		        debugRenderer.setProjectionMatrix(x.projectionMatrix);
		        debugRenderer.setColor(x.color);
		        debugRenderer.rectLine(x.start.x, x.start.y, x.end.x, x.end.y, x.lineWidth);

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
	 
		}
		lines.clear();
	}
	
	
}

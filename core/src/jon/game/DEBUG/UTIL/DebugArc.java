package jon.game.DEBUG.UTIL;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class DebugArc {
	public Vector3 start, end;
	public float rot, radius;
	public int lineWidth, smoothness;
	public Color color;
	public Matrix4 projectionMatrix;
	
	public DebugArc(Vector3 start, Vector3 end, int smoothness, int lineWidth, Color color, Matrix4 projectionMatrix){
		this.radius = (Math.abs(start.x - end.x) + Math.abs(start.y - end.y)) / 2;
		this.rot = (float) (3*Math.PI / 4);
		this.start = start;
		this.end = end;
		this.smoothness = smoothness;
		this.lineWidth = lineWidth;
		this.color = color;
		this.projectionMatrix = projectionMatrix;
	}
	/*
	public DebugArc(Vector3 start, Vector3 end, float radius, int smoothness, int lineWidth, Color color, Matrix4 projectionMatrix){
		this.start = start;
		this.end = end;
		this.smoothness = smoothness;
		this.lineWidth = lineWidth;
		this.color = color;
		this.projectionMatrix = projectionMatrix;
	}
	
	public DebugArc(Vector3 start, float radius, float rot, int smoothness, int lineWidth, Color color, Matrix4 projectionMatrix){
		this.start = start;
		this.smoothness = smoothness;
		this.lineWidth = lineWidth;
		this.color = color;
		this.projectionMatrix = projectionMatrix;
	}
	*/
}

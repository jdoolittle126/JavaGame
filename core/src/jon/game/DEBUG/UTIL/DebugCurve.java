package jon.game.DEBUG.UTIL;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class DebugCurve {
	public Vector3 start, end, c1, c2;
	public int lineWidth, smoothness;
	public Color color;
	public Matrix4 projectionMatrix;
	
	public DebugCurve(Vector3 start, Vector3 end, Vector3 c1, Vector3 c2, int smoothness, int lineWidth, Color color, Matrix4 projectionMatrix){
		this.start = start;
		this.end = end;
		this.c1 = c1;
		this.c2 = c2;
		this.smoothness = smoothness;
		this.lineWidth = lineWidth;
		this.color = color;
		this.projectionMatrix = projectionMatrix;
	}
}

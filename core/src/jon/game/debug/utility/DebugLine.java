package jon.game.debug.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class DebugLine {
	public Vector3 start;
	public Vector3 end;
	public int lineWidth;
	public Color color;
	public Matrix4 projectionMatrix;
	
	public DebugLine(Vector3 start, Vector3 end, int lineWidth, Color color, Matrix4 projectionMatrix){
		this.start = start;
		this.end = end;
		this.lineWidth = lineWidth;
		this.color = color;
		this.projectionMatrix = projectionMatrix;
	}
	
}

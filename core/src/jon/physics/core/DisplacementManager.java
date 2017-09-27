package jon.physics.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class DisplacementManager {
	public Velocity velocity, velocity_old, velocity_goal;
	public Acceleration acceleration;
	public Vector2 coords;
	
	public DisplacementManager() {
		
	}
	
	public void moveAtPoint(Vector2 point, float mod){
		velocity_goal = Velocity.moveAtPoint(coords, point, mod);
	}
	
	public void moveAtPoint(Vector2 point, float rad, float mod){
		velocity_goal = Velocity.moveAtPoint(coords, point, rad, mod);
	}
	
	public void moveAtAngle(float rad, float mod){
		velocity_goal = Velocity.moveAtAngle(coords, rad, mod);
	}
	
	public void update() {
		
		//velocity.add(acceleration)
		float t = velocity_goal.velocity.len();
		velocity.velocity.clamp(-t, t);
		
		
		velocity_old = velocity;
	}

}

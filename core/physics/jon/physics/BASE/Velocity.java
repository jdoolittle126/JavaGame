package jon.physics.BASE;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Velocity {
	public Vector3 velocity;
	public Acceleration accel;
	
	//d = d + vt + 1/2 a t^2
	
	public Velocity(Vector3 base){
		this.velocity = base;
	
	}
	
	public Velocity(Vector2 base){
		this.velocity = new Vector3(base.x, base.y, 0f);
	}
	
	public Velocity(){
		this.velocity = new Vector3();
	}
	
	public Velocity provide(Vector3 velocity){
		this.velocity.add(velocity).scl(0.5f);
		return this;
	}
	
	public Velocity deprive(Vector3 velocity){
		return this;
	}
	
	public Velocity add(Vector3 velocity){
		this.velocity.add(velocity);
		return this;
	}
	
	public Velocity sub(Vector3 velocity){
		this.velocity.sub(velocity);
		return this;
	}
	
	public static Velocity moveAtPoint(Vector3 start, Vector3 finish, float stat){
		return new Velocity();
	}
	
	public static Velocity moveAtPoint(Vector3 start, Vector3 finish, float rad, float stat){
		return new Velocity();
	}
	
	public static Velocity moveAtAngle(Vector3 start, float rad, float stat){
		// ONLY FOR 2D
		Vector3 vel = new Vector3((float) Math.cos(rad),(float) Math.sin(rad), 0);
		float h = getHyp(vel);
		vel.x /= (h / stat);
		vel.y /= (h / stat);
		vel.z = 0;		
		return new Velocity(vel);
	}
	
	public static float getHyp(Vector3 t){
		return (float) ((float) Math.sqrt(Math.pow(t.x, 2) + Math.pow(t.y, 2) + Math.pow(t.z, 2)));
	}
	
	public Vector3 update(float delta){
		return this.velocity.cpy().scl(delta);
	}
	
	public Velocity set(Vector3 velocity){
		this.velocity = velocity;
		return this;
	}
	
	public Velocity scl(float f){
		this.velocity.scl(f);
		return this;
	}
	
	public Vector3 get(){
		return this.velocity;
	}
	
}

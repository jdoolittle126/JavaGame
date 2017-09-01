package jon.game.CORE;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.ENTITY.Entity;

public class GameCamera extends OrthographicCamera {
	private long start_time;
	private float smoothing = 0;
	private Vector3 velocity = new Vector3(0f, 0f, 0f);
	private float speed = 1;
	private float counter = 0, total = 0;
	private boolean active = false;
	
	public String debug_counter, debug_pos, debug_velocity, debug_waiting;
	
	public GameCamera(){
		
	}

	public void lerpTo(Vector3 coords, float speed, float smoothing){
		Debugger.log(1, "Method lerpTo called with params: " + coords.toString() + ", " + speed + ", " + smoothing, this);
		active = true;
		start_time = System.currentTimeMillis();
		this.smoothing = smoothing;
		this.speed = speed;
		this.getVelocity(coords);
	}
	
	public void lerpOffset(Vector3 offset, float speed, float smoothing){
		Debugger.log(1, "Method lerpOffset called with params: " + offset.toString() + ", " + speed + ", " + smoothing, this);
		active = true;
		start_time = System.currentTimeMillis();
		this.smoothing = smoothing;
		this.speed = speed;
		this.getVelocity(new Vector3(this.position.x + offset.x, this.position.y + offset.y, this.zoom + offset.z));
	}
	
	public void lerpPath(ArrayList<Vector3> coords, float speed, float smoothing){
		Debugger.log(1, "Method lerpPath called with params: " + coords.toString() + ", " + speed + ", " + smoothing, this);
	}
	
	public void lockTo(Entity puppet, boolean locked, float smoothing, Vector2 lockPos){
		Debugger.log(1, "Method lockTo called with params: " + puppet.toString() + ", " + locked + ", " + smoothing + ", " + lockPos.toString(), this);
		start_time = System.currentTimeMillis();
		this.smoothing = smoothing;
		
	}
	
	private void getVelocity(Vector3 coords){
		float side_x = coords.x- this.position.x;
		float side_y = coords.y - this.position.y;
		float side_z = coords.z - this.zoom;
		float total_side = (float) (Math.sqrt(Math.pow(side_x, 2) + Math.pow(side_y, 2) + Math.pow(this.position.z * side_z, 2)));
		
		total = (total_side) / (speed * 500);
		velocity.x = (side_x/total_side) * 500 * speed;
		velocity.y = (side_y/total_side) * 500 * speed;
		velocity.z = (side_z/total_side) * 500 * speed;
	}

	public void update(float delta) {
	
		if(active){
			if(smoothing != 0){
				if(smoothing * 100 <= System.currentTimeMillis() - start_time){
					this.translate(velocity.x * delta, velocity.y * delta, velocity.z * delta);
					this.zoom += velocity.z * delta;
					counter += delta;
		
					//Debug Strings
					debug_counter = "DEBUG_COUNTER Counter: " + counter + " Total: " + total;
					debug_pos = "DEBUG_POS X: " + this.position.x + " Y: " + this.position.y + " Z: " + this.position.z + " ZOOM: " + this.zoom;
					debug_velocity = "DEBUG_VELOCITY X: " + velocity.x + " Y: " + velocity.y + " Z: " + velocity.z + " ZOOM: " + this.zoom;
					
					Debugger.log(1, debug_counter, this);
					Debugger.log(1, debug_pos, this);
					Debugger.log(1, debug_velocity, this);
					
				} else {
					
					//Debug Strings
					debug_waiting = "DEBUG_WAITING Camera Smoothing delay";
					
					Debugger.log(1, debug_waiting, this);
				}

			} else {
				this.translate(velocity.x * delta, velocity.y * delta, velocity.z * delta);
				this.zoom += velocity.z * delta;
				counter += delta;
			}
			
			if(counter >= total && total != -1f){
				velocity = new Vector3();
				counter = 0;
				total = -1;
				active = false;
			}
		}
		
		super.update();
	}
	
	

}

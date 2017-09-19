package jon.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

import jon.game.core.MyGdxGame;
import jon.game.debug.Debugger;
import jon.game.enums.Action;
import jon.game.screens.GameScreen;
import jon.game.statistics.BaseStatistics;
import jon.game.statistics.MovementStatistics;

public abstract class EntityDynamic extends Entity {
	
	public MovementStatistics movement_stats = new MovementStatistics();
	
	public Vector3 velocity = new Vector3();
	protected float delta_x = 0f, delta_y = 0f;
	private ArrayList<Action> que = new ArrayList<Action>();
	
	public EntityDynamic(){
		super();
	}
	
	public abstract void initStats();
	
	@Override
	public void update(float delta){
		for(Action a : this.getQue()){
			switch(a){
				case action_forward:
					action_forward();
					break;
				case action_backwards:
					action_backwards();
					break;
				case action_left:
					action_left();
					break;
				case action_right:
					action_right();
					break;
				default:
					break;
				}
			}
		
		this.transform(velocity.scl(delta));
		super.update(delta);
	}
	
	
	// - Action - \\
	public void startAction(Action action){
		que.add(action);
	}
	
	public void endAction(Action action){
		switch(action){
			case action_forward:
				action_forward_end();
				break;
			case action_backwards:
				action_backwards_end();
				break;
			case action_left:
				action_left_end();
				break;
			case action_right:
				action_right_end();
				break;
			default:
				break;
		}
		que.remove(action);
	}
	
	public void stop(){
		this.setVelocity(new Vector3(0, 0, 0));
	}
	
	public void remvel(Vector3 velocity) {
		this.velocity.sub(velocity);
	}
	//TODO cleanup and add more methods for more cases
	public void moveTo(Vector3 target, float stat){
		Vector3 vel = target.cpy();
		vel.sub(this.coords);
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		
		Debugger.DrawDebugLine(this.coords, MyGdxGame.mouse_coords_world, 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
		
		//Maybe make these be able 2 and up and stuff
	}
	
	public static void moveTo(Vector3 target, EntityDynamic e, float stat) {
		Vector3 vel = target.cpy();
		vel.sub(e.coords);
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		e.velocity = vel;
		
		
	}
	
	public void moveAt(float rad, float stat) {
		Vector3 vel = new Vector3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		Debugger.DrawDebugLine(this.coords, MyGdxGame.mouse_coords_world, 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
	}
	
	//Add others of this method
	public void moveAt(Vector3 target, float rad, float stat) {
		
		Vector3 vel = target.cpy();
		vel.sub(this.coords);
		
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		
		vel.x = -vel.x + (float) Math.cos(rad+ROT_OFFSET);
		vel.y = -vel.y + (float) Math.sin(rad+ROT_OFFSET);
		this.velocity.set(vel);
		
		Debugger.DrawDebugLine(this.coords, MyGdxGame.mouse_coords_world, 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
	}
	
	public void moveAt(double rad, float stat) {
		Vector3 vel = new Vector3((float) Math.cos(rad+ROT_OFFSET),(float) Math.sin(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		
		Debugger.DrawDebugLine(this.coords, MyGdxGame.mouse_coords_world, 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
	}
	
	public void moveAt(float rad, EntityDynamic e, float stat) {
		Vector3 vel = new Vector3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		e.velocity = vel;
	}
	
	public void moveAt(double rad, EntityDynamic e, float stat) {
		Vector3 vel = new Vector3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		e.velocity = vel;
	}
	
	public void setVelocity(Vector3 velocity) {
		this.velocity.set(velocity);
	}

	public ArrayList<Action> getQue() {
		return que;
	}

	public void setQue(ArrayList<Action> que) {
		this.que = que;
	}

}

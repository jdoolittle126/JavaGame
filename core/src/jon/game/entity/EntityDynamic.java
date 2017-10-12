package jon.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;

import jon.game.core.MyGdxGame;
import jon.game.debug.Debugger;
import jon.game.enums.Action;
import jon.game.screens.GameScreen;
import jon.game.statistics.BaseStatistics;
import jon.game.statistics.MovementStatistics;
import jon.game.utils.Point3;

public abstract class EntityDynamic extends Entity {
	
	private Json json = new Json();
	
	public MovementStatistics movement_stats = new MovementStatistics();
	protected float delta_x = 0, delta_y = 0;
	public Point3 velocity = new Point3();
	private ArrayList<Action> que = new ArrayList<Action>();
	
	public EntityDynamic(){
		super();
	}
	
	public abstract void initStats();
	
	@Override
	public void update(float delta, SpriteBatch batch){
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
		
		this.transform(velocity.cpy().scale(delta));
		super.update(delta, batch);
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
		this.setVelocity(new Point3());
	}
	
	public void remvel(Point3 velocity) {
		this.velocity.transform(velocity.cpy().scale(-1));
	}
	//TODO cleanup and add more methods for more cases
	public void moveTo(Point3 target, float stat){
		Point3 vel = target.cpy();
		vel.transform(this.coords.cpy().scale(-1f));
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		
		Debugger.DrawDebugLine(this.coords, MyGdxGame.getMouseCoordsWorld(), 3, Color.RED, MyGdxGame.getMatrix());
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scale(100f), 3, Color.BLUE, MyGdxGame.getMatrix());
		
		//Maybe make these be able 2 and up and stuff
	}
	
	public static void moveTo(Point3 target, EntityDynamic e, float stat) {
		Point3 vel = target.cpy();
		vel.transform(e.coords.cpy().scale(-1f));
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		e.velocity = vel;
		
	}
	
	public void moveAt(float rad, float stat) {
		Point3 vel = new Point3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		Debugger.DrawDebugLine(this.coords, MyGdxGame.getMouseCoordsWorld(), 3, Color.RED, MyGdxGame.getMatrix());
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scale(100f), 3, Color.BLUE, MyGdxGame.getMatrix());
	}
	
	//Add others of this method
	public void moveAt(Point3 target, float rad, float stat) {
		
		Point3 vel = target;
		vel.transform(this.coords.cpy().scale(-1f));
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		
		vel.x = -vel.x + (float) Math.cos(rad+ROT_OFFSET);
		vel.y = -vel.y + (float) Math.sin(rad+ROT_OFFSET);
		this.velocity = vel;
		Debugger.DrawDebugLine(this.coords, MyGdxGame.getMouseCoordsWorld(), 3, Color.RED, MyGdxGame.getMatrix());
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scale(100f), 3, Color.BLUE, MyGdxGame.getMatrix());
	}
	
	public void moveAt(double rad, float stat) {
		Point3 vel = new Point3((float) Math.cos(rad+ROT_OFFSET),(float) Math.sin(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		
		Debugger.DrawDebugLine(this.coords, MyGdxGame.getMouseCoordsWorld(), 3, Color.RED, MyGdxGame.getMatrix());
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scale(100f), 3, Color.BLUE, MyGdxGame.getMatrix());
	}
	
	public void moveAt(float rad, EntityDynamic e, float stat) {
		Point3 vel = new Point3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		e.velocity = vel;
	}
	
	public void moveAt(double rad, EntityDynamic e, float stat) {
		Point3 vel = new Point3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		e.velocity = vel;
	}
	
	public void setVelocity(Point3 point3) {
		this.velocity = point3;
	}

	public ArrayList<Action> getQue() {
		return que;
	}

	public void setQue(ArrayList<Action> que) {
		this.que = que;
	}
	


}

package jon.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Json;

import jon.game.core.ActionMethods;
import jon.game.core.GameClient;
import jon.game.debug.Debugger;
import jon.game.enums.Action;
import jon.game.statistics.BaseStatistics;
import jon.game.statistics.MovementStatistics;
import jon.game.utils.Point3;

public abstract class EntityDynamic extends Entity implements ActionMethods {
	
	public MovementStatistics movement_stats;
	public boolean scale_velocity = true;
	public Point3 velocity = new Point3();
	public ArrayList<Point3> velocity_collection = new ArrayList<Point3>();
	private ArrayList<Action> que = new ArrayList<Action>();
	
	public EntityDynamic(){
		super();
		this.movement_stats = new MovementStatistics();
	}
	
	public EntityDynamic(BaseStatistics base_stats){
		super(base_stats);
		this.movement_stats = new MovementStatistics();
	}
	
	public EntityDynamic(BaseStatistics base_stats, MovementStatistics movement_stats){
		super(base_stats);
		this.movement_stats = movement_stats;
	}
	
	@Override
	public void act(float delta) {
		for(Action a : this.getQue()){
			switch(a){
				case action_forward:
					plus_action_forward(delta);
					break;
				case action_backwards:
					plus_action_backwards(delta);
					break;
				case action_left:
					plus_action_left(delta);
					break;
				case action_right:
					plus_action_right(delta);
					break;
				default:
					break;
				}
			}
		
		velocity = new Point3();
		if(scale_velocity) {
			int i = 0;
			for(Point3 x : velocity_collection){
				velocity.transform(x);
				i++;
			}
			if(i>0) velocity.scale((float) 1/i);
		} else {
			for(Point3 x : velocity_collection){
				velocity.transform(x);
			}
		}
		
		this.transform(velocity.cpy().scale(delta));
		velocity_collection.clear();
		
	}

	// - Action - \\
	public void startAction(Action action){
		que.add(action);
	}
	
	public void endAction(Action action){
		switch(action){
			case action_forward:
				//minus_action_forward();
				break;
			case action_backwards:
				//minus_action_backwards();
				break;
			case action_left:
				//minus_action_left();
				break;
			case action_right:
				//minus_action_right();
				break;
			default:
				break;
		}
		que.remove(action);
	}
	
	public void remvel(Point3 velocity) {
		this.velocity.transform(velocity.cpy().scale(-1));
	}
	
	public Point3 moveTo(Point3 target, float stat){
		Point3 vel = target.cpy();
		vel.transform(this.coords.cpy().scale(-1f));
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;	
		return vel;
	}
	
	
	public Point3 moveAt(float rad, float stat) {
		Point3 vel = new Point3((float) Math.cos(rad),(float) Math.sin(rad), 0);
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		
		return vel;

	}
	
	public Point3 moveAt(double rad, float stat) {
		return moveAt((float) rad, stat);
	}
	
	public Point3 moveAt(Point3 target, float rad, float stat) {
		Point3 vel = target.cpy();
		vel.transform(this.coords.cpy().scale(-1f));
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;	
		
		float nx = (float) ((vel.x * Math.cos(rad)) - (vel.y * Math.sin(rad)));
		float ny = (float) ((vel.x * Math.sin(rad)) + (vel.y * Math.cos(rad)));
		
		vel.x = nx;
		vel.y = ny;

		return vel;
	}
	
	
	public static Point3 moveTo(Point3 target, EntityDynamic e, float stat) {
		Point3 vel = target.cpy();
		vel.transform(e.coords.cpy().scale(-1f));
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;	
		return vel;
	}
	
	
	public static Point3 moveAt(float rad, EntityDynamic e, float stat) {
		Point3 vel = new Point3((float) Math.cos(rad),(float) Math.sin(rad), 0);
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		
		return vel;

	}
	
	public static Point3 moveAt(double rad, EntityDynamic e, float stat) {
		return moveAt((float) rad, e, stat);
	}
	
	public static Point3 moveAt(Point3 target, float rad, EntityDynamic e, float stat) {
		Point3 vel = target.cpy();
		vel.transform(e.coords.cpy().scale(-1f));
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.movement_stats.stat_speed_base * stat * 100));
		vel.z = 0;	
		
		float nx = (float) ((vel.x * Math.cos(rad)) - (vel.y * Math.sin(rad)));
		float ny = (float) ((vel.x * Math.sin(rad)) + (vel.y * Math.cos(rad)));
		
		vel.x = nx;
		vel.y = ny;

		return vel;
	}
	
	
	public void moveBy(Point3 point3) {
		this.transform(point3);
	}
	
	public void setVelocity(Point3 point3) {
		this.velocity = point3;
	}
	
	public void addVelocity(Point3 point3) {
		this.velocity_collection.add(point3);
	}
	
	public void subVelocity(Point3 point3) {
		this.velocity_collection.add(point3.scale(-1f));
	}

	public ArrayList<Action> getQue() {
		return que;
	}

	public void setQue(ArrayList<Action> que) {
		this.que = que;
	}

}

package jon.game.ENTITY;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import jon.game.COMPONENTS.Animation;
import jon.game.CORE.Debugger;
import jon.game.CORE.GameObject;
import jon.game.CORE.ObjectType;
import jon.game.CORE.MANAGERS.Actions;
import jon.game.INIT.MyGdxGame;
import jon.game.SCREENS.GameScreen;
import jon.game.UTIL.BaseStatistics;

public abstract class Entity extends GameObject implements ActionMethods {
	
	// - Default Stats - \\
	public BaseStatistics base_stats = new BaseStatistics();
	
	// ----------------- \\
	public Vector3 velocity = new Vector3(0f, 0f, 0f);
	public Vector3 coords = new Vector3(0f, 0f, 0f);
	public float rotation =  0f;
	
	private final double ROT_OFFSET = (Math.PI);
	
	private HashMap<Actions, Animation> animations = new HashMap<Actions, Animation>();
	private ArrayList<Actions> que = new ArrayList<Actions>();
	private Texture spriteSheet;
	
	public Entity(){
		initStats();
	}
	
	public abstract void initStats();
	
	// - Direction - \\
	public void setDirection(float rads){
		this.rotation = rads;
	}
	
	public void changeDirection(float rads){
		this.rotation += rads;
	}
	
	public void setDirection(double rads){
		this.rotation = (float) rads;
	}
	
	public void changeDirection(double rads){
		this.rotation += (float) rads;
	}
	
	// - Animation - \\
	public void playAnimation(Animation animation){
		
	}
	
	public void playAnimation(Animation animation, float overrideSpeed){
		
	}
	
	public void playAnimation(Actions action){
		
	}
	
	public void playAnimation(Actions action, float overrideSpeed){
		
	}
	
	// - Actions - \\
	public void startAction(Actions action){
		que.add(action);
	}
	
	public void endAction(Actions action){
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
	
	// - Movement - \\
	public void transform(Vector3 vec){
		coords.add(vec);
	}
	
	public void stop(){
		this.setVelocity(new Vector3(0, 0, 0));
	}
	
	public void remvel(Vector3 velocity) {
		this.velocity = this.velocity.sub(velocity);
	}
	
	public void setPos(Vector3 coords) {
		this.coords = coords;
	}
	
	public void moveTo(Vector3 target, float stat){
		Vector3 vel = target.cpy();
		vel.sub(this.coords.cpy());
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
	}
	
	public static void moveTo(Vector3 target, Entity e, float stat) {
		Vector3 vel = target.cpy();
		vel.sub(e.coords.cpy());
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.base_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.base_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		e.velocity = vel;
	}
	
	public void moveAt(float rad, float stat) {
		Vector3 vel = new Vector3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		Debugger.DrawDebugLine(this.coords.cpy(), MyGdxGame.mouse_coords_world.cpy().scl(100f), 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords.cpy(), this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
	}
	
	public void moveAt(double rad, float stat) {
		Vector3 vel = new Vector3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		Debugger.DrawDebugLine(this.coords.cpy(), MyGdxGame.mouse_coords_world.cpy().scl(100f), 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords.cpy(), this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
	}
	
	public void moveAt(float rad, Entity e, float stat) {
		Vector3 vel = new Vector3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.base_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.base_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		e.velocity = vel;
	}
	
	public void moveAt(double rad, Entity e, float stat) {
		Vector3 vel = new Vector3((float) Math.sin(rad+ROT_OFFSET),(float) Math.cos(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.base_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (e.base_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		e.velocity = vel;
	}
	
	

	@Override
	public void update(float delta) {
		
		for(Actions a : this.getQue()){
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
	
		this.transform(velocity.cpy().scl(delta));
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public ObjectType getType(){
		return ObjectType.object_entity_basic;
	}

	
	public void lookAtMouse(){
		Vector3 coords_copy = this.getCoords().cpy();
		coords_copy.sub(MyGdxGame.mouse_coords_world);
		if(coords_copy.x == 0){
			if(coords_copy.y < 0) this.setDirection(0f);
			else this.setDirection(Math.PI);
		} else if(coords_copy.y == 0){
			if(coords_copy.x > 0) this.setDirection(Math.PI / 2);
			else this.setDirection(3 * Math.PI / 2);
		} else {
			this.setDirection(Math.atan2(coords_copy.y, coords_copy.x) + (Math.PI / 2));
		}
	}
	
	// CLEAN UP L8R
	public Vector3 getCoords() {
		return coords;
	}

	public void setCoords(Vector3 coords) {
		this.coords = coords;
	}

	public Texture getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(Texture spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
	}

	public ArrayList<Actions> getQue() {
		return que;
	}

	public void setQue(ArrayList<Actions> que) {
		this.que = que;
	}
	
	
	
	
}

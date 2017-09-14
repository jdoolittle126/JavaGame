package jon.game.ENTITY;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import jon.game.COMPONENTS.Action;
import jon.game.COMPONENTS.Animation;
import jon.game.CORE.Debugger;
import jon.game.CORE.GameObject;
import jon.game.CORE.MyGdxGame;
import jon.game.CORE.ObjectType;
import jon.game.SCREENS.GameScreen;
import jon.game.UTIL.BaseStatistics;

public abstract class Entity extends GameObject implements ActionMethods {
	
	// - Default Stats - \\
	public BaseStatistics base_stats = new BaseStatistics();
	
	// ----------------- \\
	public Vector3 velocity = new Vector3(0f, 0f, 0f);
	private Vector3 test_velocity = new Vector3(0f, 0f, 0f);
	public Vector3 coords = new Vector3(0f, 0f, 0f);
	public float rotation =  0f;
	
	private final double ROT_OFFSET = (Math.PI/2);
	
	private HashMap<Action, Animation> animations = new HashMap<Action, Animation>();
	private ArrayList<Action> que = new ArrayList<Action>();
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
	
	public void playAnimation(Action action){
		
	}
	
	public void playAnimation(Action action, float overrideSpeed){
		
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
		vel.sub(this.coords);
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		
		Debugger.DrawDebugLine(this.coords, MyGdxGame.mouse_coords_world, 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
		
		//Maybe make these be able 2 and up and stuff
	}
	
	public static void moveTo(Vector3 target, Entity e, float stat) {
		Vector3 vel = target.cpy();
		vel.sub(e.coords);
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
		Debugger.DrawDebugLine(this.coords, MyGdxGame.mouse_coords_world, 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
	}
	
	//Add others of this method
	public void moveAt(Vector3 target, float rad, float stat) {
		
		Vector3 vel = target.cpy();
		vel.sub(this.coords);
		
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		
		vel.x = -vel.x + (float) Math.cos(rad+ROT_OFFSET);
		vel.y = -vel.y + (float) Math.sin(rad+ROT_OFFSET);
		this.velocity = vel;
		
		Debugger.DrawDebugLine(this.coords, MyGdxGame.mouse_coords_world, 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
	}
	
	public void moveAt(double rad, float stat) {
		Vector3 vel = new Vector3((float) Math.cos(rad+ROT_OFFSET),(float) Math.sin(rad+ROT_OFFSET), 0);
		
		float h = (float) ((float) Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.y /= (h / (this.base_stats.stat_speed_base * stat * 100));
		vel.z = 0;		
		this.velocity = vel;
		
		Debugger.DrawDebugLine(this.coords, MyGdxGame.mouse_coords_world, 3, Color.RED, GameScreen.camera.combined);
		Debugger.DrawDebugLine(this.coords, this.velocity.cpy().scl(100f), 3, Color.BLUE, GameScreen.camera.combined);
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
		test_velocity.setZero();
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
		
		//this.velocity = test_velocity.cpy();
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

	public ArrayList<Action> getQue() {
		return que;
	}

	public void setQue(ArrayList<Action> que) {
		this.que = que;
	}
	
	
	
	
}

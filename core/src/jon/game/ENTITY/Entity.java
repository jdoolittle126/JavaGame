package jon.game.ENTITY;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import jon.game.COMPONENTS.Animation;
import jon.game.CORE.GameObject;
import jon.game.CORE.ObjectType;
import jon.game.CORE.MANAGERS.Actions;
import jon.game.INIT.MyGdxGame;
import jon.game.UTIL.Statistics;

public abstract class Entity extends GameObject implements ActionMethods {
	
	// - Default Stats - \\
	public Statistics stats = new Statistics();
	
	// ----------------- \\
	public Vector3 velocity = new Vector3(0f, 0f, 0f);
	public Vector3 coords = new Vector3(0f, 0f, 0f);
	public float rotation =  0f;
	
	private HashMap<Actions, Animation> animations;
	private ArrayList<Actions> que = new ArrayList<Actions>();
	private Texture spriteSheet;
	
	public Entity(){
		initStats();
	}
	
	public void initStats(){
		
	}
	
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
	
	public void playAnimation(Animation animation){
		
	}
	
	public void playAnimation(Animation animation, float overrideSpeed){
		
	}
	
	public void playAnimation(Actions action){
		
	}
	
	public void playAnimation(Actions action, float overrideSpeed){
		
	}
	
	
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
	
	public void transform(Vector3 vec){
		coords.add(vec);
	}
	
	public void stop(){
		this.setVelocity(new Vector3(0, 0, 0));
	}
	
	public void moveTo(Vector3 coords){
		Vector3 test = coords.cpy();
		test.sub(this.getCoords().cpy());
		float h = (float) ((float) 0.01f * Math.sqrt(Math.pow(test.x, 2) + Math.pow(test.y, 2)));
		test.x /= (h / (this.stats.stat_speed_base * this.stats.stat_speed_mod_forward));
		test.y /= (h / (this.stats.stat_speed_base * this.stats.stat_speed_mod_forward));
		test.z = 0;		
		this.setVelocity(test);
	}
	
	public void moveTo(Vector3 target, Entity e) {
		Vector3 vel = target.cpy();
		vel.sub(e.coords.cpy());
		float h = (float) ((float) 0.01f * Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (e.stats.stat_speed_base * e.stats.stat_speed_mod_forward));
		vel.y /= (h / (e.stats.stat_speed_base * e.stats.stat_speed_mod_forward));
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

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
import jon.game.UTIL.Radian;
import jon.game.UTIL.SimpleStats;

public abstract class Entity extends GameObject implements ActionMethods {
	
	// - Default Stats - \\
	private float base_speed = 1;
	private float mod_left = 0.75f, mod_forward = 1f, mod_right = 0.75f, mod_backwards = 0.5f;
	private float strength = 1f, weight = 100f;
	
	// ----------------- \\
	private Vector3 velocity = new Vector3(0f, 0f, 0f);
	private Vector3 coords = new Vector3(0f, 0f, 0f);
	private Radian rotation =  new Radian(0f);
	
	private HashMap<Actions, Animation> animations;
	private ArrayList<Actions> que = new ArrayList<Actions>();
	private Texture spriteSheet;
	
	public Entity(){

	}
	
	public Entity(SimpleStats stats){
		
	}
	
	public void setDirection(Radian radian){
		rotation.setValue(radian);
	}
	
	public void changeDirection(Radian radian){
		rotation.addTo(radian);
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
		test.x /= (h / (this.getBase_speed() * this.getMod_forward()));
		test.y /= (h / (this.getBase_speed() * this.getMod_forward()));
		test.z = 0;		
		this.setVelocity(test);
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
			if(coords_copy.y < 0) this.setDirection(new Radian(0));
			else this.setDirection(new Radian(Math.PI));
		} else if(coords_copy.y == 0){
			if(coords_copy.x > 0) this.setDirection(new Radian(Math.PI / 2));
			else this.setDirection(new Radian(3 * Math.PI / 2));
		} else {
			this.setDirection(new Radian(Math.atan2(coords_copy.y, coords_copy.x) + (Math.PI / 2)));
		}
	}
	
	public float getBase_speed() {
		return base_speed;
	}

	public void setBase_speed(float base_speed) {
		this.base_speed = base_speed;
	}

	public float getMod_left() {
		return mod_left;
	}

	public void setMod_left(float mod_left) {
		this.mod_left = mod_left;
	}

	public float getMod_forward() {
		return mod_forward;
	}

	public void setMod_forward(float mod_forward) {
		this.mod_forward = mod_forward;
	}

	public float getMod_right() {
		return mod_right;
	}

	public void setMod_right(float mod_right) {
		this.mod_right = mod_right;
	}

	public float getMod_backwards() {
		return mod_backwards;
	}

	public void setMod_backwards(float mod_backwards) {
		this.mod_backwards = mod_backwards;
	}

	public float getStrength() {
		return strength;
	}

	public void setStrength(float strength) {
		this.strength = strength;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
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

	public Vector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
	}

	public Radian getRotation() {
		return rotation;
	}

	public void setRotation(Radian rotation) {
		this.rotation = rotation;
	}

	public ArrayList<Actions> getQue() {
		return que;
	}

	public void setQue(ArrayList<Actions> que) {
		this.que = que;
	}
	
	
	
	
}

package jon.game.core;

import com.badlogic.gdx.graphics.g2d.Batch;
import jon.game.enums.ObjectType;
import jon.game.utils.GameObjectID;
import jon.game.utils.Point2;
import jon.game.utils.Point3;

public abstract class GameObject {
	private boolean visable;
	private boolean skip;
	private int priority;
	private String object_debug_name;
	private GameObjectID game_object_id;	
	public Point3 coords = new Point3(0, 0, 0);
	
	public GameObject(){
		visable = false;
		skip = false;
		object_debug_name = "null";
		game_object_id = new GameObjectID("null");
	}
	
	public GameObject(GameObjectID game_object_id){
		visable = false;
		skip = false;
		object_debug_name = "null";
		
		this.game_object_id = game_object_id;
	}
	
	public GameObject(boolean visable){
		skip = false;
		object_debug_name = "null";
		game_object_id = new GameObjectID("null");
		
		this.visable = visable;
	}
	
	public GameObject(String object_debug_name){
		visable = false;
		skip = false;
		game_object_id = new GameObjectID("null");
		
		this.object_debug_name = object_debug_name;
	}
	
	public GameObject(GameObjectID game_object_id, boolean visable){
		skip = false;
		object_debug_name = "null";
		
		this.game_object_id = game_object_id;
		this.visable = visable;
	}
	
	public GameObject(GameObjectID game_object_id, String object_debug_name){
		visable = false;
		skip = false;
		
		this.game_object_id = game_object_id;
		this.object_debug_name = object_debug_name;
	}
	
	public GameObject(boolean visable, String object_debug_name){
		skip = false;
		game_object_id = new GameObjectID("null");
		
		this.visable = visable;
		this.object_debug_name = object_debug_name;
	}
	
	public GameObject(GameObjectID game_object_id, boolean visable, String object_debug_name){
		skip = false;
		
		this.game_object_id = game_object_id;
		this.visable = visable;
		this.object_debug_name = object_debug_name;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public void setSkip(boolean skip){
		this.skip = skip;
	}
	
	public boolean skip(){
		return this.skip;
	}
	
	public void setVisable(boolean visable){
		this.visable = visable;
	}
	
	public boolean visable(){
		return this.visable;
	}
	
	public void setObjectDebugName(String object_debug_name){
		this.object_debug_name = object_debug_name;
	}
	
	public String getObjectDebugName(){
		return this.object_debug_name;
	}
	
	public void setGameObjectID(String game_object_id){
		this.game_object_id = new GameObjectID(game_object_id);
	}
	
	public void setGameObjectID(GameObjectID game_object_id){
		this.game_object_id = game_object_id;
	}
	
	public GameObjectID getGameObjectIDAsID(){
		return this.game_object_id;
	}
	
	public String getGameObjectIDAsString(){
		return this.game_object_id.get();
	}
	
	public void setPos(Point3 coords) {
		this.coords = coords;
	}
	
	public void transform(Point3 point){
		coords.transform(point);
	}
	public Point3 getCoords3() {
		return coords;
	}
	
	public void setPos(Point2 coords) {
		this.coords = new Point3(coords, 0);
	}
	
	public Point2 getCoords2() {
		return new Point2(coords.x, coords.y);
	}
	
	public void update(Batch batch, float parentAlpha, float delta){
		act(delta);
		if(visable) draw(batch, parentAlpha);
	}
	
	public abstract void act(float delta);
	public abstract void draw(Batch batch, float parentAlpha);
	public abstract void dispose();
	
	public ObjectType getType(){
		return ObjectType.object_basic;
	}

}

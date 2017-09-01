package jon.game.CORE;

import jon.game.INIT.GameObjectID;

public abstract class GameObject {
	private boolean visable;
	private boolean skip;
	private String object_debug_name;
	private GameObjectID game_object_id;
	
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
	
	public abstract void update(float delta);
	public abstract void dispose();
	
	public ObjectType getType(){
		return ObjectType.object_basic;
	}

}

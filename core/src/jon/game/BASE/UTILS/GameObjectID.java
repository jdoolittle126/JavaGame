package jon.game.BASE.UTILS;

public class GameObjectID {
	private String game_object_id;
	
	public GameObjectID(String game_object_id){
		this.game_object_id = game_object_id;
	}
	
	public String get(){
		return this.game_object_id;
	}
	
	public void set(String game_object_id){
		this.game_object_id = game_object_id;
	}
}

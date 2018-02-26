package jon.game.resource;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.IntMap;

import jon.game.enums.Action;

public class Controls {
	private static IntMap<ArrayList<Action>> binds;
	
	public static void init(){
		binds = new IntMap<ArrayList<Action>>();
		addBind("W", Action.action_forward);
		addBind("S", Action.action_backwards);
		addBind("A", Action.action_left);
		addBind("D", Action.action_right);
		addBind("Q", Action.action_rot_left);
		addBind("E", Action.action_rot_right);
		
		addBind("F1", Action.action_visualdebugging);
		addBind("F2", Action.action_verbosedebugging);
		addBind("F4", Action.action_screenshot);
	}
	
	public static void addBind(String key, Action action){
		ArrayList<Action> a = new ArrayList<Action>();
		a.add(action);
		binds.put(Keys.valueOf(key), a);
	}
	
	public static void addBind(String key, String... actions){
		ArrayList<Action> a = new ArrayList<Action>();
		for(String s : actions){
			a.add(Action.valueOf(s));
		}
		
		binds.put(Keys.valueOf(key), a);
	}
	
	public static void addBind(String key, ArrayList<Action> a){
		binds.put(Keys.valueOf(key), a);
	}
	
	public static void addBind(int k,  String... actions){
		ArrayList<Action> a = new ArrayList<Action>();
		for(String s : actions){
			a.add(Action.valueOf(s));
		}
		binds.put(k, a);
	}
	
	
	public static void addBind(int k,  ArrayList<Action> a){
		binds.put(k, a);
	}
	
	public static IntMap<ArrayList<Action>> getBinds(){
		return binds;
	}
}

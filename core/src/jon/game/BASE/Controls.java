package jon.game.BASE;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

import jon.game.BASE.ENUMS.Action;

public class Controls {
	public static ArrayList<HashMap<Integer, Action>> controls = new ArrayList<HashMap<Integer, Action>>();
	public static HashMap<Integer, Action> control_slot_1 = new  HashMap<Integer, Action>();
	public static HashMap<Integer, Action> control_slot_2 = new HashMap<Integer, Action>();
	public static HashMap<Integer, Action> control_slot_3 = new HashMap<Integer, Action>();
	public static HashMap<Integer, Action> control_slot_4 = new HashMap<Integer, Action>();
	
	public static void initControls(){
		control_slot_1.put(Keys.W, Action.action_forward);
		control_slot_2.put(Keys.S, Action.action_backwards);
		control_slot_3.put(Keys.A, Action.action_left);
		control_slot_4.put(Keys.D, Action.action_right);
		
		controls.add(control_slot_1);
		controls.add(control_slot_2);
		controls.add(control_slot_3);
		controls.add(control_slot_4);
	}
	
}

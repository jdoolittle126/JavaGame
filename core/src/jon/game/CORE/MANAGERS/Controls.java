package jon.game.CORE.MANAGERS;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

public class Controls {
	public static ArrayList<HashMap<Integer, Actions>> controls = new ArrayList<HashMap<Integer, Actions>>();
	public static HashMap<Integer, Actions> control_slot_1 = new  HashMap<Integer, Actions>();
	public static HashMap<Integer, Actions> control_slot_2 = new HashMap<Integer, Actions>();
	public static HashMap<Integer, Actions> control_slot_3 = new HashMap<Integer, Actions>();
	public static HashMap<Integer, Actions> control_slot_4 = new HashMap<Integer, Actions>();
	
	public static void initControls(){
		control_slot_1.put(Keys.W, Actions.action_forward);
		control_slot_2.put(Keys.S, Actions.action_backwards);
		control_slot_3.put(Keys.A, Actions.action_left);
		control_slot_4.put(Keys.D, Actions.action_right);
		
		controls.add(control_slot_1);
		controls.add(control_slot_2);
		controls.add(control_slot_3);
		controls.add(control_slot_4);
	}
	
}

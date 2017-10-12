package jon.game.debug;

import java.util.HashMap;

public class LogID {

	private static HashMap<Integer, Integer> calls = new HashMap<Integer, Integer>();
	
	
	public static String getLogId(Object object){
		if(calls.containsKey(object.hashCode())){
			calls.put(object.hashCode(), calls.get(object.hashCode() + 1));
		} else {
			calls.put(object.hashCode(), 1);
		}
		
		return object.hashCode() + "" + calls.get(object.hashCode());
	}
	
}

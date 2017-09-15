package jon.game.PHYSICS;

import java.util.ArrayList;

import jon.game.COMPONENTS.Component;
import jon.game.UTIL.Tuple;

public class Hitbox extends Component {
	
	public Hitbox(Tuple<Double, Double>... args){
	}
	
	public boolean hasTouched(Hitbox t){
		return false;
		
		
	}
	
	public boolean hasTouched(Tuple<Double, Double> t){
		return false;
		
	}
	
}

package jon.game.COMPONENTS;

import java.util.ArrayList;

import jon.game.UTIL.Tuple;

public class Hitbox extends Component {
	private ArrayList<Tuple<Double, Double>> hitbox;
	
	public Hitbox(Tuple<Double, Double>... args){
		for(Tuple<Double, Double> coord : args){
			hitbox.add(coord);
		}
	}
	
	public boolean hasTouched(Hitbox t){
		return false;
		
		
	}
	
	public boolean hasTouched(Tuple<Double, Double> t){
		return false;
		
	}
	
}

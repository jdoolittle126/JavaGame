package jon.game.UTIL;

public class Radian {
	private double value;
	
	public Radian(){
		this.value = 0;
	}
	public Radian(double value){
		this.value = value;
	}
	
	public void setValue(double value){
		this.value = value;
	}
	
	public void setValue(Radian radian){
		this.value = radian.getValue();
	}
	
	public double getValue(){
		return this.value;
	}
	
	public void addTo(double value){
		this.value += value;
	}
	
	public void addTo(Radian radian){
		this.value += radian.getValue();
	}

}

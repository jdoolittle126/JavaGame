package jon.game.statistics;

public class BaseStatistics {
	public float stat_weight;

	public BaseStatistics() {
		this.stat_weight = 100f;
	}
	
	public BaseStatistics(float stat_weight) {
		this.stat_weight = stat_weight;
	}

	public void setWeight(float stat){
		this.stat_weight = stat;
	}
}

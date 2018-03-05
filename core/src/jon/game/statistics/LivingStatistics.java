package jon.game.statistics;

public class LivingStatistics {
	// Defaults
	public float stat_health, stat_hunger, stat_thirst, stat_hunger_degrade_rate, stat_thirst_degrade_rate;
	
	public LivingStatistics() {
		this.stat_health = 100f;
		this.stat_hunger = 100f;
		this.stat_thirst = 100f;
		this.stat_hunger_degrade_rate = 0.01f;
		this.stat_thirst_degrade_rate = 0.05f;
	}	
	
	
	public LivingStatistics(float stat_health, float stat_hunger, float stat_thirst, float stat_hunger_degrade_rate, float stat_thirst_degrade_rate) {
		this.stat_health = stat_health;
		this.stat_hunger = stat_hunger;
		this.stat_thirst = stat_thirst;
		this.stat_hunger_degrade_rate = stat_hunger_degrade_rate;
		this.stat_thirst_degrade_rate = stat_thirst_degrade_rate;
	}

	public void setMaxHealth(float stat){
		this.stat_health = stat;
	}
	public void setMaxHunger(float stat){
		this.stat_hunger = stat;
	}
	public void setMaxThirst(float stat){
		this.stat_thirst = stat;
	}
	public void setHungerDegradeRate(float stat){
		this.stat_hunger_degrade_rate = stat;
	}
	public void setThirstDegradeRate(float stat){
		this.stat_thirst_degrade_rate = stat;
	}
	
}

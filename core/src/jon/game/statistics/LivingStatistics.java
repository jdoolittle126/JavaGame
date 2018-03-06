package jon.game.statistics;

public class LivingStatistics {
	public float stat_health, stat_hunger, stat_thirst, stat_hunger_degrade_rate, stat_thirst_degrade_rate, stat_fatigue;
	
	public LivingStatistics() {
		// Defaults
		this.stat_health = 100f;
		this.stat_hunger = 100f;
		this.stat_thirst = 100f;
		this.stat_hunger_degrade_rate = 0.01f;
		this.stat_thirst_degrade_rate = 0.05f;
		this.stat_fatigue = 100f;
	}	
	
	
	public LivingStatistics(float stat_health, float stat_hunger, float stat_thirst, float stat_hunger_degrade_rate, float stat_thirst_degrade_rate, float stat_fatigue) {
		this.stat_health = stat_health;
		this.stat_hunger = stat_hunger;
		this.stat_thirst = stat_thirst;
		this.stat_hunger_degrade_rate = stat_hunger_degrade_rate;
		this.stat_thirst_degrade_rate = stat_thirst_degrade_rate;
		this.stat_fatigue = stat_fatigue;
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
	public void setMaxFatigue(float stat){
		this.stat_fatigue = stat;
	}
	public void setHungerDegradeRate(float stat){
		this.stat_hunger_degrade_rate = stat;
	}
	public void setThirstDegradeRate(float stat){
		this.stat_thirst_degrade_rate = stat;
	}
	
}

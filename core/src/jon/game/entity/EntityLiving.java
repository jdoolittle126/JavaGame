package jon.game.entity;

import java.util.ArrayList;

import jon.game.enums.Action;
import jon.game.statistics.LivingStatistics;

public abstract class EntityLiving extends EntityDynamic {
	
	private float food_ticks_remaining, water_ticks_remaining;
	private float current_health;
	private float movement_modifier;
	
	public LivingStatistics living_stats = new LivingStatistics();
	
	public EntityLiving() {
		super();
	}
	
	
	
}

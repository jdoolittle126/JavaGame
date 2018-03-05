package jon.game.entity;

import java.util.ArrayList;
import jon.game.enums.Action;
import jon.game.enums.Effects;
import jon.game.items.Consumable;
import jon.game.items.Effect;
import jon.game.statistics.LivingStatistics;

public abstract class EntityLiving extends EntityDynamic {
	
	private float food_ticks_remaining, water_ticks_remaining;
	private float current_health;
	private float movement_modifier;
	private ArrayList<Effect> effects;
	public LivingStatistics living_stats;
	
	public EntityLiving() {
		super();
	}
	
	public void comsume(Consumable consumable){
		
	}
	
	
}

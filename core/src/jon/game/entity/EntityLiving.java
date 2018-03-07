package jon.game.entity;

import java.util.ArrayList;
import jon.game.enums.Action;
import jon.game.enums.Effects;
import jon.game.items.Consumable;
import jon.game.items.Effect;
import jon.game.statistics.BaseStatistics;
import jon.game.statistics.LivingStatistics;
import jon.game.statistics.MovementStatistics;

public abstract class EntityLiving extends EntityDynamic {
	private static final float stat_weight_health = 1f, stat_weight_hunger = 1f, stat_weight_thirst = 1f; //adjust
	
	public float current_hunger, current_thirst, current_health, current_fatigue;
	public float movement_modifier = 1f;
	
	private ArrayList<Effect> effects;
	public LivingStatistics living_stats;
	
	public EntityLiving() {
		super();
		this.living_stats = new LivingStatistics();
	}
	
	public EntityLiving(BaseStatistics base_stats){
		super(base_stats);
		this.living_stats = new LivingStatistics();
	}
	
	public EntityLiving(BaseStatistics base_stats, MovementStatistics movement_stats) {
		super(base_stats, movement_stats);
		this.living_stats = new LivingStatistics();
	}
	
	public EntityLiving(BaseStatistics base_stats, LivingStatistics living_stats) {
		super(base_stats);
		this.living_stats = living_stats;
	}
	
	public EntityLiving(BaseStatistics base_stats, MovementStatistics movement_stats, LivingStatistics living_stats) {
		super(base_stats, movement_stats);
		this.living_stats = living_stats;
	}
	
	@Override
	public void act(float delta) {
		current_hunger -= this.living_stats.stat_hunger_degrade_rate * delta;
		current_thirst -= this.living_stats.stat_thirst_degrade_rate * delta;
		current_fatigue -= ((this.living_stats.stat_thirst * stat_weight_thirst) + (this.living_stats.stat_health * stat_weight_health) + (this.living_stats.stat_hunger * stat_weight_hunger)) * delta;
		
		
		
		super.act(delta);
	}

	
	public void addEffect(Effect effect) {
		
	}
	
	public void comsume(Consumable consumable){
		
	}
	
	
}

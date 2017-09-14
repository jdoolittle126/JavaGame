package jon.game.ENTITY;

import java.util.ArrayList;

import jon.game.COMPONENTS.Action;
import jon.game.UTIL.LivingStatistics;

public abstract class EntityLiving extends Entity {
	
	public LivingStatistics living_stats = new LivingStatistics();
	
	private ArrayList<Action> current_Action = new ArrayList<Action>();
	
	
	
}

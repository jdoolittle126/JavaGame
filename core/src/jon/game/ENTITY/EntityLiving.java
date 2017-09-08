package jon.game.ENTITY;

import java.util.ArrayList;
import jon.game.CORE.MANAGERS.Actions;
import jon.game.UTIL.LivingStatistics;

public abstract class EntityLiving extends Entity {
	
	public LivingStatistics living_stats = new LivingStatistics();
	
	private ArrayList<Actions> current_actions = new ArrayList<Actions>();
	
	
	
}

package jon.game.entity;

import jon.game.utils.Point2;

public class AI {
	EntityLiving puppet;
	float need_hunger, need_thirst, need_fatigue;
	
	/* list of needs
	 * 
	 * avoid x entity (specific)
	 * avoid x type of entity
	 * get to x location
	 * get food
	 * get water
	 * attack x entity
	 * sleep
	 */

	public void AvoidEntity(Entity x, float fear_duration) {
		//0 = forever
	}
	
	public void AvoidSpecies(Entity x, float fear_duration) {
		//0 = forever
	}
	
	public void PathFind(Point2 loc) {
		
	}
	
	public void LocateFood(float radius) {
		
	}
	
	public void LocateWater(float radius) {
		
	}
	
	public void Attack(Entity x) {
		
	}
	
	public void AssessFatigue() {
		
	}
	
	public void update(){
		
		need_hunger = puppet.current_hunger / puppet.living_stats.stat_hunger;
		need_thirst = puppet.current_thirst / puppet.living_stats.stat_thirst;
		need_fatigue = puppet.current_fatigue / puppet.living_stats.stat_fatigue;
		
	}
	
}

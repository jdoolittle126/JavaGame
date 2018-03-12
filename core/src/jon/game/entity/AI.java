package jon.game.entity;

import java.util.ArrayList;

import jon.game.terrain.TerrainTile;
import jon.game.terrain.World;
import jon.game.utils.Point2;

public class AI {
	public static float MAX_PATH_RADIUS = TerrainTile.SUBTILE_SIZE * 20; //Pathfinding is a heavy operation so we need a hard limit 
	EntityLiving puppet;
	float need_hunger, need_thirst, need_fatigue;
	boolean isPathNav;
	ArrayList<Entity> avoid;
	ArrayList<Point2> current_path;
	int path_progress;
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
	public AI() {
		this.path_progress = 0;
		this.avoid = new ArrayList<Entity>();
		this.current_path = new ArrayList<Point2>();
	}

	public void AvoidEntity(Entity x, float fear_duration) {
		//0 = forever
	}
	
	public void AvoidSpecies(Entity x, float fear_duration) {
		//0 = forever
	}
	
	public void PathFind(Point2 coords, Point2 loc, World w) {
		PathFinder p = new PathFinder();
		this.path_progress = 0;
		this.current_path = p.getFinalPathForChunks(coords, loc, w.getMap().getChunks());
		System.out.println(this.current_path);
		this.isPathNav = true;
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

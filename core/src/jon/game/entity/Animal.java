package jon.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jon.game.core.GameClient;
import jon.game.terrain.World;
import jon.game.utils.Point2;
import jon.game.utils.Point3;

public abstract class Animal extends EntityLiving {
	AI ai;

	/*
	 * Locate food locate drinkable water
	 * Sleep
	 * Basic attack that is overridden
	 * Has loot
	 */
	
	public Animal() {
		ai = new AI();
	}
	
	@Override
	public void act(float delta) {
		
		if(ai.isPathNav) {
			Point2 a = this.getCoords2().cpy();
			a.x = (float) Math.floor(a.x);
			a.y = (float) Math.floor(a.y);
			if(a.withinRange(ai.current_path.get(ai.path_progress), 5, 5)) {
				ai.path_progress++;
				if(ai.path_progress == ai.current_path.size()-1) ai.isPathNav = false;
				else this.lookAt(ai.current_path.get(ai.path_progress));
			} else {
				Point2 p = ai.current_path.get(ai.path_progress);
				this.addVelocity(this.moveTo(new Point3(p.x, p.y, 0), this.movement_stats.stat_speed_mod_forward * this.movement_modifier).scale(delta));
			}

		}
		
		super.act(delta);
	}
	
	public void pathTo(Point2 loc, World w) {
		ai.PathFind(this.getCoords2(), loc, w);
	}
	
	public ArrayList<Point2> getCurrentPath(){
		return ai.current_path;
	}
	
	
	
}

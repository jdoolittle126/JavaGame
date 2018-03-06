package jon.game.entity;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jon.game.core.ActionMethods;
import jon.game.core.GameObject;
import jon.game.core.GameClient;
import jon.game.enums.Action;
import jon.game.enums.ObjectType;
import jon.game.statistics.BaseStatistics;
import jon.game.utils.Animation;
import jon.game.utils.Point2;

public abstract class Entity extends GameObject {
	public BaseStatistics base_stats;
	public float rotation =  0f;
	protected final double ROT_OFFSET = (Math.PI/2);
	
	public Entity(){
		base_stats = new BaseStatistics();
	}
	
	public Entity(BaseStatistics base_stats){
		this.base_stats = base_stats;
	}
	
	public void setDirection(float rads){
		this.rotation = rads;
	}
	
	public void changeDirection(float rads){
		this.rotation += rads;
	}
	
	public void setDirection(double rads){
		this.rotation = (float) rads;
	}
	
	public void changeDirection(double rads){
		this.rotation += (float) rads;
	}
	
	@Override
	public ObjectType getType(){
		return ObjectType.object_entity_basic;
	}

	public void lookAt(Point2 point){
		Point2 coords_copy = this.getCoords2().transform(point.scale(-1f));
		if(coords_copy.x == 0){
			if(coords_copy.y < 0) this.setDirection(0f);
			else this.setDirection(Math.PI);
		} else if(coords_copy.y == 0){
			if(coords_copy.x > 0) this.setDirection(Math.PI / 2);
			else this.setDirection(3 * Math.PI / 2);
		} else {
			this.setDirection(Math.atan2(coords_copy.y, coords_copy.x) + (Math.PI / 2));
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}

	
}

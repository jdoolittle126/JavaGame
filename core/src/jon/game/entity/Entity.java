package jon.game.entity;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import jon.game.core.GameObject;
import jon.game.enums.ObjectType;
import jon.game.statistics.BaseStatistics;
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
		Point2 coords_copy = this.getCoords2().transform(point.cpy().scale(-1f));
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
	
	public void drawSprite(Batch batch, Sprite s) {
		float w = s.getWidth()/2;
		float h = s.getHeight()/2;
		s.setCenter(w, h);
		s.setPosition(getCoords2().x-w, getCoords2().y-h);
		s.setRotation((float) Math.toDegrees(rotation));
		s.draw(batch);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	
}

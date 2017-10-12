package jon.game.entity;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import jon.game.core.ActionMethods;
import jon.game.core.GameObject;
import jon.game.core.MyGdxGame;
import jon.game.enums.Action;
import jon.game.enums.ObjectType;
import jon.game.statistics.BaseStatistics;
import jon.game.utils.Animation;
import jon.game.utils.Point2;

public abstract class Entity extends GameObject implements ActionMethods {
	
// - Default Stats - \\
	
	public BaseStatistics base_stats = new BaseStatistics();
	
// - Rotation - \\
	
	public float rotation =  0f;
	
	protected final double ROT_OFFSET = (Math.PI/2);
	
// - Textures & Animation - \\
	private HashMap<Action, Animation> animations = new HashMap<Action, Animation>();
	private Texture spriteSheet;
	
	
	
	public Entity(){
		//initStats();
	}
	
	//Example java doc for future reference
	/**
	 * Short one line description.                           (1)
	 * <p>
	 * Longer description. If there were any, it would be    (2)
	 * here.
	 * <p>
	 * And even more explanations to follow in consecutive
	 * paragraphs separated by HTML paragraph breaks.
	 *
	 * @param  variable Description text text text.          (3)
	 * @return Description text text text.
	 */
	
	public abstract void initStats();
	
// - Direction - \\
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
	
// - Animation - \\
	public void playAnimation(Animation animation){
		
	}
	
	public void playAnimation(Animation animation, float overrideSpeed){
		
	}
	
	public void playAnimation(Action action){
		
	}
	
	public void playAnimation(Action action, float overrideSpeed){
		
	}

	@Override
	public void update(float delta, SpriteBatch batch) {
		
	}
	

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public ObjectType getType(){
		return ObjectType.object_entity_basic;
	}

	
	public void lookAtMouse(){
		Point2 coords_copy = this.getCoords2().transform(MyGdxGame.getMouseCoordsWorld().cpy().scale(-1f));
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

	public Texture getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(Texture spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	@Override
	public void action_forward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action_backwards() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action_left() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action_right() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action_forward_end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action_backwards_end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action_left_end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action_right_end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		
		return super.toString();
	}
	

	
	
	
	
}

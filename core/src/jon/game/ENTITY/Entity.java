package jon.game.ENTITY;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import jon.game.COMPONENTS.Action;
import jon.game.COMPONENTS.Animation;
import jon.game.CORE.GameObject;
import jon.game.CORE.MyGdxGame;
import jon.game.CORE.ObjectType;
import jon.game.UTIL.BaseStatistics;

public abstract class Entity extends GameObject implements ActionMethods {
	
// - Default Stats - \\
	
	/**
	 * The basic stats of this entity
	 */
	
	public BaseStatistics base_stats = new BaseStatistics();
	
// - Coords - \\
	
	/**
	 * The coordinates of this entity, on the projection matrix of the main camera (world coords)
	 */
	
	public Vector3 coords = new Vector3(0f, 0f, 0f);
	
// - Rotation - \\
	
	/**
	 * The rotation of this entity (radians)
	 */
	public float rotation =  0f;
	
	/**
	 * The offset used so the entity is pointing upwards by default (radians)
	 */
	
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
	
	
// - Movement - \\
	public void transform(Vector3 vec){
		coords.add(vec);
	}
	

	
	public void setPos(Vector3 coords) {
		this.coords = coords;
	}
	

	

	@Override
	public void update(float delta) {

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
		Vector3 coords_copy = this.getCoords().cpy();
		coords_copy.sub(MyGdxGame.mouse_coords_world);
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
	
	public Vector3 getCoords() {
		return coords;
	}

	public void setCoords(Vector3 coords) {
		this.coords = coords;
	}

	public Texture getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(Texture spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	
	
	
	
}

package jon.game.entity.living;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import jon.game.core.GameClient;
import jon.game.debug.Debugger;
import jon.game.entity.EntityLiving;
import jon.game.enums.Action;
import jon.game.utils.Point2;
import jon.game.utils.Point3;
import jon.physics.core.Shape;
import jon.physics.core.Shape.Type;

public class Player extends EntityLiving {
	
	private TextureRegion testregion;
	
	//TODO clean up shape testing etc
	private Shape shape = new Shape(Type.Polygon, new Vector2(0f, 0f), new Vector2(200f, 200f), new Vector2(150f, 300f), new Vector2(0f, 50f));
	private Shape shape2 = new Shape(Type.Polygon, new Vector2(-100f, -60f), new Vector2(-300f, 0f), new Vector2(0f, -200f));
	private Shape shape3 = new Shape(Type.Polygon, new Vector2(-200f, -60f), new Vector2(-300f, 100f), new Vector2(0f, -200f));
	private Shape shape4 = new Shape(Type.Polygon, new Vector2(-50f, 100f), new Vector2(-100f, 100f), new Vector2(-150f, 150f), new Vector2(-125f, 200f), new Vector2(-50f, 150f));
	
	public Player(Texture texture){
		super();
		//this.setSpriteSheet(texture);
		testregion = new TextureRegion(texture);
		this.setVisable(true);
	}
	
	public void initStats(){
		this.movement_stats.stat_speed_base = 1f;
		this.movement_stats.stat_speed_mod_forward = 1f;
		this.movement_stats.stat_speed_mod_backwards = -0.5f;
		this.movement_stats.stat_speed_mod_left = 0.75f;
		this.movement_stats.stat_speed_mod_right = 0.75f;
		this.base_stats.stat_weight = 130f;
	}
	


	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		batch.draw(testregion, this.getCoords2().x - 32f, this.getCoords2().y - 32f, 32f, 32f, 64f, 64f, 1, 1, (float) Math.toDegrees(this.rotation));
	}
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void plus_action_forward(float delta) {
		this.addVelocity(this.moveTo(new Point3(GameClient.getGame().getScreenManager().active_screen.camera_main.position.x, GameClient.getGame().getScreenManager().active_screen.camera_main.position.y, 0), this.movement_stats.stat_speed_mod_forward));
		Debugger.DrawDebugLine(this.coords, this.coords.cpy().transform(this.velocity.cpy().scale(100f)), 3, Color.BLUE, GameClient.getMatrix());
	}

	@Override
	public void plus_action_backwards(float delta) {
		this.addVelocity(this.moveTo(new Point3(GameClient.getGame().getScreenManager().active_screen.camera_main.position.x, GameClient.getGame().getScreenManager().active_screen.camera_main.position.y, 0), this.movement_stats.stat_speed_mod_backwards));
		Debugger.DrawDebugLine(this.coords, this.coords.cpy().transform(this.velocity.cpy().scale(100f)), 3, Color.BLUE, GameClient.getMatrix());
	}

	@Override
	public void plus_action_left(float delta) {
		this.addVelocity(this.moveAt(new Point3(GameClient.getGame().getScreenManager().active_screen.camera_main.position.x, GameClient.getGame().getScreenManager().active_screen.camera_main.position.y, 0), (float) (Math.PI/2)+0.1f , this.movement_stats.stat_speed_mod_left));
		Debugger.DrawDebugLine(this.coords, this.coords.cpy().transform(this.velocity.cpy().scale(100f)), 3, Color.BLUE, GameClient.getMatrix());
	}

	@Override
	public void plus_action_right(float delta) {
		this.addVelocity(this.moveAt(new Point3(GameClient.getGame().getScreenManager().active_screen.camera_main.position.x, GameClient.getGame().getScreenManager().active_screen.camera_main.position.y, 0), (float) (-Math.PI/2)-0.1f, this.movement_stats.stat_speed_mod_right));
		Debugger.DrawDebugLine(this.coords, this.coords.cpy().transform(this.velocity.cpy().scale(100f)), 3, Color.BLUE, GameClient.getMatrix());
		
	}

	@Override
	public void minus_action_forward(float delta) {
		
	}

	@Override
	public void minus_action_backwards(float delta) {
		
	}

	@Override
	public void minus_action_left(float delta) {
		
	}

	@Override
	public void minus_action_right(float delta) {
		
	}

}

package jon.game.entity.living;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.core.Controls;
import jon.game.core.GameObject;
import jon.game.core.MyGdxGame;
import jon.game.debug.Debugger;
import jon.game.entity.EntityLiving;
import jon.game.enums.Action;
import jon.game.screens.GameScreen;
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
		this.movement_stats.stat_speed_mod_backwards = 0.5f;
		this.movement_stats.stat_speed_mod_left = 0.75f;
		this.movement_stats.stat_speed_mod_right = 0.75f;
		this.base_stats.stat_weight = 130f;
	}
	
	

	@Override
	public void endAction(Action action) {

		super.endAction(action);
	}

	@Override
	public void update(float delta, SpriteBatch batch) {
		
		
		
		if(this.visable()){
			this.lookAtMouse();
			
			batch.draw(testregion, this.getCoords2().x - 32f, this.getCoords2().y - 32f, 32f, 32f, 64f, 64f, 1, 1, (float) Math.toDegrees(this.rotation));
		}

		/*
		shape2.update();
		shape3.update();
		shape4.update();
		shape.update();
		
		
		if(shape2.hasCollision(shape4) || shape2.hasCollision(shape) || shape2.hasCollision(shape3)) {
			shape2.setColor(Color.RED);
		} else {
			shape2.setColor(Color.CYAN);
		}
		
		if(shape4.hasCollision(shape2) || shape4.hasCollision(shape) || shape4.hasCollision(shape3)) {
			shape4.setColor(Color.RED);
		} else {
			shape4.setColor(Color.CYAN);
		}
		
		if(shape3.hasCollision(shape)) {
			shape3.setColor(Color.RED);
		} else {
			shape3.setColor(Color.CYAN);
		}
		
		if(shape.hasCollision(shape3)) {
			shape.setColor(Color.RED);
			
		} else {
			shape.setColor(Color.CYAN);
		}
		*/
		//shape3.transform(this.velocity.scl(delta));
		//Debugger.DrawDebugArc(new Vector2(100, 100), new Vector2(-200,300), 50, 3, Color.NAVY, GameScreen.camera.combined);
		//Debugger.DrawDebugLine(new Vector2(100, 100), new Vector2(-200,300), 3, Color.GOLD, GameScreen.camera.combined);
		
		super.update(delta, batch);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action_forward() {
		this.moveTo(new Point3(MyGdxGame.mouse_coords_world.cpy(), 0), this.movement_stats.stat_speed_mod_forward);
	}

	@Override
	public void action_backwards() {
		this.moveTo(new Point3(MyGdxGame.mouse_coords_world.cpy(), 0), -this.movement_stats.stat_speed_mod_backwards);
	}

	@Override
	public void action_left() {
		
		//Maybe make an actual method?
		Point3 old = new Point3((this.coords.cpy().x - delta_x), (this.coords.cpy().y - delta_y), 0f);
		
		if(old.equals(coords)) this.moveAt(this.rotation+(Math.PI/2), this.movement_stats.stat_speed_mod_left);
		else this.moveAt(old, (float) -(Math.PI/2), this.movement_stats.stat_speed_mod_left);
		
		delta_x += this.velocity.x*Gdx.graphics.getDeltaTime();
		delta_y += this.velocity.y*Gdx.graphics.getDeltaTime();

	}

	@Override
	public void action_right() {
		Point3 old = new Point3((this.coords.cpy().x - delta_x), (this.coords.cpy().y - delta_y), 0f);
		
		if(old.equals(coords.cpy())) this.moveAt(this.rotation-(Math.PI/2), this.movement_stats.stat_speed_mod_right);
		else this.moveAt(old, (float) (Math.PI/2), this.movement_stats.stat_speed_mod_right);
		
		delta_x += this.velocity.x*Gdx.graphics.getDeltaTime();
		delta_y += this.velocity.y*Gdx.graphics.getDeltaTime();
		
	}

	@Override
	public void action_forward_end() {
		//Make these not just stop
		this.stop();

		
	}

	@Override
	public void action_backwards_end() {
		this.stop();
	}

	@Override
	public void action_left_end() {
		this.stop();
		delta_x = 0f;
		delta_y = 0f;
		
	}

	@Override
	public void action_right_end() {
		this.stop();
		delta_x = 0f;
		delta_y = 0f;
		
	}
	
	

}

package jon.game.ENTITY;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.CORE.GameObject;
import jon.game.CORE.MANAGERS.Actions;
import jon.game.CORE.MANAGERS.Controls;
import jon.game.INIT.MyGdxGame;
import jon.game.SCREENS.GameScreen;

public class Player extends EntityLiving {
	
	private TextureRegion testregion;
	
	private float delta_x = 0f, delta_y = 0f;
	
	public Player(Texture texture){
		super();
		//this.setSpriteSheet(texture);
		testregion = new TextureRegion(texture);
		this.setVisable(true);
	}
	
	public void initStats(){
		this.base_stats.stat_speed_base = 1f;
		this.base_stats.stat_speed_mod_forward = 1f;
		this.base_stats.stat_speed_mod_backwards = 0.5f;
		this.base_stats.stat_speed_mod_left = 0.75f;
		this.base_stats.stat_speed_mod_right = 0.75f;
		this.base_stats.stat_weight = 130f;
	}
	
	

	@Override
	public void endAction(Actions action) {

		super.endAction(action);
	}

	@Override
	public void update(float delta) {
		
		if(this.visable()){
			this.lookAtMouse();
			MyGdxGame.batch.draw(testregion, this.getCoords().x - 32f, this.getCoords().y - 32f, 32f, 32f, 64f, 64f, 1, 1, (float) Math.toDegrees(this.rotation));
		}
		
		
		super.update(delta);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action_forward() {
		this.moveTo(MyGdxGame.mouse_coords_world.cpy(), this.base_stats.stat_speed_mod_forward);
	}

	@Override
	public void action_backwards() {
		this.moveTo(MyGdxGame.mouse_coords_world.cpy(), -this.base_stats.stat_speed_mod_backwards);
	}

	@Override
	public void action_left() {
		Vector3 old = new Vector3((this.coords.cpy().x - delta_x), (this.coords.cpy().y - delta_y), 0f);
		
		if(old.equals(coords.cpy())) this.moveAt(this.rotation+(Math.PI/2), this.base_stats.stat_speed_mod_left);
		else this.moveAt(old, (float) -(Math.PI/2), this.base_stats.stat_speed_mod_left);
		
		delta_x += this.velocity.cpy().x*Gdx.graphics.getDeltaTime();
		delta_y += this.velocity.cpy().y*Gdx.graphics.getDeltaTime();

	}

	@Override
	public void action_right() {
		Vector3 old = new Vector3((this.coords.cpy().x - delta_x), (this.coords.cpy().y - delta_y), 0f);
		
		if(old.equals(coords.cpy())) this.moveAt(this.rotation-(Math.PI/2), this.base_stats.stat_speed_mod_right);
		else this.moveAt(old, (float) (Math.PI/2), this.base_stats.stat_speed_mod_right);
		
		delta_x += this.velocity.cpy().x*Gdx.graphics.getDeltaTime();
		delta_y += this.velocity.cpy().y*Gdx.graphics.getDeltaTime();
		
	}

	@Override
	public void action_forward_end() {
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

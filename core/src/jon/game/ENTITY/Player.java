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

public class Player extends Entity {
	private ArrayList<Actions> current_actions;
	private TextureRegion testregion;
	private Vector3 help = new Vector3(-1f, -1f, 0);
	
	private boolean oneway = false;
	
	
	public Player(Texture texture){
		super();
		current_actions = new ArrayList<Actions>();
		//this.setSpriteSheet(texture);
		testregion = new TextureRegion(texture);
		this.setVisable(true);
	}
	
	public void initStats(){
		this.stats.stat_speed_base = 1f;
		this.stats.stat_speed_mod_forward = 1f;
		this.stats.stat_speed_mod_backwards = 0.5f;
		this.stats.stat_speed_mod_left = 0.75f;
		this.stats.stat_speed_mod_right = 0.75f;
		this.stats.stat_weight = 130f;
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
		this.moveTo(MyGdxGame.mouse_coords_world.cpy(), this);
		
	}

	@Override
	public void action_backwards() {
		Vector3 vel = MyGdxGame.mouse_coords_world.cpy();
		vel.sub(this.getCoords().cpy());
		float h = (float) ((float) 0.01f * Math.sqrt(Math.pow(vel.x, 2) + Math.pow(vel.y, 2)));
		vel.x /= (h / (this.stats.stat_speed_base * this.stats.stat_speed_mod_backwards));
		vel.y /= (h / (this.stats.stat_speed_base * this.stats.stat_speed_mod_backwards));
		vel.z = 0;		
		vel.scl(-1f);
		this.setVelocity(vel);
		
	}

	@Override
	public void action_left() {
		
		if(!oneway){
			Vector3 test = MyGdxGame.mouse_coords_world.cpy().sub(this.coords.cpy());
			float x = (test.y < -1) ? 1:-1;
			x = (test.y > -50*GameScreen.camera.zoom && test.y < 50*GameScreen.camera.zoom) ? 0:x;	
			float y = (test.x > 1) ? 1:-1;	
			y = (test.x > -50*GameScreen.camera.zoom && test.x < 50*GameScreen.camera.zoom) ? 0:y;
			test.x = (float) (Math.abs(test.x) * Math.sin(Math.PI / 2) * 100 * x);
			test.y = (float) (Math.abs(test.y) * Math.cos(0) * 100 * y);
			test.add(this.coords.cpy());
			
			this.moveTo(test, this);
			oneway = true;

		}
		

	}

	@Override
	public void action_right() {
		// TODO Auto-generated method stub
		
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
		oneway = false;
		this.stop();
		
	}

	@Override
	public void action_right_end() {
		this.stop();
		
	}
	
	

}

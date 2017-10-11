package jon.game.core;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.debug.Debugger;
import jon.game.entity.Entity;
import jon.game.entity.living.Player;
import jon.game.enums.ScreenType;
import jon.game.screens.GameScreen;
import jon.game.tools.ScreenManager;

public class GameInstance {
	private ArrayList<GameObject> object_list;
	private ArrayList<Entity> object_list_specific_entity;
	private int ticks, cycles;
	private Sprite backgroundSprite;
	public static BitmapFont font;
	private Player player;
	
	public GameInstance(){
		object_list = new ArrayList<GameObject>();
		object_list_specific_entity = new ArrayList<Entity>();
		font = new BitmapFont(Gdx.files.internal("assets\\fonts\\Calibri.fnt"), Gdx.files.internal("assets\\fonts\\Calibri.png"), false);
	}
	
	public void start(){
		//Screen Manager
		MyGdxGame.getGame().getScreenManager().createStartScreen();
		MyGdxGame.getGame().setScreen(MyGdxGame.getGame().getScreenManager().active_screen);
		
		player = new Player(new Texture("assets/player.png"));
		MyGdxGame.getGame().addInputProcessor(new EntityController(player));
		backgroundSprite = new Sprite(new Texture("assets/background.png"));
		backgroundSprite.setPosition(-1000f, -1000f);
		object_list_specific_entity.add(player);
	}
	
	public void update(float delta, SpriteBatch batch){
		MyGdxGame.mouse_coords.x = Gdx.input.getX();
		MyGdxGame.mouse_coords.y = Gdx.input.getY();
		Vector3 t = MyGdxGame.getGame().getScreenManager().active_screen.camera_main.unproject(new Vector3(MyGdxGame.mouse_coords.x, MyGdxGame.mouse_coords.y, 0));
		MyGdxGame.mouse_coords_world.x = t.x;
		MyGdxGame.mouse_coords_world.y = t.y;
		
		backgroundSprite.draw(batch);
		
		//Entity Updates and Rendering
		for(Entity e : object_list_specific_entity){
			if(!e.skip()){
				e.update(delta, batch);
			}
		}
		
		//Other Game Object updates and Rendering
		for(GameObject o : object_list){
			if(!o.skip()){
				o.update(delta, batch);
			}
		}

		//font.draw(batch, String.valueOf("FPS: " + Gdx.graphics.getFramesPerSecond()), GameScreen.camera.position.x - ((((GameScreen.camera.viewportWidth * 95) / 100) * GameScreen.camera.zoom) / 2), GameScreen.camera.position.y + ((((GameScreen.camera.viewportHeight * 95) / 100) * GameScreen.camera.zoom) / 2));   
		
		Debugger.draw();
		//MyGdxGame.batch.disableBlending();
		
		//Ticks measured and reset
		ticks++;
		if(ticks == 100000) ticks = 0; cycles++;
		
	}
	

	public void dispose(){
		
	}

	
	
}

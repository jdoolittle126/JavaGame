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
import jon.game.screens.GameScreen;

public class GameInstance {
	private GameWorld gameWorld;
	private ArrayList<GameObject> object_list;
	private ArrayList<Entity> object_list_specific_entity;
	private int ticks, cycles;
	private GameScreen gameScreen;
	private Sprite backgroundSprite;
	private BitmapFont font;
	private Player player;
	
	
	
	public GameInstance(){
		//TODO make mouse to world coords work on blackbars
		
		gameScreen = new GameScreen();
		object_list = new ArrayList<GameObject>();
		object_list_specific_entity = new ArrayList<Entity>();
		font = new BitmapFont(Gdx.files.internal("assets\\fonts\\Calibri.fnt"), Gdx.files.internal("assets\\fonts\\Calibri.png"), false);
		Gdx.input.setInputProcessor(MyGdxGame.inputs);
	}
	
	public void start(MyGdxGame game){
		game.setScreen(gameScreen);
		
		player = new Player(new Texture("assets/player.png"));
		MyGdxGame.inputs.addProcessor(new EntityController(player));
		backgroundSprite = new Sprite(new Texture("assets/background.png"));
		backgroundSprite.setPosition(-1000f, -1000f);
		
		object_list_specific_entity.add(player);
	}
	
	public void update(float delta){
		MyGdxGame.mouse_coords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0f);
		MyGdxGame.mouse_coords_world = GameScreen.camera.unproject(new Vector3(MyGdxGame.mouse_coords.x, MyGdxGame.mouse_coords.y, 0));
		
		MyGdxGame.batch.begin();
		
		backgroundSprite.draw(MyGdxGame.batch);
		//Entity Updates and Rendering
		for(Entity e : object_list_specific_entity){
			if(!e.skip()){
				e.update(delta);
			}
		}
		
		//Other Game Object updates and Rendering
		for(GameObject o : object_list){
			if(!o.skip()){
				o.update(delta);
			}
		}
		
		

		font.draw(MyGdxGame.batch, String.valueOf("FPS: " + Gdx.graphics.getFramesPerSecond()), GameScreen.camera.position.x - ((((GameScreen.camera.viewportWidth * 95) / 100) * GameScreen.camera.zoom) / 2), GameScreen.camera.position.y + ((((GameScreen.camera.viewportHeight * 95) / 100) * GameScreen.camera.zoom) / 2));   

		MyGdxGame.batch.end();
		
		if(MyGdxGame.debug){
			Debugger.draw();
		}
		//MyGdxGame.batch.disableBlending();
		
		MyGdxGame.mouse_coords_world_old = MyGdxGame.mouse_coords_world.cpy();
		
		//Ticks measured and reset
		ticks++;
		if(ticks == 100000) ticks = 0; cycles++;
		
	}
	

	public void dispose(){
		
	}

	
	
}

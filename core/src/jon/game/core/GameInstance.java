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
import jon.game.terrain.TerrainTile;
import jon.game.tools.ScreenManager;
import jon.game.utils.Point2;

public class GameInstance {
	private ArrayList<GameObject> object_list;
	private int ticks, cycles;
	private Sprite backgroundSprite;
	public static BitmapFont font;
	private Player player;
	
	public GameInstance(){
		
		object_list = new ArrayList<GameObject>();
		font = new BitmapFont(Gdx.files.internal("assets\\fonts\\Calibri.fnt"), Gdx.files.internal("assets\\fonts\\Calibri.png"), false);
	}
	
	public void start(){
		//Screen Manager

		player = new Player(new Texture("assets/player.png"));
		GameClient.getGame().addInputProcessor(new EntityController(player));
		backgroundSprite = new Sprite(new Texture("assets/background.png"));
		backgroundSprite.setPosition(-1000f, -1000f);
		object_list.add(player);
	}
	
	public void update(float delta, SpriteBatch batch){
		//backgroundSprite.draw(batch);
		
		for(GameObject o : object_list){
			if(!o.skip()){
				o.update(delta, batch);
			}
		} 
		
		//Ticks measured and reset
		ticks++;
		if(ticks == 100000) ticks = 0; cycles++;
		
	}
	

	public void dispose(){
		
	}

	
	
}

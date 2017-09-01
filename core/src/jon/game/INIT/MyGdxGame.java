package jon.game.INIT;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jon.game.CORE.Debugger;
import jon.game.CORE.MANAGERS.Controls;

public class MyGdxGame extends Game {
	public static boolean blackbars = true, fullscreen = false;
	public static int V_WIDTH = 1024, V_HEIGHT = 768;
	public static String title = "Jon's Sick Game";
	public static boolean debug = false;
	public static Vector2 mouse_coords = new Vector2(0, 0);
	public static Vector3 mouse_coords_world = new Vector3(0, 0, 0);
	public static GameInstance gameInstance;
	public static SpriteBatch batch;

	@Override
	public void create() {
		Debugger.debugging = debug;
		Debugger.mute();
		batch = new SpriteBatch();
		Controls.initControls();
		gameInstance = new GameInstance();
		gameInstance.start(this);
		
		
	}

	@Override
	public void render() {
		super.render();
	}
	
	@Override
	public void dispose() {
		gameInstance.dispose();
		
	}
	
}

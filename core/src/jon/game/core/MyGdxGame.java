package jon.game.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import jon.game.debug.Debugger;
import jon.game.screens.JScreen;
import jon.game.tools.*;
import jon.game.utils.Point2;

public class MyGdxGame extends Game {
	public static boolean blackbars = false, fullscreen = false;
	public static int V_WIDTH = 1024, V_HEIGHT = 768;
	public static String title = "Jon's Game", version = "0.1a";
	public static boolean debug_graphic = true, debug_verbose = false;
	public static Point2 mouse_coords = new Point2(0, 0), mouse_coords_world = new Point2(0, 0);
	private static MyGdxGame game;
	public static Skin skin_default;
	
	MusicManager manager_music;
	AssetManager manager_asset;
	ConfigManager manager_config;
	FontManager manager_font;
	LanguageManager manager_lang;
	PreferenceManager manager_pref;
	ScreenManager manager_screen;
	GameInstance gameInstance;
	SpriteBatch batch;
	InputMultiplexer inputs;
	float delta;

	@Override
	public void create() {
		skin_default = new Skin(new FileHandle("assets/skins/flat/skin/flat-earth-ui.json"));
		game = this;
		inputs = new InputMultiplexer();
		batch = new SpriteBatch();
		
		Debugger.debugging_verbose = debug_verbose;
		Debugger.debugging_graphic = debug_graphic;
		Controls.initControls();
		
		manager_screen = new ScreenManager();
		gameInstance = new GameInstance();
		gameInstance.start();
		
		
	}

	@Override
	public void render() {
		delta = Gdx.graphics.getDeltaTime();
		
		if (Gdx.input.isKeyJustPressed(Keys.F1)) {
			//Visual Debugging
			debug_graphic = !debug_graphic;
		}
			
		if (Gdx.input.isKeyJustPressed(Keys.F2)) {
			//Verbose Debugging
			debug_verbose = !debug_verbose;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.F3)) {
			
		}
			
		if (Gdx.input.isKeyJustPressed(Keys.F4)) {
			//Screenshot
			byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
			
			Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
			BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
			PixmapIO.writePNG(Gdx.files.external("screenshots/" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "_screenshot.png"), pixmap);
			pixmap.dispose();
		}
		
		batch.setProjectionMatrix(manager_screen.active_screen.camera_main.combined);
		batch.begin();
		
		/*
		manager_music
		manager_asset
		manager_config
		manager_font
		manager_lang
		manager_pref
		*/
		
		manager_screen.update(delta);
		gameInstance.update(delta, batch);
		batch.end();
		super.render();
	}
	
	@Override
	public void dispose() {
		gameInstance.dispose();
		
	}
	
	public static Point2 getMouseCoords(){
		return mouse_coords;
	}
	
	public static Point2 getMouseCoordsWorld(){
		return mouse_coords_world;
	}
	
	public void addInputProcessor(InputProcessor p) {
		inputs.addProcessor(p);
		Gdx.input.setInputProcessor(inputs);
	}
	public MusicManager getMusicManager() {
		return this.manager_music;
	}
	public AssetManager getAssetManager() {
		return this.manager_asset;
	}
	public ConfigManager getConfigManager() {
		return this.manager_config;
	}
	public FontManager getFontManager() {
		return this.manager_font;
	}
	public LanguageManager getLanguageManager() {
		return this.manager_lang;
	}
	public PreferenceManager getPreferenceManager() {
		return this.manager_pref;
	}
	public ScreenManager getScreenManager() {
		return this.manager_screen;
	}
	public GameInstance getGameInstance() {
		return this.gameInstance;
	}
	public SpriteBatch getSpriteBatch() {
		return this.batch;
	}
	public InputMultiplexer getInputMultiplexer() {
		return this.inputs;
	}
	public static MyGdxGame getGame() {
		return game;
	}

	
	
}

package jon.game.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import jon.game.debug.Debugger;
import jon.game.debug.LogID;
import jon.game.gui.BasicWindow;
import jon.game.resource.Controls;
import jon.game.resource.Materials;
import jon.game.tools.*;
import jon.game.utils.Point2;

public class GameClient extends Game {
	
	public static boolean blackbars = true, fullscreen = false;
	public static int V_WIDTH = 1024, V_HEIGHT = 768;
	public static String title = "Jon's Game", version = "0.1a";
	public static boolean debug_graphic = true, debug_verbose = false;
	public static Point2 mouse_coords = new Point2(0, 0), mouse_coords_world = new Point2(0, 0);
	public static Skin skin_default;
	private static GameClient game;
	
	private Vector3 mouse_coordinate_update;
	
	MusicManager manager_music;
	ConfigManager manager_config;
	FontManager manager_font;
	LanguageManager manager_lang;
	PreferenceManager manager_pref;
	ScreenManager manager_screen;
	GameInstance gameInstance;
	SpriteBatch batch;
	InputMultiplexer inputs;
	float delta, parentAlpha;

	@Override
	public void create() {
		
		skin_default = new Skin(new FileHandle("assets/skins/flat-earth/skin/flat-earth-ui.json"));
		game = this;
		
		Controls.init();
		Materials.load();
		
		mouse_coordinate_update = new Vector3();
		
		batch = new SpriteBatch();
		inputs = new InputMultiplexer();
		
		manager_music = new MusicManager();
		manager_config = new ConfigManager();
		manager_font = new FontManager();
		manager_lang = new LanguageManager();
		manager_pref = new PreferenceManager();
		manager_screen = new ScreenManager();
		manager_screen.createStartScreen();
		setScreen(manager_screen.active_screen);
		
		gameInstance = new GameInstance();
		gameInstance.start();
		
		Debugger.debugging_verbose = debug_verbose;
		Debugger.debugging_graphic = debug_graphic;
	
	
	}

	@Override
	public void render() {
		delta = Gdx.graphics.getDeltaTime();
		parentAlpha = 255;
		
		if (Gdx.input.isKeyJustPressed(Keys.F1)) {
			//Visual Debugging
			debug_graphic = !debug_graphic;
			Debugger.debugging_graphic = debug_graphic;
			
			if(debug_graphic) Debugger.log(1, "Graphic Debugger enabled", this, LogID.getLogId(this));
			else Debugger.log(1, "Verbose Debugger disabled", this, LogID.getLogId(this));
		}
			
		if (Gdx.input.isKeyJustPressed(Keys.F2)) {
			//Verbose Debugging
			debug_verbose = !debug_verbose;
			Debugger.debugging_verbose = debug_verbose;
			
			if(debug_verbose) Debugger.log(1, "Verbose Debugger enabled", this, LogID.getLogId(this));
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.F3)) {
			manager_screen.active_screen.camera_main.zoom += 1f;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			manager_screen.active_screen.camera_main.translate(-300f, 0);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			manager_screen.active_screen.camera_main.translate(300f, 0);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			manager_screen.active_screen.camera_main.translate(0, 300f);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			manager_screen.active_screen.camera_main.translate(0, -300f);
		}
		
			
		if (Gdx.input.isKeyJustPressed(Keys.F4)) {
			//Screenshot
			byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
			
			Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
			BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
			String writeloc = "JonsGame\\screenshots\\" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "_screenshot.png";
			PixmapIO.writePNG(Gdx.files.external(writeloc), pixmap);
			pixmap.dispose();
			Debugger.log(1, "Screenshot taken, saved at: " + Gdx.files.getExternalStoragePath() + writeloc, this, LogID.getLogId(this));
		}
		
		//Pre-Render
		manager_screen.update(batch, parentAlpha, delta);
		
		manager_screen.active_screen.camera_main.update(delta);
		
		
		//Render
		batch.begin();
		
		//Input
		mouse_coords.x = Gdx.input.getX();
		mouse_coords.y = Gdx.input.getY();

		mouse_coordinate_update = GameClient.getGame().getScreenManager().active_screen.camera_main.
						unproject(new Vector3(mouse_coords.x, mouse_coords.y, 0), manager_screen.active_screen.
						getViewport().getScreenX(), manager_screen.active_screen.getViewport().getScreenY(), 
						manager_screen.active_screen.getViewport().getScreenWidth(), manager_screen.active_screen.getViewport().
						getScreenHeight());
		
		mouse_coords_world.x = mouse_coordinate_update.x;
		mouse_coords_world.y = mouse_coordinate_update.y;
		
		//Managers
		manager_music.update(batch, parentAlpha, delta);
		manager_config.update(batch, parentAlpha, delta);
		manager_font.update(batch, parentAlpha, delta);
		manager_lang.update(batch, parentAlpha, delta);
		manager_pref.update(batch, parentAlpha, delta);
		
		gameInstance.update(batch, parentAlpha, delta);
		
		batch.end();
		
		//Post-Render
		Debugger.outputLogs(delta);
		Debugger.draw();
		
	}
	
	@Override
	public void dispose() {
		manager_screen.dispose();
		manager_music.dispose();
		manager_config.dispose();
		manager_font.dispose();
		manager_lang.dispose();
		manager_pref.dispose();
		
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
	public static GameClient getGame() {
		return game;
	}
	
	public static Matrix4 getMatrix(){
		return GameClient.getGame().getScreenManager().active_screen.camera_main.combined;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}

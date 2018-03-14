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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import jon.game.debug.Debugger;
import jon.game.debug.LogID;
import jon.game.resource.Controls;
import jon.game.resource.Materials;
import jon.game.tools.*;
import jon.game.utils.Point2;

/**
 * @author Jonathan Doolittle
 */

public class GameClient extends Game {
	
	//Demo variables
	public static boolean DEMO1 = false, DEMO2 = false, DEMO3 = false, DEMO4 = false;
	
	public static int V_WIDTH = 1024, V_HEIGHT = 768;
	public static int FPS_MAX = 60;
	
	private static boolean blackbars = true, fullscreen = false;
	private static boolean debug_graphic = false, debug_verbose = false;
	
	private static String title = "The Lone Woodsman", version = "0.1a";
	
	private static Skin skin_default;
	
	private static GameClient game;
	
	public static Point2 mouse_coords = new Point2(0, 0), mouse_coords_world = new Point2(0, 0);
	
	MusicManager manager_music;
	ConfigManager manager_config;
	FontManager manager_font;
	LanguageManager manager_lang;
	PreferenceManager manager_pref;
	ScreenManager manager_screen;
	SpriteBatch batch;
	InputMultiplexer inputs;
	float delta, parentAlpha;

	@Override
	public void create() {
		game = this;
		
		load();
		init();
		createManagers();
		
		Debugger.debugging_verbose = debug_verbose;
		Debugger.debugging_graphic = debug_graphic;
	}
	
	public void load() {
		Controls.load();
		Materials.load();
	}
	
	public void init() {
		parentAlpha = 255;
		skin_default = new Skin(new FileHandle("assets/skins/shade/skin/uiskin.json"));
		batch = new SpriteBatch();
		inputs = new InputMultiplexer();
	}
	
	public void createManagers() {
		manager_music = new MusicManager();
		manager_config = new ConfigManager();
		manager_font = new FontManager();
		manager_lang = new LanguageManager();
		manager_pref = new PreferenceManager();
		manager_screen = new ScreenManager();
	}

	@Override
	public void render() {
		delta = Gdx.graphics.getDeltaTime();
		
		if (Gdx.input.isKeyJustPressed(Keys.F1)) {
			toggleGraphicDebugging();
		}
			
		if (Gdx.input.isKeyJustPressed(Keys.F2)) {
			toggleVerboseDebugging();
		}
		
		//f3 is in game instance for speed boost
			
		if (Gdx.input.isKeyJustPressed(Keys.F4)) {
			takeScreenshot();
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.F5)) {
			manager_screen.gameCameraZoomIn();
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.F6)) {
			manager_screen.gameCameraZoomOut();
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.F7)) {
			
			if(DEMO4) { DEMO4 = false; System.out.println("Demo4 Complete"); }
			if(DEMO3) { DEMO3 = false; DEMO4 = true; System.out.println("Demo3 Complete"); }
			if(DEMO2) { DEMO2 = false; DEMO3 = true; System.out.println("Demo2 Complete"); }
			if(DEMO1) { DEMO1 = false; DEMO2 = true; System.out.println("Demo1 Complete"); }
			
			if(!DEMO1 && !DEMO2 && !DEMO3 && !DEMO4) DEMO1 = true;
		}
		
		//Pre-Render
		
		
		//Render
		batch.begin();
		
		//Update mouse coords
		mouse_coords.x = Gdx.input.getX();
		mouse_coords.y = Gdx.input.getY();

		Vector3 world_translation = manager_screen.getWorldTranslation(mouse_coords);
		
		mouse_coords_world.x = world_translation.x;
		mouse_coords_world.y = world_translation.y;
		
		//Managers
		manager_music.update(batch, parentAlpha, delta);
		manager_config.update(batch, parentAlpha, delta);
		manager_font.update(batch, parentAlpha, delta);
		manager_lang.update(batch, parentAlpha, delta);
		manager_pref.update(batch, parentAlpha, delta);
		manager_screen.update(batch, parentAlpha, delta);

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
		super.dispose();
	}
	
	public void toggleGraphicDebugging() {
		debug_graphic = !debug_graphic;
		Debugger.debugging_graphic = debug_graphic;
		
		if(debug_graphic) Debugger.log(1, "Graphic Debugger enabled", this, LogID.getLogId(this));
		else Debugger.log(1, "Graphic Debugger disabled", this, LogID.getLogId(this));
	}
	
	public void toggleVerboseDebugging() {
		debug_verbose = !debug_verbose;
		Debugger.debugging_verbose = debug_verbose;
		
		if(debug_verbose) Debugger.log(1, "Verbose Debugger enabled", this, LogID.getLogId(this));
		else Debugger.log(1, "Verbose Debugger disabled", this, LogID.getLogId(this));
	}
	
	public void takeScreenshot() {
		byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
		Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), Pixmap.Format.RGBA8888);
		BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
		String writeloc = "JonsGame\\screenshots\\" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + "_screenshot.png";
		PixmapIO.writePNG(Gdx.files.external(writeloc), pixmap);
		pixmap.dispose();
		Debugger.log(1, "Screenshot taken, saved at: " + Gdx.files.getExternalStoragePath() + writeloc, this, LogID.getLogId(this));
	}
	
	public static Point2 getMouseCoords(){
		return mouse_coords;
	}
	
	public static Point2 getMouseCoordsWorld(){
		return mouse_coords_world;
	}
	
	/**
	 * @param p The input processor to be added to the multiplexer
	 */
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
	public SpriteBatch getSpriteBatch() {
		return this.batch;
	}
	public InputMultiplexer getInputMultiplexer() {
		return this.inputs;
	}
	public static boolean hasBlackbars() {
		return blackbars;
	}
	public static boolean isFullscreen() {
		return fullscreen;
	}
	public static String getTitle() {
		return title;
	}
	public static String getVersion() {
		return version;
	}
	public static boolean isDebuggingGraphic() {
		return debug_graphic;
	}
	public static boolean isDebuggingVerbose() {
		return debug_verbose;
	}
	public static Skin getSkin() {
		return skin_default;
	}
	public static GameClient getGame() {
		return game;
	}
	public static Matrix4 getMatrix(){
		return GameClient.getGame().getScreenManager().getMatrix();
	}
	@Override
	public String toString() {
		return super.toString();
	}
	
}

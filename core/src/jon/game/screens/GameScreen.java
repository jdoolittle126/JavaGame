package jon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jon.game.core.GameCamera;
import jon.game.core.GameInstance;
import jon.game.core.MyGdxGame;
import jon.game.debug.Debugger;
import jon.game.gui.BasicWindow;
import jon.game.gui.InventorySlot;

public class GameScreen extends Stage implements Screen{
	public static OrthographicCamera cameraHUD;
	public static GameCamera camera;
	public static Skin skin ;
	public Viewport gameViewPort;
	Stage stage;
	
	public GameScreen(){
		camera = new GameCamera();
		cameraHUD = new OrthographicCamera();
		
		if(MyGdxGame.blackbars){
			gameViewPort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera);
		} else {
			gameViewPort = new StretchViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera);
		}
		
		stage = new Stage();
		skin = new Skin(new FileHandle("assets/skins/flat/skin/flat-earth-ui.json"));
		
		
		skin.add("invslot", resource);
		Window x = new Window("Inventory Sample Window", skin);
		stage.addActor(x);
		x.setSize(850f, 550f);
		x.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, 0);
		x.setMovable(false);
		x.addActor(new InventorySlot(skin));
		MyGdxGame.inputs.addProcessor(stage);
	}


	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		MyGdxGame.batch.setProjectionMatrix(camera.combined);
		MyGdxGame.gameInstance.update(delta);
		//stage.act(delta);
		camera.update(delta);
		cameraHUD.update();
		if(Gdx.input.isKeyPressed(Keys.E)) stage.getActors().get(0).setVisible(true);
		else stage.getActors().get(0).setVisible(false);
		stage.act(delta);
		stage.draw();
		Debugger.log(1, "FPS - " + Gdx.graphics.getFramesPerSecond());
	}

	@Override
	public void resize(int width, int height) {
		gameViewPort.update(width, height);
	}

	@Override
	public void show() {
		Debugger.addToList(camera);
		camera.translate(0f, 0f, 1f);
		//camera.lerpTo(new Vector3(200f, 300f, 1.5f), 0.25f, 1f);
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

package jon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jon.game.core.GameCamera;
import jon.game.core.MyGdxGame;
import jon.game.debug.Debugger;
import jon.game.enums.ScreenType;

public class JScreen extends Stage implements Screen {
	public OrthographicCamera camera_hud;
	public GameCamera camera_main;
	public Skin skin;
	public Viewport gameViewPort;
	public ScreenType type;
	Stage stage;
	
	public JScreen(){
		type = ScreenType.aux;
		camera_main = new GameCamera();
		camera_hud = new OrthographicCamera();
		
		if(MyGdxGame.blackbars){
			gameViewPort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera_main);
		} else {
			gameViewPort = new StretchViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera_main);
		}
		
		stage = new Stage();
		skin = MyGdxGame.skin_default;
		/*
		NinePatch p = new NinePatch(new Texture("assets/skins/flat/raw/invslot.9.png"));
		skin.add("invslot", p);
		Window x = new Window("Inventory Sample Window", skin);
		stage.addActor(x);
		x.setSize(850f, 550f);
		x.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, 0);
		x.setMovable(false);
		RectangularLayout layout = new RectangularLayout(50f, false, 5, 5);
		layout.fill(100f, 100f);
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				x.addActor(new InventorySlot(skin, layout.getWidth(i, j), layout.getHeight(i, j)));
			}
		}
		*/
		MyGdxGame.getGame().addInputProcessor(stage);
	}
	
	public JScreen(ScreenType type){
		this.type = type;
		camera_main = new GameCamera();
		camera_hud = new OrthographicCamera();
		
		if(MyGdxGame.blackbars){
			gameViewPort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera_main);
		} else {
			gameViewPort = new StretchViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera_main);
		}
		
		stage = new Stage();
		skin = MyGdxGame.skin_default;
		MyGdxGame.getGame().addInputProcessor(stage);
	}
	
	public JScreen(Skin skin){
		type = ScreenType.aux;
		camera_main = new GameCamera();
		camera_hud = new OrthographicCamera();
		
		if(MyGdxGame.blackbars){
			gameViewPort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera_main);
		} else {
			gameViewPort = new StretchViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera_main);
		}
		
		stage = new Stage();
		this.skin = skin;
		MyGdxGame.getGame().addInputProcessor(stage);
	}
	
	public JScreen(float width, float height, boolean blackbars) {
		type = ScreenType.aux;
		camera_main = new GameCamera();
		camera_hud = new OrthographicCamera();
		
		if(blackbars){
			gameViewPort = new FitViewport(width, height, camera_main);
		} else {
			gameViewPort = new StretchViewport(width, height, camera_main);
		}
		
		stage = new Stage();
		skin = MyGdxGame.skin_default;
		MyGdxGame.getGame().addInputProcessor(stage);
	}
	
	public JScreen(float width, float height, boolean blackbars, Skin skin) {
		type = ScreenType.aux;
		camera_main = new GameCamera();
		camera_hud = new OrthographicCamera();
		
		if(blackbars){
			gameViewPort = new FitViewport(width, height, camera_main);
		} else {
			gameViewPort = new StretchViewport(width, height, camera_main);
		}
		
		stage = new Stage();
		this.skin = skin;
		MyGdxGame.getGame().addInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera_main.update(delta);
		camera_hud.update();
		stage.act(delta);
		stage.draw();
		Debugger.log(1, "FPS - " + Gdx.graphics.getFramesPerSecond(), this);
	}

	@Override
	public void resize(int width, int height) {
		gameViewPort.update(width, height);
	}

	@Override
	public void show() {
		
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

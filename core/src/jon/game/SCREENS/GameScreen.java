package jon.game.SCREENS;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jon.game.CORE.Debugger;
import jon.game.CORE.GameCamera;
import jon.game.CORE.GameInstance;
import jon.game.CORE.MyGdxGame;

public class GameScreen implements Screen {
	public static OrthographicCamera cameraHUD;
	public static GameCamera camera;
	public Viewport gameViewPort;
	
	
	public GameScreen(){
		camera = new GameCamera();
		cameraHUD = new OrthographicCamera();
		
		if(MyGdxGame.blackbars){
			gameViewPort = new FitViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera);
		} else {
			gameViewPort = new StretchViewport(MyGdxGame.V_WIDTH, MyGdxGame.V_HEIGHT, camera);
		}
	}


	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		MyGdxGame.batch.setProjectionMatrix(camera.combined);
		MyGdxGame.gameInstance.update(delta);
		
		camera.update(delta);
		cameraHUD.update();
		
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

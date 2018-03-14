package jon.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jon.game.core.GameCamera;
import jon.game.core.GameClient;
import jon.game.core.GameInstance;
import jon.game.debug.Debugger;
import jon.game.debug.LogID;
import jon.game.utils.Point2;

public class GameScreen extends BasicScreen {
	private GameInstance gameInstance;
	private Viewport gameViewPort;
	public GameCamera camera_main;

	public GameScreen (GameInstance gameInstance) {
		this.gameInstance = gameInstance;
		camera_main = new GameCamera();
		if(GameClient.hasBlackbars()){
			gameViewPort = new FitViewport(GameClient.V_WIDTH, GameClient.V_HEIGHT, camera_main);
		} else {
			gameViewPort = new StretchViewport(GameClient.V_WIDTH, GameClient.V_HEIGHT, camera_main);
		}
	}

	@Override
	public void show () {
		Gdx.input.setCursorCatched(true);
		Gdx.input.setCursorPosition(1024/2, 768/2);
		gameViewPort.apply();
		gameInstance.start();
		
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera_main.update(delta);
		Debugger.log(1, "FPS - " + Gdx.graphics.getFramesPerSecond(), this, LogID.getLogId(this));
	}

	@Override
	public void update(SpriteBatch batch, float parentAlpha, float delta) {
		batch.setProjectionMatrix(camera_main.combined);
		render(delta);
		gameInstance.update(batch, parentAlpha, delta);
		gameInstance.getWorldrender().buildQue();
	}
	
	@Override
	public void hide () {

	}

	@Override
	public void resize(int width, int height) {
		gameViewPort.update(width, height);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		gameInstance.dispose();
	}
	
	@Override
	public Matrix4 getTransform() {
		return camera_main.combined;
	}
	
	public GameInstance getGame() {
		return gameInstance;
	}
	
	@Override
	public Vector3 getTranslation(Point2 coords) {
		return camera_main.unproject(new Vector3(coords.x, coords.y, 0),
				gameViewPort.getScreenX(), gameViewPort.getScreenY(), 
				gameViewPort.getScreenWidth(), gameViewPort.getScreenHeight());
	}
}
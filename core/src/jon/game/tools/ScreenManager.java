package jon.game.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import jon.game.core.GameClient;
import jon.game.core.GameInstance;
import jon.game.gui.HUD;
import jon.game.screens.BasicScreen;
import jon.game.screens.GameScreen;
import jon.game.screens.MenuScreen;
import jon.game.screens.TitleScreen;
import jon.game.utils.Point2;
import net.dermetfan.utils.math.MathUtils;

public class ScreenManager implements Manager {
	
	TitleScreen titleScreen;
	GameScreen gameScreen;
	MenuScreen menuScreen;
	BasicScreen currentScreen;
	HUD hud;
	
	public ScreenManager() {
		hud = new HUD();
		titleScreen = new TitleScreen();
		currentScreen = titleScreen;
		GameClient.getGame().setScreen(currentScreen);
	}
	
	@Override
	public void update(SpriteBatch batch, float parentAlpha, float delta) {
		currentScreen.update(batch, parentAlpha, delta);
	}
	
	public void updateHUD(float parentAlpha, float delta) {
		hud.act(delta);
		hud.draw(parentAlpha);
	}

	@Override
	public void dispose() {
		if(currentScreen != null) currentScreen.dispose();
		if(titleScreen != null) titleScreen.dispose();
		if(gameScreen != null) gameScreen.dispose();
	}
	
	public void toTitle() {
		//Create a new title screen, nothing needs to be saved
		titleScreen = new TitleScreen();
		currentScreen = titleScreen;
		GameClient.getGame().setScreen(currentScreen);
	}
	
	public void toGame() {
		currentScreen = gameScreen;
		GameClient.getGame().setScreen(currentScreen);
	}
	
	public GameScreen getGameScreen() {
		return this.gameScreen;
	}
	
	public void setGameScreen(GameInstance gameInstance) {
		gameScreen = new GameScreen(gameInstance, GameClient.getViewPort());
	}
	
	public Vector3 getWorldTranslation(Point2 coords) {
		return currentScreen.getTranslation(coords);
	}
	
	public Matrix4 getMatrix() {
		return currentScreen.getTransform();
	}
	
	public void gameCameraZoomIn() {
		gameScreen.camera_main.zoom = MathUtils.clamp(gameScreen.camera_main.zoom-1, 1, 100);
	}
	
	public void gameCameraZoomOut() {
		gameScreen.camera_main.zoom = MathUtils.clamp(gameScreen.camera_main.zoom+1, 1, 100);
	}
	
}

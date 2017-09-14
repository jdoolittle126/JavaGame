package jon.game.COMPONENTS;

import java.util.HashMap;
import java.util.ResourceBundle.Control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input.Keys;

import jon.game.CORE.MyGdxGame;
import jon.game.CORE.MANAGERS.Controls;
import jon.game.ENTITY.Entity;
import jon.game.SCREENS.GameScreen;

public class PlayerController implements InputProcessor {
	private Entity puppet;
	private int camType = 2; //0 locked cam, 1, cam on entity, 2 freecam
	private float camSmoothing = 0f;
	private float[] camOffset = {0f, 0f};
	
	public PlayerController(Entity puppet){
		this.puppet = puppet;
		
	}

	@Override
	public boolean keyDown(int keycode) {
		for(HashMap<Integer, Action> item : Controls.controls){
			if(item.containsKey(keycode)){
				puppet.startAction(item.get(keycode));
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		for(HashMap<Integer, Action> item : Controls.controls){
			if(item.containsKey(keycode)){
				puppet.endAction(item.get(keycode));
			}
		}
		return false;

	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
		
	}
	
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	
	
	
}

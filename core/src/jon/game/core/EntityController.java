package jon.game.core;

import java.util.HashMap;
import java.util.ResourceBundle.Control;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input.Keys;

import jon.game.entity.Entity;
import jon.game.entity.EntityDynamic;
import jon.game.enums.Action;
import jon.game.resource.Controls;

public class EntityController implements InputProcessor {
	private EntityDynamic puppet;
	private int camType = 2; //0 locked cam, 1, cam on entity, 2 freecam
	private float camSmoothing = 0f;
	private float[] camOffset = {0f, 0f};
	
	public EntityController(EntityDynamic puppet){
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

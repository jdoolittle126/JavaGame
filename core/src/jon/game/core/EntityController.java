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
	
	public EntityController(EntityDynamic puppet){
		this.puppet = puppet;
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if(Controls.getBinds().get(keycode) != null){
			for(Action a : Controls.getBinds().get(keycode)){
				puppet.startAction(a);
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(Controls.getBinds().get(keycode) != null){
			for(Action a : Controls.getBinds().get(keycode)){
				puppet.endAction(a);
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

package jon.game.core;

import com.badlogic.gdx.InputProcessor;
import jon.game.entity.EntityDynamic;
import jon.game.enums.Action;
import jon.game.resource.Controls;

public class EntityController implements InputProcessor {
	
	/*
	 * This class takes in and entity and allows user input to
	 * start and finish actions
	 */
	
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

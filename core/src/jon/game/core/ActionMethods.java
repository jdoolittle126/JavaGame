package jon.game.core;

public interface ActionMethods {
	
	abstract void plus_action_forward(float delta);
	abstract void plus_action_backwards(float delta);
	abstract void plus_action_left(float delta);
	abstract void plus_action_right(float delta);
	
	abstract void minus_action_forward(float delta);
	abstract void minus_action_backwards(float delta);
	abstract void minus_action_left(float delta);
	abstract void minus_action_right(float delta);
}

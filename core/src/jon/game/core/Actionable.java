package jon.game.core;

public interface Actionable {
	
	/*
	 * This interface provides the core methods used to initiate
	 * and finish actions.
	 */
	
	abstract void plus_action_forward(float delta);
	abstract void plus_action_backwards(float delta);
	abstract void plus_action_left(float delta);
	abstract void plus_action_right(float delta);
	
	abstract void minus_action_forward(float delta);
	abstract void minus_action_backwards(float delta);
	abstract void minus_action_left(float delta);
	abstract void minus_action_right(float delta);
}

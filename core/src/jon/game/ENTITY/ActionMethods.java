package jon.game.ENTITY;

public interface ActionMethods {
	
	abstract void action_forward();
	abstract void action_backwards();
	abstract void action_left();
	abstract void action_right();
	
	abstract void action_forward_end();
	abstract void action_backwards_end();
	abstract void action_left_end();
	abstract void action_right_end();
}

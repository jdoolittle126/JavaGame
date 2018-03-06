package jon.game.statistics;

public class MovementStatistics {
	
	public float stat_speed_base, stat_speed_mod_left, stat_speed_mod_forward, stat_speed_mod_right, stat_speed_mod_backwards;

	public MovementStatistics() {
		this.stat_speed_base = 40f;
		this.stat_speed_mod_left = 0.75f;
		this.stat_speed_mod_forward = 1f;
		this.stat_speed_mod_right = 0.75f;
		this.stat_speed_mod_backwards = 0.5f;
	}
	
	public MovementStatistics(float stat_speed_base, float stat_speed_mod_left, float stat_speed_mod_forward, float stat_speed_mod_right, float stat_speed_mod_backwards) {
		this.stat_speed_base = stat_speed_base;
		this.stat_speed_mod_left = stat_speed_mod_left;
		this.stat_speed_mod_forward = stat_speed_mod_forward;
		this.stat_speed_mod_right = stat_speed_mod_right;
		this.stat_speed_mod_backwards = stat_speed_mod_backwards;
	}

	public void setSpeedBase(float stat_speed_base) {
		this.stat_speed_base = stat_speed_base;
	}
	public void setSpeedForward(float stat_speed_mod_forward) {
		this.stat_speed_mod_forward = stat_speed_mod_forward;
	}
	public void setSpeedBackwards(float stat_speed_mod_backwards) {
		this.stat_speed_mod_backwards = stat_speed_mod_backwards;
	}
	public void setSpeedLeft(float stat_speed_mod_left) {
		this.stat_speed_mod_left = stat_speed_mod_left;
	}
	public void setSpeedRight(float stat_speed_mod_right) {
		this.stat_speed_mod_right = stat_speed_mod_right;
	}

	
}

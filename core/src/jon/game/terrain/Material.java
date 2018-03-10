package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Material {
	private Sprite sprite;
	private int collision;
	private float movement_modifier;
	public String name = "";
	
	public Material(Material material) {
		this.sprite = material.sprite;
		this.collision = material.collision;
		this.movement_modifier = material.movement_modifier;
	}

	public Material(Sprite sprite) {
		this.sprite = sprite;
		this.collision = 0;
		this.movement_modifier = 1f;
	}

	public Material(Sprite sprite, int collision) {
		this.sprite = sprite;
		this.collision = collision;
		this.movement_modifier = 1f;
	}
	
	public Material(Sprite sprite, int collision, float movement_modifer) {
		this.sprite = sprite;
		this.collision = collision;
		this.movement_modifier = movement_modifer;
	}
	
	public Material(Sprite sprite, float movement_modifer) {
		this.sprite = sprite;
		this.collision = 0;
		this.movement_modifier = movement_modifer;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public int getCollision() {
		return this.collision;
	}
	
	public float getMovementModifier() {
		return this.movement_modifier;
	}
	
}

package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Material {
	private Sprite sprite;
	private float z;
	
	public Material(Sprite sprite, float z) {
		this.sprite = sprite;
		this.z = z;
	}
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public float getZ(){
		return this.z;
	}
	
}

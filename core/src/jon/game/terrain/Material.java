package jon.game.terrain;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Material {
	
	private Sprite texture;
	
	public static Material grass = new Material("assets/materials/grass.jpg");
	public static Material water = new Material("assets/materials/water.jpg");
	public static Material test = new Material("assets/materials/test.jpg");
	public static Material outline = new Material("assets/materials/outline.png");
	
	public Material(String filepath) {
		texture = new Sprite(new Texture(new FileHandle(filepath)));
	}
	
	public Sprite getTexture() {
		return this.texture;
	}
	
}

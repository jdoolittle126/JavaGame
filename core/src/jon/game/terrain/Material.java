package jon.game.terrain;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Material {
	
	public Sprite texture;

	public Material(String filepath) {
		texture = new Sprite(new Texture(new FileHandle(filepath)));
	}
	
}

package jon.game.gui;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class BasicWindow extends Actor {
	Stage stage;
	Rectangle size;
	int z;
	NinePatch border;
	Texture background;
	
	public BasicWindow() {
		
	}
	
	public void render() {
		
	}
	
	
}

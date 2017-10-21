package jon.tools.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import jon.game.terrain.MapEditWindow;
import jon.game.terrain.Material;

public class TestActor extends Actor {
	
	public TestActor(){
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Material.grass.getTexture().setPosition(MapEditWindow.mouse_coords_window.x, MapEditWindow.mouse_coords_window.y);
		Material.grass.getTexture().draw(batch);
		
	}
	
	

}

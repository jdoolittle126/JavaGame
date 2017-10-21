package jon.tools.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import jon.game.terrain.MapEditWindow;
import jon.game.terrain.Material;

public class TestActor extends Actor {
	MapEditWindow editWindow;
	
	
	public TestActor(MapEditWindow editWindow){
		this.editWindow = editWindow;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		System.out.println(this.getX() + ", " + this.getY());
		
		if(		(this.getX() + this.getWidth() < editWindow.getWindowCoords().x) ||
				(this.getX() > editWindow.getWindowCoords().x + editWindow.getWindowSize().x) ||
				(this.getY() > editWindow.getWindowCoords().y + editWindow.getWindowSize().y) ||
				(this.getY() + this.getHeight() < editWindow.getWindowCoords().y)) this.setVisible(false);
		
		
		this.setPosition(MapEditWindow.mouse_coords_window.x, MapEditWindow.mouse_coords_window.y);
		this.setSize(Material.grass.getTexture().getWidth(), Material.grass.getTexture().getHeight());
		
		batch.setProjectionMatrix(this.getStage().getCamera().combined);
		Material.grass.getTexture().setPosition(this.getX(), this.getY());
		Material.grass.getTexture().draw(batch);
		
	}
	
	

}

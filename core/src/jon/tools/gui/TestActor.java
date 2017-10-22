package jon.tools.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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

		System.out.println("OBJECT: " + this.getX() + ", " + this.getY() + "\t" + this.getWidth() + ", " + this.getHeight());
		System.out.println("WINDOW: " + editWindow.getX() + ", " + editWindow.getY() + "\t" + editWindow.getWidth() + ", " + editWindow.getHeight());
		
		/*
		if(		(editWindow.getX() + this.getX() + this.getWidth() < editWindow.getX()) ||
				(editWindow.getX() + this.getX() > editWindow.getX() + editWindow.getWidth()) ||
				(this.getY() > editWindow.getWindowCoords().y + editWindow.getWindowSize().y) ||
				(this.getY() + this.getHeight() < editWindow.getWindowCoords().y)) System.out.print("");
		else System.out.print("");
		*/
		
		//MapEditWindow.mouse_coords_window.x + 
		Vector3 test = MapEditor.getEditor().getStage().getCamera().unproject(new Vector3(editWindow.getX(), editWindow.getY(), 0));
		Vector3 test2 = editWindow.getTestStage().getCamera().unproject(test);
		//Vector3 test3 = 
		
		this.setPosition(test2.x, test2.y);
		
		
		batch.setProjectionMatrix(this.getStage().getCamera().combined);
		Material.grass.getTexture().setPosition(this.getX(), this.getY());
		Material.grass.getTexture().draw(batch);
		
	}
	
	

}

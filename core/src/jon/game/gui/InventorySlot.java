package jon.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jon.game.screens.GameScreen;

public class InventorySlot extends Actor {
	public Skin skin;

	public InventorySlot(Skin skin) {
		this.skin = skin;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		//add to skin later
		skin.getDrawable("invslot").draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		super.draw(batch, parentAlpha);
	}
	
	public static Table getInventoryConfiguration(int x, int y) {
		
	}
	
	
	
}

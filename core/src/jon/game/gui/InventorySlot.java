package jon.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jon.game.screens.GameScreen;

public class InventorySlot extends Actor {
	public Skin skin;
	public static final float DEFAULT_CELL_WIDTH = 50f, DEFAULT_CELL_HEIGHT = 50f;

	public InventorySlot(Skin skin) {
		this.skin = skin;
		this.setX(0f);
		this.setY(0f);
		this.setWidth(50f);
		this.setHeight(50f);
	}
	
	public InventorySlot(Skin skin, float x, float y) {
		this.skin = skin;
		this.setX(x * DEFAULT_CELL_WIDTH);
		this.setY(y * DEFAULT_CELL_HEIGHT);
		this.setWidth(DEFAULT_CELL_WIDTH);
		this.setHeight(DEFAULT_CELL_HEIGHT);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		//add to skin later
		skin.getDrawable("invslot").draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
		super.draw(batch, parentAlpha);
	}
	
	public static Table getInventoryConfiguration(int x, int y) {
		return null;
		
		
	}
	
	
	
}

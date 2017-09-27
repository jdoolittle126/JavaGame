package jon.game.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public abstract class BasicWindow extends Window {
	
	
	public BasicWindow(String title, Skin skin) {
		super(title, skin);
	}

	public BasicWindow(String title, Skin skin, String styleName) {
		super(title, skin, styleName);
	}

	public BasicWindow(String title, WindowStyle style) {
		super(title, style);
	}
	
	public BasicWindow() {
		super("", new Skin());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}

	@Override
	protected void drawStageBackground(Batch batch, float parentAlpha, float x, float y, float width, float height) {
		// TODO Auto-generated method stub
		super.drawStageBackground(batch, parentAlpha, x, y, width, height);
	}

	@Override
	protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
		// TODO Auto-generated method stub
		super.drawBackground(batch, parentAlpha, x, y);
	}
	
	
	
}

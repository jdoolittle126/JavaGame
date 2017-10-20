package jon.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
	public <T extends Actor> Cell<T> add(T actor) {
		// TODO Auto-generated method stub
		return super.add(actor);
	}

	@Override
	public void add(Actor... actors) {
		// TODO Auto-generated method stub
		super.add(actors);
	}

	@Override
	public Cell<Label> add(CharSequence text) {
		// TODO Auto-generated method stub
		return super.add(text);
	}

	@Override
	public Cell<Label> add(CharSequence text, String labelStyleName) {
		// TODO Auto-generated method stub
		return super.add(text, labelStyleName);
	}

	@Override
	public Cell<Label> add(CharSequence text, String fontName, Color color) {
		// TODO Auto-generated method stub
		return super.add(text, fontName, color);
	}

	@Override
	public Cell<Label> add(CharSequence text, String fontName, String colorName) {
		// TODO Auto-generated method stub
		return super.add(text, fontName, colorName);
	}

	@Override
	public Cell add() {
		// TODO Auto-generated method stub
		return super.add();
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

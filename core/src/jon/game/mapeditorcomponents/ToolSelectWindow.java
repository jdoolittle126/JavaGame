package jon.game.mapeditorcomponents;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class ToolSelectWindow extends Window {

	public ToolSelectWindow(String title, Skin skin) {
		super(title, skin);
		this.setWidth(64f*2);
		this.setHeight(64f*6f);
	}

}

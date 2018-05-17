package jon.game.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jon.game.core.GameClient;
import jon.game.inventory.Inventory;

public class HUD implements Disposable {
	
	Stage stage;
	Skin skin;
	Table root;
	

	public HUD() {
		skin = GameClient.getSkin();
	    stage = new Stage();
	    
	    GameClient.getGame().addInputProcessor(stage);
	    
	    root = new Table();
	    root.setFillParent(true);
	    root.debug();

	    Inventory inv = new Inventory();
	    
	    root.add(inv).expand();
	    stage.addActor(root);
	    
	}

	
	public void act(float delta) {
		stage.act(delta);
	}
	
	public void draw(float parentAlpha) {
		//fix alpha
		stage.draw();
	}


	@Override
	public void dispose() {
		stage.dispose();
	}

}
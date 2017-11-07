package jon.game.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
	
	private AssetManager manager;
	private TextureAtlas atlas;
	
	//All Assets
	public static final String 
		string_atlas = "assets/packs/texture_assets.atlas";

	
	public Assets(){
		manager = new AssetManager();
	}
	
	public void load(){
		manager.load(string_atlas, TextureAtlas.class);

	}
	
	public void done(){
		atlas = manager.get(string_atlas);
	}
	
	public AssetManager getManager(){
		return this.manager;
	}
	
	public TextureAtlas getAtlas() {
		return this.atlas;
	}
	
	

}

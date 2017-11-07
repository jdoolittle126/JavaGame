package jon.game.resource;

import jon.game.terrain.Material;

public class Materials {
	public static Material 
			grass,
			water,
			test,
			outline;
	
	public static void load() {
		Assets assets = new Assets();
		
		grass = new Material(assets.getAtlas().createSprite("grass"), 0);
		water = new Material(assets.getAtlas().createSprite("water"), 0);
		test = new Material(assets.getAtlas().createSprite("test"), 0);
		outline = new Material(assets.getAtlas().createSprite("outline"), 0);
	}
}

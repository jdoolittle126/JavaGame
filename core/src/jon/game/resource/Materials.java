package jon.game.resource;

import jon.game.terrain.Material;

public class Materials {
	public static Material 
			grass,
			water,
			sand,
			test,
			outline;
	
	public static void load() {
		Assets assets = new Assets();
		assets.load();
		assets.done();
		
		grass = new Material(assets.getAtlas().createSprite("materials/grass"), 0, 0);
		water = new Material(assets.getAtlas().createSprite("materials/water"), 1, 0);
		sand = new Material(assets.getAtlas().createSprite("materials/sand"), 0, 0);
		test = new Material(assets.getAtlas().createSprite("materials/test"), 0);
		outline = new Material(assets.getAtlas().createSprite("materials/outline"), 0);
	}
}

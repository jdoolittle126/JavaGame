package jon.game.resource;

import com.badlogic.gdx.graphics.g2d.Sprite;
import jon.game.terrain.Material;

public class Materials {
	public static Material 
			grass,
			water,
			sand,
			test,
			outline;
	
	public static Sprite
			bear,
			beaver,
			duck,
			player,
			rabbit,
			racoon,
			rock,
			tree;
			
	
	public static void load() {
		Assets assets = new Assets();
		assets.load();
		assets.done();
		
		grass = new Material(assets.getAtlas().createSprite("materials/grass"), 0, 1);
		water = new Material(assets.getAtlas().createSprite("materials/water"), 1, 1);
		sand = new Material(assets.getAtlas().createSprite("materials/sand"), 0, 1);
		test = new Material(assets.getAtlas().createSprite("materials/test"), 1);
		outline = new Material(assets.getAtlas().createSprite("materials/outline"), 0);
		
		bear = assets.getAtlas().createSprite("entities/bear");
		beaver = assets.getAtlas().createSprite("entities/beaver");
		duck = assets.getAtlas().createSprite("entities/duck");
		player = assets.getAtlas().createSprite("entities/player");
		rabbit = assets.getAtlas().createSprite("entities/bunny");
		racoon = assets.getAtlas().createSprite("entities/racoon");
		rock = assets.getAtlas().createSprite("entities/rock");
		tree = assets.getAtlas().createSprite("entities/tree");
	}
}

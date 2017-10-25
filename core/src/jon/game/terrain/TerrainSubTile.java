package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import jon.game.enums.TileType;

public class TerrainSubTile extends Actor {
	
	private TileType type;
	private Material material;
	
	public TerrainSubTile(TileType type) {
		this.setWidth(TerrainTile.SUBTILE_SIZE);
		this.setHeight(TerrainTile.SUBTILE_SIZE);
		this.type = type;
		if(type == TileType.water) material = Material.water;
		else if(type == TileType.grass) material = Material.grass;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		material.getTexture().draw(batch);
		super.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public TileType getTileType() {
		return type;
	}
	
	public Material getMaterial() {
		return this.material;
	}

}

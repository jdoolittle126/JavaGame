package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import jon.game.enums.TileType;
import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class TerrainSubTile extends Actor {
	
	private TileType type;
	private Material material;
	
	public TerrainSubTile(Point2 coords, TileType type) {
		this.setX(coords.x * TerrainTile.SUBTILE_SIZE);
		this.setY(coords.y * TerrainTile.SUBTILE_SIZE);
		this.setWidth(TerrainTile.SUBTILE_SIZE);
		this.setHeight(TerrainTile.SUBTILE_SIZE);
		this.type = type;
		
		if(type == TileType.grass) material = Materials.grass;
		else if(type == TileType.water) material = Materials.water;
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		material.getSprite().draw(batch);
	}

	@Override
	public void act(float delta) {
		material.getSprite().setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	public TileType getTileType() {
		return type;
	}
	
	public Material getMaterial() {
		return this.material;
	}

}

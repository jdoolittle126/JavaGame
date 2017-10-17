package jon.game.terrain;

import jon.game.enums.TileType;

public class TerrainSubTile {
	
	private TileType type;
	private Material material;
	
	public TerrainSubTile(TileType type) {
		this.type = type;
		if(type == TileType.water) material = Material.water;
		else if(type == TileType.grass) material = Material.grass;
	}
	
	public TileType getTileType() {
		return type;
	}
	
	public Material getMaterial() {
		return this.material;
	}

}

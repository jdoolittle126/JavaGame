package jon.game.terrain;

import com.badlogic.gdx.files.FileHandle;

public class TerrainSubTile {
	
	public enum TileType {
		water,
		grass,
		rock;
	}
	
	private TileType type;
	public Material material;
	
	public TerrainSubTile(TileType type) {
		if(type == TileType.water) material = new Material("assets/materials/water.jpg");
		if(type == TileType.grass) material = new Material("assets/materials/grass.jpg");
	}
	
	public TileType getTileType() {
		return this.type;
	}

}

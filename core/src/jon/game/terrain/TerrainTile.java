package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jon.game.terrain.TerrainSubTile.TileType;
import jon.game.utils.Point2;

public class TerrainTile {
	
	public TerrainSubTile[][] subsections;
	public static final int DETAIL_PER_SECTION = 3;
	public Point2 coords;
	
	public TerrainTile() {
		subsections = new TerrainSubTile[DETAIL_PER_SECTION][DETAIL_PER_SECTION];
		for(int x = 0; x < DETAIL_PER_SECTION; x++) {
			for(int y = 0; y < DETAIL_PER_SECTION; y++) {
				
				if(x == 1 && y == 0) subsections[x][y] = new TerrainSubTile(TileType.water);
				else subsections[x][y] = new TerrainSubTile(TileType.grass);
				}
			}
	}
	
	public void update(float delta, SpriteBatch batch) {
		
		for(int x = 0; x < DETAIL_PER_SECTION; x++) {
			for(int y = 0; y < DETAIL_PER_SECTION; y++) {
				subsections[x][y].material.texture.setRegion(coords.x * 96 + x * 32, coords.y * 96 + y * 32, 32, 32);
				subsections[x][y].material.texture.draw(batch);
				/*	
				TerrainSubTile tl = subsections[x-1][y-1];
				TerrainSubTile tm = subsections[x][y-1];
				TerrainSubTile tr = subsections[x+1][y-1];
				
				TerrainSubTile ml = subsections[x-1][y];
				TerrainSubTile mm = subsections[x][y];
				TerrainSubTile mr = subsections[x+1][y];
				
				TerrainSubTile bl = subsections[x-1][y+1];
				TerrainSubTile bm = subsections[x][y+1];
				TerrainSubTile br = subsections[x+1][y+1];
				*/
			}
		}
		
	}
	
	
	

}

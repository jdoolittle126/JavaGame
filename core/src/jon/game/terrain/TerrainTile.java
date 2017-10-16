package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jon.game.enums.TileType;
import jon.game.utils.Point2;

public class TerrainTile {
	
	public TerrainSubTile[][] subsections;
	public static final int DETAIL_PER_SECTION = 3, SUBTILE_SIZE = 32;
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
		
		for(int y = 0; y < DETAIL_PER_SECTION; y++) {
			for(int x = 0; x < DETAIL_PER_SECTION; x++) {
				subsections[x][y].getMaterial().getTexture().setX(((coords.x * SUBTILE_SIZE * DETAIL_PER_SECTION) + (x * SUBTILE_SIZE)));
				subsections[x][y].getMaterial().getTexture().setY(((coords.y * SUBTILE_SIZE * DETAIL_PER_SECTION) + (y * SUBTILE_SIZE)));
				subsections[x][y].getMaterial().getTexture().draw(batch);
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

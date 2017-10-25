package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jon.game.enums.TileType;
import jon.game.utils.Point2;

public class TerrainTile {
	
	public float scale = 1f;
	public TerrainSubTile[][] subsections;
	public static final int DETAIL_PER_SECTION = 3, SUBTILE_SIZE = 32;
	public Point2 coords;
	
	public TerrainTile(Point2 coords) {
		this.coords = coords;
		subsections = new TerrainSubTile[DETAIL_PER_SECTION][DETAIL_PER_SECTION];
	}
	
	public void add(int x, int y, TerrainSubTile tile){
		subsections[x][y] = tile;
	}
	
	public void update(float delta, SpriteBatch batch) {
		
		for(int y = 0; y < DETAIL_PER_SECTION; y++) {
			for(int x = 0; x < DETAIL_PER_SECTION; x++) {
				subsections[x][y].getMaterial().getTexture().setX(scale * ((coords.x * SUBTILE_SIZE * DETAIL_PER_SECTION) + (x * SUBTILE_SIZE)));
				subsections[x][y].getMaterial().getTexture().setY(scale * ((coords.y * SUBTILE_SIZE * DETAIL_PER_SECTION) + (y * SUBTILE_SIZE)));
				subsections[x][y].getMaterial().getTexture().setScale(scale);
				subsections[x][y].getMaterial().getTexture().draw(batch);
			}
		}
		
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	

}

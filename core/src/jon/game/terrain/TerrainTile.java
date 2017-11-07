package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jon.game.enums.TileType;
import jon.game.utils.Point2;

public class TerrainTile extends Group {
	
	public static final int DETAIL_PER_SECTION = 3, SUBTILE_SIZE = 32;
	
	public TerrainSubTile[][] subsections;
	public Point2 coords;
	boolean flag = false;
	
	public TerrainTile(Point2 coords) {
		
		this.setX(coords.x * TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION);
		this.setY(coords.y * TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION);
		this.setWidth(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION);
		this.setHeight(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION);
		
		subsections = new TerrainSubTile[DETAIL_PER_SECTION][DETAIL_PER_SECTION];
		this.coords = coords;
		
	}
	
	public void add(int x, int y, TerrainSubTile tile){
		subsections[x][y] = tile;
	}

	@Override
	public void act(float delta) {
		if(flag) {
			for(int x = 0; x < DETAIL_PER_SECTION; x++) {
				for(int y = 0; y < DETAIL_PER_SECTION; y++) {
					this.addActor(subsections[x][y]);
				}
			}
			flag = false;
		}
		super.act(delta);
	}
	
	

}

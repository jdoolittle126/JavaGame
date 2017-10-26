package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jon.game.enums.TileType;
import jon.game.utils.Point2;

public class TerrainTile extends Table {
	
	public float scale = 1f;
	public TerrainSubTile[][] subsections;
	private Cell<?>[][] cell_data;
	public static final int DETAIL_PER_SECTION = 3, SUBTILE_SIZE = 32;
	public Point2 coords;
	
	public TerrainTile(Point2 coords) {
		this.setWidth(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION);
		this.setHeight(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION);
		this.coords = coords;
		subsections = new TerrainSubTile[DETAIL_PER_SECTION][DETAIL_PER_SECTION];
		cell_data = new Cell[DETAIL_PER_SECTION][DETAIL_PER_SECTION];
		
		for(int x = 0; x < DETAIL_PER_SECTION; x++) {
			for(int y = 0; y < DETAIL_PER_SECTION; y++) {
				cell_data[x][y] = this.add().fill().width(SUBTILE_SIZE).height(SUBTILE_SIZE).pad(0f);
			}
			this.row();
		}
		
	}
	
	public void add(int x, int y, TerrainSubTile tile){
		subsections[x][y] = tile;
		cell_data[x][y].setActor(tile).expand();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		for(int y = 0; y < DETAIL_PER_SECTION; y++) {
			for(int x = 0; x < DETAIL_PER_SECTION; x++) {
				/*
				subsections[x][y].getMaterial().getTexture().setX(scale * ((coords.x * SUBTILE_SIZE * DETAIL_PER_SECTION) + (x * SUBTILE_SIZE)));
				subsections[x][y].getMaterial().getTexture().setY(scale * ((coords.y * SUBTILE_SIZE * DETAIL_PER_SECTION) + (y * SUBTILE_SIZE)));
				*/
				
				subsections[x][y].getMaterial().getTexture().setScale(scale);
			}
		}
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	
	

}

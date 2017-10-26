package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jon.game.utils.Point2;

public class Chunk extends Table {
	
	private TerrainTile[][] chunk_data;
	private Cell<?>[][] cell_data;
	private Point2 coords;
	public static final int CHUNK_SIZE = 32;
	
	public Chunk(Point2 coords){
		this.setWidth(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * CHUNK_SIZE);
		this.setHeight(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * CHUNK_SIZE);
		
		chunk_data = new TerrainTile[CHUNK_SIZE][CHUNK_SIZE];
		cell_data = new Cell[CHUNK_SIZE][CHUNK_SIZE];
		this.coords = coords;
		
		this.setDebug(true);
		
		
		for(int x = 0; x < CHUNK_SIZE; x++) {
			for(int y = 0; y < CHUNK_SIZE; y++) {
				cell_data[x][y] = this.add().fill();
			}
			this.row();
		}
	}
	
	public void add(int x, int y, TerrainTile tile){
		chunk_data[x][y] = tile;
		cell_data[x][y].setActor(tile).expand();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public TerrainTile get(int x, int y){
		return chunk_data[x][y];
	}
	
	public TerrainTile[][] getAll(){
		return chunk_data;
	}
	
	public Point2 getCoords() {
		return this.coords;
	}

}

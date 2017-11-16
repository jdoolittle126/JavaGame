package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class Chunk extends Group {
	
	public static final int CHUNK_SIZE = 32;
	
	private TerrainTile[][] chunk_data;
	private Point2 coords;
	boolean flag = true;
	
	public Chunk(Point2 coords){
		
		this.setX(coords.x * TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * CHUNK_SIZE);
		this.setY(coords.y * TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * CHUNK_SIZE);
		this.setWidth(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * CHUNK_SIZE);
		this.setHeight(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * CHUNK_SIZE);
		
		chunk_data = new TerrainTile[CHUNK_SIZE][CHUNK_SIZE];
		this.coords = coords;
	}
	
	public void add(int x, int y, TerrainTile tile){
		chunk_data[x][y] = tile;
	}

	@Override
	public void act(float delta) {
		if(flag) {
			for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
				for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {
					this.addActor(chunk_data[x][y]);
				}
			}
			flag = false;
		}
		super.act(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		Materials.grass.getSprite().draw(batch);
		super.draw(batch, parentAlpha);
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

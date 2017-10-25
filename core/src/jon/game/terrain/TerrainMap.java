package jon.game.terrain;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jon.game.enums.TileType;
import jon.game.utils.Point2;

public class TerrainMap extends Table {
	
	private int chunk_min_x = 0, chunk_min_y = 0, chunk_max_x = 0, chunk_max_y = 0;
	protected ArrayList<Chunk> loaded_chunks;
	protected Cell cell_data[][];
	
	protected TerrainMapIO readwrite;
	protected boolean force_load_all_chunks = false;
	
	public enum MapType {
		filled,
		blank,
		fixed;
	}
	
	public TerrainMap(MapType type) {
		
		if(!(type.equals(MapType.blank))) loaded_chunks = loadTestMap(3, 3);
		else loaded_chunks = new ArrayList<Chunk>();
		
		int total_x = Math.abs(chunk_min_x - chunk_max_x);
		int total_y = Math.abs(chunk_min_y - chunk_max_y);
		
		cell_data = new Cell[total_x][total_y];
		
		readwrite = new TerrainMapIO("path");
		this.setDebug(true);
		
		for(int x = 0; x < total_x; x++) {
			for(int y = 0; y < total_y; y++) {
				cell_data[x][y] = this.add().fill();
				Chunk c = loaded_chunks. //for each get points
				cell_data[x][y].setActor().expand();
			}
			this.row();
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		for(Chunk c : loaded_chunks){
			for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
				for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {
					c.get(x, y).draw(batch, parentAlpha);
				}
			}
		}
	}

	@Override
	public void act(float delta) {
		for(Chunk c : loaded_chunks){
			for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
				for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {
					c.get(x, y).act(delta);
				}
			}
		}
	}
	
	@Override
	public void drawDebug(ShapeRenderer shapes) {
		for(Chunk c : loaded_chunks){
			for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
				for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {
					//c.drawDebug(shapes);
				}
			}
		}
		
		loaded_chunks.get(0).drawDebug(shapes);
		super.drawDebug(shapes);
	}
	
	public void loadChunk(Point2 loc) {
		readwrite.readChunk(loc);
	}
	
	public void unloadChunk(Point2 loc, boolean safe) {
		if(!force_load_all_chunks) {
			int i = 0;
			for(Chunk c : loaded_chunks) {
				if(c.getCoords().equals(loc)) {
					readwrite.writeChunk(c);
					loaded_chunks.remove(c);
					return;
				}
				i++;
			}
			if(!safe) {
				loaded_chunks.remove(i);
			}
		}
	}
	
	public Point2 getMinSize(){
		return new Point2(chunk_min_x, chunk_min_y);
	}
	public Point2 getMaxSize(){
		return new Point2(chunk_max_x, chunk_max_y);
	}
	
	public ArrayList<Chunk> loadTestMap(int width, int height){
	
		SimplexNoise noise = new SimplexNoise(500, 0.15, 2500);
		ArrayList<Chunk> chunks = new ArrayList<Chunk>();
		
		//Higher Octave, bigger land masses, Higher per, more scattering
		for(int cx = -1; cx < width; cx++){
			
			for(int cy = -1; cy < height; cy++){
				
				if(cx < chunk_min_x) chunk_min_x = cx;
				else if(cx > chunk_max_x) chunk_max_x = cx;
				if(cy < chunk_min_y) chunk_min_y = cy;
				else if(cy > chunk_max_y) chunk_max_y = cy;
				
				Chunk chunk = new Chunk(new Point2(cx, cy));
				for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
					for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {
						
						TerrainTile tile = new TerrainTile(new Point2(x+((cx-1)*(Chunk.CHUNK_SIZE)), y+((cy-1)*(Chunk.CHUNK_SIZE))));
						
						for(int a = 0; a < TerrainTile.DETAIL_PER_SECTION; a++) {
							for(int b = 0; b < TerrainTile.DETAIL_PER_SECTION; b++) {
								
								double val = noise.getNoise(((cx-1)*(Chunk.CHUNK_SIZE)*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (x*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (a*TerrainTile.SUBTILE_SIZE), ((cy-1)*(Chunk.CHUNK_SIZE)*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (y * TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION) + (b * TerrainTile.SUBTILE_SIZE));
								if(val < 0) tile.add(a, b, new TerrainSubTile(TileType.water));
								else tile.add(a, b, new TerrainSubTile(TileType.grass));
								
								}
							}
						
						chunk.add(x, y, tile);
					}
				}
				chunks.add(chunk);
			}
		}
		
		if(chunk_min_x <= 0 && chunk_max_x > 0) chunk_min_x--;
		if(chunk_min_y <= 0 && chunk_max_y > 0) chunk_min_x--;
		return chunks;
	}
	

}

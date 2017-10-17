package jon.game.terrain;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jon.game.enums.TileType;
import jon.game.utils.Point2;

public class TerrainMap {
	
	protected ArrayList<Chunk> loaded_chunks;
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
		readwrite = new TerrainMapIO("path");
	}
	
	public void update(float delta, SpriteBatch batch){
		
		for(Chunk c : loaded_chunks){
			for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
				for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {
					c.get(x, y).update(delta, batch);
				}
			}
		}
		
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
	
	public ArrayList<Chunk> loadTestMap(int width, int height){
	
		SimplexNoise noise = new SimplexNoise(500, 0.15, 2500);
		ArrayList<Chunk> chunks = new ArrayList<Chunk>();
		
		//Higher Octave, bigger land masses, Higher per, more scattering
		for(int cx = -1; cx < width; cx++){
			for(int cy = -1; cy < height; cy++){
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
		
		
		return chunks;
	}

}

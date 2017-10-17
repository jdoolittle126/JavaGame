package jon.game.terrain;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jon.game.enums.TileType;
import jon.game.utils.Point2;

public class TerrainMap {
	
	private ArrayList<Chunk> loaded_chunks;
	
	public enum MapType {
		fixed,
		infinite;
	}
	
	public TerrainMap(MapType type) {
		loaded_chunks = loadTestMap();
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

	
	
	public ArrayList<Chunk> loadTestMap(){
		
		
		Random random = new Random();
		int o = random.nextInt(2000);
		double p = random.nextDouble();
		SimplexNoise noise = new SimplexNoise(500, 0.15, 2500);
		ArrayList<Chunk> chunks = new ArrayList<Chunk>();
		
		//Higher Octave, bigger land masses, Higher per, more scattering
		for(int cx = -1; cx < 3; cx++){
			for(int cy = -1; cy < 3; cy++){
				Chunk chunk = new Chunk();
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

package jon.game.terrain;

import java.util.ArrayList;

import jon.game.enums.TileType;
import jon.game.utils.Point2;

public class MoistureMap {
	
	SimplexNoise noise;
	
	public MoistureMap(){
		noise = new SimplexNoise(20000, .75, 2500);
	}
	
	public Chunk loadSection(int startx, int starty){

		for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
			for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {	
				for(int a = 0; a < TerrainTile.DETAIL_PER_SECTION; a++) {
					for(int b = 0; b < TerrainTile.DETAIL_PER_SECTION; b++) {
						double val = noise.getNoise((startx*(Chunk.CHUNK_SIZE)*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (x*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (a*TerrainTile.SUBTILE_SIZE), (starty*(Chunk.CHUNK_SIZE)*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (y * TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION) + (b * TerrainTile.SUBTILE_SIZE));
							
							//if(val < 0) 
								
							}
						}
				}
			}

		return chunk;
	}
	
	public ArrayList<Chunk> loadSections(int startx, int starty, int width, int height){
		
		ArrayList<Chunk> chunks = new ArrayList<Chunk>();
		
		//Higher Octave, bigger land masses, Higher per, more scattering
		for(int cx = startx; cx < width+startx; cx++){
			for(int cy = startx; cy < height+startx; cy++){
				
				Chunk chunk = new Chunk(new Point2(cx, cy));
				for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
					for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {
						
						TerrainTile tile = new TerrainTile(new Point2(x, y));
						for(int a = 0; a < TerrainTile.DETAIL_PER_SECTION; a++) {
							for(int b = 0; b < TerrainTile.DETAIL_PER_SECTION; b++) {
								
								double val = noise.getNoise((cx*(Chunk.CHUNK_SIZE)*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (x*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (a*TerrainTile.SUBTILE_SIZE), (cy*(Chunk.CHUNK_SIZE)*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (y * TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION) + (b * TerrainTile.SUBTILE_SIZE));
								
								if(val < 0) tile.add(a, b, new TerrainSubTile(new Point2(a, b), TileType.water));
								else tile.add(a, b, new TerrainSubTile(new Point2(a, b), TileType.grass));
								
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

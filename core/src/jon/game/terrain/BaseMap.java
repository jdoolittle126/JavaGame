package jon.game.terrain;

import java.util.ArrayList;

import jon.game.enums.TileType;
import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class BaseMap {

	SimplexNoise noise;
	float sea_level = 0;
	float beach_level = 0.25f;
	
	public BaseMap(){
		noise = new SimplexNoise(20000, .75, 2500); //Good numbers for now
	}
	
	public Chunk loadSection(int startx, int starty){
		Chunk chunk = new Chunk(new Point2(startx, starty));
		for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
			for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {
				TerrainTile tile = new TerrainTile(new Point2(x, y));
				for(int a = 0; a < TerrainTile.DETAIL_PER_SECTION; a++) {
					for(int b = 0; b < TerrainTile.DETAIL_PER_SECTION; b++) {
						double val = noise.getNoise((startx*(Chunk.CHUNK_SIZE)*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (x*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (a*TerrainTile.SUBTILE_SIZE), (starty*(Chunk.CHUNK_SIZE)*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (y * TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION) + (b * TerrainTile.SUBTILE_SIZE));		
						if(val < sea_level){
							tile.add(a, b, new TerrainSubTile(new Point2(a, b), Materials.water));
							chunk.editCollisonMap(a, b, 1);
						}
						else if(val < sea_level+beach_level){
							tile.add(a, b, new TerrainSubTile(new Point2(a, b), Materials.sand));
							chunk.editCollisonMap(a, b, 0);
						}
						else{
							tile.add(a, b, new TerrainSubTile(new Point2(a, b), Materials.grass));
							chunk.editCollisonMap(a, b, 0);
						}
					}
				}
				chunk.add(x, y, tile);
			}
		}
		return chunk;
	}
	
	public ArrayList<Chunk> loadSections(int startx, int starty, int width, int height){
		
		ArrayList<Chunk> chunks = new ArrayList<Chunk>();
		for(int cx = startx; cx < width+startx; cx++){
			for(int cy = startx; cy < height+startx; cy++){
				chunks.add(loadSection(cx, cy));
			}
		}
		
		return chunks;
	}
	
}

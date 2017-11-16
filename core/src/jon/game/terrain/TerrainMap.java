package jon.game.terrain;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jon.game.enums.TileType;
import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class TerrainMap extends Group {
	
	private int chunk_min_x = 0, chunk_min_y = 0, chunk_max_x = 0, chunk_max_y = 0;
	protected ArrayList<Chunk> loaded_chunks;
	protected boolean force_load_all_chunks = false;
	boolean flag2 = true;
	
	public enum MapType {
		filled,
		blank,
		fixed;
	}
	
	public TerrainMap(MapType type) {
		this.setX(0);
		this.setY(0);
		this.setWidth(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE * 1);
		this.setHeight(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE * 1);
		
		if(!(type.equals(MapType.blank))) loaded_chunks = loadTestMap(1, 1);
		else loaded_chunks = new ArrayList<Chunk>();

		
		float minx=0, miny=0, maxx=0, maxy=0;
		boolean flag = true;
		
		for(Chunk c : loaded_chunks){
			this.addActor(c);
			
			if(flag) {
				minx=c.getCoords().x;
				miny=c.getCoords().y;
				maxx=c.getCoords().x;
				maxy=c.getCoords().y;
				flag = false;	
			}
			
			if(c.getCoords().x > maxx) maxx = c.getCoords().x;
			else if(c.getCoords().x < minx) minx = c.getCoords().x;
			
			if(c.getCoords().y > maxy) maxy = c.getCoords().y;
			else if(c.getCoords().y < miny) miny = c.getCoords().y;
		}
		
		
	}

	public void loadChunk(Point2 loc) {
		//READ
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);	
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public void unloadChunk(Point2 loc, boolean safe) {
		if(!force_load_all_chunks) {
			int i = 0;
			for(Chunk c : loaded_chunks) {
				if(c.getCoords().equals(loc)) {
					//WRITE
					this.removeActor(c);
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
		for(int cx = 0; cx < width; cx++){
			
			for(int cy = 0; cy < height; cy++){
				
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
								if(val < 0) tile.add(a, b, new TerrainSubTile(new Point2(TerrainTile.DETAIL_PER_SECTION * (x + (cx-1)*Chunk.CHUNK_SIZE) + a, TerrainTile.DETAIL_PER_SECTION * (y + (cy-1)*Chunk.CHUNK_SIZE) + b), TileType.water));
								else tile.add(a, b, new TerrainSubTile(new Point2(TerrainTile.DETAIL_PER_SECTION * (x + (cx-1)*Chunk.CHUNK_SIZE) + a, TerrainTile.DETAIL_PER_SECTION * (y + (cy-1)*Chunk.CHUNK_SIZE) + b), TileType.grass));
								
								}
							}
						
						chunk.add(x, y, tile);
					}
				}
				chunks.add(chunk);
			}
		}
		
		//if(chunk_min_x <= 0 && chunk_max_x > 0) chunk_min_x--;
		//if(chunk_min_y <= 0 && chunk_max_y > 0) chunk_min_y--;
		return chunks;
	}
	
	
	

}

package jon.game.terrain;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import jon.game.utils.Point2;

public class TerrainMap extends Group {
	
	protected ArrayList<Chunk> loaded_chunks;
	protected boolean force_load_all_chunks = false;
	protected BaseMap basemap;
	protected MoistureMap moisturemap;
	
	public TerrainMap() {
		setup();
		init();
	}
	
	
	public void setup(){
		this.setWidth(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE);
		this.setHeight(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE);
		this.setX(0);
		this.setY(0);
	}
	
	public void init(){
		
		basemap = new BaseMap();
		moisturemap = new MoistureMap();
		loaded_chunks = new ArrayList<Chunk>();
		
		/*
		 * MIN MAX CODE
		float minx=0, miny=0, maxx=0, maxy=0;
		boolean firstChunk = true;
		
		for(Chunk c : loaded_chunks){
			this.addActor(c);
			
			if(firstChunk) {
				minx=c.getCoords().x;
				miny=c.getCoords().y;
				maxx=c.getCoords().x;
				maxy=c.getCoords().y;
				firstChunk = false;	
			}
			
			if(c.getCoords().x > maxx) maxx = c.getCoords().x;
			else if(c.getCoords().x < minx) minx = c.getCoords().x;
			
			if(c.getCoords().y > maxy) maxy = c.getCoords().y;
			else if(c.getCoords().y < miny) miny = c.getCoords().y;
		}
		*/
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);	
		
	}
	
	public void draw_chunk(Batch batch, float parentAlpha, Point2 loc){
		loadChunk(loc, true);
		for(Chunk c : loaded_chunks){
			if (c.getCoords().equals(loc)){
				c.draw(batch, parentAlpha);
				break;
			}
		}
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public void loadChunk(Point2 loc, boolean safe) {
		
		if(safe){
			for(Chunk c : loaded_chunks) {
				if(c.getCoords().equals(loc)) {
					return;
				}
			}
		}
		
		Chunk c = basemap.loadSection((int) loc.x, (int) loc.y);
		c.addObjects(moisturemap.loadSection(c));
		loaded_chunks.add(c);
		this.addActor(c);
		
	}

	public void unloadChunk(Point2 loc, boolean safe) {
		if(!force_load_all_chunks) {
			int i = 0;
			for(Chunk c : loaded_chunks) {
				if(c.getCoords().equals(loc)) {
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
	
	public boolean isChunkLoaded(Point2 loc){
		for(Chunk c : loaded_chunks) {
			if(c.getCoords().equals(loc)) return true;
		}
		return false;
	}
	
	public ArrayList<Chunk> getChunks(){
		return this.loaded_chunks;
	}
	
	

}

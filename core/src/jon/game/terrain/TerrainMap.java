package jon.game.terrain;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import jon.game.enums.TileType;
import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class TerrainMap extends Group {
	
	protected ArrayList<Chunk> loaded_chunks;
	protected boolean force_load_all_chunks = false;
	
	public enum MapType {
		filled,
		blank,
		fixed;
	}
	
	public TerrainMap(MapType type) {
		
		
		this.setWidth(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE * 1);
		this.setHeight(TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE * 1);
		this.setX(0);
		this.setY(0);
		
		if(!(type.equals(MapType.blank))) loaded_chunks = loadTestMap(0, 0, 1, 1);
		else loaded_chunks = new ArrayList<Chunk>();

		
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
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		float moveBy = TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE;
		
		if(Gdx.input.isKeyJustPressed(Keys.W)) {
			this.moveBy(0, -moveBy);
		}
		if(Gdx.input.isKeyJustPressed(Keys.A)) {
			this.moveBy(moveBy, 0);
		}
		if(Gdx.input.isKeyJustPressed(Keys.S)) {
			this.moveBy(0, moveBy);
		}
		if(Gdx.input.isKeyJustPressed(Keys.D)) {
			this.moveBy(-moveBy, 0);
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q)) {
			this.scaleBy(0.1f);
		}
		if(Gdx.input.isKeyJustPressed(Keys.E)) {
			if(!(this.getScaleX() <= 0.1 || this.getScaleY() <= 0.1)) this.scaleBy(-0.1f);
			
		}
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			this.setScale(1);
		}
		
		super.draw(batch, parentAlpha);	
		
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public void loadChunk(Point2 loc) {
		//READ
	}

	public void unloadChunk(Point2 loc, boolean safe) {
		//Fix safe, clean, removing actor, etc
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
	
	
	public ArrayList<Chunk> loadTestMap(int startx, int starty, int width, int height){
	
		SimplexNoise noise = new SimplexNoise(500, 0.15, 2500);
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

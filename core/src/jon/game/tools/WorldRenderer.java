package jon.game.tools;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

import jon.game.terrain.Chunk;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.World;

public class WorldRenderer {
	World world;
	FrameBuffer tiles, entities, weather;
	
	public WorldRenderer(World world) {
		this.world = world;
		
	}
	
	public void init(){
		int size = TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE;
		tiles = new FrameBuffer(Format.RGB888, size, size, false);
		//world.getMap().draw_chunk(batch, parentAlpha, loc);
	}
	
	public void render(){
		
	}
	
	
}

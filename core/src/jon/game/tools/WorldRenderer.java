package jon.game.tools;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

import jon.game.terrain.Chunk;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.World;

public class WorldRenderer {
	World world;
	FrameBuffer tiles, entities_s, weather;
	Sprite test;
	int size = TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE;
	boolean oneway = true;
	
	public WorldRenderer(World world) {
		this.world = world;
		tiles = new FrameBuffer(Format.RGB888, size*5, size*5, false);
		
	}
	
	public void draw(Batch batch, float parentAlpha) {
		
		//tiles.begin();
		world.draw(batch, parentAlpha);
		//tiles.end();
		//test = new Sprite(tiles.getColorBufferTexture());
		
		//test.setBounds(0, 0, size, size);
		//test.setCenter(size/2, size/2);
		//test.draw(batch);
		
	}
	
	
}

package jon.game.tools;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.GLFrameBuffer;
import com.badlogic.gdx.math.Matrix4;

import jon.game.terrain.Chunk;
import jon.game.terrain.TerrainMap;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.World;
import jon.game.utils.Point2;

public class WorldRenderer {
	World world;
	TextureRegion test;
	int size =  TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE;
	HashMap<Chunk, TextureRegion> chunk_textures;
	ArrayList<Chunk> que;

	
	public WorldRenderer(World world) {
		this.world = world;
		chunk_textures = new HashMap<Chunk, TextureRegion>();
		que = new ArrayList<Chunk>();
		
		test = buildMap(world);
		test.flip(false, true);
	}
	
	public void draw(Batch batch, float parentAlpha) {
		
		for(Chunk c : world.getMap().getChunks()) {
			if(chunk_textures.containsKey(c)) batch.draw(chunk_textures.get(c), c.getX(), c.getY());
			else que.add(c);
		}
		
	}
	
	public void buildQue() {
		for(Chunk c : que) {
			chunk_textures.put(c, buildChunk(c));
			chunk_textures.get(c).flip(false, true);
		}
		que.clear();
	}
	
	public TextureRegion buildMap(World w) {
		GLFrameBuffer.FrameBufferBuilder frameBufferBuilder = new GLFrameBuffer.FrameBufferBuilder(size, size);
		frameBufferBuilder.addBasicColorTextureAttachment(Format.RGB888);
		FrameBuffer tiles = frameBufferBuilder.build();
		TerrainMap t = w.getMap();
		t.loadChunk(new Point2(), true);
		Batch b = new SpriteBatch();
		Matrix4 m = new Matrix4();
		m.setToOrtho2D(0, 0, size, size);
		b.setProjectionMatrix(m);
		
		b.begin();
		tiles.begin();
		t.act(Gdx.graphics.getDeltaTime());
		t.draw(b, 255f);
		tiles.end();
		b.end();
		return new TextureRegion(tiles.getColorBufferTexture());
	}
	
	public TextureRegion buildChunk(Chunk c) {
		float oldX=c.getX(), oldY=c.getY();
		GLFrameBuffer.FrameBufferBuilder frameBufferBuilder = new GLFrameBuffer.FrameBufferBuilder(size, size);
		frameBufferBuilder.addBasicColorTextureAttachment(Format.RGB888);
		FrameBuffer tiles = frameBufferBuilder.build();
		Batch b = new SpriteBatch();
		Matrix4 m = new Matrix4();
		m.setToOrtho2D(0, 0, size, size);
		b.setProjectionMatrix(m);
		c.setX(0);
		c.setY(0);
		b.begin();
		tiles.begin();
		c.act(Gdx.graphics.getDeltaTime());
		c.draw(b, 255f);
		tiles.end();
		b.end();
		c.setX(oldX);
		c.setY(oldY);
		return new TextureRegion(tiles.getColorBufferTexture());
	}
	
	
}

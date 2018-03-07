package jon.game.entity;

import java.util.ArrayList;

import jon.game.terrain.Chunk;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.World;
import jon.game.utils.Point2;
import jon.tools.gui.MapEditor;

public class PathFinder {
	ArrayList<Point2> used, path;
	TileNode[][] surroundings;
	TileNode last;
	
	World world;
	
	public PathFinder(Point2 start, Point2 end) {
		
		float sx = (float) Math.floor(start.x / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		float sy = (float) Math.floor(start.y / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		
		float ex = (float) Math.floor(end.x / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		float ey = (float) Math.floor(end.y / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		
		// (sx, sy) (sx, ey) (ex, ey) (ex, sy)
		
		surroundings = new TileNode[3][3];
		world.getMap().getChunks().get(0).getCollisionMap();
	}
	
	public void next() {
		last = surroundings[1][1];
		
	}

}

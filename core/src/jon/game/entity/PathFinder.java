package jon.game.entity;

import java.util.ArrayList;

import jon.game.terrain.Chunk;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.World;
import jon.game.utils.Point2;
import jon.tools.gui.MapEditor;

public class PathFinder {
	ArrayList<Point2> used, path;
	ArrayList<Entity> avoid;
	TileNode last;
	
	public PathFinder(Point2 start, Point2 end, ArrayList<Chunk> chunks) {
		//check if start and end are in chunks
		//make sure path exists
		
		float sx = (float) Math.floor(start.x / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		float sy = (float) Math.floor(start.y / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		
		float ex = (float) Math.floor(end.x / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		float ey = (float) Math.floor(end.y / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		
		// (sx, sy) (sx, ey) (ex, ey) (ex, sy)
		
		
		
	}
	
	public void findPath(Point2 start, Point2 end, Chunk c) {
		//make sure its possiable
		Point2 current_point = start;
		TileNode[][] surroundings = new TileNode[3][3];
		float total = 0; //increase
		
		for(int i = 0; i < Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION; i++) {
			for(int x = -1; x < 2; x++) {
				for(int y = -1; y < 2; y++) {
					Point2 pos = new Point2(current_point.x + x, current_point.y + y);
					if(c.get((int)pos.x, (int)pos.y) != null) {
						
						boolean s = c.getCollisionMap()[(int) pos.x][(int) pos.y] < 1; //change based on entity,some can use water
						int x1, y1, x2, y2;
						float g = total + 1/c.get(x1,y1).subsections[x2][y2].getMaterial().getMovementModifier(); //1/movement_mod
						
						Point2 b = pos.cpy().scale(-1f).transform(end);
						float h = Math.abs(b.x) + Math.abs(b.y);//city block
						
						
						float t = 0;//1/distance * fear order
						
						for(Entity entity : avoid) {
							Vector2 oof = 
						}
						
						surroundings[x][y] = new TileNode(pos, s, g, h, t);
					} else {
						
					}

				}
			}
		}
		
	}
	
	public void step() {
		
	}

}

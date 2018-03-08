package jon.game.entity;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import jon.game.core.GameClient;
import jon.game.debug.Debugger;
import jon.game.terrain.Chunk;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.World;
import jon.game.utils.Point2;
import jon.tools.gui.MapEditor;

public class PathFinder {
	ArrayList<TileNode> used, open;
	ArrayList<Entity> avoid;
	Point2 start_point;
	boolean path_made = false;
	
	enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT;
	}
	
	public PathFinder() {
		avoid = new ArrayList<Entity>();
		
		//check if start and end are in chunks
		//make sure path exists
		
		//float sx = (float) Math.floor(start.x / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		//float sy = (float) Math.floor(start.y / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		
		//float ex = (float) Math.floor(end.x / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		//float ey = (float) Math.floor(end.y / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
		
		// (sx, sy) (sx, ey) (ex, ey) (ex, sy)
		
	}
	
	public void findPath(Point2 start, Point2 end, Chunk c) {
		used = new ArrayList<TileNode>();
		open = new ArrayList<TileNode>();
		path_made = true;
		start_point = start;
		Point2 current_point = start;
		int total = 0;
		
		if(genNode(Direction.UP, c, start, end, total) == null || genNode(Direction.UP, c, start, end, total).s) return;
		
		while(true){
			TileNode u = genNode(Direction.UP, c, current_point, end, total);
			total++;
			TileNode d = genNode(Direction.DOWN, c, current_point, end, total);
			total++;
			TileNode l = genNode(Direction.LEFT, c, current_point, end, total);
			total++;
			TileNode r = genNode(Direction.RIGHT, c, current_point, end, total);
			total++;
			
			if(u != null && !u.s) open.add(u);
			if(d != null && !d.s) open.add(d);
			if(l != null && !l.s) open.add(l);
			if(r != null && !r.s) open.add(r);
			
			if(open.isEmpty()) break; //no path
			
			TileNode best = open.get(0);
			for(TileNode t : open){
				if(t.F < best.F) best = t;
			}
			open.remove(best);
			used.add(best);
			current_point = best.pos;
			
			if(current_point.equals(end)) break; //path found
		}			
		
	}
	
	public void draw(Batch batch, float parentAlpha){
		if(path_made){
			
			//Debugger.DrawDebugLine(new Point2(1.5f, 1.5f).scale(TerrainTile.SUBTILE_SIZE), used.get(4).pos.cpy().transform(new Point2(0.5f, 0.5f).scale(TerrainTile.SUBTILE_SIZE)), 1, Color.BLUE, GameClient.getMatrix());

			/*
			Point2 last = start_point;
			for(TileNode t : used){
				Point2 n = t.pos.cpy();
				Debugger.DrawDebugLine(last.cpy().scale(TerrainTile.SUBTILE_SIZE), n.cpy().scale(TerrainTile.SUBTILE_SIZE), 1, new Color(Color.rgba8888(n.x, n.y, n.x + n.y, 1)), GameClient.getMatrix());
				last = n;
			}	
			*/
			
		}
	}
	
	public TileNode genNode(Direction direction, Chunk c, Point2 current_point, Point2 end, int total){
		float mx = 0, my = 0;
		switch(direction){
			case UP:
				mx = 0;
				my = 1;
				break;
			case DOWN:
				mx = 0;
				my = -1;
				break;
			case LEFT:
				mx = -1;
				my = 0;
				break;
			case RIGHT:
				mx = 1;
				my = 0;
				break;
		}
		
		Point2 pos = new Point2(current_point.x + mx, current_point.y + my);
		
		if(inOpen(pos) || inUsed(pos)){
			return null;
		} else {
			if(pos.x < 0 || pos.y < 0 || pos.x > Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION || pos.y > Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION) {
				return null;
			} else {
				Point2 slope_to_end = pos.cpy().transform(end.cpy().scale(-1f));
				int size = (TerrainTile.DETAIL_PER_SECTION);
				int fear_order = 0;
				int x1 = (int) Math.floor(pos.x / size);
				int y1 = (int) Math.floor(pos.y / size);
				int x2 = (int) (pos.x - (x1*size));
				int y2 = (int) (pos.y - (y1*size));
				boolean _s = c.getCollisionMap()[(int) pos.x][(int) pos.y] < 1; //change based on entity,some can use water
				float _g = total + 1/c.get(x1,y1).subsections[x2][y2].getMaterial().getMovementModifier(); //1/movement_mod
				float _h = Math.abs(slope_to_end.x) + Math.abs(slope_to_end.y);//city block
				float _t = 0;//1/distance * fear order
				for(Entity entity : avoid) {
					fear_order++;
					Point2 entity_distance = entity.getCoords2().transform(pos.cpy().scale(-1f));
					Vector2 entity_distance2 = new Vector2(entity_distance.x, entity_distance.y);
					_t+=1/(entity_distance2.len()*fear_order);
				}
					
				return new TileNode(pos, _s, _g, _h, _t);
			}	
		}
	}
	
	public boolean inOpen(Point2 pos){
		for(TileNode t : open){
			if(t.pos.equals(pos)) return true;
		}
		return false;
	}
	
	public boolean inUsed(Point2 pos){
		for(TileNode t : used){
			if(t.pos.equals(pos)) return true;
		}
		return false;
	}
	
	
	

}

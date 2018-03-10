package jon.game.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import jon.game.core.GameClient;
import jon.game.debug.Debugger;
import jon.game.terrain.Chunk;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.World;
import jon.game.utils.Point2;
import jon.tools.gui.MapEditor;

public class PathFinder {
	ArrayList<Entity> avoid;
	BitmapFont font;
	int x_mod = 0, y_mod = 0;
	
	enum Direction{
		UP,
		DOWN,
		LEFT,
		RIGHT;
	}
	
	public PathFinder() {
		avoid = new ArrayList<Entity>();
		font = new BitmapFont();
	}
	
	public ArrayList<Point2> getFinalPathForChunks(Point2 start, Point2 end, ArrayList<Chunk> c){
		
		Point2 chunk_start = start.cpy();
		Point2 chunk_end = end.cpy();
		chunk_start.x = (float) Math.floor(chunk_start.x * 1/(TerrainTile.SUBTILE_SIZE*TerrainTile.SUBTILE_SIZE*Chunk.CHUNK_SIZE));
		chunk_start.y = (float) Math.floor(chunk_start.y * 1/(TerrainTile.SUBTILE_SIZE*TerrainTile.SUBTILE_SIZE*Chunk.CHUNK_SIZE));
		chunk_end.x = (float) Math.floor(chunk_end.x * 1/(TerrainTile.SUBTILE_SIZE*TerrainTile.SUBTILE_SIZE*Chunk.CHUNK_SIZE));
		chunk_end.y = (float) Math.floor(chunk_end.y * 1/(TerrainTile.SUBTILE_SIZE*TerrainTile.SUBTILE_SIZE*Chunk.CHUNK_SIZE));
		
		if(chunk_start.equals(chunk_end)) {
			for(Chunk c1 : c) {
				if(c1.getCoords().equals(chunk_start)) return getFinalPathForChunk(start, end, c1);
			}
		}
		
		ArrayList<Point2> r = new ArrayList<Point2>();
		//for(TileNode aa : findPathMultiChunk(toBaseCoords(start), toBaseCoords(end), c)) {
		//	r.add(new Point2(aa.pos.transform(x_mod + 0.5f, y_mod + 0.5f).scale(TerrainTile.SUBTILE_SIZE)));
		//}
		return r;
	}
	
	public ArrayList<Point2> getFinalPathForChunk(Point2 start, Point2 end, Chunk c){
		ArrayList<Point2> r = new ArrayList<Point2>();
		for(TileNode aa : findPathSingleChunk(toBaseCoords(start), toBaseCoords(end), c)) {
			r.add(new Point2(aa.pos.transform(0.5f, 0.5f).scale(TerrainTile.SUBTILE_SIZE)));
		}
		return r;
		
	}
	
	
	private ArrayList<TileNode> findPathSingleChunk(Point2 start, Point2 end, Chunk c) {
		
		Point2 transform = c.getCoords().cpy().scale(-Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION);
		Point2 transform_start = start.cpy().transform(transform);
		Point2 transform_end = end.cpy().transform(transform);
		
		//Move stuff based on chunk coords
		ArrayList<TileNode> used = new ArrayList<TileNode>();
		ArrayList<TileNode> open = new ArrayList<TileNode>();
		HashMap<TileNode, TileNode> nav = new HashMap<TileNode, TileNode>();
		
		Point2 current_point = transform_start;
		int total = 0;
		
		TileNode sN = genNode(Direction.UP, c, transform_start.cpy().transform(new Point2(0, -1)), transform_end, total);
		
		if(sN == null || sN.s) return used;
		sN.G = 0;
		open.add(sN);
		
		while(!open.isEmpty()){
			
			TileNode best = open.get(0);
			
			for(TileNode t : open){
				if(t.pos.equals(transform_end)) {
					used.add(t);
					return finalPath(used, nav, transform);
				}
				
				if(t.getF() < best.getF()) best = t;
			}
			
			open.remove(best);
			used.add(best);
			
			current_point = best.pos;
			
			ArrayList<TileNode> successors = new ArrayList<TileNode>();
			successors.add(genNode(Direction.UP, c, current_point, transform_end, total+(best.G-(total-1))));
			successors.add(genNode(Direction.DOWN, c, current_point, transform_end, total+(best.G-(total-1))));
			successors.add(genNode(Direction.LEFT, c, current_point, transform_end, total+(best.G-(total-1))));
			successors.add(genNode(Direction.RIGHT, c, current_point, transform_end, total+(best.G-(total-1))));
			for(TileNode v : successors) {
				if(v != null && !v.s && !inList(v.pos, used)) {
					if(inList(v.pos, open)) {
						TileNode h = getFromList(v.pos, open);
						if(h.G > v.G) {
							open.remove(h);
							open.add(v);
						}
					} else {
						open.add(v);
						nav.put(v, best);
					}		
				}
			}
			total++;
			
		}
		return new ArrayList<TileNode>();
		
	}
	
	public ArrayList<TileNode> findPathMultiChunk(Point2 start, Point2 end, ArrayList<Chunk> c) {
		
		float x_min = c.get(0).getCoords().x, x_max = c.get(0).getCoords().x, y_min = c.get(0).getCoords().y, y_max = c.get(0).getCoords().y;
		
		for(Chunk c1 : c) {
			if(c1.getCoords().x < x_min) x_min = c1.getCoords().x;
			if(c1.getCoords().x > x_max) x_max = c1.getCoords().x;
			if(c1.getCoords().y < y_min) y_min = c1.getCoords().y;
			if(c1.getCoords().y > y_max) y_max = c1.getCoords().y;
		}
		//This is about to get real inefficient close ur eyes
		int len_x = (int) (x_max-x_min) + 1, len_y = (int) (y_max-y_min) + 1;
		int[][] chunk_collison_data = new int[len_x*Chunk.CHUNK_SIZE*TerrainTile.DETAIL_PER_SECTION][len_y*Chunk.CHUNK_SIZE*TerrainTile.DETAIL_PER_SECTION];
		float[][] chunk_movemod_data = new float[len_x*Chunk.CHUNK_SIZE*TerrainTile.DETAIL_PER_SECTION][len_y*Chunk.CHUNK_SIZE*TerrainTile.DETAIL_PER_SECTION];
		
		x_mod = (int) ((0-x_min)* Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION);
		y_mod = (int) ((0-y_min)* Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION);
		
		start.x += x_mod;
		start.y += y_mod;
		end.x += x_mod;
		end.y += y_mod;
		
		for(Chunk c2 : c) {
			
			int x1 = ((int) c2.getCoords().x + (int) (0-x_min)) * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION;
			int y1 = ((int) c2.getCoords().y + (int) (0-y_min)) * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION;
			
			for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
				for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {
					for(int a = 0; a < TerrainTile.DETAIL_PER_SECTION; a++) {
						for(int b = 0; b < TerrainTile.DETAIL_PER_SECTION; b++) {
							
							int x2 = (x*TerrainTile.DETAIL_PER_SECTION + a) + x1;
							int y2 = (y*TerrainTile.DETAIL_PER_SECTION + b) + y1;
							
							chunk_collison_data[x2][y2] = c2.get(x, y).subsections[a][b].getMaterial().getCollision();
							chunk_movemod_data[x2][y2] = c2.get(x, y).subsections[a][b].getMaterial().getMovementModifier();
						}
					}
				}
			}	
		}
		
		
		ArrayList<TileNode> used = new ArrayList<TileNode>();
		ArrayList<TileNode> open = new ArrayList<TileNode>();
		HashMap<TileNode, TileNode> nav = new HashMap<TileNode, TileNode>();
		
		Point2 current_point = start;
		int total = 0;
		
		TileNode sN = genNodeMulti(Direction.UP, chunk_collison_data, chunk_movemod_data, start.cpy().transform(new Point2(0, -1)), end, total);
		
		if(sN == null || sN.s) return used;
		sN.G = 0;
		open.add(sN);
		
		while(!open.isEmpty()){
			
			TileNode best = open.get(0);
			
			for(TileNode t : open){
				if(t.pos.equals(end)) {
					used.add(t);
					return finalPath(used, nav);
				}
				
				if(t.getF() < best.getF()) best = t;
			}
			
			open.remove(best);
			used.add(best);
			
			current_point = best.pos;
			
			ArrayList<TileNode> successors = new ArrayList<TileNode>();
			successors.add(genNodeMulti(Direction.UP, chunk_collison_data, chunk_movemod_data, current_point, end, total+(best.G-(total-1))));
			successors.add(genNodeMulti(Direction.DOWN, chunk_collison_data, chunk_movemod_data, current_point, end, total+(best.G-(total-1))));
			successors.add(genNodeMulti(Direction.LEFT, chunk_collison_data, chunk_movemod_data, current_point, end, total+(best.G-(total-1))));
			successors.add(genNodeMulti(Direction.RIGHT, chunk_collison_data, chunk_movemod_data, current_point, end, total+(best.G-(total-1))));
			for(TileNode v : successors) {
				if(v != null && !v.s && !inList(v.pos, used)) {
					if(inList(v.pos, open)) {
						TileNode h = getFromList(v.pos, open);
						if(h.G > v.G) {
							open.remove(h);
							open.add(v);
						}
					} else {
						open.add(v);
						nav.put(v, best);
					}		
				}
			}
			total++;
			
		}
		return new ArrayList<TileNode>();
		
	}
	
	private ArrayList<TileNode> finalPath(ArrayList<TileNode> used, HashMap<TileNode, TileNode> nav, Point2 transform){
		ArrayList<TileNode> t = new ArrayList<TileNode>();
		TileNode current = used.get(used.size()-1);
		while(nav.containsKey(current)) {
			t.add(current);
			current = nav.get(current);
		}
		
		for(TileNode g : t) {
			g.pos.transform(transform.cpy().scale(-1f));
		}
		
		return t;
	}
	
	private TileNode genNode(Direction direction, Chunk c, Point2 current_point, Point2 end, float total){
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
		Point2 pos = new Point2((current_point.x + mx), (current_point.y + my));

		if(pos.x < 0 || pos.y < 0 || pos.x >= Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION || pos.y >= Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION) {
			return null;
		} else {
			Point2 slope_to_end = pos.cpy().transform(end.cpy().scale(-1f));
			int size = (TerrainTile.DETAIL_PER_SECTION);
			int fear_order = 0;
			int x1 = (int) Math.floor(pos.x / size);
			int y1 = (int) Math.floor(pos.y / size);		
			int x2 = (int) (pos.x - (x1*size));
			int y2 = (int) (pos.y - (y1*size));
			boolean _s = c.get(x1,y1).subsections[x2][y2].getMaterial().getCollision() > 0; //change based on entity,some can use water
			float _g = total + (1/c.get(x1,y1).subsections[x2][y2].getMaterial().getMovementModifier()); //1/movement_mod
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
	
	private TileNode genNodeMulti(Direction direction, int[][] col, float[][] mod, Point2 current_point, Point2 end, float total){
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
		Point2 pos = new Point2((current_point.x + mx), (current_point.y + my));

		
		if(pos.x < 0 || pos.y < 0 || pos.x >= col.length || pos.y >= col[0].length) {
			return null;
		} else {
			Point2 slope_to_end = pos.cpy().transform(end.cpy().scale(-1f));
			int fear_order = 0;
			int x1 = (int) pos.x;
			int y1 = (int) pos.y;		
			boolean _s = col[x1][y1] > 0; //change based on entity,some can use water
			float _g = total + (1/mod[x1][y1]); //1/movement_mod
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
	
	private Point2 toBaseCoords(Point2 point) {
		Point2 x = new Point2();
		x.x = (float) Math.floor(point.x / TerrainTile.SUBTILE_SIZE);
		x.y = (float) Math.floor(point.y / TerrainTile.SUBTILE_SIZE);
		return x;
	}
	
	private boolean inList(Point2 pos, ArrayList<TileNode> list){
		return (getFromList(pos, list) == null) ? false : true;
	}
	
	private TileNode getFromList(Point2 pos, ArrayList<TileNode> list) {
		for(TileNode t : list){
			if(t.pos.equals(pos)) return t;
		}
		return null;
	}
	

}

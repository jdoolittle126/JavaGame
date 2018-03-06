package jon.game.entity;

import java.util.ArrayList;

import jon.game.utils.Point2;

public class PathFinder {
	ArrayList<Point2> used, path;
	TileNode[][] surroundings;
	TileNode last;
	
	public PathFinder(Point2 start, Point2 end) {
		surroundings = new TileNode[3][3];
	}
	
	public void next() {
		last = surroundings[1][1];
		
	}

}

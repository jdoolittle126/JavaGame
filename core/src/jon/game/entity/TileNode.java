package jon.game.entity;

import jon.game.utils.Point2;

public class TileNode {
	boolean s;
	float G, H, T;
	Point2 pos;
	
	public TileNode(Point2 pos, boolean s, float g, float h, float t) {
		G = g;
		H = h;
		T = t;
		this.s = s;
		this.pos = pos;
	}
	

}

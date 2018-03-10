package jon.game.entity;

import jon.game.utils.Point2;

public class TileNode {
	public boolean s;
	public float G, H, T;
	public Point2 pos;
	
	public TileNode(Point2 pos, boolean s, float g, float h, float t) {
		this.G = g;
		this.H = h;
		this.T = t;
		this.s = s;
		this.pos = pos;
	}
	
	public float getF() {
		return G+H+T;
	}
	

}

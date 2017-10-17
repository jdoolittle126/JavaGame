package jon.game.terrain;

import jon.game.utils.Point2;

public class Chunk {
	
	private TerrainTile[][] chunk_data;
	private Point2 coords;
	public static final int CHUNK_SIZE = 32;
	
	public Chunk(Point2 coords){
		chunk_data = new TerrainTile[CHUNK_SIZE][CHUNK_SIZE];
		this.coords = coords;
	}
	
	public void add(int x, int y, TerrainTile tile){
		chunk_data[x][y] = tile;
	}
	
	public TerrainTile get(int x, int y){
		return chunk_data[x][y];
	}
	
	public TerrainTile[][] getAll(){
		return chunk_data;
	}
	
	public Point2 getCoords() {
		return this.coords;
	}

}

package jon.game.terrain;

public class Chunk {
	
	private TerrainTile[][] chunk_data;
	public static final int CHUNK_SIZE = 32;
	
	public Chunk(){
		chunk_data = new TerrainTile[CHUNK_SIZE][CHUNK_SIZE];
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

}

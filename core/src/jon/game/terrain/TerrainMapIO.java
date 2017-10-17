package jon.game.terrain;

import com.badlogic.gdx.utils.Json;

import jon.game.utils.Point2;

public class TerrainMapIO {
	private Json data;
	
	public TerrainMapIO(String filepath) {
		data = new Json();
	}

	public void writeChunk(Chunk chunk) {
		data.toJson(chunk);
	}
	
	public Chunk readChunk(Point2 pos) {
		return new Chunk(pos);
	}
	
	

}

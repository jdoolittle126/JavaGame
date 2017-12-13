package jon.game.terrain;

public class World {
	TerrainMap map;
	WeatherController weather;
	
	public World(TerrainMap map) {
		this.map = map;
		
	}
	
	public TerrainMap getMap(){
		return this.map;
	}
	
}

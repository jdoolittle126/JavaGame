package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class World extends Actor {
	TerrainMap map;
	WeatherController weather;
	
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		map.draw(batch, parentAlpha);
	}

	@Override
	public void act(float delta) {
		map.act(delta);
	}

	public World(TerrainMap map) {
		this.map = map;
	}
	
	public TerrainMap getMap(){
		return this.map;
	}
	
}

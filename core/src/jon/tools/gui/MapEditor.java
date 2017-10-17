package jon.tools.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jon.game.terrain.Chunk;
import jon.game.terrain.EditableTerrainMap;
import jon.game.terrain.TerrainBrush;
import jon.game.terrain.TerrainMap.MapType;
import jon.game.utils.Point2;

public class MapEditor extends Game {
	
	public static Point2 mouse_coords_world = new Point2(0, 0);
	
	EditableTerrainMap map;
	TerrainBrush brush;
	OrthographicCamera camera;
	SpriteBatch batch;	
	Viewport viewPort;
	float delta;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		map = new EditableTerrainMap(MapType.filled);
		viewPort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
	}
	
	public void setMap(EditableTerrainMap map) {
		this.map = map;
	}
	
	public void setBrush(TerrainBrush brush) {
		this.brush = brush;
	}

	@Override
	public void resize(int width, int height) {
		viewPort.update(width, height);
	}

	@Override
	public void render() {
		delta = Gdx.graphics.getDeltaTime();
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			camera.zoom += 1f;
			camera.zoom = MathUtils.clamp(camera.zoom, 1, 17);
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			camera.zoom -= 1f;
			camera.zoom = MathUtils.clamp(camera.zoom, 1, 17);
		}
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
			Point2 selected_chunk = new Point2((int) (mouse_coords_world.x / Chunk.CHUNK_SIZE), (int) (mouse_coords_world.y / Chunk.CHUNK_SIZE));
			map.getChunk(selected_chunk);
			camera.translate(vec);
		}
		
		Vector3 mouse_coords_world_vector = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		mouse_coords_world = new Point2(mouse_coords_world_vector.x, mouse_coords_world_vector.y);
		
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();
		map.update(delta, batch);
		batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	
	
}

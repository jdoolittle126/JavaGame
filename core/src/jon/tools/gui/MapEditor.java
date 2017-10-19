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
import jon.game.terrain.Material;
import jon.game.terrain.TerrainBrush;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.TerrainMap.MapType;
import jon.game.utils.Point2;

public class MapEditor extends Game {
	
	public enum SelectorType {
		chunk,
		tile,
		subtile;
	}
	
	public static Point2 mouse_coords_world = new Point2(0, 0);
	
	SelectorType selectorType;
	EditableTerrainMap map;
	TerrainBrush brush;
	OrthographicCamera camera;
	SpriteBatch batch;	
	Viewport viewPort;
	float delta;
	boolean selected = false;
	
	@Override
	public void create() {
		selectorType = SelectorType.subtile;
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
		
		Vector3 mouse_coords_world_vector = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		mouse_coords_world = new Point2(mouse_coords_world_vector.x, mouse_coords_world_vector.y);
		
		
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			camera.zoom += 1f;
			camera.zoom = MathUtils.clamp(camera.zoom, 1, 17);
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			camera.zoom -= 1f;
			camera.zoom = MathUtils.clamp(camera.zoom, 1, 17);
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			switch(selectorType) {
				case chunk:
					selectChunk(); break;
				case tile:
					selectTile(); break;
				case subtile:
					selectSubtile(); break;
			}
			//selected = !selected;

		}
		
		if(Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
			selectorType = SelectorType.chunk;
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
			selectorType = SelectorType.tile;
		}
		if(Gdx.input.isKeyJustPressed(Keys.NUM_3)) {
			selectorType = SelectorType.subtile;
		}
		
		
		batch.setProjectionMatrix(camera.combined);
		camera.update();
		batch.begin();
		map.update(delta, batch);
		Material.outline.getTexture().draw(batch);
		batch.end();
	}

	public void selectChunk() {
		Point2 selected_chunk = new Point2(MathUtils.floor(mouse_coords_world.x / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE)), MathUtils.floor(mouse_coords_world.y / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE)));
		Material.outline.getTexture().setOrigin(0, 0);
		Material.outline.getTexture().setX(selected_chunk.x * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setY(selected_chunk.y * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setScale(Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION);
	}
	
	public void selectTile() {
		Point2 selected_tile = new Point2(MathUtils.floor(mouse_coords_world.x / (TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE)), MathUtils.floor(mouse_coords_world.y / (TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE)));
		Material.outline.getTexture().setOrigin(0, 0);
		Material.outline.getTexture().setX(selected_tile.x * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setY(selected_tile.y * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setScale(TerrainTile.DETAIL_PER_SECTION);
	}
	
	public void selectSubtile() {
		Point2 selected_subtile = new Point2(MathUtils.floor(mouse_coords_world.x / (TerrainTile.SUBTILE_SIZE)), MathUtils.floor(mouse_coords_world.y / (TerrainTile.SUBTILE_SIZE)));
		Material.outline.getTexture().setOrigin(0, 0);
		Material.outline.getTexture().setX(selected_subtile.x * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setY(selected_subtile.y * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setScale(1);
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

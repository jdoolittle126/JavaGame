package jon.game.terrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import jon.game.terrain.Chunk;
import jon.game.terrain.EditableTerrainMap;
import jon.game.terrain.Material;
import jon.game.terrain.TerrainBrush;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.TerrainMap.MapType;
import jon.game.utils.Point2;
import jon.tools.gui.MapEditor;

public class MapEditWindow extends Window {
	
	EditableTerrainMap map;
	TerrainBrush brush;
	OrthographicCamera camera;
	ScreenViewport viewPort;
	SelectorType selectorType;
	Point2 mouse_coords_world = MapEditor.mouse_coords_world;
	float delta;
	
	
	public MapEditWindow(String title, Skin skin, MapType mapType) {
		super(title, skin);
		camera = new OrthographicCamera();
		viewPort = new ScreenViewport(camera);
		map = new EditableTerrainMap(mapType);
		selectorType = SelectorType.subtile;
		this.addActor(map);
		
		// TODO Auto-generated constructor stub
	}

	public enum SelectorType {
		chunk,
		tile,
		subtile;
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		render(batch);
	}

	
	
	public void setMap(EditableTerrainMap map) {
		this.map = map;
	}
	
	public void setBrush(TerrainBrush brush) {
		this.brush = brush;
	}

	public void render(Batch batch) {
		delta = Gdx.graphics.getDeltaTime();
		mouse_coords_world = new Point2(MapEditor.mouse_coords_world.x - this.getX(), MapEditor.mouse_coords_world.y - this.getY());
		Vector3 mouse_coords_world_vector = new Vector3(mouse_coords_world.x, mouse_coords_world.y, 0);
		
		this.getStage().getBatch().setProjectionMatrix(camera.combined);
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
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){
			this.camera.translate(mouse_coords_world_vector.sub(camera.position).scl(0.1f * 1 / (camera.zoom)));
		}
		camera.update();	
		camera.position.x = MathUtils.clamp(camera.position.x, (map.getMinSize().x * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) + (camera.viewportWidth * camera.zoom / 2), (map.getMaxSize().x * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) - (camera.viewportWidth * camera.zoom / 2));
		camera.position.y = MathUtils.clamp(camera.position.y, (map.getMinSize().y  * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) + (camera.viewportHeight * camera.zoom / 2), (map.getMaxSize().y  * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) - (camera.viewportHeight * camera.zoom / 2));
		this.getStage().getBatch().setProjectionMatrix(this.getStage().getCamera().combined);
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


	
	
}

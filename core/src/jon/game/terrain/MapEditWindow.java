package jon.game.terrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
import jon.tools.gui.TestActor;

public class MapEditWindow extends Window {
	
	EditableTerrainMap map;
	TerrainBrush brush;
	OrthographicCamera camera;
	Viewport viewPort;
	Stage stage;
	SelectorType selectorType;
	TestActor testActor;
	public static Point2 mouse_coords_window = MapEditor.mouse_coords_world;
	float delta;
	
	
	public MapEditWindow(String title, Skin skin, MapType mapType) {
		super(title, skin);
		
		camera = new OrthographicCamera(this.getWidth(), this.getHeight());
		viewPort = new StretchViewport(this.getWidth(), this.getHeight(), camera);
		stage = new Stage(viewPort);
		this.setKeepWithinStage(true);
		map = new EditableTerrainMap(mapType);
		selectorType = SelectorType.subtile;
		testActor = new TestActor(this);
		stage.addActor(testActor);
		
	}

	public enum SelectorType {
		chunk,
		tile,
		subtile;
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		Vector3 test = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		
		mouse_coords_window = new Point2(test.x, test.y);
	
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
			//this.camera.translate(mouse_coords_world_vector.sub(camera.position).scl(0.1f * 1 / (camera.zoom)));
		}
		
		camera.position.x = MathUtils.clamp(camera.position.x, (map.getMinSize().x * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) + (camera.viewportWidth * camera.zoom / 2), (map.getMaxSize().x * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) - (camera.viewportWidth * camera.zoom / 2));
		camera.position.y = MathUtils.clamp(camera.position.y, (map.getMinSize().y  * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) + (camera.viewportHeight * camera.zoom / 2), (map.getMaxSize().y  * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) - (camera.viewportHeight * camera.zoom / 2));

	}

	
	
	@Override
	public void setPosition(float x, float y) {
		resizePort();
		super.setPosition(x, y);
	}



	@Override
	public void setPosition(float x, float y, int alignment) {
		resizePort();
		super.setPosition(x, y, alignment);
	}



	@Override
	public void sizeBy(float size) {
		resizePort();
		super.sizeBy(size);
	}



	@Override
	public void sizeBy(float width, float height) {
		resizePort();
		super.sizeBy(width, height);
	}



	@Override
	public void setBounds(float x, float y, float width, float height) {
		resizePort();
		super.setBounds(x, y, width, height);
	}

	public Point2 getWindowCoords(){
		return new Point2(viewPort.getScreenX(), viewPort.getScreenY());
	}
	
	public Point2 getWindowSize(){
		return new Point2(camera.viewportWidth, camera.viewportHeight);
	}
	
	public void resizePort(){
		viewPort.setScreenPosition((int) this.getX(), (int) this.getY());
		camera.viewportHeight = this.getHeight() ;
		camera.viewportWidth = this.getWidth();
		
	}

	public void setMap(EditableTerrainMap map) {
		this.map = map;
	}
	
	public void setBrush(TerrainBrush brush) {
		this.brush = brush;
	}
	
	public Stage getTestStage(){
		return stage;
	}

	public void selectChunk() {
		Point2 selected_chunk = new Point2(MathUtils.floor(mouse_coords_window.x / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE)), MathUtils.floor(mouse_coords_window.y / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE)));
		Material.outline.getTexture().setOrigin(0, 0);
		Material.outline.getTexture().setX(selected_chunk.x * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setY(selected_chunk.y * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setScale(Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION);
	}
	
	public void selectTile() {
		Point2 selected_tile = new Point2(MathUtils.floor(mouse_coords_window.x / (TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE)), MathUtils.floor(mouse_coords_window.y / (TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE)));
		Material.outline.getTexture().setOrigin(0, 0);
		Material.outline.getTexture().setX(selected_tile.x * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setY(selected_tile.y * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setScale(TerrainTile.DETAIL_PER_SECTION);
	}
	
	public void selectSubtile() {
		Point2 selected_subtile = new Point2(MathUtils.floor(mouse_coords_window.x / (TerrainTile.SUBTILE_SIZE)), MathUtils.floor(mouse_coords_window.y / (TerrainTile.SUBTILE_SIZE)));
		Material.outline.getTexture().setOrigin(0, 0);
		Material.outline.getTexture().setX(selected_subtile.x * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setY(selected_subtile.y * TerrainTile.SUBTILE_SIZE);
		Material.outline.getTexture().setScale(1);
	}


	
	
}

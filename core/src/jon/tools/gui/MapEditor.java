package jon.tools.gui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.VisUI.SkinScale;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuBar;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;

import jon.game.core.GameClient;
import jon.game.terrain.Chunk;
import jon.game.terrain.EditableTerrainMap;
import jon.game.terrain.MapEditWindow;
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
	Stage stage;
	MenuBar barMenu;
	MapEditWindow mapEditWindow;
	public static Skin skin_default;
	float delta;
	boolean selected = false;
	private static MapEditor editor;
	
	@Override
	public void create() {
		VisUI.load(SkinScale.X1);
		skin_default = new Skin(new FileHandle("assets/skins/flat/skin/flat-earth-ui.json"));
		selectorType = SelectorType.subtile;
		
		Window test = new Window("Ye2", skin_default);
		mapEditWindow = new MapEditWindow("Yee", skin_default, MapType.fixed);
		//batch = new SpriteBatch();
		//camera = new OrthographicCamera();
		//map = new EditableTerrainMap(MapType.filled);
		viewPort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage(viewPort);
		final Table root = new Table();
		root.setFillParent(true);
		stage.addActor(root);
		
		barMenu = new MenuBar();
		
		test.setSize(100, 100);
		test.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, 0);
		test.setMovable(true);
		
		stage.addActor(test);
		mapEditWindow.setSize(400, 400);
		mapEditWindow.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, 0);
		mapEditWindow.setMovable(true);
		mapEditWindow.setResizable(true);
		
		stage.addActor(mapEditWindow);
		
		root.add(barMenu.getTable()).expandX().fillX().row();
		root.add().expand().fill();
		createMenus();
		InputMultiplexer plex = new InputMultiplexer();
		plex.addProcessor(stage);
		Gdx.input.setInputProcessor(plex);
	}
	
	private void createMenus () {
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu windowMenu = new Menu("Window");
		Menu helpMenu = new Menu("Help");
		//stuff
		barMenu.addMenu(fileMenu);
		barMenu.addMenu(editMenu);
		barMenu.addMenu(windowMenu);
		barMenu.addMenu(helpMenu);
		
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
		stage.getViewport().update(width, height, true);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		delta = Gdx.graphics.getDeltaTime();
		
		Vector3 mouse_coords_world_vector = stage.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		mouse_coords_world = new Point2(mouse_coords_world_vector.x, mouse_coords_world_vector.y);
		
		/*
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			mapEditWindow.camera.zoom += 1f;
			mapEditWindow.camera.zoom = MathUtils.clamp(mapEditWindow.camera.zoom, 1, 17);
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
			camera.translate(mouse_coords_world_vector.sub(camera.position).scl(0.1f * 1 / (camera.zoom)));
		}
		
		
		batch.setProjectionMatrix(camera.combined);

		camera.update();
		batch.begin();
		map.update(delta, batch);
		camera.position.x = MathUtils.clamp(camera.position.x, (map.getMinSize().x * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) + (camera.viewportWidth * camera.zoom / 2), (map.getMaxSize().x * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) - (camera.viewportWidth * camera.zoom / 2));
		camera.position.y = MathUtils.clamp(camera.position.y, (map.getMinSize().y  * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) + (camera.viewportHeight * camera.zoom / 2), (map.getMaxSize().y  * Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE) - (camera.viewportHeight * camera.zoom / 2));
		Material.outline.getTexture().draw(batch);
		*/
		stage.act(delta);
		stage.draw();
		//batch.end();
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
		stage.dispose();
		super.dispose();
	}

	public static MapEditor getEditor() {
		return editor;
	}
	
	
}

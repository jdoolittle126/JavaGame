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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.FillViewport;
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
import jon.game.enums.TileType;
import jon.game.resource.Materials;
import jon.game.terrain.Chunk;
import jon.game.terrain.EditableTerrainMap;
import jon.game.terrain.MapEditWindow;
import jon.game.terrain.Material;
import jon.game.terrain.TerrainBrush;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.TerrainMap.MapType;
import jon.game.terrain.TerrainSubTile;
import jon.game.utils.Point2;

public class MapEditor extends Game {
	public enum SelectorType {
		chunk,
		tile,
		subtile;
	}
	
	public static Point2 mouse_coords_world = new Point2(0, 0);
	
	SelectorType selectorType;
	TerrainBrush brush;
	Viewport viewPort;
	OrthographicCamera camera;
	Stage stage;
	MenuBar barMenu;
	MapEditWindow mapEditWindow;
	ShapeRenderer drender;
	public static Skin skin_default;
	float delta;
	boolean selected = false;
	public static int V_WIDTH = 1024, V_HEIGHT = 768;
	private static MapEditor editor;
	
	@Override
	public void create() {
		Materials.load();
		editor = this;
		VisUI.load(SkinScale.X1);
		skin_default = new Skin(new FileHandle("assets/skins/flat/skin/flat-earth-ui.json"));
		selectorType = SelectorType.subtile;
		camera = new OrthographicCamera();
		Window test = new Window("Ye2", skin_default);
		
		//batch = new SpriteBatch();
		//camera = new OrthographicCamera();
		//map = new EditableTerrainMap(MapType.filled);
		viewPort = new StretchViewport(V_WIDTH, V_HEIGHT, camera);
		stage = new Stage(viewPort);
		
		barMenu = new MenuBar();
		
		test.setSize(100, 100);
		test.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, 0);
		test.setMovable(true);
		
		stage.addActor(test);
		
		mapEditWindow = new MapEditWindow("Map Edit Window", skin_default, MapType.fixed);
		mapEditWindow.setSize(400, 400);
		mapEditWindow.setPosition(stage.getWidth() / 2, stage.getHeight() / 2, 0);
		mapEditWindow.setMovable(true);
		mapEditWindow.setResizable(true);
		
		stage.addActor(mapEditWindow);
		
		createMenus();
		InputMultiplexer plex = new InputMultiplexer();
		plex.addProcessor(stage);
		Gdx.input.setInputProcessor(plex);
		drender = new ShapeRenderer();
		
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
	
	public void setBrush(TerrainBrush brush) {
		this.brush = brush;
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		delta = Gdx.graphics.getDeltaTime();
		
		Vector2 mouse_coords_world_vector = stage.screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
		mouse_coords_world = new Point2(mouse_coords_world_vector.x, mouse_coords_world_vector.y);
		stage.act(delta);
		stage.draw();

		//drender.setAutoShapeType(true);
		//drender.begin();
		//mapEditWindow.drawDebug(drender);
		//drender.end();
	}

	/*
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
	*/
	
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
	
	public float getDelta(){
		return delta;
	}

	
	public Stage getStage(){
		return stage;
	}
	
	public static MapEditor getEditor() {
		return editor;
	}
	
	
}

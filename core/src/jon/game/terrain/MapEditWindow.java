package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import jon.game.terrain.EditableTerrainMap;
import jon.game.terrain.TerrainBrush;
import jon.game.terrain.TerrainMap.MapType;
import jon.game.utils.Point2;
import jon.tools.gui.MapEditor;

public class MapEditWindow extends Window {
	float scale;
	TerrainMap map;
	TerrainBrush brush;
	SelectorType selectorType;
	public static Point2 mouse_coords_window = MapEditor.mouse_coords_world;
	float delta;
	
	
	public MapEditWindow(String title, Skin skin) {
		super(title, skin);
		map = new TerrainMap(MapType.fixed);
		selectorType = SelectorType.subtile;
	}

	public enum SelectorType {
		chunk,
		tile,
		subtile;
	}
	
	
	@Override
	public void draw(Batch batch, float parentAlpha) {

		super.draw(batch, parentAlpha);	
		
	}



	@Override
	public void drawDebug(ShapeRenderer shapes) {
		//map.drawDebug(shapes);
		//super.drawDebug(shapes);
	}
	
	public void zoom(float value, float min, float max) {
		this.scale -= value;
		this.scale = MathUtils.clamp(this.scale, min, max);
	}

	public void setMap() {
		this.clearChildren();
		this.setClip(true);
		this.addActor(map);
	}
	
	public void setBrush(TerrainBrush brush) {
		this.brush = brush;
	}

	/*
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
	*/

	
	
}

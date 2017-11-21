package jon.game.mapeditorcomponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import jon.game.terrain.TerrainMap;
import jon.game.terrain.TerrainTile;
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
		float moveBy = TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE;
		
		if(Gdx.input.isKeyJustPressed(Keys.W)) {
			map.moveBy(0, -moveBy);
		}
		if(Gdx.input.isKeyJustPressed(Keys.A)) {
			map.moveBy(moveBy, 0);
		}
		if(Gdx.input.isKeyJustPressed(Keys.S)) {
			map.moveBy(0, moveBy);
		}
		if(Gdx.input.isKeyJustPressed(Keys.D)) {
			map.moveBy(-moveBy, 0);
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q)) {
			map.scaleBy(0.1f);
		}
		if(Gdx.input.isKeyJustPressed(Keys.E)) {
			if(!(map.getScaleX() <= 0.1 || map.getScaleY() <= 0.1)) map.scaleBy(-0.1f);
			
		}
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			map.setScale(1);
		}
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

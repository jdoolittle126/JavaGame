package jon.game.terrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import jon.game.enums.TileType;
import jon.game.resource.Materials;
import jon.game.utils.Point2;
import jon.tools.gui.MapEditor;

public class TerrainSubTile extends Actor {
	
	private TileType type;
	//private Material material;
	public static boolean flag = true;
	
	public TerrainSubTile(Point2 coords, TileType type) {
		
		this.setX(coords.x * TerrainTile.SUBTILE_SIZE);
		this.setY(coords.y * TerrainTile.SUBTILE_SIZE);
		this.setWidth(TerrainTile.SUBTILE_SIZE);
		this.setHeight(TerrainTile.SUBTILE_SIZE);
		this.type = type;
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		switch(type) {
			case grass:
				renderTile(Materials.grass, batch);
				break;
			case water:
				renderTile(Materials.water, batch);
				break;
		}
		
		super.draw(batch, parentAlpha);
	}
	
	public void renderTile(Material material, Batch batch) {
		material.getSprite().setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		material.getSprite().draw(batch);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
	
	public TileType getTileType() {
		return type;
	}
	

}

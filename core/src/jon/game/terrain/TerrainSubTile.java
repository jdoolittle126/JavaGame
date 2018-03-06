package jon.game.terrain;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import jon.game.enums.TileType;
import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class TerrainSubTile extends Actor {
	private Material material;
	
	public TerrainSubTile(Point2 coords, Material material) {
		this.material = material;
		
		this.setX(coords.x * TerrainTile.SUBTILE_SIZE);
		this.setY(coords.y * TerrainTile.SUBTILE_SIZE);
		this.setWidth(TerrainTile.SUBTILE_SIZE);
		this.setHeight(TerrainTile.SUBTILE_SIZE);
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		renderTile(material, batch);
		
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
	
	public Material getMaterial() {
		return this.material;
	}
	

}

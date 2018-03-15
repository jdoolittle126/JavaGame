package jon.game.items;

import jon.game.entity.EntityStatic;
import jon.game.enums.ItemsList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item extends EntityStatic {
	private ItemsList identifier;
	private Texture texture;
	
	public Item(ItemsList identifier, Texture texture) {
		this.identifier = identifier;
		this.texture = texture;
	}

	public ItemsList getIdentifier() {
		return identifier;
	}

	public void setIdentifier(ItemsList identifier) {
		this.identifier = identifier;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}


	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(new TextureRegion(texture), this.getCoords2().x - 32f, this.getCoords2().y - 32f, 32f, 32f, 64f, 64f, 1, 1, (float) Math.toDegrees(this.rotation));

		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

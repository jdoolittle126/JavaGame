package jon.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import jon.game.enums.ItemsList;

public class Consumable extends Item {
	private Effect effect;
	private boolean consumes_item;
	
	public Consumable(ItemsList identifier, Texture texture, Effect effect, boolean consumes_item) {
		super(identifier, texture);
		this.consumes_item = consumes_item;
		this.effect = effect;
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	public boolean doesConsumeItem() {
		return consumes_item;
	}

	public void setConsumesItem(boolean consumes_item) {
		this.consumes_item = consumes_item;
	}
	
	
	

}

package jon.game.items;

import com.badlogic.gdx.graphics.Texture;

import jon.game.enums.ItemsList;

public class Berry extends Consumable {
	
	public Berry() {
		super(ItemsList.berry, new Texture("assets/textures/items/berry.png"), new Effect(), true);
	}
	
	
	
}

package jon.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;

import jon.game.entity.Animal;
import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class Beaver extends Animal {

	public Beaver(Point2 loc) {
		super();
		this.coords.x = loc.x;
		this.coords.y = loc.y;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		drawSprite(batch, Materials.beaver);
	}
	
	@Override
	public void plus_action_forward(float delta) {}

	@Override
	public void plus_action_backwards(float delta) {}

	@Override
	public void plus_action_left(float delta) {}

	@Override
	public void plus_action_right(float delta) {}

	@Override
	public void minus_action_forward(float delta) {}

	@Override
	public void minus_action_backwards(float delta) {}

	@Override
	public void minus_action_left(float delta) {}

	@Override
	public void minus_action_right(float delta) {}

	@Override
	public void dispose() {}
}

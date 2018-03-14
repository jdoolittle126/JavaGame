package jon.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;

import jon.game.entity.EntityStatic;
import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class Rock extends EntityStatic {
	
	public Rock(Point2 loc) {
		super();
		coords.x = loc.x;
		coords.y = loc.y;
	}
	
	@Override
	public void act(float delta) {
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		drawSprite(batch, Materials.rock);
	}

	@Override
	public void dispose() {
		
	}
}

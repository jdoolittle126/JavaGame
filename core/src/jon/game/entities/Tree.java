package jon.game.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import jon.game.entity.EntityStatic;
import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class Tree extends EntityStatic {

	public Tree(Point2 loc) {
		super();
		coords.x = loc.x;
		coords.y = loc.y;
	}
	
	@Override
	public void act(float delta) {
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		drawSprite(batch, Materials.tree);
	}

	@Override
	public void dispose() {
		
	}

}

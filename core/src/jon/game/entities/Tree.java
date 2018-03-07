package jon.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import jon.game.entity.EntityStatic;
import jon.game.utils.Point2;

public class Tree extends EntityStatic {

	TextureRegion testregion;
	
	public Tree(Point2 loc) {
		this.coords.x = loc.x;
		this.coords.y = loc.y;
		testregion = new TextureRegion(new Texture("assets/textures/entities/tree.png"));
		
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(testregion, this.getCoords2().x - 64f, this.getCoords2().y - 64f, 64f, 64f, 128f, 128f, 1, 1, (float) Math.toDegrees(this.rotation));
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

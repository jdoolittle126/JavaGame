package jon.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import jon.game.entity.Animal;
import jon.game.utils.Point2;

public class Duck extends Animal {

	//Prefers water
	
	TextureRegion testregion;
	
	public Duck(Point2 loc) {
		super();
		this.coords.x = loc.x;
		this.coords.y = loc.y;
		testregion = new TextureRegion(new Texture("assets/textures/entities/duck.png"));
		
	}
	
	
	@Override
	public void plus_action_forward(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void plus_action_backwards(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void plus_action_left(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void plus_action_right(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void minus_action_forward(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void minus_action_backwards(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void minus_action_left(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void minus_action_right(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(testregion, this.getCoords2().x - 32f, this.getCoords2().y - 32f, 32f, 32f, 64f, 64f, 1, 1, (float) Math.toDegrees(this.rotation));
		
	}
	
}

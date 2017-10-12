package jon.game.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Manager {

	public abstract void update(float delta, SpriteBatch batch);
	public abstract void dispose();
	
}

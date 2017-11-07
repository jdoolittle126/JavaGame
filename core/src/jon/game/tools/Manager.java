package jon.game.tools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Manager {

	public abstract void update(SpriteBatch batch, float parentAlpha, float delta);
	public abstract void dispose();
	
}

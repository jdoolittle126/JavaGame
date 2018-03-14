package jon.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import jon.game.utils.Point2;

public abstract class BasicScreen implements Screen {

	public abstract void update(SpriteBatch batch, float parentAlpha, float delta);
	public abstract Matrix4 getTransform();
	public abstract Vector3 getTranslation(Point2 coords);
	
}

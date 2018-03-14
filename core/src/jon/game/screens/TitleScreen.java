package jon.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import jon.game.core.GameClient;
import jon.game.core.GameInstance;
import jon.game.utils.Point2;

public class TitleScreen extends BasicScreen {
	Texture background;
	Stage stage;
	Skin skin;

	public TitleScreen () {
		this.skin = GameClient.getSkin();
		init();
	}

	public TitleScreen (Skin skin) {
		this.skin = skin;
		init();
		
	}

	public void init() {
		stage = new Stage();
		background = new Texture("assets/misc/background.jpg");
		createTitleScreen();
	}
	
	@Override
	public void show () {
		Gdx.input.setCursorCatched(false);
		Gdx.input.setCursorPosition(1024/2, 768/2);
		Gdx.input.setInputProcessor(stage);
	}
	
	private void createTitleScreen(){
		final Table root = new Table();
		root.setFillParent(true);
		root.left().top();
		root.background(new TextureRegionDrawable(new TextureRegion(background)));
		
		TextButton button_play = new TextButton("new game!", skin);
		TextButton button_editor = new TextButton("editor! (wip)", skin);
		TextButton button_quit = new TextButton("quit!", skin);
		
		button_play.addCaptureListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	Gdx.input.setCursorPosition(1024/2, 768/2);
            	GameClient.getGame().getScreenManager().setGameScreen(new GameInstance());
            	GameClient.getGame().getScreenManager().toGame();
                return true;
            }
        });
		
		button_editor.addCaptureListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	//GameClient.getGame().startEditor();
                return true;
            }
        });
		
		button_quit.addCaptureListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	Gdx.app.exit();
				return true;
            }
        });
		
		root.add(button_play).padLeft(50f).padBottom(10f).padTop(100f).fillX();
		root.row();
		root.add(button_editor).padLeft(50f).padBottom(10f).fillX();
		root.row();
		root.add(button_quit).padLeft(50f).fillX();
		
		stage.addActor(root);
	}

	@Override
	public void update(SpriteBatch batch, float parentAlpha, float delta) {
		render(delta);
	}
	
	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void hide () {
		background.dispose();
		stage.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {

	}

	@Override
	public Matrix4 getTransform() {
		return new Matrix4();
	}
	
	@Override
	public Vector3 getTranslation(Point2 coords) {
		return new Vector3(coords.x, coords.y, 0);
	}

}
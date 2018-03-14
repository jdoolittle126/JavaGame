package jon.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

public class TitleScreen implements Screen {
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
		
	}
	
	private void createTitleScreen(){
		final Table root = new Table();
		root.setFillParent(true);
		root.left().top();
		root.background(new TextureRegionDrawable(new TextureRegion(background)));
		
		TextButton button_play = new TextButton("play!", skin);
		TextButton button_editor = new TextButton("editor! (disabled)", skin);
		TextButton button_quit = new TextButton("quit!", skin);
		
		button_play.addCaptureListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	Gdx.input.setCursorPosition(1024/2, 768/2);
            	//start game
                return true;
            }
        });
		
		button_editor.addCaptureListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	//start editor
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
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void hide () {
		stage.dispose();
		background.dispose();
		skin.dispose();
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
		// TODO Auto-generated method stub
		
	}

}
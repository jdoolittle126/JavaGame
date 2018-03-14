package jon.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import jon.game.core.GameClient;
import jon.game.debug.Debugger;
import jon.game.entity.EntityLiving;
import jon.game.resource.Materials;
import jon.game.utils.Point3;
import jon.physics.core.Shape;
import jon.physics.core.Shape.Type;

public class Player extends EntityLiving {
	
	//Sample Shapes for Demo
	private Shape shape = new Shape(Type.Polygon, new Vector2(0f, 0f), new Vector2(200f, 200f), new Vector2(150f, 300f), new Vector2(0f, 50f));
	private Shape shape2 = new Shape(Type.Polygon, new Vector2(-100f, -60f), new Vector2(-300f, 0f), new Vector2(0f, -200f));
	private Shape shape3 = new Shape(Type.Polygon, new Vector2(-200f, -60f), new Vector2(-300f, 100f), new Vector2(0f, -200f));
	private Shape shape4 = new Shape(Type.Polygon, new Vector2(-50f, 100f), new Vector2(-100f, 100f), new Vector2(-150f, 150f), new Vector2(-125f, 200f), new Vector2(-50f, 150f));
	
	public Player(){
		super();
	}
	

	@Override
	public void draw(Batch batch, float parentAlpha) {
		drawSprite(batch, Materials.player);
		
		if(Gdx.input.isKeyJustPressed(Keys.E)) {
			Shape.debug_draw_bounding = !Shape.debug_draw_bounding;
		}
		
		if(GameClient.DEMO1) {
			//Simple Shape Collision showcase
			shape.debug_draw = true;
			shape2.debug_draw = false;
			shape3.debug_draw = false;
			shape4.debug_draw = false;
			
			shape.update();
			if (shape.hasCollision(new Vector2(coords.x, coords.y))) shape.setColor(Color.GREEN);
			else shape.setColor(Color.BLUE);
		}
		
		
		if(GameClient.DEMO2) {
			//Shape transform and collision showcase
			shape.debug_draw = false;
			shape2.debug_draw = false;
			shape3.debug_draw = false;
			shape4.debug_draw = true;
			
			shape4.update();
			if(Gdx.input.isKeyPressed(Keys.Q)) shape4.transform(new Vector2(1,0));
			if (shape4.hasCollision(new Vector2(coords.x, coords.y))) shape4.setColor(Color.GREEN);
			else shape4.setColor(Color.BLUE);
		}
		
		if(GameClient.DEMO3) {
			//Shape collision showcase
			shape.debug_draw = false;
			shape2.debug_draw = true;
			shape3.debug_draw = true;
			shape4.debug_draw = false;
			
			shape2.update();
			shape3.update();
			if(Gdx.input.isKeyPressed(Keys.Q)) shape2.transform(new Vector2(1,0));
			
			if (shape2.hasCollision(shape3)) shape2.setColor(Color.GREEN);
			else shape2.setColor(Color.BLUE);
			
			if (shape3.hasCollision(shape2)) shape3.setColor(Color.GREEN);
			else shape3.setColor(Color.BLUE);
		}
		
	}
	
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void plus_action_forward(float delta) {
		this.addVelocity(this.moveTo(new Point3(GameClient.getGame().getScreenManager().getGameScreen().camera_main.position.x, GameClient.getGame().getScreenManager().getGameScreen().camera_main.position.y, 0), this.movement_stats.stat_speed_mod_forward * this.movement_modifier).scale(delta));
		Debugger.DrawDebugLine(this.coords, this.coords.cpy().transform(this.velocity.cpy().scale(100f)), 3, Color.BLUE, GameClient.getMatrix());
	}

	@Override
	public void plus_action_backwards(float delta) {
		this.addVelocity(this.moveTo(new Point3(GameClient.getGame().getScreenManager().getGameScreen().camera_main.position.x, GameClient.getGame().getScreenManager().getGameScreen().camera_main.position.y, 0), -this.movement_stats.stat_speed_mod_backwards * this.movement_modifier).scale(delta));
		Debugger.DrawDebugLine(this.coords, this.coords.cpy().transform(this.velocity.cpy().scale(100f)), 3, Color.BLUE, GameClient.getMatrix());
	}

	@Override
	public void plus_action_left(float delta) {
		this.addVelocity(this.moveAt(new Point3(GameClient.getGame().getScreenManager().getGameScreen().camera_main.position.x, GameClient.getGame().getScreenManager().getGameScreen().camera_main.position.y, 0), (float) (Math.PI/2)+0.1f , this.movement_stats.stat_speed_mod_left * this.movement_modifier).scale(delta));
		Debugger.DrawDebugLine(this.coords, this.coords.cpy().transform(this.velocity.cpy().scale(100f)), 3, Color.BLUE, GameClient.getMatrix());
	}

	@Override
	public void plus_action_right(float delta) {
		this.addVelocity(this.moveAt(new Point3(GameClient.getGame().getScreenManager().getGameScreen().camera_main.position.x, GameClient.getGame().getScreenManager().getGameScreen().camera_main.position.y, 0), (float) (-Math.PI/2)-0.1f, this.movement_stats.stat_speed_mod_right * this.movement_modifier).scale(delta));
		Debugger.DrawDebugLine(this.coords, this.coords.cpy().transform(this.velocity.cpy().scale(100f)), 3, Color.BLUE, GameClient.getMatrix());
		
	}

	@Override
	public void minus_action_forward(float delta) {
		
	}

	@Override
	public void minus_action_backwards(float delta) {
		
	}

	@Override
	public void minus_action_left(float delta) {
		
	}

	@Override
	public void minus_action_right(float delta) {
		
	}

}

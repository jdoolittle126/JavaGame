package jon.game.core;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import jon.game.debug.Debugger;
import jon.game.debug.LogID;
import jon.game.entity.living.Player;
import jon.game.enums.Action;
import jon.game.enums.ItemsList;
import jon.game.items.Consumable;
import jon.game.items.Effect;
import jon.game.terrain.Chunk;
import jon.game.terrain.TerrainMap;
import jon.game.terrain.TerrainTile;
import jon.game.terrain.World;
import jon.game.tools.PriorityCalculator;
import jon.game.tools.WorldRenderer;
import jon.game.utils.Point2;
import jon.game.utils.Point3;
import jon.tools.gui.MapEditor;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameInstance {
	private World world;
	private WorldRenderer worldrender;
	private Player player;
	private Point2 cam_old, cur_old;
	private float cam_lock_pos_temp = (768/2) - 150;
	private ArrayList<GameObject> object_list;
	boolean flag = true;
	boolean locko = true;
	private float angle = 0f;
	
	private int ticks, cycles;
	private int TICKS_TO_CYCLE;
	
	public GameInstance(){
		object_list = new ArrayList<GameObject>();
	}
	
	/*
	 *Clean this class
	 *Fix Debugger (Lines appear skewed sometimes etc)
	 *Player moving backwards clips screen
	 *Animals and AI
	 *Moisture Map and terrain work
	 *Start commenting and detailing work
	 *Alex's Assets in game
	 *UI and title screen etc
	 *Move hardcode for player into EntityController
	 *Camera Class ?smoothing
	 *Framebuffers for terrain
	 *Sound stuff
	 *Options (Screen for keybinds) (Could do this in launcher)
	 *Assets Manager
	 *Fix priority manager
	 *Gradual blur for vision
	 *Blur out while in inventory etc
	 *Fix resolution changing and scaling
	 *Sound
	 */
	
	
	public void start(){
		Gdx.input.setCursorCatched(true);
		Gdx.input.setCursorPosition(1024/2, 768/2);
		
		cam_old = new Point2(GameClient.getGame().getScreenManager().active_screen.camera_main.position.x, GameClient.getGame().getScreenManager().active_screen.camera_main.position.y);
		cur_old = new Point2(GameClient.mouse_coords);
		world = new World(new TerrainMap());
		world.setBounds(0, 0, 200, 200);
		worldrender = new WorldRenderer(world);
		
		player = new Player(new Texture("assets/textures/entities/player.png"));
		EntityController c = new EntityController(player);
		
		GameClient.getGame().addInputProcessor(c);
		
		object_list.add(player);
		object_list.get(0).setPriority(PriorityCalculator.PRIORITY_1);
		priorities();

	}
	
	public void priorities() {
		PriorityCalculator.init();
		PriorityCalculator.calculate();
		if((long) PriorityCalculator.TICKS_TO_UPDATE * PriorityCalculator.PRIORITY_1 * PriorityCalculator.PRIORITY_2 * PriorityCalculator.PRIORITY_3 * 
				PriorityCalculator.PRIORITY_4 * PriorityCalculator.PRIORITY_5 > Integer.MAX_VALUE) {
			
			TICKS_TO_CYCLE = Integer.MAX_VALUE;
		} else {
			TICKS_TO_CYCLE = PriorityCalculator.TICKS_TO_UPDATE * PriorityCalculator.PRIORITY_1 * PriorityCalculator.PRIORITY_2 * PriorityCalculator.PRIORITY_3 *
					PriorityCalculator.PRIORITY_4 * PriorityCalculator.PRIORITY_5;
		}
		
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		
		
		
		Debugger.DrawDebugLine(new Vector3(0, -100, 0), new Vector3(0, 100, 0), 1, Color.CORAL, new Matrix4(new Vector3(), new Quaternion(), new Vector3(0.05f, 0.05f, 0.05f)));
		
		if(locko) {	
			if(cur_old.x != GameClient.mouse_coords.x || cur_old.y != GameClient.mouse_coords.y) {
				float dx = cur_old.x - GameClient.mouse_coords.x;
				Vector3 v = new Vector3(player.getCoords2().x, player.getCoords2().y, 0);
				
				if(dx != 0) {
					float sens = 2;
					float a = (float) dx * Gdx.graphics.getDeltaTime() * sens;
					GameClient.getGame().getScreenManager().active_screen.camera_main.rotateAround(v, new Vector3(0, 0, 1),  a);
					
					angle += a;
					if(angle >= 360) angle -= 360;
					else if(angle <= -360) angle += 360;
				}
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.input.setCursorCatched(!Gdx.input.isCursorCatched());
			Gdx.input.setCursorPosition(1024/2, 768/2);
			locko = !locko;
			GameClient.mouse_coords.x = Gdx.input.getX();
			GameClient.mouse_coords.y = Gdx.input.getY();
			cur_old.x = GameClient.mouse_coords.x;
			cur_old.y = GameClient.mouse_coords.y;
			
		} else {
			cur_old.x = GameClient.mouse_coords.x;
			cur_old.y = GameClient.mouse_coords.y;
		}

		
		GameClient.getGame().getScreenManager().active_screen.camera_main.lockTo(player, 0, new Vector2((float) Math.sin(Math.toRadians(180-angle)) * cam_lock_pos_temp, (float) Math.cos(Math.toRadians(180-angle)) * cam_lock_pos_temp));
		player.lookAt(new Point2(GameClient.getGame().getScreenManager().active_screen.camera_main.position.x, GameClient.getGame().getScreenManager().active_screen.camera_main.position.y));

	
		//world.draw(batch, parentAlpha);
		worldrender.draw(batch, parentAlpha);
		
		for(GameObject o : object_list) o.draw(batch, parentAlpha);

	}
	
	public void act(float delta) {
		
		if(cam_old.x != GameClient.getGame().getScreenManager().active_screen.camera_main.position.x || cam_old.y != GameClient.getGame().getScreenManager().active_screen.camera_main.position.y) {
			
			float tx = (float) Math.floor(GameClient.getGame().getScreenManager().active_screen.camera_main.position.x / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
			float ty = (float) Math.floor(GameClient.getGame().getScreenManager().active_screen.camera_main.position.y / (Chunk.CHUNK_SIZE * TerrainTile.DETAIL_PER_SECTION * TerrainTile.SUBTILE_SIZE));
			Point2 p = new Point2(tx, ty);

			for(int x = (int) (tx - 1); x <= (int) (tx + 1); x++){
				for(int y = (int) (ty - 1); y <= (int) (ty + 1); y++){
					Point2 l = new Point2(x, y);
					if(!world.getMap().isChunkLoaded(l)) world.getMap().loadChunk(l, true);
				}
			}
			
			for(int x2 = (int) (tx - 2); x2 <= (int) (tx + 2); x2++){
				Point2 a = new Point2(x2, ty+2);
				Point2 b = new Point2(x2, ty-2);
				if(world.getMap().isChunkLoaded(a)) world.getMap().unloadChunk(a, true);
				if(world.getMap().isChunkLoaded(b)) world.getMap().unloadChunk(b, true);
			}
			
			for(int y2 = (int) (ty - 1); y2 <= (int) (ty + 1); y2++){
				Point2 a = new Point2(tx+2, y2);
				Point2 b = new Point2(tx-2, y2);
				if(world.getMap().isChunkLoaded(a)) world.getMap().unloadChunk(a, true);
				if(world.getMap().isChunkLoaded(b)) world.getMap().unloadChunk(b, true);
			}
			
			
		}
		
		world.act(delta);
		for(GameObject o : object_list) o.act(delta);
		
		cam_old.x = GameClient.getGame().getScreenManager().active_screen.camera_main.position.x;
		cam_old.y =	GameClient.getGame().getScreenManager().active_screen.camera_main.position.y;

	}
	
	
	public void update(SpriteBatch batch, float parentAlpha, float delta){
		act(delta);
		draw(batch, parentAlpha);
		
	}
		/*
		//fix
		
		//Calculate Priorities
		if(ticks % PriorityCalculator.TICKS_TO_UPDATE == 0) {
			PriorityCalculator.COUNT_A = PriorityCalculator.COUNT_B = PriorityCalculator.COUNT_C = PriorityCalculator.COUNT_D = PriorityCalculator.COUNT_E = 0;
		}
		
		for(GameObject o : object_list){
			
			if(ticks % PriorityCalculator.TICKS_TO_UPDATE == 0) {
				int p = o.getPriority();
				
				if(p > 4) {
					PriorityCalculator.COUNT_E++;
				} else if(p > 3) {
					PriorityCalculator.COUNT_D++;
				} else if (p > 2) {
					PriorityCalculator.COUNT_C++;
				} else if(p > 1){
					PriorityCalculator.COUNT_B++;
				} else {
					PriorityCalculator.COUNT_A++;
				}
			}
			
			
			if(!o.skip()){
				int p = o.getPriority();
				if(p > 4) {
					PriorityCalculator.LIVE_COUNT_E++;
					if(PriorityCalculator.LIVE_COUNT_E <= PriorityCalculator.DISTRIBUTION_COUNT_E && 
							PriorityCalculator.LIVE_COUNT_E >= PriorityCalculator.DISTRIBUTION_COUNT_E - 1) o.update(batch, parentAlpha, delta);
					
					if(PriorityCalculator.LIVE_COUNT_E >= PriorityCalculator.COUNT_E){
						PriorityCalculator.LIVE_COUNT_E = 0;
					}
					
				} else if(p > 3) {
					PriorityCalculator.LIVE_COUNT_D++;
					if(PriorityCalculator.LIVE_COUNT_D <= PriorityCalculator.DISTRIBUTION_COUNT_D && 
							PriorityCalculator.LIVE_COUNT_D >= PriorityCalculator.DISTRIBUTION_COUNT_D - 1) o.update(batch, parentAlpha, delta);
					
					if(PriorityCalculator.LIVE_COUNT_D >= PriorityCalculator.COUNT_D){
						PriorityCalculator.LIVE_COUNT_D = 0;
					}
					
				} else if (p > 2) {
					PriorityCalculator.LIVE_COUNT_C++;
					if(PriorityCalculator.LIVE_COUNT_C <= PriorityCalculator.DISTRIBUTION_COUNT_C && 
							PriorityCalculator.LIVE_COUNT_C >= PriorityCalculator.DISTRIBUTION_COUNT_C - 1) o.update(batch, parentAlpha, delta);
					
					if(PriorityCalculator.LIVE_COUNT_C >= PriorityCalculator.COUNT_C){
						PriorityCalculator.LIVE_COUNT_C = 0;
					}
					
				} else if(p > 1){
					PriorityCalculator.LIVE_COUNT_B++;
					if(PriorityCalculator.LIVE_COUNT_B <= PriorityCalculator.DISTRIBUTION_COUNT_B && 
							PriorityCalculator.LIVE_COUNT_B >= PriorityCalculator.DISTRIBUTION_COUNT_B - 1) o.update(batch, parentAlpha, delta);
					
					if(PriorityCalculator.LIVE_COUNT_B >= PriorityCalculator.COUNT_B){
						PriorityCalculator.LIVE_COUNT_B = 0;
					}
					
				} else {
					PriorityCalculator.LIVE_COUNT_A++;
					if(PriorityCalculator.LIVE_COUNT_A <= PriorityCalculator.DISTRIBUTION_COUNT_A && 
						PriorityCalculator.LIVE_COUNT_A >= PriorityCalculator.DISTRIBUTION_COUNT_A - 1) o.update(batch, parentAlpha, delta);
					
					if(PriorityCalculator.LIVE_COUNT_A >= PriorityCalculator.COUNT_A){
						PriorityCalculator.LIVE_COUNT_A = 0;
					}
					
				}
				
			}
		} 
		
		if(ticks % PriorityCalculator.TICKS_TO_UPDATE == 0) {
			PriorityCalculator.calculate();
		}
		
		//Ticks measured and reset
		ticks++;
		if(ticks == TICKS_TO_CYCLE) {
			ticks = 0; cycles++;
			if(cycles == Integer.MAX_VALUE) cycles = 0;
		}
		
	}
	*/

	public void dispose() {
		// TODO Auto-generated method stub
		
	}	
	
}

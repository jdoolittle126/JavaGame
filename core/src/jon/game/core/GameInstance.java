package jon.game.core;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import jon.game.debug.Debugger;
import jon.game.entity.Entity;
import jon.game.entity.living.Player;
import jon.game.enums.ScreenType;
import jon.game.screens.GameScreen;
import jon.game.terrain.TerrainMap;
import jon.game.terrain.TerrainMap.MapType;
import jon.game.terrain.TerrainTile;
import jon.game.tools.PriorityCalculator;
import jon.game.tools.ScreenManager;
import jon.game.utils.Point2;

public class GameInstance {
	private ArrayList<GameObject> object_list;
	private int ticks, cycles;
	private int TICKS_TO_CYCLE;
	
	private Sprite backgroundSprite;
	public static BitmapFont font;
	private Player player;
	private TerrainMap map;
	
	public GameInstance(){
		
		object_list = new ArrayList<GameObject>();
		map = new TerrainMap(MapType.fixed);
		font = new BitmapFont(Gdx.files.internal("assets\\fonts\\Calibri.fnt"), Gdx.files.internal("assets\\fonts\\Calibri.png"), false);
	}
	
	public void start(){
		priorities();
		
	}
	
	public void update(float delta, SpriteBatch batch){
		
		for(GameObject o : object_list){
			
			if(ticks == PriorityCalculator.TICKS_TO_UPDATE) {
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
				//FIX
				if(p > 4) {
					PriorityCalculator.LIVE_COUNT_E++;
					if(PriorityCalculator.LIVE_COUNT_E < PriorityCalculator.DISTRIBUTIONS_5) o.update(delta, batch);
					if(PriorityCalculator.LIVE_COUNT_E == PriorityCalculator.COUNT_E) PriorityCalculator.LIVE_COUNT_E = 0;
				} else if(p > 3) {
					PriorityCalculator.LIVE_COUNT_D++;
					if(PriorityCalculator.LIVE_COUNT_D < PriorityCalculator.DISTRIBUTIONS_4) o.update(delta, batch);
					if(PriorityCalculator.LIVE_COUNT_D == PriorityCalculator.COUNT_D) PriorityCalculator.LIVE_COUNT_D = 0;
				} else if (p > 2) {
					PriorityCalculator.LIVE_COUNT_C++;
					if(PriorityCalculator.LIVE_COUNT_C < PriorityCalculator.DISTRIBUTIONS_3) o.update(delta, batch);
					if(PriorityCalculator.LIVE_COUNT_C == PriorityCalculator.COUNT_C) PriorityCalculator.LIVE_COUNT_C = 0;
				} else if(p > 1){
					PriorityCalculator.LIVE_COUNT_B++;
					if(PriorityCalculator.LIVE_COUNT_B < PriorityCalculator.DISTRIBUTIONS_2) o.update(delta, batch);
					if(PriorityCalculator.LIVE_COUNT_B == PriorityCalculator.COUNT_B) PriorityCalculator.LIVE_COUNT_B = 0;
				} else {
					PriorityCalculator.LIVE_COUNT_A++;
					if(PriorityCalculator.LIVE_COUNT_A < PriorityCalculator.DISTRIBUTIONS_1) o.update(delta, batch);
					if(PriorityCalculator.LIVE_COUNT_A == PriorityCalculator.COUNT_A) PriorityCalculator.LIVE_COUNT_A = 0;
				}
				
			}
		} 
		
		if(ticks == PriorityCalculator.TICKS_TO_UPDATE) {
			PriorityCalculator.distribute();
		}
		
		//Ticks measured and reset
		ticks++;
		if(ticks == TICKS_TO_CYCLE) {
			ticks = 0; cycles++;
			if(cycles == Integer.MAX_VALUE) cycles = 0;
		}
		
	}
	

	public void dispose(){
		
	}

	public void priorities() {
		PriorityCalculator.calculate();
		if((long) PriorityCalculator.TICKS_TO_UPDATE * PriorityCalculator.PRIORITY_1 * PriorityCalculator.PRIORITY_2 * PriorityCalculator.PRIORITY_3 * 
				PriorityCalculator.PRIORITY_4 * PriorityCalculator.PRIORITY_5 > Integer.MAX_VALUE) {
			
			TICKS_TO_CYCLE = Integer.MAX_VALUE;
		} else {
			TICKS_TO_CYCLE = PriorityCalculator.TICKS_TO_UPDATE * PriorityCalculator.PRIORITY_1 * PriorityCalculator.PRIORITY_2 * PriorityCalculator.PRIORITY_3 *
					PriorityCalculator.PRIORITY_4 * PriorityCalculator.PRIORITY_5;
		}
	}
	
	
}

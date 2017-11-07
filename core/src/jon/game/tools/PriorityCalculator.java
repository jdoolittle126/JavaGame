package jon.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class PriorityCalculator {
	public static int 
		TICKS_TO_UPDATE, 
		PRIORITY_1, PRIORITY_2, PRIORITY_3, PRIORITY_4, PRIORITY_5, 
		DISTRIBUTIONS_1, DISTRIBUTIONS_2, DISTRIBUTIONS_3, DISTRIBUTIONS_4, DISTRIBUTIONS_5, //milliseconds between updates min
		COUNT_A, COUNT_B, COUNT_C, COUNT_D, COUNT_E,
		LIVE_COUNT_A, LIVE_COUNT_B, LIVE_COUNT_C, LIVE_COUNT_D, LIVE_COUNT_E,
		DISTRIBUTION_COUNT_A, DISTRIBUTION_COUNT_B, DISTRIBUTION_COUNT_C, DISTRIBUTION_COUNT_D, DISTRIBUTION_COUNT_E;
	
	public static void init() {
		TICKS_TO_UPDATE = 1000;
		PRIORITY_1 = PRIORITY_2 = PRIORITY_3 = PRIORITY_4 = PRIORITY_5 = 0;
		DISTRIBUTIONS_1 = 1;
		DISTRIBUTIONS_2 = 50;
		DISTRIBUTIONS_3 = 200;
		DISTRIBUTIONS_4 = 1000;
		DISTRIBUTIONS_5 = 5000;
		COUNT_A = COUNT_B = COUNT_C = COUNT_D = COUNT_E = 0;
		LIVE_COUNT_A = LIVE_COUNT_B = LIVE_COUNT_C = LIVE_COUNT_D = LIVE_COUNT_E = 0;
		DISTRIBUTION_COUNT_A = DISTRIBUTION_COUNT_B = DISTRIBUTION_COUNT_C = DISTRIBUTION_COUNT_D = DISTRIBUTION_COUNT_E = 1;
		
		
	}
	
	public static void calculate() {
		DISTRIBUTION_COUNT_A = 1 + MathUtils.floor((float) ((COUNT_A * Math.floor(Gdx.graphics.getDeltaTime() * 1000)) / DISTRIBUTIONS_1));
		DISTRIBUTION_COUNT_B = 1 + MathUtils.floor((float) ((COUNT_B * Math.floor(Gdx.graphics.getDeltaTime() * 1000)) / DISTRIBUTIONS_2));
		DISTRIBUTION_COUNT_C = 1 + MathUtils.floor((float) ((COUNT_C * Math.floor(Gdx.graphics.getDeltaTime() * 1000)) / DISTRIBUTIONS_3));
		DISTRIBUTION_COUNT_D = 1 + MathUtils.floor((float) ((COUNT_D * Math.floor(Gdx.graphics.getDeltaTime() * 1000)) / DISTRIBUTIONS_4));
		DISTRIBUTION_COUNT_E = 1 + MathUtils.floor((float) ((COUNT_E * Math.floor(Gdx.graphics.getDeltaTime() * 1000)) / DISTRIBUTIONS_5));
		 
	}	

}

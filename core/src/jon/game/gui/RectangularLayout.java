package jon.game.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class RectangularLayout {
	public float spacing;
	public boolean padding;
	public int width, height;
	private float[][] coordsX, coordsY;
	
	
	public RectangularLayout (float spacing, boolean padding, int width, int height) {
		this.spacing = spacing;
		this.padding = padding;
		this.width = width;
		this.height = height;
		coordsX = new float[width][height];
		coordsY = new float[width][height];
	}
	
	
	public void fill(float actor_width, float actor_height) {
		if(padding) {
			coordsX[0][0] = spacing;
			coordsY[0][0] = spacing;
		} else {
			coordsX[0][0] = 0;
			coordsY[0][0] = 0;
		}
		
		for(int x = 1; x < width; x++) {
			for(int y = 1; y < height; y++) {
				coordsX[x][y] = coordsX[x-1][y] +  actor_width + spacing;
				coordsY[x][y] = coordsY[x][y-1] + actor_height + spacing;
			}
		}
	}
	
	public void fill(float actor_width, float actor_height, float startX, float startY) {
		if(padding) {
			coordsX[0][0] = startX+spacing;
			coordsY[0][0] = startY+spacing;
		} else {
			coordsX[0][0] = startX;
			coordsY[0][0] = startY;
		}
		
		for(int x = 1; x < width; x++) {
			for(int y = 1; y < height; y++) {
				coordsX[x][y] = coordsX[x-1][y] +  actor_width + spacing;
				coordsY[x][y] = coordsY[x][y-1] + actor_height + spacing;
			}
		}
	}
	
	public void set(Actor a, int x, int y) {
		
	}
	
	public void rem(int x, int y) {
		
	}
	
	public float getWidth(int x, int y) {
		return coordsX[x][y];
	}
	
	public float getHeight(int x, int y) {
		return coordsY[x][y];
	}
	
	
}

package jon.game.terrain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import jon.game.terrain.MapEditWindow.SelectorType;
import jon.game.utils.Point2;
import jon.tools.gui.MapEditor;

public class EditableTerrainMap extends TerrainMap {
	
	public EditableTerrainMap(MapType type) {
		super(type);
		force_load_all_chunks = true;
		
	}
	
	
	
	@Override
	public void update(float delta, SpriteBatch batch) {
		super.update(delta, batch);
		Material.outline.getTexture().draw(batch);
	
	}



	public void paint(Point2 coords, TerrainBrush brush) {
		
		
	}
	
	public Chunk getChunk(Point2 coords) {
		for(Chunk c : loaded_chunks){
			if(c.getCoords().equals(coords)) return c;
		}
		return null;
	}
	

}

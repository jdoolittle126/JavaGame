package jon.game.terrain;

import jon.game.utils.Point2;

public class EditableTerrainMap extends TerrainMap {

	
	
	public EditableTerrainMap(MapType type) {
		super(type);
		force_load_all_chunks = true;
		
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

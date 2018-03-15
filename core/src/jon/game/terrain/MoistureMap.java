package jon.game.terrain;

import java.util.ArrayList;

import jon.game.core.GameObject;
import jon.game.entities.Tree;
import jon.game.resource.Materials;
import jon.game.utils.Point2;

public class MoistureMap {
	
	SimplexNoise noise;
	float tree_level = 1.3f;
	float tree_size = 64f;
	
	public MoistureMap(){
		noise = new SimplexNoise(100, 0.8, 100); //dees numbers goo!
	}
	
	public ArrayList<GameObject> loadSection(Chunk c){
		ArrayList<GameObject> objects = new ArrayList<GameObject>();
		for(int x = 0; x < Chunk.CHUNK_SIZE; x++) {
			for(int y = 0; y < Chunk.CHUNK_SIZE; y++) {	
				for(int a = 0; a < TerrainTile.DETAIL_PER_SECTION; a++) {
					for(int b = 0; b < TerrainTile.DETAIL_PER_SECTION; b++) {
						if(c.get(x, y).subsections[a][b].getMaterial().equals(Materials.grass)){
							int coordx = ((int)(c.getCoords().x)*Chunk.CHUNK_SIZE*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (x*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (a*TerrainTile.SUBTILE_SIZE);
							int coordy = ((int)(c.getCoords().y)*Chunk.CHUNK_SIZE*TerrainTile.SUBTILE_SIZE*TerrainTile.DETAIL_PER_SECTION) + (y * TerrainTile.SUBTILE_SIZE * TerrainTile.DETAIL_PER_SECTION) + (b * TerrainTile.SUBTILE_SIZE);
							double val = noise.getNoise(coordx, coordy);
							if(val > tree_level){
								objects.add(new Tree(new Point2(coordx, coordy)));
								c.editCollisonMap((x*TerrainTile.DETAIL_PER_SECTION)+a, (y*TerrainTile.DETAIL_PER_SECTION)+b, 2);
							}
						}	
					}
				}
			}
		}
		return objects;
	}
		
}

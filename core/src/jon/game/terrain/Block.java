package jon.game.terrain;

import com.badlogic.gdx.scenes.scene2d.Actor;

import jon.game.enums.BlockType;
import jon.game.utils.Point2;

public class Block extends Actor {
	BlockType type;
	
	public Block(Point2 coords, BlockType type) {
		this.type = type;
		
		
	}

}

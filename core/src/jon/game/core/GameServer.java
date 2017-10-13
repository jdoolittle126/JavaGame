package jon.game.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.esotericsoftware.kryo.*;

public class GameServer extends ApplicationAdapter {
	
    @Override
	public void create() {
    	Kryo c;
    	Server server = new Server();
        server.start();
        server.bind(54555, 54777);
        
        Client client = new Client();
        client.start();
        client.connect(5000, "192.168.0.4", 54555, 54777);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}

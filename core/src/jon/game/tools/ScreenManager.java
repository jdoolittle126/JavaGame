package jon.game.tools;

import java.util.ArrayList;
import java.util.HashMap;

import jon.game.enums.ScreenType;
import jon.game.screens.JScreen;

public class ScreenManager {
	
	//Screen, Active
	private ArrayList<JScreen> screens;
	public JScreen active_screen;
	
	public ScreenManager() {
		screens = new ArrayList<JScreen>();
		active_screen = new JScreen();
	}
	
	public ScreenManager(JScreen... screens) {
		active_screen = new JScreen();
		this.screens = new ArrayList<JScreen>();
		for(JScreen s : screens) {
			this.screens.add(s);
		}
	}
	
	public void update(float delta) {
		active_screen.render(delta);
		active_screen.act(delta);
		active_screen.draw();
	}
	
	public ArrayList<JScreen> getScreen(ScreenType type) {
		ArrayList<JScreen> r = new ArrayList<JScreen>();
		screens.forEach(screen -> {
			if(screen.type.equals(type)) r.add(screen);
		});
		return r;
	}

	public void createStartScreen() {
		active_screen = new JScreen(ScreenType.main_window);
		screens.add(active_screen);
	}
	
}

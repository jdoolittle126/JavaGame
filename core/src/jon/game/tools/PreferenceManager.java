package jon.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferenceManager {
	private Preferences prefs;
	
	
	public PreferenceManager() {
		prefs = Gdx.app.getPreferences("basic");
		
		
	}

}

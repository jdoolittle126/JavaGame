package jon.game.tools;

import java.io.StringWriter;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlWriter;

public class ConfigManager {
	public StringWriter writer = new StringWriter();
	public XmlWriter xmlwrite = new XmlWriter(writer);
	
	public XmlReader xmlread = new XmlReader();
	/*
	 xml.element("meow")
	     .attribute("moo", "cow")
	     .element("child")
	             .attribute("moo", "cow")
	             .element("child")
	                     .attribute("moo", "cow")
	                     .text("XML is like violence. If it doesn't solve your problem, you're not using enough of it.")
	             .pop()
	    .pop()
	.pop();
	*/

}

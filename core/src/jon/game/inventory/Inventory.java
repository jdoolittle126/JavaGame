package jon.game.inventory;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntIntMap.Entry;

import jon.game.core.GameClient;

import com.badlogic.gdx.utils.IntMap;

public class Inventory extends Group {
	Texture background;
	Skin skin;
	protected ArrayList<InventoryItem> inventory = new ArrayList<InventoryItem>();
	private ArrayList<String> text_display;
	
	public Inventory() {
		skin = GameClient.getSkin();
		createInventoryUi();
	}
	
	public void updateTextDisplay() {
		IntIntMap base = new IntIntMap();
		text_display.clear();
		
		for(InventoryItem item : inventory) {
			base.put(item.getId(), base.get(item.getId(), 0)+1);
		}
		
		for(Entry k : base) {
			//change from id to name
			String s = k.key + "\t|\tx" + k.value;
			text_display.add(s);
		}
		
		
	}
	
	private void createInventoryUi() {

		final Table inv = new Table();
        //inv.setSize(850, 600);
		inv.setSize(850, 500);
		inv.bottom();
		
		Table inventory = new Table();
		
        HorizontalGroup group = new HorizontalGroup();      
        final Button tab1 = new TextButton("Tab1", skin, "toggle");
        final Button tab2 = new TextButton("Tab2", skin, "toggle");
        final Button tab3 = new TextButton("Tab3", skin, "toggle");
        group.addActor(tab1);
        group.addActor(tab2);
        group.addActor(tab3);


        
    	Object[] listEntries = {"ExampleItem", "ExampleItem", "ExampleItem", "ExampleItem",
    			"ExampleItem1", "ExampleItem2", "ExampleItem3", "ExampleItem4"};
    	//little string builder for items [TAB] quantity
        List list = new List<>(skin);
        list.setItems(listEntries);
        list.getSelection().setMultiple(false);
		list.getSelection().setRequired(true);
		list.getSelection().setToggle(true);
        
		inventory.add(list).width(200f);
		
		VerticalGroup vg = new VerticalGroup();
		
		Label desc_label = new Label("thing\ndesc. of thing\n----------------\nstuff woot", skin);
		Label tool_label = new Label("look at my cool toolbar", skin);
		vg.space(100f);
		vg.addActor(desc_label);
		vg.addActor(tool_label);
		
		inventory.add(vg).width(200f);
		
		Label status_label = new Label("this is where player stats would go", skin);
		
		inventory.add(status_label).width(200f);
		
        final Table content1 = new Table();
        final Table content2 = new Table();
        final Table content3 = new Table();
        
        content1.add(inventory);
        
        
        Stack content = new Stack();
        content.addActor(content1);
        content.addActor(content2);
        content.addActor(content3);
       

        // Listen to changes in the tab button checked states
        // Set visibility of the tab content to match the checked state
        ChangeListener tab_listener = new ChangeListener(){
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                content1.setVisible(tab1.isChecked());
                content2.setVisible(tab2.isChecked());
                content3.setVisible(tab3.isChecked());
            }
        };
        tab1.addListener(tab_listener);
        tab2.addListener(tab_listener);
        tab3.addListener(tab_listener);

        // Let only one tab button be checked at a time
        ButtonGroup tabs = new ButtonGroup();
        tabs.setMinCheckCount(1);
        tabs.setMaxCheckCount(1);
        tabs.add(tab1);
        tabs.add(tab2);
        tabs.add(tab3);
        
        inv.add(group);
        inv.row();
        inv.add(content).expand().fill();

        this.addActor(inv);
		
	}
	
}

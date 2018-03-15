package jon.game.inventory;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import jon.game.enums.Orientation;
import jon.game.utils.Point2;

public class Inventory extends Actor {
	public Skin skin;
	public Point2 dimensions;
	public Orientation orientation;
	protected ArrayList<InventoryItem> inventory = new ArrayList<InventoryItem>();
	public final InventoryItem slot_blank = new InventoryItem();
	
	public Inventory(){
		initInventory();
	}
	
	
	public void initInventory(){
		clearInv();
		for(int a = 0; a <= dimensions.x * dimensions.y; a++) {
			inventory.add(slot_blank);
		}
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}


	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
	}


	public void swap(Vector2 slot_a, Vector2 slot_b){
		InventoryItem a = rem(slot_a);
		rem(slot_b);
		add(a, slot_b);
		
	}
	
	public boolean add(InventoryItem item, Vector2 slot){
		if(get(slot).equals(slot_blank)){
			inventory.add((int) (slot.x * slot.y), item);
			inventory.remove((int) (slot.x * slot.y) + 1);
			return true;
		}
		return false;
	}
	
	public InventoryItem get(Vector2 slot){
		int slotval = (int) (slot.x * slot.y);
		return inventory.get(slotval);
	}
	
	public InventoryItem get(int slot){
		return inventory.get(slot);
	}
	
	
	public InventoryItem rem(Vector2 slot){
		InventoryItem item = get(slot);
		inventory.remove((int) (slot.x * slot.y));
		return item;
	}
	
	public InventoryItem rem(int slot){
		InventoryItem item = get(slot);
		inventory.remove(slot);
		return item;
	}
	
	public void replace(InventoryItem item, Vector2 slot){
		rem(slot);
		inventory.add((int) (slot.x * slot.y), item);
	}
	
	public void replace(InventoryItem item, int slot){
		rem(slot);
		inventory.add(slot, item);
	}
	
	public void replaceAll(InventoryItem item, Vector2... slots){
		for(Vector2 slot : slots){
			replace(item, slot);
		}
	}
	
	public ArrayList<InventoryItem> remAll(Vector2... slots){
		ArrayList<InventoryItem> items = new ArrayList<InventoryItem>();
		for(Vector2 slot : slots){
			items.add(rem(slot));
		}
		return items;
	}
	
	public void remAll(InventoryItem item){
		inventory.removeIf(i->i.equals(item));
	}
	
	public void clearInv(){
		inventory.clear();
	}
	
	public void clean(){
		initInventory();
	}
	
	public void fill(InventoryItem item){
		for(int a = 0; a <= dimensions.x * dimensions.y; a++){
			try {
				if(inventory.get(a).equals(slot_blank)) replace(item, a);
			} catch(NullPointerException e) {
				inventory.add(item);
			}	
		}
	}
	
	public void fillAll(InventoryItem item){
		clear();
		for(int a = 0; a <= dimensions.x * dimensions.y; a++){
			inventory.add(item);
		}
	}
	
	public void rotateVertical(int shifts){
		
	}
	
	public void rotateHorizontal(int shifts){
		
	}
	
	public void shuffle(){
		
	}
	
	public void expand(Vector2 expansion){
		
	}
}

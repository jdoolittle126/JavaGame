package jon.game.inventory;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Inventory {
	public Vector2 inv_dimensions = new Vector2(0, 0);
	protected ArrayList<InventoryItem> inventory = new ArrayList<InventoryItem>();
	private static final InventoryItem INTENTORY_ITEM_DEFAULT = new InventoryItem();
	public boolean horzOrientation = true;
	
	
	public Inventory(){
		initInventory();
	}
	
	
	public void initInventory(){
		clear();
		for(int a = 0; a <= inv_dimensions.x * inv_dimensions.y; a++){
			inventory.add(INTENTORY_ITEM_DEFAULT);
		}
	}
	
	public void swap(Vector2 slot_a, Vector2 slot_b){
		InventoryItem a = rem(slot_a);
		rem(slot_b);
		add(a, slot_b);
		
	}
	
	public boolean add(InventoryItem item, Vector2 slot){
		if(get(slot).equals(INTENTORY_ITEM_DEFAULT)){
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
	
	public void clear(){
		inventory.clear();
	}
	
	public void clean(){
		initInventory();
	}
	
	public void fill(InventoryItem item){
		for(int a = 0; a <= inv_dimensions.x * inv_dimensions.y; a++){
			try {
				if(inventory.get(a).equals(INTENTORY_ITEM_DEFAULT)) replace(item, a);
			} catch(NullPointerException e) {
				inventory.add(item);
			}	
		}
	}
	
	public void fillAll(InventoryItem item){
		clear();
		for(int a = 0; a <= inv_dimensions.x * inv_dimensions.y; a++){
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

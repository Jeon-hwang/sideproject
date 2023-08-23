package sideproject.java.rpsmember;

public class ItemDTO {
	private int itemId;
	private String itemName;
	private int itemPrice;
	
	public ItemDTO() {}
	
	public ItemDTO(int itemId, String itemName, int itemPrice) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	@Override
	public String toString() {
		return "ItemDTO [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + "]";
	}
	
	
	
}

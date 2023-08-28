package sideproject.java.rpsmember;

import java.util.ArrayList;

public interface ItemDAO {
	abstract public ItemDTO getItemInfo(int itemId);
	
	abstract public ArrayList<ItemDTO> getItemList();
}

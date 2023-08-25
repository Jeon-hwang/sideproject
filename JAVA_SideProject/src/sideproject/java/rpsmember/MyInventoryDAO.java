package sideproject.java.rpsmember;

import java.util.ArrayList;

public interface MyInventoryDAO {
	abstract public int buyItem(RPSMemberDTO dto, ItemDTO idto);
	
	abstract public ArrayList<MyInventoryDTO> myInventory(RPSMemberDTO dto);
	
	abstract public MyInventoryDTO getInventory(RPSMemberDTO dto,ItemDTO idto);
	
	abstract public void usedItem(MyInventoryDTO myidto);
}

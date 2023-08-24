package sideproject.java.rpsmember;

import java.util.ArrayList;

public interface MyInventoryDAO {
	abstract int buyItem(RPSMemberDTO dto, ItemDTO idto);
	
	abstract ArrayList<MyInventoryDTO> myInventory(RPSMemberDTO dto);
}

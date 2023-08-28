package sideproject.java.rpsmember;

import java.util.ArrayList;

public interface BoardDAO {
	abstract public int gameResult(RPSMemberDTO dto, boolean gameResult);
	
	abstract public ArrayList<BoardDTO> leaderBoard(String id);
	
	abstract public ArrayList<BoardDTO> winBoard();
	
	abstract public int usedDiamond(RPSMemberDTO dto);
}

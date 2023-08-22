package sideproject.java.rpsmember;

import java.util.ArrayList;

public interface BoardDAO {
	abstract public void gameResult(RPSMemberDTO dto, boolean gameResult);
	
	abstract public ArrayList<BoardDTO> leaderBoard(String id);
	
	abstract public ArrayList<BoardDTO> winBoard();
}

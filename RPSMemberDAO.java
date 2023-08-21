package sideproject.java.rpsmember;

import java.util.ArrayList;

public interface RPSMemberDAO {
	abstract public int memberInsert(RPSMemberDTO dto);
	
	abstract public boolean memberLogin(String memberId,String memberPassword);
	
	abstract public RPSMemberDTO memberInfo(String memberId);
	
	abstract public int memberUpdate(String pw, String name, String email, RPSMemberDTO dto);
	
	abstract public int memberDelete(RPSMemberDTO dto);
	
	abstract public ArrayList<RPSMemberDTO> pointList();
	
	abstract public RPSMemberDTO updatePoint(int point, RPSMemberDTO dto);
}

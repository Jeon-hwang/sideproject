package sideproject.java.rpsmember;

import java.util.Date;

public class BoardDTO {
	private int boardNum;
	private String memberId;
	private int boardWin;
	private int boardLose;
	private Date boardTime;
	
	public BoardDTO() {}
	
	public BoardDTO(int boardNum,String memberId, int boardWin, int boardLose, Date boardTime) {
		
		this.boardNum = boardNum;
		this.memberId = memberId;
		this.boardWin = boardWin;
		this.boardLose = boardLose;
		this.boardTime = boardTime;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getBoardWin() {
		return boardWin;
	}
	public void setBoardWin(int boardWin) {
		this.boardWin = boardWin;
	}
	public int getBoardLose() {
		return boardLose;
	}
	public void setBoardLose(int boardLose) {
		this.boardLose = boardLose;
	}
	public Date getBoardTime() {
		return boardTime;
	}
	public void setBoardTime(Date boardTime) {
		this.boardTime = boardTime;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "BoardDTO [boardNum=" + boardNum + ", memberId=" + memberId + ", boardWin=" + boardWin + ", boardLose="
				+ boardLose + ", boardTime=" + boardTime + "]";
	}

		
	
	
}

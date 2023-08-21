package sideproject.java.rpsmember;

import java.util.Date;

public class BoardDTO {
	private int boardNum;
	private int boardWin;
	private int boardLose;
	private Date boardTime;
	
	public BoardDTO() {}
	
	public BoardDTO(int boardNum, int boardWin, int boardLose, Date boardTime) {
		super();
		this.boardNum = boardNum;
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
	@Override
	public String toString() {
		return "BoardDTO [boardNum=" + boardNum + ", boardWin=" + boardWin + ", boardLose=" + boardLose + ", boardTime="
				+ boardTime + "]";
	}
		
	
	
}

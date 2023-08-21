package sideproject.java.rpsmember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import oracle.jdbc.OracleDriver;

public class BoardDAOImple implements BoardDAO,RPSOracleQuery {
	private BoardDAOImple instance = null;
	private ArrayList<BoardDTO> list = new ArrayList<>();
	public BoardDAOImple getInstance() {
		if(instance==null) {
			instance = new BoardDAOImple(); 
		}
		return instance;
	}
	
	@Override
	public void gameResult(RPSMemberDTO dto,boolean gameResult) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int winCount = 0;
		int loseCount = 0;
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_BOARD_FIND);
			pstmt.setString(1,dto.getMemberId());
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				winCount = rs.getInt(1);
				loseCount = rs.getInt(2);
			}
			System.out.println(winCount+" "+loseCount);
			pstmt.close();
//			SELECT * FROM(SELECT * FROM RPS_BOARD ORDER BY BOARD_NUMBER DESC) WHERE MEMBER_ID = ? AND ROWNUM = 1;
			pstmt = conn.prepareStatement(SQL_BOARD_INSERT);
			// "INSERT INTO "+ TABLE_BNAME +
			// " VALUES(RPS_BOARD_SEQ.NEXTVAL, ?,?,?,CURRENT_TIMESTAMP)";
			if(gameResult) {
			pstmt.setString(1, dto.getMemberId());
			pstmt.setInt(2, winCount+1);
			pstmt.setInt(3, loseCount);
			}else {
				pstmt.setString(1, dto.getMemberId());
				pstmt.setInt(2, winCount);
				pstmt.setInt(3, loseCount+1);
			}
			pstmt.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}// end gameResult

	@Override
	public ArrayList<BoardDTO> myLeaderBoard(String id) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				DriverManager.registerDriver(new OracleDriver());
				System.out.println("드라이버 로드 성공");
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				System.out.println("DB 연결 성공");
				
				pstmt=conn.prepareStatement(SQL_BOARD_LEADER_BOARD);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int boardNum = rs.getInt(1);
					int boardWin = rs.getInt(3);
					int boardLose = rs.getInt(4);
					Date boardTime = rs.getTimestamp(5);
					BoardDTO bdto = new BoardDTO(boardNum, boardWin, boardLose, boardTime);
					
					list.add(bdto);
					System.out.println(list.toString());
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return list;
	}

}
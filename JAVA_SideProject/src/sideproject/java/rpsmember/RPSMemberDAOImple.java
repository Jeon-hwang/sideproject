package sideproject.java.rpsmember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import oracle.jdbc.OracleDriver;

public class RPSMemberDAOImple implements RPSMemberDAO, RPSOracleQuery {
	private RPSMemberDAOImple instance = null;

	public RPSMemberDAOImple getInstance() {
		if (instance == null) {
			instance = new RPSMemberDAOImple();
		}
		return instance;
	}

	// ---- 싱글톤
	@Override
	public int memberInsert(RPSMemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		int result = 0;

		try {
			DriverManager.registerDriver(new OracleDriver());
//			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_INSERT);
			// "INSERT INTO "+ TABLE_NAME+" VALUES(RPS_SEQ, ?, ?, ?, ?, 1000)";
	
			pstmt.setString(1, dto.getMemberId());
		
			pstmt.setString(2, dto.getMemberPassword());
		
			pstmt.setString(3, dto.getMemberName());
			
			pstmt.setString(4, dto.getMemberEmail());
		try {
			result = pstmt.executeUpdate();
		}catch(SQLIntegrityConstraintViolationException e){
			JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.", "회원 등록 실패", JOptionPane.WARNING_MESSAGE);
		}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {

			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean memberLogin(String memberId, String memberPassword) { // memberId, memberPassword로 DB에 있는 내용 검색 ID로
																			// 검색 Password로 일치 확인
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		boolean result = false;
		try {
			DriverManager.registerDriver(new OracleDriver());
//			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_LOGIN);
			// "SELECT "+COL_MEMBER_PASSWORD+" FROM "+ TABLE_NAME +" WHERE "+ COL_MEMBER_ID
			// +" = ?";

			pstmt.setString(1, memberId); // Id가 틀렸을때를 대비하여 오류를 잡아야함

			rs = pstmt.executeQuery();

			String checkPassword = null;
			try {
				if (rs.next()) {
					checkPassword = rs.getString(1);
				} // end if

				if (checkPassword.equals(memberPassword)) {
					result = true;
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다", "로그인 오류", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "없는 아이디 입니다.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
			}

//			System.out.println(checkPassword); // Id에 맞는 password가 나오는지 확인용
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}// end memberLogin

	@Override
	public RPSMemberDTO memberInfo(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RPSMemberDTO dto = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
//			System.out.println("드라이버 로드 성공2");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("DB 연결 성공2");

			pstmt = conn.prepareStatement(SQL_INFO);
			// "SELECT * FROM "+ TABLE_NAME +" WHERE "+ COL_MEMBER_ID + " = ?";
			pstmt.setString(1, memberId); // Id가 틀렸을때를 대비하여 오류를 잡아야함

			rs = pstmt.executeQuery();

			if (rs.next()) {

				int memberNumber = rs.getInt(1); //
				String memberPassword = rs.getString(3); // PHONE 컬럼
				String memberName = rs.getString(4); // EMAIL 컬럼
				String memberEmail = rs.getString(5); // EMAIL 컬럼
				int memberPoint = rs.getInt(6);

				dto = new RPSMemberDTO(memberNumber, memberId, memberPassword, memberName, memberEmail, memberPoint);

			} // end if

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return dto;
	}// end memberInfo

	@Override
	public int memberUpdate(String pw, String name, String email, RPSMemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			DriverManager.registerDriver(new OracleDriver());
//			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_UPDATE);
			// "UPDATE " + TABLE_NAME + " SET " + COL_MEMBER_PASSWORD + " = ?, "
			// + COL_MEMBER_NAME + " = ?, " + COL_EMAIL + " = ? " + "WHERE " +
			// COL_MEMBER_NUMBER + " = ?";
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setInt(4, dto.getMemberNumber());

			result = pstmt.executeUpdate();

			System.out.println(result + "행이 수정됐습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		return result;
	}// end memberUpdate

	@Override
	public int memberDelete(RPSMemberDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
//			System.out.println("드라이버 로드 성공");

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_DELETE);
			// "DELETE "+ TABLE_NAME + " WHERE " + COL_MEMBER_NUMBER + " = ?";

			pstmt.setInt(1, dto.getMemberNumber());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} // throws
		return result;
	}

	@Override
	public ArrayList<RPSMemberDTO> pointList() {
		ArrayList<RPSMemberDTO> list = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			DriverManager.registerDriver(new OracleDriver());
//			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_POINT_RANKING);

			rs = pstmt.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) { // 레코드가 존재할 때까지 반복
				int number = rs.getInt(1); // CONTACT_ID 컬럼
				String id = rs.getString(2); // NAME 컬럼
				String pw = rs.getString(3); // PHONE 컬럼
				String name = rs.getString(4); // EMAIL 컬럼
				String email = rs.getString(5);
				int point = rs.getInt(6);

				RPSMemberDTO dto = new RPSMemberDTO(number, id, pw, name, email, point);
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}// end pointList

	public RPSMemberDTO updatePoint(int point, RPSMemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			DriverManager.registerDriver(new OracleDriver());
//			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_UPDATE_POINT);
			// "UPDATE " +TABLE_NAME+" SET " + COL_POINT+ " = ? WHERE "+ COL_MEMBER_NUMBER +
			// " = ?";
			// UPDATE TBLENAME SET COLPOINT = ? WHERE COLMEMBERNUMBER = ?
			pstmt.setInt(1, point);
			pstmt.setInt(2, dto.getMemberNumber());
//			System.out.println("성공1?");

			pstmt.executeUpdate();
			pstmt.close(); // 한번 닫아주고 다시연다

			pstmt = conn.prepareStatement(SQL_INFO);
			pstmt.setString(1, dto.getMemberId());
			rs = pstmt.executeQuery();

//			System.out.println("포인트가 업데이트 되었습니다.");

			if (rs.next()) {

				int memberNumber = rs.getInt(1); //
				String memberId = rs.getString(2);
				String memberPassword = rs.getString(3); // PHONE 컬럼
				String memberName = rs.getString(4);
				String memberEmail = rs.getString(5);
				int memberPoint = rs.getInt(6);

				dto = new RPSMemberDTO(memberNumber, memberId, memberPassword, memberName, memberEmail, memberPoint);

			} // end if
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}

}

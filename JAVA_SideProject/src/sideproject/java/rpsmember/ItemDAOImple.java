package sideproject.java.rpsmember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

public class ItemDAOImple implements ItemDAO, RPSOracleQuery {

	private ItemDAOImple instance = null;
	
	public ItemDAOImple getInstance() {
		if(instance == null) {
			instance = new ItemDAOImple();
		}
			return instance;
	}

	@Override
	public ItemDTO getItemInfo(int itemId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemDTO idto = null;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 로드 성공");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");
			
			pstmt = conn.prepareStatement(SQL_ITEM_INFO);
			pstmt.setInt(1, itemId);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String itemName = rs.getString(2);
				int itemPrice = rs.getInt(3);
				idto = new ItemDTO(itemId, itemName, itemPrice);
	
			}
			
			System.out.println(idto.toString());
			
			
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
		
		return idto;
	}
	
	

}

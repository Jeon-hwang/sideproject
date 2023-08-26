package sideproject.java.rpsmember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import oracle.jdbc.OracleDriver;

public class MyInventoryDAOImple implements MyInventoryDAO,RPSOracleQuery {
	private MyInventoryDAOImple instance = null;
	
	public MyInventoryDAOImple getInstance() {
		if(instance ==null) {
			instance = new MyInventoryDAOImple();
		}
		return instance;
	}

	@Override
	public int buyItem(RPSMemberDTO dto, ItemDTO idto) { // 아이템사고 남은 포인트가 반환
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		int itemCount = 0;
		int result = 0;
		try {
			DriverManager.registerDriver(new OracleDriver());
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			
//			System.out.println(dto.toString());
			pstmt = conn.prepareStatement(SQL_UPDATE_POINT);
			if(dto.getMemberPoint()<idto.getItemPrice()) {
				JOptionPane.showMessageDialog(null, "포인트가 부족합니다!", "구매 실패", JOptionPane.WARNING_MESSAGE);
			}else{
			pstmt.setInt(1, dto.getMemberPoint()-idto.getItemPrice());
			pstmt.setInt(2, dto.getMemberNumber());
			
			pstmt.executeUpdate();// 아이템 구매시 포인트 차감된 것을 RPS_MEMBER DB로 넘긴다
			
			pstmt.close();
			
			pstmt = conn.prepareStatement(SQL_ITEM_COUNT);
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, idto.getItemName());
			
			rs=pstmt.executeQuery(); 
			if(rs.next()) {
				itemCount = rs.getInt(1);
			}
//			System.out.println("itemCount = "+ itemCount); // 아이템 수량(Count)를 불러낸다
			
			rs.close();
			pstmt.close();
			
			pstmt = conn.prepareStatement(SQL_ITEM_BUY);
			pstmt.setString(1,dto.getMemberId());
			pstmt.setString(2,idto.getItemName());
			pstmt.setInt(3, itemCount+1);
			
			result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	@Override
	public ArrayList<MyInventoryDTO> myInventory(RPSMemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MyInventoryDTO> list=null;
		try {
			DriverManager.registerDriver(new OracleDriver());
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			
			pstmt= conn.prepareStatement(SQL_MY_INVENTORY);
			pstmt.setString(1, dto.getMemberId());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String itemName = rs.getString(1);
				int itemCount = rs.getInt(2);
				
				MyInventoryDTO myDTO = new MyInventoryDTO(0, dto.getMemberId(), itemName, itemCount, null);
				list.add(myDTO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	@Override
	public MyInventoryDTO getInventory(RPSMemberDTO dto, ItemDTO idto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyInventoryDTO result = null;
		try {
			DriverManager.deregisterDriver(new OracleDriver());

			conn = DriverManager.getConnection(URL,USER,PASSWORD);
	
			pstmt = conn.prepareStatement(SQL_ITEM_COUNT);
			pstmt.setString(1,dto.getMemberId());
			pstmt.setString(2,idto.getItemName());
			
			rs = pstmt.executeQuery();
			
			
//			System.out.println("알에스" +rs.toString());
			if(rs.next()) {
//				System.out.println("이프문 작동을 안하나?");
//				System.out.println(rs.getInt(1));
				int itemCount = rs.getInt(1);
//				System.out.println("여기가 부적합한가");
				System.out.println(itemCount);
				result = new MyInventoryDTO(0, dto.getMemberId(), idto.getItemName(), itemCount, null);
				
				System.out.println(result.toString()); 
			}else {
//				System.out.println("서버에 아예 없을경우");
				result = new MyInventoryDTO(0, dto.getMemberId(), idto.getItemName(), 0, null);
			}
			
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
	}

	@Override
	public void usedItem(MyInventoryDTO myidto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			DriverManager.deregisterDriver(new OracleDriver());
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			
			pstmt = conn.prepareStatement(SQL_ITEM_BUY);
			pstmt.setString(1, myidto.getMemberId());
			pstmt.setString(2, myidto.getItemName());
			pstmt.setInt(3, myidto.getItemCount()-1);
		
			pstmt.executeUpdate();
			System.out.println("DB에 들어가나요?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
	}
	
	
}

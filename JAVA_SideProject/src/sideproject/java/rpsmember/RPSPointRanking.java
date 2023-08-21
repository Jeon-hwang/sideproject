package sideproject.java.rpsmember;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class RPSPointRanking extends JFrame {
	


	private JPanel contentPane;
	private JTable table;
	private RPSMemberDAO dao = new RPSMemberDAOImple().getInstance();
	private DefaultTableModel tableModel;
	private String[] colNames = {"순위","아이디", "이름", "포인트"};
	
	
	public RPSPointRanking() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 649, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tableModel = new DefaultTableModel(colNames, 0) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}; // 각 cell 변경 불가능 
		
		table = new JTable(tableModel);
		table.setBounds(5, 97, 623, 508);
		contentPane.add(table);
		rankingTable();
		
		JLabel lblTitle = new JLabel("포인트별 랭킹");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 18));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(5, 5, 623, 32);
		contentPane.add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 97, 628, 513);
		contentPane.add(scrollPane);
	}//end RPSPointRanking()
	
	private void rankingTable() {
		ArrayList<RPSMemberDTO> list = dao.pointList();
		tableModel.setRowCount(0);
		for(int i = 0; i < list.size(); i++) {
			Object[] records = new Object[colNames.length];
			records[0] = i+1;
			records[1] = list.get(i).getMemberId();
			records[2] = list.get(i).getMemberName();
			records[3] = list.get(i).getMemberPoint();
			tableModel.addRow(records);			
		}
	}
}

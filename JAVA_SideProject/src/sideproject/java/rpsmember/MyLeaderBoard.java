package sideproject.java.rpsmember;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MyLeaderBoard extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private BoardDAO dao = new BoardDAOImple().getInstance();
	private RPSMemberDTO dto;
	private DefaultTableModel tableModel;
	private String[] colNames = {"게임번호","승리횟수", "패배횟수", "게임 시간"};
	private Object[] records = new Object[colNames.length];
	private RPSMyInfo myInfo;
	
	
	public MyLeaderBoard(RPSMyInfo myInfo) {
		this.myInfo = myInfo;
		this.dto = myInfo.getDTO();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("나의 최근 10 게임 전적");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 410, 56);
		contentPane.add(lblNewLabel);
		
		tableModel = new DefaultTableModel(colNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(tableModel);
		table.setBounds(12, 65, 410, 351);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 64, 410, 352);
		contentPane.add(scrollPane);
		leaderBoardTable();
	}


	private void leaderBoardTable() {
		ArrayList<BoardDTO> list = dao.myLeaderBoard(dto.getMemberId());
		tableModel.setRowCount(0);
		for(int i = 0; i < list.size(); i++) {
			records[0] = list.get(i).getBoardNum();
			records[1] = list.get(i).getBoardWin();
			records[2] = list.get(i).getBoardLose();
			records[3] = list.get(i).getBoardTime();
			tableModel.addRow(records);
		}
		
	}
}

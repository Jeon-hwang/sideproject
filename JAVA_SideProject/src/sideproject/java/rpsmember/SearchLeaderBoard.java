package sideproject.java.rpsmember;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SearchLeaderBoard extends JFrame {

	private JLabel lblFoundId;
	private JPanel contentPane;
	private JTextField findIdTF;
	private JTable table;
	private JLabel lblFindId;
	private BoardDAO dao;
	private DefaultTableModel tableModel;
	private String[] header = {"게임번호","승리 횟수","패배 횟수","게임 시간"};
	private ArrayList<BoardDTO> list;
	
	
	
	public SearchLeaderBoard() {
		interfaceGUI();
		
		JButton findBtn = new JButton("검색");
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchLB();
			}
		});
		findBtn.setBounds(321, 12, 66, 39);
		contentPane.add(findBtn);
	}//end SearchLeaderBoard



	private void interfaceGUI() {
		dao = new BoardDAOImple().getInstance();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 443, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFindId = new JLabel("검색할 ID : ");
		lblFindId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFindId.setFont(new Font("굴림", Font.BOLD, 20));
		lblFindId.setBounds(32, 10, 146, 39);
		contentPane.add(lblFindId);
		
		findIdTF = new JTextField();
		findIdTF.setHorizontalAlignment(SwingConstants.LEFT);
		findIdTF.setFont(new Font("굴림", Font.BOLD, 20));
		findIdTF.setBounds(178, 10, 131, 39);
		contentPane.add(findIdTF);
		findIdTF.setColumns(10);
		
		lblFoundId = new JLabel("");
		lblFoundId.setVisible(false);
		lblFoundId.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundId.setFont(new Font("굴림", Font.BOLD, 20));
		lblFoundId.setBounds(12, 59, 403, 39);
		contentPane.add(lblFoundId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 96, 403, 277);
		contentPane.add(scrollPane);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		tableModel = new DefaultTableModel(header, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}; // 각 cell 변경 불가능 ;
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		table.getColumn("게임 시간").setPreferredWidth(150);
		table.getColumn("게임번호").setCellRenderer(celAlignCenter);
		table.getColumn("승리 횟수").setCellRenderer(celAlignCenter);
		table.getColumn("패배 횟수").setCellRenderer(celAlignCenter);
		table.getColumn("게임 시간").setCellRenderer(celAlignCenter);
		
	}//end interfactGUI()



	protected void searchLB() {
		lblFoundId.setVisible(true);
		lblFoundId.setText(findIdTF.getText()+"님의 전적 최근 10게임");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String findId = findIdTF.getText();
		if(list==null) {
		list = dao.leaderBoard(findId);
		}
		tableModel.setRowCount(0);
		
		for(int i=0; i<list.size(); i++) {
			Object[] records = new Object[header.length];
			records[0] = list.get(i).getBoardNum();
			records[1] = list.get(i).getBoardWin();
			records[2] = list.get(i).getBoardLose();
			records[3] = sdf.format(list.get(i).getBoardTime());
			tableModel.addRow(records);
		}
		list=null;
	
	}//end searchLB()
}

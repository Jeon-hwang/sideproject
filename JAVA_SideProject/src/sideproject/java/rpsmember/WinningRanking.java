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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class WinningRanking extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private String[] header = {"순위","아이디","승리횟수","패배횟수"};
	private Object[] records = new Object[header.length];
	private BoardDAO dao = new BoardDAOImple().getInstance();
	
	public WinningRanking() {
		winInterface();
		winBoardTable();
	}
	
	private void winInterface() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("승리 횟수별 랭킹");
		lblTitle.setFont(new Font("굴림", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 0, 484, 54);
		contentPane.add(lblTitle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 48, 484, 413);
		contentPane.add(scrollPane);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		table.getColumn("순위").setCellRenderer(celAlignCenter);
		table.getColumn("아이디").setCellRenderer(celAlignCenter);
		table.getColumn("승리횟수").setCellRenderer(celAlignCenter);
		table.getColumn("패배횟수").setCellRenderer(celAlignCenter);
		
	}//end winInterface()

	private void winBoardTable() {
		ArrayList<BoardDTO> list = dao.winBoard();
		tableModel.setRowCount(0);
		for(int i=0 ; i<list.size(); i++) {
			records[0] = i;
			records[1] = list.get(i).getMemberId();
			records[2] = list.get(i).getBoardWin();
			records[3] = list.get(i).getBoardLose();
			tableModel.addRow(records);
		}
		
	}//end winBoardTable
	
	
}

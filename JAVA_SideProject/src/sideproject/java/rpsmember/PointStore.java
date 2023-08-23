package sideproject.java.rpsmember;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PointStore extends JFrame {

	private JPanel contentPane;
	private RPSMainGUI mainGUI;
	private RPSMemberDTO dto;
	private int myPoint;
	
	public PointStore(RPSMainGUI mainGUI) {
		
		this.mainGUI = mainGUI;
		this.dto = mainGUI.getInfo();
		ItemDAO idao = new ItemDAOImple().getInstance();
		myPoint = dto.getMemberPoint();
		
		ImageIcon shield = new ImageIcon("res/shield.jpg");
		ImageIcon itemDouble = new ImageIcon("res/double.jpg");
		ImageIcon timeSlow = new ImageIcon("res/slowTime.jpg");
		ImageIcon justHand = new ImageIcon("res/oneHand.jpg");
		ImageIcon diamond = new ImageIcon("res/diamond.jpg");
		ImageIcon itemReset = new ImageIcon("res/reset.jpg");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("포인트 상점");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("굴림", Font.BOLD, 18));
		lblTitle.setBounds(12, 10, 104, 33);
		contentPane.add(lblTitle);
		
		JLabel lblMyPoint = new JLabel("내 포인트 : "+myPoint);
		lblMyPoint.setFont(new Font("굴림", Font.BOLD, 14));
		lblMyPoint.setBounds(212, 14, 160, 27);
		contentPane.add(lblMyPoint);
		
		JButton btnShield = new JButton(shield); //방패 아이템
		btnShield.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShield.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemDTO shield = idao.getItemInfo(1001);
				myPoint -= shield.getItemPrice();
				mainGUI.setPoint(myPoint);
				lblMyPoint.setText("내 포인트 : "+myPoint);
			}
		});
		btnShield.setHorizontalAlignment(SwingConstants.CENTER);
		btnShield.setBounds(12, 67, 90, 90);
		contentPane.add(btnShield);
		
		JButton btnDouble = new JButton(itemDouble);
		btnDouble.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDouble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idao.getItemInfo(1002);
			}
		});
		btnDouble.setHorizontalAlignment(SwingConstants.CENTER);
		btnDouble.setBounds(142, 67, 90, 90);
		contentPane.add(btnDouble);
		
		JButton btnTimeSlow = new JButton(timeSlow);
		btnTimeSlow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTimeSlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idao.getItemInfo(1004);
			}
		});
		btnTimeSlow.setHorizontalAlignment(SwingConstants.CENTER);
		btnTimeSlow.setBounds(12, 268, 90, 90);
		contentPane.add(btnTimeSlow);
		
		JButton btnJustHand = new JButton(justHand);
		btnJustHand.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnJustHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idao.getItemInfo(1005);
			}
		});
		btnJustHand.setHorizontalAlignment(SwingConstants.CENTER);
		btnJustHand.setBounds(142, 268, 90, 90);
		contentPane.add(btnJustHand);
		
		JButton btnDiamond = new JButton(diamond);
		btnDiamond.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDiamond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idao.getItemInfo(1003);
			}
		});
		btnDiamond.setHorizontalAlignment(SwingConstants.CENTER);
		btnDiamond.setBounds(267, 67, 90, 90);
		contentPane.add(btnDiamond);
		
		JButton btnReset = new JButton(itemReset);
		btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idao.getItemInfo(1006);
			}
		});
		btnReset.setHorizontalAlignment(SwingConstants.CENTER);
		btnReset.setBounds(267, 268, 90, 90);
		contentPane.add(btnReset);
		
		JLabel shieldTxt = new JLabel("<html><body><center>실드<br>20pt<br>포인트 감소를 한번 막아준다.</center></body></html>");
		shieldTxt.setBounds(12, 164, 90, 94);
		contentPane.add(shieldTxt);
		
		JLabel doubleTxt = new JLabel("<html><body><center>더블<br>20pt<br>포인트를 두배로 얻는다.</center></body></html>");
		doubleTxt.setBounds(142, 164, 90, 94);
		contentPane.add(doubleTxt);
		
		JLabel diamondTxt = new JLabel("<html><body><center>다이아몬드<br>101pt<br>모든 손을 이긴다.</center></body></html>");
		diamondTxt.setBounds(267, 164, 90, 94);
		contentPane.add(diamondTxt);
		
		JLabel timeTxt = new JLabel("<html><body><center>슬로우<br>50pt<br>손을 1초 늦게 낼 수 있다.</center></body></html>");
		timeTxt.setBounds(12, 368, 90, 94);
		contentPane.add(timeTxt);
		
		JLabel JustTxt = new JLabel("<html><body><center>손 금지<br>50pt<br>컴퓨터 손을 한개 봉인한다.</center></body></html>");
		JustTxt.setBounds(142, 368, 90, 94);
		contentPane.add(JustTxt);
		
		JLabel resetTxt = new JLabel("<html><body><center>판 엎기<br>300pt<br>게임 시작 시 포인트로 되돌아 간다.</center></body></html>");
		resetTxt.setBounds(267, 368, 90, 94);
		contentPane.add(resetTxt);
	}
}

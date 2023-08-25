package sideproject.java.rpsmember;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainGame extends JFrame {

	private RPSMainGUI mainGUI;
	private JPanel contentPane;
	private JTextField pointTF;
	private JButton btnStart;
	private GameStart game;
	private RPSMemberDTO dto;
	private RPSMemberDTO backUpdto;
	private int myPoint;
	private JLabel lblMyPoint;
	
	public MainGame(RPSMainGUI mainGUI) {
		this.mainGUI = mainGUI;
		dto = mainGUI.getInfo();
		myPoint = dto.getMemberPoint();
		backUpdto=dto;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 460, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblOnPoint = new JLabel("배팅할 포인트 입력");
		lblOnPoint.setFont(new Font("굴림", Font.BOLD, 14));
		lblOnPoint.setBounds(12, 10, 136, 30);
		contentPane.add(lblOnPoint);

		pointTF = new JTextField();
		pointTF.setFont(new Font("굴림", Font.PLAIN, 14));
		pointTF.setBounds(155, 15, 80, 21);
		contentPane.add(pointTF);
		pointTF.setColumns(10);

		btnStart = new JButton("시작");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					game = new GameStart(MainGame.this);
					game.setVisible(false);
					game.setBounds(0, 50, 444, 281);
					contentPane.add(game);
	

					if (Integer.parseInt(pointTF.getText()) >= 0 && Integer.parseInt(pointTF.getText()) <= 100) {
						
						if (myPoint >= 0 && myPoint > Integer.parseInt(pointTF.getText())) {
							game.setVisible(true); //게임창으로 전환
							game = new GameStart(MainGame.this);
							game.setVisible(false);
							game.setBounds(0, 50, 444, 281);
							contentPane.add(game);
							btnStart.setEnabled(false);
							pointTF.setEnabled(false);
						} else {
							JOptionPane.showMessageDialog(null, "포인트가 부족합니다!", "포인트 입력 오류",
									JOptionPane.WARNING_MESSAGE);

						}
					} else {
						JOptionPane.showMessageDialog(null, "0~100사이의 숫자를 입력하세요", "포인트 입력 오류",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(null, "숫자를 입력하세요", "포인트 입력 오류", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnStart.setBounds(255, 14, 74, 23);
		contentPane.add(btnStart);

		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainGUI.setInfo(dto);
				dispose();
			}
		});
		btnExit.setBounds(341, 14, 74, 23);
		contentPane.add(btnExit);
		
		lblMyPoint = new JLabel("현재 포인트 :" +myPoint);
		lblMyPoint.setFont(new Font("굴림", Font.BOLD, 14));
		lblMyPoint.setBounds(12, 37, 223, 30);
		contentPane.add(lblMyPoint);
	}// end MainGame
	
	public int settingPoint() {
		return Integer.parseInt(pointTF.getText());
	}
	
	public void setInfo(RPSMemberDTO dto) {
		this.dto = dto;
	}
	
	public RPSMemberDTO getBackUp() {
		return backUpdto;
	}
	
	public void isRestart() {
		btnStart.setEnabled(true);
		pointTF.setEnabled(true);
	}
	
	public RPSMemberDTO getInfo() {
		return dto;
	}
	public int getPoint() {
		return myPoint;
	}
	
	public void changePoint(int point) {
		this.myPoint = point;
		lblMyPoint.setText("현재 포인트 :" +myPoint);
	}
}

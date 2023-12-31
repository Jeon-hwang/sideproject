package sideproject.java.rpsmember;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RPSMyInfo extends JFrame {
	
	private boolean isUpdate = true;
	private RPSMemberDTO dto; // mainGUI에서 가져온 dto 내역
	private RPSMainGUI mainGUI;
	private JPanel JPanel;
	private JTextField pwTF;
	private JTextField nameTF;
	private JTextField emailTF;
	private JLabel lblGetName;
	private JLabel lblGetEmail;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnUpdateOn;
	private JButton btnCancle;
	
	public RPSMyInfo(RPSMainGUI mainGUI) {
		this.mainGUI = mainGUI;
		this.dto = mainGUI.getInfo();
		RPSMemberDAO dao = new RPSMemberDAOImple().getInstance();
		
		JPanel = new JPanel();
		setBounds(100, 100, 420, 320);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("내 정보");
		lblTitle.setFont(new Font("돋움", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(12, 10, 96, 38);
		getContentPane().add(lblTitle);
		
		JLabel lblId = new JLabel("아이디");
		lblId.setFont(new Font("굴림", Font.BOLD, 16));
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(22, 58, 86, 33);
		getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("패스워드");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("굴림", Font.BOLD, 16));
		lblPassword.setBounds(22, 93, 86, 33);
		getContentPane().add(lblPassword);
		
		JLabel lblName = new JLabel("이름");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("굴림", Font.BOLD, 16));
		lblName.setBounds(22, 128, 86, 33);
		getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel("이메일");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("굴림", Font.BOLD, 16));
		lblEmail.setBounds(22, 163, 86, 33);
		getContentPane().add(lblEmail);
		
		JLabel lblGetId = new JLabel(dto.getMemberId());
		lblGetId.setHorizontalAlignment(SwingConstants.LEFT);
		lblGetId.setFont(new Font("굴림", Font.BOLD, 16));
		lblGetId.setBounds(108, 58, 86, 33);
		getContentPane().add(lblGetId);
		
		lblGetName = new JLabel(dto.getMemberName());
		lblGetName.setHorizontalAlignment(SwingConstants.LEFT);
		lblGetName.setFont(new Font("굴림", Font.BOLD, 16));
		lblGetName.setBounds(108, 128, 86, 33);
		getContentPane().add(lblGetName);
		
		lblGetEmail = new JLabel(dto.getMemberEmail());
		lblGetEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblGetEmail.setFont(new Font("굴림", Font.BOLD, 16));
		lblGetEmail.setBounds(108, 163, 201, 33);
		getContentPane().add(lblGetEmail);
		
		JLabel lblWinRank = new JLabel("승리 순위");
		lblWinRank.setFont(new Font("굴림", Font.BOLD, 16));
		lblWinRank.setBounds(252, 63, 73, 23);
		getContentPane().add(lblWinRank);
		
		JLabel lblPointRank = new JLabel("포인트 순위");
		lblPointRank.setHorizontalAlignment(SwingConstants.CENTER);
		lblPointRank.setFont(new Font("굴림", Font.BOLD, 16));
		lblPointRank.setBounds(235, 106, 96, 38);
		getContentPane().add(lblPointRank);
		
		
		btnUpdate = new JButton("회원 수정");
		btnUpdate.setVisible(isUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isUpdate(false);
			}
		});
		btnUpdate.setFont(new Font("바탕", Font.BOLD, 14));
		btnUpdate.setBounds(140, 221, 112, 50);
		getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("회원 탈퇴");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result =JOptionPane.showConfirmDialog(JPanel, "정말로 회원탈퇴를 하시겠습니까?","확인",
                        	JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					System.out.println("삭제되었습니다.");
					dao.memberDelete(dto);
					mainGUI.setUpdate(true);
					dispose();
				}else {
					System.out.println("취소되었습니다.");
					
				}
			}
		});
		btnDelete.setFont(new Font("바탕체", Font.BOLD, 14));
		btnDelete.setBounds(270, 221, 112, 50);
		getContentPane().add(btnDelete);
		
		JButton btnGameLB = new JButton("<html><body><center>최근<br>게임 전적</center></body></html>");
		btnGameLB.setFont(new Font("바탕", Font.BOLD, 14));
		btnGameLB.setBounds(20, 221, 106, 50);
		getContentPane().add(btnGameLB);
		
		pwTF = new JPasswordField();
		pwTF.setVisible(!isUpdate);
		pwTF.setBounds(108, 98, 116, 26);
		getContentPane().add(pwTF);
		pwTF.setColumns(10);
		
		nameTF = new JTextField();
		nameTF.setVisible(!isUpdate);
		nameTF.setColumns(10);
		nameTF.setBounds(108, 136, 116, 26);
		getContentPane().add(nameTF);
		
		emailTF = new JTextField();
		emailTF.setVisible(!isUpdate);
		emailTF.setColumns(10);
		emailTF.setBounds(108, 171, 201, 26);
		getContentPane().add(emailTF);
		
		btnUpdateOn = new JButton("수정 완료");
		btnUpdateOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pwTF.getText().isBlank() || nameTF.getText().isBlank() || emailTF.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "모든 빈칸을 채워 주세요", "회원 수정 오류", JOptionPane.WARNING_MESSAGE);
				}else {
				dao.memberUpdate(pwTF.getText(),nameTF.getText(),emailTF.getText(),dto);
				mainGUI.setInfo(dao.memberInfo(lblGetId.getText()));
				mainGUI.setLogin(true);
				lblGetName.setText(nameTF.getText());
				lblGetEmail.setText(emailTF.getText());
				isUpdate(true);
				}
			}
		});
		btnUpdateOn.setFont(new Font("바탕", Font.BOLD, 14));
		btnUpdateOn.setBounds(140, 221, 112, 50);
		getContentPane().add(btnUpdateOn);
		
		btnCancle = new JButton("수정 취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isUpdate(true);
			}
		});
		btnCancle.setFont(new Font("바탕체", Font.BOLD, 14));
		btnCancle.setBounds(270, 221, 112, 50);
		getContentPane().add(btnCancle);
		
		JLabel lblPRankNum = new JLabel("");
		lblPRankNum.setFont(new Font("굴림", Font.BOLD, 14));
		lblPRankNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblPRankNum.setBounds(335, 106, 57, 38);
		getContentPane().add(lblPRankNum);
		ArrayList<RPSMemberDTO> list = dao.pointList();
		for(int i= 0; i<list.size();i++) {
			if(list.get(i).getMemberNumber()==dto.getMemberNumber()) {
				lblPRankNum.setText(Integer.toString(i+1)+"위");  
			}
		}
		
	}//end RPSMyInfo
	
	public void isUpdate(boolean isUpdate) {
		this.isUpdate=isUpdate;
		lblGetName.setVisible(isUpdate);
		lblGetEmail.setVisible(isUpdate);
		pwTF.setVisible(!isUpdate);
		nameTF.setVisible(!isUpdate);
		emailTF.setVisible(!isUpdate);
		btnUpdate.setVisible(isUpdate);
		btnDelete.setVisible(isUpdate);
		btnUpdateOn.setVisible(!isUpdate);
		btnCancle.setVisible(!isUpdate);
		
	}
}//end RPSMyInfo

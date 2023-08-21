package sideproject.java.rpsmember;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RPSLoginGUI extends JFrame {

	private RPSMainGUI mainGUI;
	private JPanel contentPanel;
	private JTextField idTF;
	private JTextField passwordTF;

	public RPSLoginGUI(RPSMainGUI mainGUI) {
		this.mainGUI = mainGUI;

		RPSMemberDAO dao = new RPSMemberDAOImple().getInstance();

		contentPanel = new JPanel();
		setBounds(100, 100, 300, 232);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JButton btnMemberInsert = new JButton("회원등록");
		btnMemberInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMemberInsert.setEnabled(false);// 프레임 생성시 비활성화
				RPSMemberInsertGUI insert = new RPSMemberInsertGUI();
				insert.setVisible(true);

				insert.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosed(java.awt.event.WindowEvent windowEvent) {
						btnMemberInsert.setEnabled(true);
					}// 해당 프레임을 종료시 버튼을 다시 활성화
				});
			}
		});
		btnMemberInsert.setFont(new Font("굴림", Font.BOLD, 16));
		btnMemberInsert.setBounds(25, 120, 106, 44);
		getContentPane().add(btnMemberInsert);

		JLabel lblId = new JLabel("아이디");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("굴림", Font.BOLD, 18));
		lblId.setBounds(27, 28, 72, 33);
		getContentPane().add(lblId);

		JLabel lblPassword = new JLabel("패스워드");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("굴림", Font.BOLD, 18));
		lblPassword.setBounds(27, 77, 84, 33);
		getContentPane().add(lblPassword);

		idTF = new JTextField();
		idTF.setBounds(130, 28, 131, 33);
		getContentPane().add(idTF);
		idTF.setColumns(10);

		passwordTF = new JPasswordField();
		passwordTF.setColumns(10);
		passwordTF.setBounds(130, 78, 131, 33);
		getContentPane().add(passwordTF);

		JButton btnLogin = new JButton("로그인");

		btnLogin.addActionListener(new ActionListener() {
			// 현재 로그인에서의 오류 Id가 맞지 않으면 오류창이 나온다
			public void actionPerformed(ActionEvent e) {
			if(idTF.getText().isBlank()||passwordTF.getText().isBlank()) {
				JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 입력하세요", "로그인 오류", JOptionPane.WARNING_MESSAGE);
			}else {
				if (dao.memberLogin(idTF.getText(), passwordTF.getText())) {
//					if(dao.memberLogin("test2","test")) { // 일단 test2로 시험중
							mainGUI.setInfo(dao.memberInfo(idTF.getText())); // 순서상 데이터를 먼저 보낸 후 버튼 활성화를 한다
							mainGUI.setLogin(true); // 메인에있는 로그인여부를 true로 바꾸고 메뉴들을 출력한다
							dispose();
						}
			}
						
			}
		});
		btnLogin.setFont(new Font("굴림", Font.BOLD, 16));
		btnLogin.setBounds(155, 120, 106, 44);
		getContentPane().add(btnLogin);

	}

}

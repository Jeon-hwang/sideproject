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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class RPSMemberInsertGUI extends JFrame {
	private JTextField idTF;
	private JTextField pwTF;
	private JTextField nameTF;
	private JTextField emailTF;
	private JPanel contentPanel;
	public RPSMemberInsertGUI() {
		
		
		RPSMemberDAO dao = new RPSMemberDAOImple().getInstance();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 종료 버튼(상단의 X버튼) 옵션 설정
															// 상단 종료버튼으로 종료 안되게 해놓음
		setBounds(100, 100, 350, 330);
		contentPanel = new JPanel(); // frame.getContentPane()과 동일
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("아이디 :");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("굴림", Font.BOLD, 18));
		lblId.setBounds(26, 10, 108, 37);
		getContentPane().add(lblId);
		
		JLabel lblPassword = new JLabel("패스워드 :");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("굴림", Font.BOLD, 18));
		lblPassword.setBounds(23, 57, 97, 34);
		getContentPane().add(lblPassword);
		
		JLabel lblName = new JLabel("이름 :");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("굴림", Font.BOLD, 18));
		lblName.setBounds(52, 104, 68, 34);
		getContentPane().add(lblName);
		
		JLabel lblEmail = new JLabel("이메일 :");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("굴림", Font.BOLD, 18));
		lblEmail.setBounds(26, 151, 108, 34);
		getContentPane().add(lblEmail);
		
		JLabel lblNewLabel = new JLabel("가입시 1000포인트 자동지급");
		lblNewLabel.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 198, 226, 31);
		getContentPane().add(lblNewLabel);
		
		JButton btnInsert = new JButton("등록");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(idTF.getText().isBlank() || pwTF.getText().isBlank() || nameTF.getText().isBlank() || emailTF.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "모든 빈칸을 채워주세요", "회원가입 오류", JOptionPane.WARNING_MESSAGE);
				}else {
				String memberId = idTF.getText();
				String memberPassword = pwTF.getText();
				String memberName = nameTF.getText();
				String memberEmail= emailTF.getText();
				
				RPSMemberDTO dto = new RPSMemberDTO(0, memberId, memberPassword, memberName, memberEmail, 1000);
				int result = dao.memberInsert(dto);
				
				if(result == 1) {
					dispose();
				}
				}
			}
		});
		btnInsert.setFont(new Font("굴림", Font.BOLD, 16));
		btnInsert.setBounds(37, 242, 97, 37);
		getContentPane().add(btnInsert);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setFont(new Font("굴림", Font.BOLD, 16));
		btnCancle.setBounds(200, 242, 97, 37);
		getContentPane().add(btnCancle);
		
		idTF = new JTextField();
		idTF.setBounds(140, 13, 157, 34);
		getContentPane().add(idTF);
		idTF.setColumns(10);
		
		pwTF = new JPasswordField();
		pwTF.setColumns(10);
		pwTF.setBounds(140, 57, 157, 34);
		getContentPane().add(pwTF);
		pwTF.selectAll();
		
		nameTF = new JTextField();
		nameTF.setColumns(10);
		nameTF.setBounds(140, 104, 157, 34);
		getContentPane().add(nameTF);
		
		emailTF = new JTextField();
		emailTF.setColumns(10);
		emailTF.setBounds(140, 153, 157, 34);
		getContentPane().add(emailTF);
	}

	

	
}// end RPSMemberInserGUI

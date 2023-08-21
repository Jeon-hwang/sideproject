package sideproject.java.rpsmember;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import sideproject.java.rpsgame.MainGame;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RPSMainGUI {
	private RPSLoginGUI gameLogin;
	private RPSMemberDTO dto;// 로그인 정보 저장
	private JFrame frame;	
	private JLabel lblMyPoint;
	private JLabel lblHello;
	private boolean isLogin = false;
	private JButton btnLogin;
	private JButton btnLogout;
	private JButton btnMyInfo;
	private RPSMyInfo myInfo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RPSMainGUI window = new RPSMainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public RPSMainGUI() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 455, 315);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblMyPoint = new JLabel("");
		lblMyPoint.setBounds(141, 39, 151, 23);
		frame.getContentPane().add(lblMyPoint);
		
		lblHello = new JLabel("");
		lblHello.setBounds(141, 8, 151, 26);
		frame.getContentPane().add(lblHello);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnLogin.setEnabled(isLogin);
				gameLogin = new RPSLoginGUI(RPSMainGUI.this); 
				gameLogin.setVisible(true); // 로그인 창을 띄운다
				
				gameLogin.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosed(java.awt.event.WindowEvent windowEvent) {
						btnLogin.setEnabled(true);
					}// 해당 프레임을 종료시 버튼을 다시 활성화
				});
			}
		});
		
		btnLogin.setBounds(330, 10, 97, 23);
		frame.getContentPane().add(btnLogin);
		
		JLabel mainTitle = new JLabel("가위바위보!");
		mainTitle.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		mainTitle.setBounds(12, 10, 104, 32);
		frame.getContentPane().add(mainTitle);
		
		JLabel lblVersion = new JLabel("Ver 0.1");
		lblVersion.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblVersion.setBounds(12, 40, 62, 20);
		frame.getContentPane().add(lblVersion);
		
		btnMyInfo = new JButton("내 정보");
		btnMyInfo.setVisible(isLogin);
		btnMyInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMyInfo.setEnabled(false);
				myInfo = new RPSMyInfo(RPSMainGUI.this);
				myInfo.setVisible(true);
				myInfo.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosed(java.awt.event.WindowEvent windowEvent) {
						btnMyInfo.setEnabled(true);
					}// 해당 프레임을 종료시 버튼을 다시 활성화
				});
			}
		});
		btnMyInfo.setBounds(330, 41, 97, 23);
		frame.getContentPane().add(btnMyInfo);
		
		
		
		btnLogout = new JButton("로그아웃");
		btnLogout.setVisible(false);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isLogin = false;
				btnLogin.setVisible(!isLogin);
				btnLogout.setVisible(isLogin);
				btnMyInfo.setVisible(isLogin);
				lblHello.setText("로그아웃 되었습니다.");
				lblMyPoint.setText(null);
				dto = null;
				if(myInfo!=null) {
					myInfo.setVisible(isLogin);
					btnMyInfo.setEnabled(!isLogin);
				}
			}
		});
		btnLogout.setBounds(330, 10, 97, 23);
		frame.getContentPane().add(btnLogout);
		
		
		
		JButton btnLBPerson = new JButton("<html><body><center>다른사람<br>최근 전적</center></body></html>");
		btnLBPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLBPerson.setFont(new Font("굴림", Font.BOLD, 18));
		btnLBPerson.setBounds(155, 109, 117, 58);
		frame.getContentPane().add(btnLBPerson);
		
		
		
		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("굴림", Font.BOLD, 18));
		btnExit.setBounds(284, 186, 117, 58);
		frame.getContentPane().add(btnExit);
		
		JButton btnGameStart = new JButton("게임 시작");
		btnGameStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dto!=null) {
					frame.setVisible(false);
					MainGame mainGame = new MainGame(RPSMainGUI.this);
					mainGame.setVisible(true);
					mainGame.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosed(java.awt.event.WindowEvent windowEvent) {
							lblMyPoint.setText("내 포인트 : "+mainGame.getPoint());
							frame.setVisible(true);
						}// 해당 프레임을 종료시 버튼을 다시 활성화
					});
				}else {
					JOptionPane.showMessageDialog(null, "로그인을 해주세요","실행 오류",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnGameStart.setFont(new Font("굴림", Font.BOLD, 18));
		btnGameStart.setBounds(26, 109, 117, 58);
		frame.getContentPane().add(btnGameStart);
		
		JButton btnWinRank = new JButton("<html><body><center>승리 횟수<br>랭킹</center></body></html>");
		btnWinRank.setFont(new Font("굴림", Font.BOLD, 18));
		btnWinRank.setBounds(26, 186, 117, 58);
		frame.getContentPane().add(btnWinRank);
		
		JButton btnPointRank = new JButton("<html><body><center>포인트별<br> 랭킹</center></body></html>");
		btnPointRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPointRank.setEnabled(false);
				RPSPointRanking pointRanking = new RPSPointRanking();
				pointRanking.setVisible(true);
				
				pointRanking.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosed(java.awt.event.WindowEvent windowEvent) {
						btnPointRank.setEnabled(true);
					}// 해당 프레임을 종료시 버튼을 다시 활성화
				});
			}
		});
		btnPointRank.setFont(new Font("굴림", Font.BOLD, 18));
		btnPointRank.setBounds(155, 186, 117, 58);
		frame.getContentPane().add(btnPointRank);
	} // end initialize()
	
	public void setCancle(boolean cancle) {
		isLogin = cancle;
		btnLogin.setEnabled(isLogin);
	} //end setCancle
	
	
	 public void setLogin(boolean login) {
	        isLogin = login;
	        btnLogin.setVisible(!isLogin);
	        btnLogin.setEnabled(isLogin);
	        btnLogout.setVisible(isLogin);
	        btnMyInfo.setVisible(isLogin);
	        lblMyPoint.setText("내 포인트 : "+dto.getMemberPoint());
			lblHello.setText(dto.getMemberName()+"님 반갑습니다"); //get 메서드를 추가해서 id를 넣도록
	    } // end setLogin
	 
	 public void setUpdate(boolean update) {
		 isLogin = update;
		 btnLogin.setVisible(isLogin);
	     btnLogin.setEnabled(!isLogin);
	     btnLogout.setVisible(!isLogin);
	     btnMyInfo.setVisible(!isLogin);
	     lblMyPoint.setText(null);
		 lblHello.setText("존재하지 않는 회원 입니다"); 
		 dto=null;
	 } //end setUpdate
	/*public boolean isLogin() {
		return isLogin;
	}*/
	
	public void setPoint(int point) {
		this.dto.setMemberPoint(point);
	} 
	public int getPoint() {
		return dto.getMemberPoint();
	}
	
	public void setInfo(RPSMemberDTO dto) {
		this.dto = dto;
	} 
	
	public RPSMemberDTO getInfo() {
		return dto;
	}
}

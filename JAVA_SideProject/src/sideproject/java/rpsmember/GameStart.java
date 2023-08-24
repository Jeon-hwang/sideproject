package sideproject.java.rpsmember;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameStart extends JPanel {
	private int myChoice = 0;
	private JButton btnSissor;
	private JButton btnRock;
	private JButton btnPaper;
	private JButton btnRestart;
	private static final String[] HANDS = { "가위", "바위", "보" };
	private int count = 1;
	private JLabel lblComHands;
	private JLabel lblResult;
	private int comChoice = -1;
	private RPSMemberDTO dto;
	private RPSMemberDAO dao;
	private BoardDAO bdao;
	private ItemDTO idto;

	private MainGame mainGame;
	private int myPoint;
	private int settingPoint;
	private JButton btnCancleItem;

	public GameStart(MainGame mainGame) {
		dao = new RPSMemberDAOImple();
		bdao = new BoardDAOImple();
		this.mainGame = mainGame;

		settingPoint = mainGame.settingPoint();
		dto = mainGame.getInfo();
		myPoint = dto.getMemberPoint();
//		System.out.println(dto.toString());
		setLayout(null);

		lblComHands = new JLabel("손을 선택하세요.");
		lblComHands.setHorizontalAlignment(SwingConstants.CENTER);
		lblComHands.setFont(new Font("돋움", Font.BOLD, 18));
		lblComHands.setBounds(0, 10, 444, 34);
		add(lblComHands);

		btnSissor = new JButton("가위");
		btnSissor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myChoice = 1;
				ComputerHands();

			}
		});
		btnSissor.setFont(new Font("바탕", Font.BOLD, 20));
		btnSissor.setBounds(30, 158, 100, 100);
		add(btnSissor);

		btnRock = new JButton("바위");
		btnRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myChoice = 2;
				ComputerHands();

			}
		});
		btnRock.setFont(new Font("바탕", Font.BOLD, 20));
		btnRock.setBounds(173, 158, 100, 100);
		add(btnRock);

		btnPaper = new JButton("보");
		btnPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myChoice = 3;
				ComputerHands();

			}
		});
		btnPaper.setFont(new Font("바탕", Font.BOLD, 20));
		btnPaper.setBounds(310, 158, 100, 100);
		add(btnPaper);

		lblResult = new JLabel("");
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("돋움", Font.BOLD, 16));
		lblResult.setBounds(0, 66, 444, 34);
		add(lblResult);

		btnRestart = new JButton("다시 시작");
		btnRestart.setVisible(false);
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRestart.setVisible(false);
				mainGame.isRestart();
				setVisible(false);
			}
		});
		btnRestart.setFont(new Font("바탕", Font.BOLD, 26));
		btnRestart.setBounds(30, 158, 380, 100);
		add(btnRestart);

		JButton btnUseItem = new JButton("아이템 사용");
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemList itemList = new ItemList(GameStart.this);
				itemList.setVisible(true);
				btnUseItem.setEnabled(false);

				itemList.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosed(java.awt.event.WindowEvent windowEvent) {
						if(idto==null) {
						btnUseItem.setEnabled(true);
						}else {
						btnCancleItem.setVisible(true);}
					}
				});

			}
		});
		btnUseItem.setBounds(332, 9, 106, 23);
		add(btnUseItem);
		
		btnCancleItem = new JButton("사용 취소");
		btnCancleItem.setVisible(false);
		btnCancleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idto = null;
				btnCancleItem.setVisible(false);
				btnUseItem.setEnabled(true);
			}
		});
		btnCancleItem.setBounds(332, 42, 106, 23);
		add(btnCancleItem);

	}// end GameStart()

	private void SissorHand() {

		if (comChoice == 0) { // 가위일 경우
			lblResult.setText("무승부 입니다 다시하세요");
		} else if (comChoice == 1) { // 바위일 경우
			lblResult.setText("졌습니다 ㅠㅠ");
			myPoint -= settingPoint;
			mainGame.changePoint(myPoint);
			completeGame();
			bdao.gameResult(dto, false);

		} else { // 보 일 경우
			lblResult.setText("이겼습니다 !!");
			myPoint += settingPoint;
			mainGame.changePoint(myPoint);
			completeGame();
			bdao.gameResult(dto, true);
		}
	} // end SissorHand()

	private void RockHand() {
		if (comChoice == 0) { // 가위일 경우
			lblResult.setText("이겼습니다 !!");
			myPoint += settingPoint;
			mainGame.changePoint(myPoint);
			completeGame();
			bdao.gameResult(dto, true);
		} else if (comChoice == 1) { // 바위일 경우
			lblResult.setText("무승부 입니다 다시하세요");
		} else { // 보 일 경우
			lblResult.setText("졌습니다 ㅠㅠ");
			myPoint -= settingPoint;
			mainGame.changePoint(myPoint);
			completeGame();
			bdao.gameResult(dto, false);
		}
	} // end RockHand()

	private void PaperHand() {
		if (count == 4) {
			if (comChoice == 0) { // 가위일 경우
				lblResult.setText("졌습니다 ㅠㅠ");
				myPoint -= settingPoint;
				mainGame.changePoint(myPoint);
				completeGame();
				bdao.gameResult(dto, false);
			} else if (comChoice == 1) { // 바위일 경우
				lblResult.setText("이겼습니다 !!");
				myPoint += settingPoint;
				mainGame.changePoint(myPoint);
				completeGame();
				bdao.gameResult(dto, true);
			} else { // 보 일 경우
				lblResult.setText("무승부 입니다 다시하세요");
			}
		}
	} // end PaperHand()

	private void ComputerHands() {
		btnPaper.setEnabled(false);
		btnRock.setEnabled(false);
		btnSissor.setEnabled(false);
		count = 1;
		comChoice = (int) (Math.random() * 3);
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				if (count <= 3) {
					lblComHands.setText(Integer.toString(count));
					count++;

				} else if (count == 4) {
					lblComHands.setText("컴퓨터의 손 : " + HANDS[comChoice]);

					switch (myChoice) {
					case 1:
						SissorHand();
						break;
					case 2:
						RockHand();
						break;
					case 3:
						PaperHand();
						break;
					}
					btnPaper.setEnabled(true);
					btnRock.setEnabled(true);
					btnSissor.setEnabled(true);

//					System.out.println(myPoint);
					dto = dao.updatePoint(myPoint, dto);
					mainGame.setInfo(dto);
//					System.out.println(dto.toString());
					myChoice = 0;
					time.cancel();
				}

			}
		}, 0, 1200);
	} // end ComputerHands()
	
	private void completeGame() {
		btnPaper.setVisible(false);
		btnRock.setVisible(false);
		btnSissor.setVisible(false);
		btnRestart.setVisible(true);
	}
	
	public void setItem(ItemDTO idto) {
		this.idto = idto;
		
	}
}// end GameStart;

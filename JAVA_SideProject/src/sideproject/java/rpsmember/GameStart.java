package sideproject.java.rpsmember;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private JButton btnUseItem;
	private JButton btnCancleItem;
	private static final String[] HANDS = { "가위", "바위", "보" };
	private int count = 1;
	private JLabel lblComHands;
	private JLabel lblResult;
	private int comChoice = -1;
	private int comChoiceSec;
	private RPSMemberDTO dto;
	private RPSMemberDTO backupDto;
	private RPSMemberDAO dao;
	private BoardDAO bdao;
	private MyInventoryDTO myidto;
	private MyInventoryDAO myidao;
	private ItemDAO idao = new ItemDAOImple().getInstance();
	private ArrayList<ItemDTO> iList = idao.getItemList();
	private int gameResult = 0; // 0 무승부 1 승리 2 패배

	private MainGame mainGame;
	private int myPoint;
	private int settingPoint;
	private JLabel lblSetItem;
	private JButton btnSissorSlow;
	private JButton btnRockSlow;
	private JButton btnPaperSlow;
	private JLabel lblChangeHand;

	public GameStart(MainGame mainGame) {
		dao = new RPSMemberDAOImple().getInstance();
		bdao = new BoardDAOImple().getInstance();
		myidao = new MyInventoryDAOImple().getInstance();
		this.mainGame = mainGame;

//		System.out.println("리스트 0"+list.get(0).toString()); 
//		System.out.println("리스트 1"+list.get(1).toString()); 
//		System.out.println("리스트 2"+list.get(2).toString()); 

		settingPoint = mainGame.settingPoint();
		dto = mainGame.getInfo();
		backupDto = mainGame.getBackUp();
//		System.out.println("backupDto :" + backupDto.getMemberPoint());
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

		btnUseItem = new JButton("아이템 사용");
		btnUseItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemList itemList = new ItemList(GameStart.this);
				itemList.setVisible(true);
				btnUseItem.setEnabled(false);

				itemList.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosed(java.awt.event.WindowEvent windowEvent) {
						if (myidto == null) {
							btnUseItem.setEnabled(true);
						} else {
							btnCancleItem.setVisible(true);
							lblSetItem.setText("사용된 아이템 : " + myidto.getItemName());
							lblSetItem.setVisible(true);
							if (myidto != null && myidto.getItemName().equals(iList.get(4).getItemName())) { // 손 봉인을 쓸 경우
								comChoice = (int) (Math.random() * 3);
								lblResult.setText("컴퓨터의 " + HANDS[comChoice] + "가 봉인 되었습니다!");
							}	
						}
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
				if (myidto.getItemName().equals(iList.get(4).getItemName())) {
					comChoice = -1;
				}
				myidto = null;
				btnCancleItem.setVisible(false);
				btnUseItem.setEnabled(true);
				lblSetItem.setText("사용된 아이템 없음");
			}
		});
		btnCancleItem.setBounds(332, 42, 106, 23);
		add(btnCancleItem);

		lblSetItem = new JLabel("사용된 아이템 없음");
		lblSetItem.setFont(new Font("굴림", Font.BOLD, 12));
		lblSetItem.setBounds(10, 42, 154, 23);
		add(lblSetItem);

		btnSissorSlow = new JButton("가위");
		btnSissorSlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myChoice = 1;
				lblChangeHand.setText(btnSissorSlow.getText()+"로 바꿨습니다!");
			}
		});
		btnSissorSlow.setVisible(false);
		btnSissorSlow.setFont(new Font("바탕", Font.BOLD, 20));
		btnSissorSlow.setBounds(30, 158, 100, 100);
		add(btnSissorSlow);

		btnRockSlow = new JButton("바위");
		btnRockSlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myChoice = 2;
				lblChangeHand.setText(btnRockSlow.getText()+"로 바꿨습니다!");
			}
		});
		btnRockSlow.setVisible(false);
		btnRockSlow.setFont(new Font("바탕", Font.BOLD, 20));
		btnRockSlow.setBounds(173, 158, 100, 100);
		add(btnRockSlow);

		btnPaperSlow = new JButton("보");
		btnPaperSlow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myChoice = 3;
				lblChangeHand.setText(btnPaperSlow.getText()+"로 바꿨습니다!");
			}
		});
		btnPaperSlow.setVisible(false);
		btnPaperSlow.setFont(new Font("바탕", Font.BOLD, 20));
		btnPaperSlow.setBounds(310, 158, 100, 100);
		add(btnPaperSlow);
		
		lblChangeHand = new JLabel("");
		lblChangeHand.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeHand.setFont(new Font("돋움", Font.BOLD, 12));
		lblChangeHand.setBounds(0, 114, 444, 34);
		add(lblChangeHand);

	}// end GameStart()

	private void ComputerHands() {
		btnPaper.setEnabled(false);
		btnRock.setEnabled(false);
		btnSissor.setEnabled(false);
		btnUseItem.setEnabled(false);
		btnCancleItem.setEnabled(false);

		count = 1;

		if (myidto != null && myidto.getItemName().equals(iList.get(4).getItemName())) { // 손 봉인을 쓸 경우
			usedJustHand();
		} else if(myidto != null && myidto.getItemName().equals(iList.get(3).getItemName())) { // 슬로우를 쓸 경우
			usedSlow();
		}else {
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
							SissorHand(comChoice);
							break;
						case 2:
							RockHand(comChoice);
							break;
						case 3:
							PaperHand(comChoice);
							break;
						}
						btnPaper.setEnabled(true);
						btnRock.setEnabled(true);
						btnSissor.setEnabled(true);

						if (myidto != null) {// 아이템이 선택 되어있다면
							if (myidto.getItemName().equals(iList.get(0).getItemName())) { // 실드
								usedShield(gameResult);
							} else if (myidto.getItemName().equals(iList.get(1).getItemName())) { // 더블
								usedDoubleUp(gameResult);
							} else if (myidto.getItemName().equals(iList.get(2).getItemName())) { // 다이아모든
								usedDiamond(gameResult);
							} else if (myidto.getItemName().equals(iList.get(5).getItemName())) { // 판 엎기
								usedReset();
							}
						}

						if (gameResult != 0) {// 무승부가 아닐때만 컴플리트 게임 작동
//						System.out.println("이거 실행 되나? 게임 완료");
							completeGame();
							if (myidto != null) {
//							System.out.println("요게 실행이 안되나? 아이템 사용");
								usedItem();
								gameResult = 0;

							}
						}
//		System.out.println(myPoint);
						dto = dao.updatePoint(myPoint, dto); // 게임 결과 이후 변경 된 포인트를 DB에 옮긴후 바로 DB에서 꺼내와 DTO에 저장한다.
						mainGame.setInfo(dto);
//		System.out.println(dto.toString());
						myChoice = 0;
						time.cancel();
					}

				}

			}, 0, 1200);
		}
	} // end ComputerHands()

	private void SissorHand(int comChoice) {

		if (comChoice == 0) { // 가위일 경우
			gameResult = 0;
			lblResult.setText("무승부 입니다 다시하세요");
		} else if (comChoice == 1) { // 바위일 경우
			lblResult.setText("졌습니다 ㅠㅠ");
			myPoint -= settingPoint;
			mainGame.changePoint(myPoint);
			gameResult = bdao.gameResult(dto, false);

		} else { // 보 일 경우
			lblResult.setText("이겼습니다 !!");
			myPoint += settingPoint;
			mainGame.changePoint(myPoint);
			gameResult = bdao.gameResult(dto, true);
		}
	} // end SissorHand()

	private void RockHand(int comChoice) {
		if (comChoice == 0) { // 가위일 경우
			lblResult.setText("이겼습니다 !!");
			myPoint += settingPoint;
			mainGame.changePoint(myPoint);
			gameResult = bdao.gameResult(dto, true);
		} else if (comChoice == 1) { // 바위일 경우
			gameResult = 0;
			lblResult.setText("무승부 입니다 다시하세요");
		} else { // 보 일 경우
			lblResult.setText("졌습니다 ㅠㅠ");
			myPoint -= settingPoint;
			mainGame.changePoint(myPoint);
			gameResult = bdao.gameResult(dto, false);
		}
	} // end RockHand()

	private void PaperHand(int comChoice) {
		if (count == 4) {
			if (comChoice == 0) { // 가위일 경우
				lblResult.setText("졌습니다 ㅠㅠ");
				myPoint -= settingPoint;
				mainGame.changePoint(myPoint);
				gameResult = bdao.gameResult(dto, false);
			} else if (comChoice == 1) { // 바위일 경우
				lblResult.setText("이겼습니다 !!");
				myPoint += settingPoint;
				mainGame.changePoint(myPoint);
				gameResult = bdao.gameResult(dto, true);
			} else { // 보 일 경우
				gameResult = 0;
				lblResult.setText("무승부 입니다 다시하세요");
			}
		}
	} // end PaperHand()

	private void usedItem() {
		myidao.usedItem(myidto);
		myidto = null;
	}

	private void completeGame() {
		btnPaper.setVisible(false);
		btnRock.setVisible(false);
		btnSissor.setVisible(false);
		btnRestart.setVisible(true);

	}// end completeGame

	private void usedShield(int gameResult) {
		if (gameResult == 2) {
			myPoint += settingPoint;
			mainGame.changePoint(myPoint);
			completeGame();

		}
	} // end usedShield

	private void usedDoubleUp(int gameResult) {
		if (gameResult == 1) {
			myPoint += settingPoint;
			mainGame.changePoint(myPoint);
			completeGame();

		}
	}// end usedDoubleUp

	private void usedDiamond(int gameResult) {
		if (gameResult == 0) {
			lblResult.setText("이겼습니다 !!");
			myPoint += settingPoint;
			mainGame.changePoint(myPoint);
			this.gameResult = bdao.gameResult(dto, true);

		} else if (gameResult == 2) {
			lblResult.setText("이겼습니다 !!");
			myPoint += (settingPoint * 2);
			mainGame.changePoint(myPoint);
			this.gameResult = bdao.usedDiamond(dto);

		}
	}// end usedDiamond()

	private void usedSlow() {
		count=0;
		comChoice = (int)(Math.random()*3);
		Timer time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				count++;
				if (count <= 2) {
					lblComHands.setText(Integer.toString(count));
					
				} else if (count == 3) {
					lblComHands.setText("컴퓨터의 손 : " + HANDS[comChoice]);
					btnPaper.setVisible(false);
					btnRock.setVisible(false);
					btnSissor.setVisible(false);
					btnSissorSlow.setVisible(true);
					btnRockSlow.setVisible(true);
					btnPaperSlow.setVisible(true);
				} else if (count == 4) {
					switch (myChoice) {
					case 1:
						SissorHand(comChoice);
						break;
					case 2:
						RockHand(comChoice);
						break;
					case 3:
						PaperHand(comChoice);
						break;
					}
					btnSissorSlow.setVisible(false);
					btnRockSlow.setVisible(false);
					btnPaperSlow.setVisible(false);
					btnPaper.setVisible(true);
					btnRock.setVisible(true);
					btnSissor.setVisible(true);
					btnPaper.setEnabled(true);
					btnRock.setEnabled(true);
					btnSissor.setEnabled(true);

					if (gameResult != 0) {// 무승부가 아닐때만 컴플리트 게임 작동
//						System.out.println("게임 완료 확인!");
						completeGame();
						if (myidto != null) {
//							System.out.println("아이템 사용 확인!");
							usedItem();
							gameResult = 0;

						}
					}
//		System.out.println(myPoint);
					dto = dao.updatePoint(myPoint, dto); // 게임 결과 이후 변경 된 포인트를 DB에 옮긴후 바로 DB에서 꺼내와 DTO에 저장한다.
					mainGame.setInfo(dto); // 메인창으로 해당 dto값을 전송시킨다.
//		System.out.println(dto.toString());
					myChoice = 0;
					time.cancel();
				}
			}
		}, 0, 1200);
	}

	private void usedJustHand() {

		comChoiceSec = (int) (Math.random() * 3);
		while (comChoice == comChoiceSec) {
			comChoiceSec = (int) (Math.random() * 3);
			
		}

		Timer time = new Timer();
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				if (count <= 3) {
					lblComHands.setText(Integer.toString(count));
					count++;

				} else if (count == 4) {
					lblComHands.setText("컴퓨터의 손 : " + HANDS[comChoiceSec]);

					switch (myChoice) {
					case 1:
						SissorHand(comChoiceSec);
						break;
					case 2:
						RockHand(comChoiceSec);
						break;
					case 3:
						PaperHand(comChoiceSec);
						break;
					}
					btnPaper.setEnabled(true);
					btnRock.setEnabled(true);
					btnSissor.setEnabled(true);

					if (gameResult != 0) {// 무승부가 아닐때만 컴플리트 게임 작동
//						System.out.println("게임 완료 확인!");
						completeGame();
						if (myidto != null) {
//							System.out.println("아이템 사용 확인!");
							usedItem();
							gameResult = 0;

						}
					}
//		System.out.println(myPoint);
					dto = dao.updatePoint(myPoint, dto); // 게임 결과 이후 변경 된 포인트를 DB에 옮긴후 바로 DB에서 꺼내와 DTO에 저장한다.
					mainGame.setInfo(dto); // 메인창으로 해당 dto값을 전송시킨다.
//		System.out.println(dto.toString());
					myChoice = 0;
					time.cancel();
				}
			}
		}, 0, 1200);
	}

	private void usedReset() {
		// TODO Auto-generated method stub
		lblResult.setText("처음으로 되돌아 갑니다..");
		dto = backupDto;
		myPoint = dto.getMemberPoint();
		mainGame.changePoint(myPoint);
		completeGame();
		if(gameResult==0) {
			usedItem();
			gameResult = 0;
		}
	}

	public void setItem(MyInventoryDTO myidto) {
		this.myidto = myidto;

	}// end setItem

	public RPSMemberDTO getMemberDTO() {
		return dto;
	}
}// end GameStart;

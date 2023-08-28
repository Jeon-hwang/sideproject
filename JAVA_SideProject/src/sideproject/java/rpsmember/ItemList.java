package sideproject.java.rpsmember;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ItemList extends JFrame {
	private final ImageIcon[] itemIcon = {new ImageIcon("res/shield.jpg"),
										  new ImageIcon("res/double.jpg"),
										  new ImageIcon("res/diamond.jpg"),
										  new ImageIcon("res/slowTime.jpg"),
										  new ImageIcon("res/oneHand.jpg"),
										  new ImageIcon("res/reset.jpg")
										  };
	private final String[] itemInfo = {"<html><body><center>실드<br>포인트 감소를 한번 막아 줍니다.<br>",
									   "<html><body><center>더블<br>승리 시 포인트를 두배로 올려 줍니다.<br>",
									   "<html><body><center>다이아몬드<br>컴퓨터의 손의 관계없이 이깁니다.<br>",
									   "<html><body><center>슬로우<br>컴퓨터보다 손을 1초 늦게 낼 수 있습니다.<br>",
									   "<html><body><center>손 봉인<br>컴퓨터가 낼 수 있는 손을 한개 막습니다.<br>",
									   "<html><body><center>판 엎기<br>게임을 시작 할 때의 <br>포인트로 되돌아 갑니다.<br>"};
	private JPanel contentPane;
	private int index=0;
	private ItemDTO idto;
	private RPSMemberDTO dto;
	private GameStart gameStart;
	private MyInventoryDTO myidto;
	
	public ItemList(GameStart gameStart) {
		this.gameStart = gameStart;
		dto = gameStart.getMemberDTO();
		ItemDAO idao = new ItemDAOImple().getInstance();
		MyInventoryDAO mydao = new MyInventoryDAOImple().getInstance();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblItemInfo = new JLabel(itemInfo[index]);
		lblItemInfo.setFont(new Font("굴림", Font.BOLD, 14));
		lblItemInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemInfo.setBounds(23, 151, 281, 67);
		contentPane.add(lblItemInfo);
		
		JLabel lblItemImage = new JLabel(itemIcon[index]);
		idto = idao.getItemInfo(1001+index);
		myidto = mydao.getInventory(dto, idto);
		lblItemInfo.setText(itemInfo[index]+"수량 : "+myidto.getItemCount()+"</center></body></html>");
		lblItemImage.setBounds(93, 10, 150, 150);
		contentPane.add(lblItemImage);
		
		ImageIcon prevIcon = new ImageIcon("res/Left.png");
		JButton btnPrev = new JButton(prevIcon);
		
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				if(index<0) {
					index=itemIcon.length-1;
				}
				lblItemImage.setIcon(itemIcon[index]);
				idto = idao.getItemInfo(1001+index);
				myidto = mydao.getInventory(dto, idto);
				lblItemInfo.setText(itemInfo[index]+"수량 : "+myidto.getItemCount()+"</center></body></html>");
			
			}
		});
		btnPrev.setBounds(12, 57, 65, 65);
		contentPane.add(btnPrev);
		
		
		
		ImageIcon nextIcon = new ImageIcon("res/Right.png");
		JButton btnRight = new JButton(nextIcon);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				if(index>=itemIcon.length) {
					index=0;
				}
				
				lblItemImage.setIcon(itemIcon[index]);
				idto = idao.getItemInfo(1001+index);
				myidto = mydao.getInventory(dto, idto);
				lblItemInfo.setText(itemInfo[index]+"수량 : "+myidto.getItemCount()+"</center></body></html>");
			}
		});
		btnRight.setBounds(257, 57, 65, 65);
		contentPane.add(btnRight);
		
		JButton btnSelect = new JButton("선택");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mydao.getInventory(dto, idto).getItemCount() != 0) {
				gameStart.setItem(myidto);
				dispose();
				}else {
					JOptionPane.showMessageDialog(null, "아이템이 없습니다", "선택 오류", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnSelect.setBounds(80, 228, 62, 23);
		contentPane.add(btnSelect);
		
		JButton btnCancle = new JButton("취소");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancle.setBounds(188, 228, 62, 23);
		contentPane.add(btnCancle);
		
		
	}
}

package sideproject.java.rpsmember;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ItemList extends JFrame {
	private final ImageIcon[] itemIcon = {new ImageIcon("res/shield.jpg"),
										  new ImageIcon("res/double.jpg"),
										  new ImageIcon("res/diamond.jpg"),
										  new ImageIcon("res/slowTime.jpg"),
										  new ImageIcon("res/oneHand.jpg"),
										  new ImageIcon("res/reset.jpg")
										  };
	private JPanel contentPane;
	private int index=0;
	private ItemDTO idto;
	private GameStart gameStart;
	
	public ItemList(GameStart gameStart) {
		this.gameStart = gameStart;
		ItemDAO idao = new ItemDAOImple().getInstance();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblItemImage = new JLabel(itemIcon[index]);
		lblItemImage.setBounds(93, 10, 150, 150);
		contentPane.add(lblItemImage);
		
		ImageIcon prevIcon = new ImageIcon("res/Left.png");
		JButton btnPrev = new JButton(prevIcon);
		
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index--;
				if(index<0) {
					index=5;
				}
				lblItemImage.setIcon(itemIcon[index]);
				idto = idao.getItemInfo(1001+index);
			}
		});
		btnPrev.setBounds(12, 57, 65, 65);
		contentPane.add(btnPrev);
		
		
		
		ImageIcon nextIcon = new ImageIcon("res/Right.png");
		JButton btnRight = new JButton(nextIcon);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index++;
				if(index>5) {
					index=0;
				}
				lblItemImage.setIcon(itemIcon[index]);
				idto = idao.getItemInfo(1001+index);
			}
		});
		btnRight.setBounds(257, 57, 65, 65);
		contentPane.add(btnRight);
		
		JButton btnSelect = new JButton("선택");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameStart.setItem(idto);
				dispose();
				
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

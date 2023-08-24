package sideproject.java.rpsmember;

import java.util.Date;

public class MyInventoryDTO {
	private int invetoryList;
	private String memberId;
	private String itemName;
	private int itemCount;
	private Date itemDate;
	
	
	
	public MyInventoryDTO(int invetoryList, String memberId, String itemName, int itemCount, Date itemDate) {
		super();
		this.invetoryList = invetoryList;
		this.memberId = memberId;
		this.itemName = itemName;
		this.itemCount = itemCount;
		this.itemDate = itemDate;
	}
	public int getInvetoryList() {
		return invetoryList;
	}
	public void setInvetoryList(int invetoryList) {
		this.invetoryList = invetoryList;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemCount() {
		return itemCount;
	}
	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
	public Date getItemDate() {
		return itemDate;
	}
	public void setItemDate(Date itemDate) {
		this.itemDate = itemDate;
	}
	
	@Override
	public String toString() {
		return "myInventoryDTO [invetoryList=" + invetoryList + ", memberId=" + memberId + ", itemName=" + itemName
				+ ", itemCount=" + itemCount + ", itemDate=" + itemDate + "]";
	}
	
	
}

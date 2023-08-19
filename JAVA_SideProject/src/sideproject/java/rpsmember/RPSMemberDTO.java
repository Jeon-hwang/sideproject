package sideproject.java.rpsmember;

public class RPSMemberDTO {
	private int memberNumber;
	private String memberId;
	private String memberPassword;
	private String memberName;
	private String memberEmail;
	private int memberPoint;
	
	
	
	public RPSMemberDTO(int memberNumber, String memberId, String memberPassword, String memberName, String memberEmail,
			int memberPoint) {
		this.memberNumber = memberNumber;
		this.memberId = memberId;
		this.memberPassword = memberPassword;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberPoint = memberPoint;
	}
	
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public int getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}

	@Override
	public String toString() {
		return "RPSMemberDTO [memberNumber=" + memberNumber + ", memberId=" + memberId + ", memberPassword="
				+ memberPassword + ", memberName=" + memberName + ", memberEmail=" + memberEmail + ", memberPoint="
				+ memberPoint + "]";
	}
	
	
	
	
}

package model;

public class Patron {
	
	private int memberId;
	private String FullName;
	private String memberType;
	private String joinDate;
	private String email;
	
	public Patron(int memberId, String fullName, String memberType, String joinDate, String email) {
		this.memberId = memberId;
		this.FullName = fullName;
		this.memberType = memberType;
		this.joinDate = joinDate;
		this.email = email;
	}
	
	public Patron() {
	}
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		this.FullName = fullName;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

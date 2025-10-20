package po;

import java.time.LocalDate;

public class Trorder {
	private int trorderId;
	private String trorderNo;
	private String memberNo;
	private String employeeNo;
	private LocalDate orderDate;
	private int totalAmount;
	private int paidAmount;
	private int changeAmount;

	public Trorder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trorder(int trorderId, String trorderNo, String memberNo, String employeeNo, LocalDate orderDate,
			int totalAmount, int paidAmount, int changeAmount) {
		this.trorderId = trorderId;
		this.trorderNo = trorderNo;
		this.memberNo = memberNo;
		this.employeeNo = employeeNo;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.paidAmount = paidAmount;
		this.changeAmount = changeAmount;
	}

	public int getTrorderId() {
		return trorderId;
	}

	public void setTrorderId(int trorderId) {
		this.trorderId = trorderId;
	}

	public String getTrorderNo() {
		return trorderNo;
	}

	public void setTrorderNo(String trorderNo) {
		this.trorderNo = trorderNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	public int getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}

	public void setStatus(String string) {
		// TODO Auto-generated method stub

	}

	public Object getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
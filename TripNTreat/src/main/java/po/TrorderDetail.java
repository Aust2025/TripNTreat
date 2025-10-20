package po;

public class TrorderDetail {
	private int trorderdetailId; // 對應資料庫 trorderdetail_id
	private String trorderdetailNo; // 對應 trorderdetailno
	private String trorderNo; // 對應 trorderno (訂單編號)
	private String itemNo;
	private String itemName;
	private int quantity;
	private int unitPrice;
	private int amount;

	public TrorderDetail() {
	}

	public TrorderDetail(int trorderdetailId, String trorderdetailNo, String trorderNo, String itemNo, String itemName,
			int quantity, int unitPrice, int amount) {
		this.trorderdetailId = trorderdetailId;
		this.trorderdetailNo = trorderdetailNo;
		this.trorderNo = trorderNo;
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.amount = amount;
	}

	public int getTrorderdetailId() {
		return trorderdetailId;
	}

	public void setTrorderdetailId(int trorderdetailId) {
		this.trorderdetailId = trorderdetailId;
	}

	public String getTrorderdetailNo() {
		return trorderdetailNo;
	}

	public void setTrorderdetailNo(String trorderdetailNo) {
		this.trorderdetailNo = trorderdetailNo;
	}

	public String getTrorderNo() {
		return trorderNo;
	}

	public void setTrorderNo(String trorderNo) {
		this.trorderNo = trorderNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Trorderdetail [trorderdetailId=" + trorderdetailId + ", trorderdetailNo=" + trorderdetailNo
				+ ", trorderNo=" + trorderNo + ", itemNo=" + itemNo + ", itemName=" + itemName + ", quantity="
				+ quantity + ", unitPrice=" + unitPrice + ", amount=" + amount + "]";
	}
}

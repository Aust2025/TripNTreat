package po;

import java.time.LocalDate;

public class Trip {
	private int tripId;
	private String tripNo;
	private String region;
	private String destination;
	private String tripName;
	private LocalDate departureDate;
	private int price;
	private int stock;

	public Trip() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trip(String tripNo, String region, String destination, String tripName, LocalDate departureDate, int price,
			int stock) {
		super();
		this.tripNo = tripNo;
		this.region = region;
		this.destination = destination;
		this.tripName = tripName;
		this.departureDate = departureDate;
		this.price = price;
		this.stock = stock;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public String getTripNo() {
		return tripNo;
	}

	public void setTripNo(String tripNo) {
		this.tripNo = tripNo;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTripName() {
		return tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tripName + "（行程號：" + tripNo + ") （出發時間：" + departureDate + ") (單價：" + price + "）(庫存：" + stock + "）";
	}

}
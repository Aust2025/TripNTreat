package po;

public class Service {
	private int serviceId;
	private String serviceNo;
	private String serviceName;
	private int price;

	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Service(String serviceNo, String serviceName, int price) {
		super();
		this.serviceNo = serviceNo;
		this.serviceName = serviceName;
		this.price = price;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return serviceName;
	}

}

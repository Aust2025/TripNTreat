package po;

public class Employee {
	private int id;
	private String employeeNo;
	private String name;
	private String employeePass;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String employeeNo, String name, String employeePass) {
		super();
		this.employeeNo = employeeNo;
		this.name = name;
		this.employeePass = employeePass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmployeePass() {
		return employeePass;
	}

	public void setEmployeePass(String employeePass) {
		this.employeePass = employeePass;
	}

}

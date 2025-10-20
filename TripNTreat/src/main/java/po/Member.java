package po;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Member implements Serializable {
	private Integer id;
	private String memberno;
	private String name;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String address;
	private boolean isAdmin;

	private static final long serialVersionUID = 1L;

	private static final Map<String, Member> USERS = new HashMap<>();
	static {
		USERS.put("admin", new Member("admin", "123", true));
		USERS.put("guest", new Member("guest", "123", false));
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member(String memberno, String name, String username, String password, String email, String phone,
			String address) {
		super();
		this.memberno = memberno;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.isAdmin = false;
	}

	// 登入用
	public Member(String username, String password) {
		this.username = username;
		this.password = password;
		this.isAdmin = false;
	}

	// 預設帳號用
	public Member(String username, String password, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMemberno() {
		return memberno;
	}

	public void setMemberno(String memberno) {
		this.memberno = memberno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
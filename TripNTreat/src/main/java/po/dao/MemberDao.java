package po.dao;

import po.Member;

public interface MemberDao {
	// create
	void insert(String name, String username, String password, String email, String phone, String address);

	void insert(Member member);

	// read
	String selectAll();// select * from member

	boolean selectUsername(String username);

	Member selectByUsernameAndPassword(String username, String password);

	// update
	void update(String name, String password, int id);

	// delete
	void delete(int id);
}

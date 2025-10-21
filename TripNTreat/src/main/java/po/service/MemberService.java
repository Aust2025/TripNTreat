package po.service;

import po.Member;

public interface MemberService {
	// create
	int addMember(Member member);

	// read
	String findAllMember();

	Member Login(String username, String password);

	// update
	void updateMember(Member member);

	// delete
	void deleteMember(int id);

}

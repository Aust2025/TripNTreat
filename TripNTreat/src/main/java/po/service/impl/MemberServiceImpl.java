package po.service.impl;

import po.Member;
import po.dao.impl.MemberDaoImpl;
import po.service.MemberService;

public class MemberServiceImpl implements MemberService {

	public static void main(String[] args) {
		// Member m = new Member("M009", "Alex", "alex", "123", "alex@email.com",
		// "0987654321", "Taipei");

		// System.out.println(new MemberServiceImpl().addMember(m));

		// System.out.println(new MemberServiceImpl().Login("tea", "1234536"));

	}

	private MemberDaoImpl mdi = new MemberDaoImpl();

	// 新增會員(Create)
	public int addMember(Member member) {
		if (mdi.selectUsername(member.getUsername())) { // 檢查帳號是否重複
			return 1; // 帳號重複
		} else {
			mdi.insert(member);
			return 0;
		}
	}

	// 會員登入方法(Read)
	@Override
	public Member Login(String username, String password) {
		return mdi.selectByUsernameAndPassword(username, password);

	}

	// 查詢所有會員資料(Read)
	@Override
	public String findAllMember() {
		return mdi.selectAll();
	}

	// 更新會員資料(Update)
	@Override
	public void updateMember(Member member) {
		mdi.update(member.getName(), member.getPassword(), member.getId());
	}

	// 刪除資料
	@Override
	public void deleteMember(int id) {
		mdi.delete(id);
		return;
	}
}
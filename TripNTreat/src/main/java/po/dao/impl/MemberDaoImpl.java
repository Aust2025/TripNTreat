package po.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import po.Member;
import po.dao.MemberDao;
import util.DbConnection;

public class MemberDaoImpl implements MemberDao {

	public static void main(String[] args) {

		// new MemberDaoImpl().insert(new Member("a","ter","456","台北","123"));;
		// Member m=new Member("bbb","teryy","456","台北","123");
		// new MemberDaoImpl().insert(m);

		// System.out.println(new MemberDaoImpl().selectAll());

		// new MemberDaoImpl().update("abcdef", "456789", 4);

		// new MemberDaoImpl().delete(5);

		// System.out.println(new MemberDaoImpl().selectUsername("tea123"));
		// System.out.println(new MemberDaoImpl().selectByUsernameAndPassword("tearrr",
		// "123456"));

		Member m = new MemberDaoImpl().selectByUsernameAndPassword("teaee", "123456");

		System.out.println(m + "\t" + m.getId() + "\t" + m.getName() + "\t" + m.getUsername() + "\t" + m.getPassword()
				+ "\t" + m.getAddress() + "\t" + m.getPhone() + "\t" + m.getEmail());

	}

	private static Connection conn = DbConnection.getDb();

	@Override
	public void insert(Member member) {
		String sql = "insert into member(name,username,password,email,phone,address) " + "values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, member.getName());
			ps.setString(2, member.getUsername());
			ps.setString(3, member.getPassword());
			ps.setString(4, member.getEmail());
			ps.setString(5, member.getPhone());
			ps.setString(6, member.getAddress());

			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String selectAll() {
		String sql = "select * from member";
		String show = "";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				show = show + "id:" + rs.getInt("id") + "\t姓名:" + rs.getString("name") + "\t帳號:"
						+ rs.getString("username") + "\t密碼:" + rs.getString("password") + "\t信箱:"
						+ rs.getString("email") + "\t電話:" + rs.getString("phone") + "\t地址:" + rs.getString("address")
						+ "\n";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return show;
	}

	@Override
	public boolean selectUsername(String username) {
		String sql = "select * from member where username=?";
		boolean result = false;

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				result = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public void update(String name, String password, int id) {
		String sql = "update member set name=?,password=? where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setInt(3, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Member selectByUsernameAndPassword(String username, String password) {
		String sql = "select * from member where username=? and password=?";
		Member member = null;

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setUsername(rs.getString("username"));
				member.setPassword(rs.getString("password"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setAddress(rs.getString("address"));
				member.setAdmin(rs.getBoolean("isAdmin"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return member;
	}

	@Override
	public void delete(int id) {
		String sql = "delete from member where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void insert(String name, String username, String password, String email, String phone, String address) {
		// TODO Auto-generated method stub

	}

}
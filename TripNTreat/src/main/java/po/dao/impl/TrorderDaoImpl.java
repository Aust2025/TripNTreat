package po.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import po.Trorder;
import po.dao.TrorderDao;
import util.DbConnection;

public class TrorderDaoImpl implements TrorderDao {

	public static void main(String[] args) {
		// Trorder tr = new Trorder(2, "a001", "a001", "a001", LocalDate.of(2025, 10,
		// 11), 10000, 11000, 1000);
		// new TrorderDaoImpl().add(tr);

		// System.out.println(new TrorderDaoImpl().selectAll());

		// System.out.println(new TrorderDaoImpl().selectById(1));

		// Trorder trorder=new TrorderDaoImpl().selectById(1) ;
		// Trorder.setTrorderno("T002");
		// Trorder.setTrorderid("2");
		// new TrorderDaoImpl().update(trorder);

		// new TrorderDaoImpl().delete(2);

	}

	private Connection conn = DbConnection.getDb();

	@Override
	public void add(Trorder trorder) {
		String sql = "insert into trorder(trorderno, memberno, employeeno, orderdate, totalamount, paidamount, changeamount) values(?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, trorder.getTrorderNo());
			ps.setString(2, trorder.getMemberNo());
			ps.setString(3, trorder.getEmployeeNo());
			ps.setString(4, trorder.getOrderDate() != null ? trorder.getOrderDate().toString() : null);
			ps.setInt(5, trorder.getTotalAmount());
			ps.setInt(6, trorder.getPaidAmount());
			ps.setInt(7, trorder.getChangeAmount());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Trorder> selectAll() {
		String sql = "select * from trorder";
		List<Trorder> list = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Trorder trorder = new Trorder();
				trorder.setTrorderId(rs.getInt("trorder_id"));
				trorder.setTrorderNo(rs.getString("trorderno"));
				trorder.setMemberNo(rs.getString("memberno"));
				trorder.setEmployeeNo(rs.getString("employeeno"));
				String orderDateStr = rs.getString("orderdate");
				trorder.setOrderDate(orderDateStr != null ? LocalDate.parse(orderDateStr) : null);
				trorder.setTotalAmount(rs.getInt("totalamount"));
				trorder.setPaidAmount(rs.getInt("paidamount"));
				trorder.setChangeAmount(rs.getInt("changeamount"));
				list.add(trorder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Trorder selectById(int id) {
		String sql = "select * from trorder where trorder_id=?";
		Trorder trorder = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				trorder = new Trorder();
				trorder.setTrorderId(rs.getInt("trorder_id"));
				trorder.setTrorderNo(rs.getString("trorderno"));
				trorder.setMemberNo(rs.getString("memberno"));
				trorder.setEmployeeNo(rs.getString("employeeno"));
				String orderDateStr = rs.getString("orderdate");
				trorder.setOrderDate(orderDateStr != null ? LocalDate.parse(orderDateStr) : null);
				trorder.setTotalAmount(rs.getInt("totalamount"));
				trorder.setPaidAmount(rs.getInt("paidamount"));
				trorder.setChangeAmount(rs.getInt("changeamount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trorder;
	}

	@Override
	public void update(Trorder trorder) {
		String sql = "update trorder set trorderno=?, memberno=?, employeeno=?, orderdate=?, totalamount=?, paidamount=?, changeamount=? where trorder_id=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, trorder.getTrorderNo());
			ps.setString(2, trorder.getMemberNo());
			ps.setString(3, trorder.getEmployeeNo());
			ps.setString(4, trorder.getOrderDate() != null ? trorder.getOrderDate().toString() : null);
			ps.setInt(5, trorder.getTotalAmount());
			ps.setInt(6, trorder.getPaidAmount());
			ps.setInt(7, trorder.getChangeAmount());
			ps.setInt(8, trorder.getTrorderId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String sql = "delete from trorder where trorder_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Trorder> selectByMemberNo(String memberNo) {
		String sql = "select * from trorder where memberno=?";
		List<Trorder> list = new ArrayList<>();
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, memberNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Trorder trorder = new Trorder();
				trorder.setTrorderId(rs.getInt("trorder_id")); // 設定主鍵
				trorder.setTrorderNo(rs.getString("trorderno"));
				trorder.setMemberNo(rs.getString("memberno"));
				trorder.setEmployeeNo(rs.getString("employeeno"));
				String orderDateStr = rs.getString("orderdate");
				trorder.setOrderDate(orderDateStr != null ? rs.getDate("orderdate").toLocalDate() : null);
				trorder.setTotalAmount(rs.getInt("totalamount"));
				trorder.setPaidAmount(rs.getInt("paidamount"));
				trorder.setChangeAmount(rs.getInt("changeamount"));
				list.add(trorder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

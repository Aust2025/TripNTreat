package po.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import po.TrorderDetail;
import po.dao.TrorderDetailDao;
import util.DbConnection;

public class TrorderDetailDaoImpl implements TrorderDetailDao {

	private Connection conn = DbConnection.getDb();

	@Override
	public void add(TrorderDetail trorderDetail) {
		String sql = "insert into trorderdetail(trorderdetailno, trorderno, itemno, itemname, quantity, unitprice, amount) VALUES(?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, trorderDetail.getTrorderdetailNo());
			ps.setString(2, trorderDetail.getTrorderNo()); // 對應訂單編號
			ps.setString(3, trorderDetail.getItemNo());
			ps.setString(4, trorderDetail.getItemName());
			ps.setInt(5, trorderDetail.getQuantity());
			ps.setInt(6, trorderDetail.getUnitPrice());
			ps.setInt(7, trorderDetail.getAmount());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TrorderDetail> selectAll() {
		String sql = "SELECT * FROM trorderdetail";
		List<TrorderDetail> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TrorderDetail trorderDetail = new TrorderDetail();
				trorderDetail.setTrorderdetailId(rs.getInt("trorderdetail_id"));
				trorderDetail.setTrorderdetailNo(rs.getString("trorderdetailno"));
				trorderDetail.setTrorderNo(rs.getString("trorderno"));
				trorderDetail.setItemNo(rs.getString("itemno"));
				trorderDetail.setItemName(rs.getString("itemname"));
				trorderDetail.setQuantity(rs.getInt("quantity"));
				trorderDetail.setUnitPrice(rs.getInt("unitprice"));
				trorderDetail.setAmount(rs.getInt("amount"));
				list.add(trorderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TrorderDetail selectById(int id) {
		String sql = "SELECT * FROM trorderdetail WHERE trorderdetail_id=?";
		TrorderDetail trorderDetail = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				trorderDetail = new TrorderDetail();
				trorderDetail.setTrorderdetailId(rs.getInt("trorderdetail_id"));
				trorderDetail.setTrorderdetailNo(rs.getString("trorderdetailno"));
				trorderDetail.setTrorderNo(rs.getString("trorderno"));
				trorderDetail.setItemNo(rs.getString("itemno"));
				trorderDetail.setItemName(rs.getString("itemname"));
				trorderDetail.setQuantity(rs.getInt("quantity"));
				trorderDetail.setUnitPrice(rs.getInt("unitprice"));
				trorderDetail.setAmount(rs.getInt("amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trorderDetail;
	}

	@Override
	public void update(TrorderDetail trorderDetail) {
		String sql = "UPDATE trorderdetail SET trorderdetailno=?, trorderno=?, itemno=?, itemname=?, quantity=?, unitprice=?, amount=? WHERE trorderdetail_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, trorderDetail.getTrorderdetailNo());
			ps.setString(2, trorderDetail.getTrorderNo());
			ps.setString(3, trorderDetail.getItemNo());
			ps.setString(4, trorderDetail.getItemName());
			ps.setInt(5, trorderDetail.getQuantity());
			ps.setInt(6, trorderDetail.getUnitPrice());
			ps.setInt(7, trorderDetail.getAmount());
			ps.setInt(8, trorderDetail.getTrorderdetailId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM trorderdetail WHERE trorderdetail_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 依訂單編號查詢
	public List<TrorderDetail> selectByTrorderNo(String trorderNo) {
		String sql = "select * from trorderdetail where trorderno=?";
		List<TrorderDetail> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, trorderNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TrorderDetail trorderDetail = new TrorderDetail();
				trorderDetail.setTrorderdetailId(rs.getInt("trorderdetail_id"));
				trorderDetail.setTrorderdetailNo(rs.getString("trorderdetailno"));
				trorderDetail.setTrorderNo(rs.getString("trorderno"));
				trorderDetail.setItemNo(rs.getString("itemno"));
				trorderDetail.setItemName(rs.getString("itemname"));
				trorderDetail.setQuantity(rs.getInt("quantity"));
				trorderDetail.setUnitPrice(rs.getInt("unitprice"));
				trorderDetail.setAmount(rs.getInt("amount"));
				list.add(trorderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

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

	// Create
	@Override
	public void add(TrorderDetail trorderDetail) {
		String sql = "INSERT INTO trorderdetail(trorderdetailno, trorderno, itemno, itemname, quantity, unitprice, amount) VALUES(?,?,?,?,?,?,?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, trorderDetail.getTrorderdetailNo());
			ps.setString(2, trorderDetail.getTrorderNo());
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

	// Read
	@Override
	public List<TrorderDetail> selectAll() {
		List<TrorderDetail> list = new ArrayList<>();
		String sql = "SELECT * FROM trorderdetail";
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				TrorderDetail td = new TrorderDetail();
				td.setTrorderdetailId(rs.getInt("trorderdetail_id"));
				td.setTrorderdetailNo(rs.getString("trorderdetailno"));
				td.setTrorderNo(rs.getString("trorderno"));
				td.setItemNo(rs.getString("itemno"));
				td.setItemName(rs.getString("itemname"));
				td.setQuantity(rs.getInt("quantity"));
				td.setUnitPrice(rs.getInt("unitprice"));
				td.setAmount(rs.getInt("amount"));
				list.add(td);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public TrorderDetail selectById(int id) {
		TrorderDetail td = null;
		String sql = "SELECT * FROM trorderdetail WHERE trorderdetail_id=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					td = new TrorderDetail();
					td.setTrorderdetailId(rs.getInt("trorderdetail_id"));
					td.setTrorderdetailNo(rs.getString("trorderdetailno"));
					td.setTrorderNo(rs.getString("trorderno"));
					td.setItemNo(rs.getString("itemno"));
					td.setItemName(rs.getString("itemname"));
					td.setQuantity(rs.getInt("quantity"));
					td.setUnitPrice(rs.getInt("unitprice"));
					td.setAmount(rs.getInt("amount"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return td;
	}

	@Override
	public List<TrorderDetail> selectByTrorderNo(String trorderNo) {
		List<TrorderDetail> list = new ArrayList<>();
		String sql = "SELECT * FROM trorderdetail WHERE trorderno=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, trorderNo);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					TrorderDetail td = new TrorderDetail();
					td.setTrorderdetailId(rs.getInt("trorderdetail_id"));
					td.setTrorderdetailNo(rs.getString("trorderdetailno"));
					td.setTrorderNo(rs.getString("trorderno"));
					td.setItemNo(rs.getString("itemno"));
					td.setItemName(rs.getString("itemname"));
					td.setQuantity(rs.getInt("quantity"));
					td.setUnitPrice(rs.getInt("unitprice"));
					td.setAmount(rs.getInt("amount"));
					list.add(td);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	// Update
	@Override
	public void update(TrorderDetail trorderDetail) {
		String sql = "UPDATE trorderdetail SET trorderdetailno=?, trorderno=?, itemno=?, itemname=?, quantity=?, unitprice=?, amount=? WHERE trorderdetail_id=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
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

	// Delete
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM trorderdetail WHERE trorderdetail_id=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteByTrorderNo(String trorderNo) {
		String sql = "DELETE FROM trorderdetail WHERE trorderno=?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, trorderNo);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

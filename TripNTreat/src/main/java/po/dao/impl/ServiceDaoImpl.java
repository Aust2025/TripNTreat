package po.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import po.Service;
import po.dao.ServiceDao;
import util.DbConnection;

public class ServiceDaoImpl implements ServiceDao {

	public static void main(String[] args) {
		// Service se = new Service(23, "TKO03", "日本東京銀座餐廳", 2500);
		// new ServiceDaoImpl().add(se);

		// System.out.println(new ServiceDaoImpl().selectAll());

		// System.out.println(new ServiceDaoImpl().selectById(1));

		// Service service=new ServiceDaoImpl().selectById(22) ;
		// service.setServiceNo("HKD03");
		// service.setServiceName("日本北海道螃蟹吃到飽");
		// new ServiceDaoImpl().update(service);

		// new ServiceDaoImpl().delete(23);

	}

	private Connection conn = DbConnection.getDb();

	// Create
	@Override
	public void add(Service service) {
		String sql = "insert into service(serviceno, servicename, price) values(?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, service.getServiceNo());
			ps.setString(2, service.getServiceName());
			ps.setInt(3, service.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Read
	public List<Service> selectAll() {
		String sql = "select * from service";
		List<Service> list = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Service service = new Service();
				service.setServiceId(rs.getInt("service_id"));
				service.setServiceNo(rs.getString("serviceno"));
				service.setServiceName(rs.getString("servicename"));
				service.setPrice(rs.getInt("price"));
				list.add(service);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Service selectById(int id) {
		String sql = "select * from service where service_id=?";
		Service service = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				service = new Service();
				service.setServiceId(rs.getInt("service_id"));
				service.setServiceNo(rs.getString("serviceno"));
				service.setServiceName(rs.getString("servicename"));
				service.setPrice(rs.getInt("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return service;
	}

	// Update
	@Override
	public void update(Service service) {
		String sql = "update service set serviceno=?, servicename=?, price=? where service_id=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, service.getServiceNo());
			ps.setString(2, service.getServiceName());
			ps.setInt(3, service.getPrice());
			ps.setInt(4, service.getServiceId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Delete
	@Override
	public void delete(int id) {
		String sql = "delete from service where service_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
package po.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import po.Trip;
import po.dao.TripDao;
import util.DbConnection;

public class TripDaoImpl implements TripDao {

	public static void main(String[] args) {
		// Trip trip = new Trip(9, "MEL1015A", "澳洲", "墨爾本", "澳洲墨爾本皇家建築5日遊",
		// LocalDate.of(2025, 10, 11), 45000, 10);
		// new TripDaoImpl().add(trip);

		// System.out.println(new TripDaoImpl().selectAll());

		// System.out.println(new TripDaoImpl().selectById(1));

		// Trip trip = new TripDaoImpl().selectById(4);
		// trip.setTripName("日本東京澀谷2日遊");
		// trip.setPrice("12000");
		// new TripDaoImpl().update(trip);

		// new TripDaoImpl().delete(9);

	}

	private Connection conn = DbConnection.getDb();

	@Override
	public void add(Trip trip) {
		String sql = "insert into trip(tripno, region, destination, tripname, departuredate, price, stock) values(?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, trip.getTripNo());
			ps.setString(2, trip.getRegion());
			ps.setString(3, trip.getDestination());
			ps.setString(4, trip.getTripName());
			ps.setString(5, trip.getDepartureDate() != null ? trip.getDepartureDate().toString() : null);
			ps.setInt(6, trip.getPrice());
			ps.setInt(7, trip.getStock());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Trip> selectAll() {
		String sql = "select * from trip";
		List<Trip> list = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Trip trip = new Trip();
				trip.setTripId(rs.getInt("trip_id"));
				trip.setTripNo(rs.getString("tripno"));
				trip.setRegion(rs.getString("region"));
				trip.setDestination(rs.getString("destination"));
				trip.setTripName(rs.getString("tripname"));
				String departureDateStr = rs.getString("departuredate");
				trip.setDepartureDate(departureDateStr != null ? LocalDate.parse(departureDateStr) : null);
				trip.setPrice(rs.getInt("price"));
				trip.setStock(rs.getInt("stock"));
				list.add(trip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Trip selectById(int id) {
		String sql = "select * from trip where trip_id=?";
		Trip trip = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				trip = new Trip();
				trip.setTripId(rs.getInt("trip_id"));
				trip.setTripNo(rs.getString("tripno"));
				trip.setRegion(rs.getString("region"));
				trip.setDestination(rs.getString("destination"));
				trip.setTripName(rs.getString("tripname"));
				String departureDateStr = rs.getString("departuredate");
				trip.setDepartureDate(departureDateStr != null ? LocalDate.parse(departureDateStr) : null);
				trip.setPrice(rs.getInt("price"));
				trip.setStock(rs.getInt("stock"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trip;
	}

	@Override
	public void update(Trip trip) {
		String sql = "update trip set tripno=?, region=?, destination=?, tripname=?, departuredate=?, price=?, stock=? where trip_id=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, trip.getTripNo());
			ps.setString(2, trip.getRegion());
			ps.setString(3, trip.getDestination());
			ps.setString(4, trip.getTripName());
			ps.setString(5, trip.getDepartureDate() != null ? trip.getDepartureDate().toString() : null);
			ps.setInt(6, trip.getPrice());
			ps.setInt(7, trip.getStock());
			ps.setInt(8, trip.getTripId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		String sql = "delete from trip where trip_id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
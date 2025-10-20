package po.dao;

import java.util.List;

import po.Trip;

public interface TripDao {

	/// create
	void add(Trip trip);

	// read
	List<Trip> selectAll();

	Trip selectById(int id);

	// update
	void update(Trip trip);

	// delete
	void delete(int id);
}

package po.service.impl;

import java.util.List;

import po.Trip;
import po.dao.impl.TripDaoImpl;
import po.service.TripService;

public class TripServiceImpl implements TripService {

	private static TripDaoImpl tripDao = new TripDaoImpl();

	// create
	@Override
	public void addTrip(Trip trip) {
		tripDao.add(trip);
	}

	// read
	@Override
	public List<Trip> findAllTrip() {
		return tripDao.selectAll();
	}

	@Override
	public Trip findTripById(int id) {
		return tripDao.selectById(id);
	}

	// update
	@Override
	public void updateTrip(Trip trip) {
		tripDao.update(trip);
	}

	// delete
	@Override
	public void deleteTrip(int id) {
		tripDao.delete(id);
	}

}
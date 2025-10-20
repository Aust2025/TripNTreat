package po.service;

import java.util.List;

import po.Trip;

public interface TripService {
	// create
	void addTrip(Trip trip);

	// read
	List<Trip> findAllTrip();

	Trip findTripById(int id);

	// update
	void updateTrip(Trip trip);

	// delete
	void deleteTrip(int id);

}

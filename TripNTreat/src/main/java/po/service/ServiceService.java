package po.service;

import java.util.List;

import po.Service;

public interface ServiceService {
	// create
	void addService(Service service);

	// read
	List<Service> findAllService();

	Service findServiceById(int id);

	// update
	void updateService(Service service);

	// delete
	void deleteService(int id);
}
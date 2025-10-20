package po.dao;

import java.util.List;

import po.Service;

public interface ServiceDao {
	// Create
	void add(Service service);

	// Read
	List<Service> selectAll();

	Service selectById(int id);

	// Update
	void update(Service service);

	// Delete
	void delete(int id);

}

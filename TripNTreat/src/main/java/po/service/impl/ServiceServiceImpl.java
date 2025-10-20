package po.service.impl;

import java.util.List;

import po.Service;
import po.dao.impl.ServiceDaoImpl;
import po.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {

	private static ServiceDaoImpl serviceDao = new ServiceDaoImpl();

	// create
	@Override
	public void addService(Service service) {
		serviceDao.add(service);
	}

	// read
	@Override
	public List<Service> findAllService() {
		return serviceDao.selectAll();
	}

	@Override
	public Service findServiceById(int id) {
		return serviceDao.selectById(id);
	}

	// update
	@Override
	public void updateService(Service service) {
		serviceDao.update(service);
	}

	// delete
	@Override
	public void deleteService(int id) {
		serviceDao.delete(id);
	}
}
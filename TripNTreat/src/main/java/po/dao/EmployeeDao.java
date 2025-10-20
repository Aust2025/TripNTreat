package po.dao;

import java.util.List;

import po.Employee;

public interface EmployeeDao {
	// create
	void add(Employee employee);

	// read
	List<Employee> selectAll();

	Employee selectById(int id);

	// update
	void update(Employee employee);

	// delete
	void delete(int id);

}

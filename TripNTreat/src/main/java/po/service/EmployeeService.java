package po.service;

import java.util.List;

import po.Employee;

public interface EmployeeService {

	// create
	void addEmployee(Employee employee);

	// read
	List<Employee> findAllEmployee();

	// update
	void updateEmployee(Employee employee);

	// delete
	void deleteEmployee(int id);

}

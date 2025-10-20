package po.service.impl;

import java.util.List;

import po.Employee;
import po.dao.impl.EmployeeDaoImpl;
import po.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	public static void main(String[] args) {
		new EmployeeServiceImpl().addEmployee(new Employee("E009", "Abby", "009"));
	}

	public Employee employeeLogin(String employeeNo, String employeePass) {
		for (Employee emp : findAllEmployee()) {
			if (emp.getEmployeeNo().equals(employeeNo) && emp.getEmployeePass().equals(employeePass)) {
				return emp;
			}
		}
		return null;
	}

	private static EmployeeDaoImpl em = new EmployeeDaoImpl();

	// create
	@Override
	public void addEmployee(Employee employee) {
		em.add(employee);

	}

	// read
	@Override
	public List<Employee> findAllEmployee() {
		// TODO Auto-generated method stub
		return em.selectAll();
	}

	// update
	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		em.update(employee);
	}

	// delete
	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		em.delete(id);
		return;
	}

}

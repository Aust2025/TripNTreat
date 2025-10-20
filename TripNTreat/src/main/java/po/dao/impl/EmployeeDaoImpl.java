package po.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import po.Employee;
import po.dao.EmployeeDao;
import util.DbConnection;

public class EmployeeDaoImpl implements EmployeeDao {

	public static void main(String[] args) {
		// Employee em = new Employee("E009", "abc", "000");
		// new EmployeeDaoImpl().add(em);

		// System.out.println(new EmployeeDaoImpl().selectAll());

		// System.out.println(new EmployeeDaoImpl().selectById(1));

		// Employee employee=new EmployeeDaoImpl().selectById(4) ;
		// employee.setName("allen");
		// employee.setEmployeePass("009");
		// new EmployeeDaoImpl().update(employee);

		// new EmployeeDaoImpl().delete(4);

	}

	private Connection conn = DbConnection.getDb();

	// Create
	@Override
	public void add(Employee employee) {
		String sql = "insert into employee(employeeno,name,employeepass) values(?,?,?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getEmployeeNo());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getEmployeePass());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Read
	@Override
	public List<Employee> selectAll() {
		String sql = "select * from employee";
		List<Employee> list = new ArrayList();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee em = new Employee();
				em.setId(rs.getInt("id"));
				em.setEmployeeNo(rs.getString("employeeno"));
				em.setName(rs.getString("name"));
				em.setEmployeePass(rs.getString("employeepass"));

				list.add(em);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Employee selectById(int id) {
		String sql = "select * from employee where id=?";
		Employee employee = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee em = new Employee();
				em.setId(rs.getInt("id"));
				em.setEmployeeNo(rs.getString("employeeno"));
				em.setName(rs.getString("name"));
				em.setEmployeePass(rs.getString("employeepass"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employee;
	}

	// Update
	@Override
	public void update(Employee employee) {
		String sql = "update employee set employeeno=? name=? employeepass=? where id=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, employee.getEmployeeNo());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getEmployeePass());
			ps.setInt(4, employee.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Delete
	@Override
	public void delete(int id) {
		String sql = "delete from employee where id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

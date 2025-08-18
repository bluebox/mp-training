package payroll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository {

	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public List<Employee> findAll() {
		String statement = "select * from Employee";
		List<Employee> employees = template.query(statement, (result, row) -> {
			Employee m = new Employee();
			m.setName(result.getString("name"));
			m.setRole(result.getString("role"));
			m.setId(result.getInt("Id"));
			return m;
		});
		return employees;
	}

	public Employee save(Employee employee) {

		String statement = "insert into Employee(name,role) values(?,?)";
		template.update(statement, employee.getName(), employee.getRole());
		return employee;
	}

	public Employee findById(Long id) {
		String statement = "select * from Employee where Id= '"+id+"'";
		List<Employee> employees=template.query(statement, (rs,row)->{
			Employee m = new Employee();
			m.setName(rs.getString("name"));
			m.setRole(rs.getString("role"));
			m.setId(rs.getInt("Id"));
			return m;
		});
		return employees.get(0);
	}

	public void deleteById(Long id) {
		String statement = "delete from Employee where Id= '"+id+"'";
		template.update(statement);
	}
	


	public Employee update(Employee newEmployee,Long id) {
		String statement = "update Employee set name=? ,role=? where id='"+id+"'";
		template.update(statement, newEmployee.getName(), newEmployee.getRole());
		return newEmployee;
	}
}

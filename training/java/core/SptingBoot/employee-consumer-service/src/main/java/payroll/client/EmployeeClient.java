package payroll.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import payroll.Employee;

import java.util.List;

@FeignClient(name = "employee-service", url = "http://localhost:8080")
public interface EmployeeClient {
    @GetMapping("/employees")
    List<Employee> getAll();

    @GetMapping("/employees/{id}")
    Employee getById(@PathVariable("id") int id);

    @PostMapping("/employees")
    Employee create(@RequestBody Employee employee);

    @PutMapping("/employees/{id}")
    Employee update(@PathVariable("id") int id, @RequestBody Employee employee);

    @DeleteMapping("/employees/{id}")
    void delete(@PathVariable("id") int id);
}
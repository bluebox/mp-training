package payroll.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import payroll.Employee;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestTemplateService {
    private final RestTemplate restTemplate;

    public List<Employee> getAll() {
        ResponseEntity<Employee[]> response = restTemplate.getForEntity("/employees", Employee[].class);
        Employee[] arr = response.getBody();
        return arr == null ? List.of() : Arrays.asList(arr);
    }

    public Employee getById(int id) {
        return restTemplate.getForObject("/employees/{id}", Employee.class, id);
    }

    public Employee create(Employee employee) {
        return restTemplate.postForObject("/employees", employee, Employee.class);
    }

    public Employee update(int id, Employee employee) {
        ResponseEntity<Employee> response = restTemplate.exchange("/employees/{id}", HttpMethod.PUT, new HttpEntity<>(employee), Employee.class, id);
        return response.getBody();
    }

    public void delete(int id) {
        restTemplate.delete("/employees/{id}", id);
    }
}
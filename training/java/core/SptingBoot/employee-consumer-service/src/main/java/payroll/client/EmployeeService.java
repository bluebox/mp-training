package payroll.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import payroll.Employee;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeClient feignClient;
    private final WebClientService webClientService;
    private final RestTemplateService restTemplateService;

    public List<Employee> getAllWithFeign() {
        return feignClient.getAll();
    }

    public Employee getByIdWithFeign(int id) {
        return feignClient.getById(id);
    }

    public Employee createWithFeign(Employee employee) {
        return feignClient.create(employee);
    }

    public Employee updateWithFeign(int id, Employee employee) {
        return feignClient.update(id, employee);
    }

    public void deleteWithFeign(int id) {
        feignClient.delete(id);
    }

    public List<Employee> getAllWithWebClient() {
        return webClientService.getAll();
    }

    public Employee getByIdWithWebClient(int id) {
        return webClientService.getById(id);
    }

    public Employee createWithWebClient(Employee employee) {
        return webClientService.create(employee);
    }

    public Employee updateWithWebClient(int id, Employee employee) {
        return webClientService.update(id, employee);
    }

    public void deleteWithWebClient(int id) {
        webClientService.delete(id);
    }

    public List<Employee> getAllWithRestTemplate() {
        return restTemplateService.getAll();
    }

    public Employee getByIdWithRestTemplate(int id) {
        return restTemplateService.getById(id);
    }

    public Employee createWithRestTemplate(Employee employee) {
        return restTemplateService.create(employee);
    }

    public Employee updateWithRestTemplate(int id, Employee employee) {
        return restTemplateService.update(id, employee);
    }

    public void deleteWithRestTemplate(int id) {
        restTemplateService.delete(id);
    }
}
package payroll;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import payroll.client.EmployeeService;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/feign/employees")
    public ResponseEntity<List<Employee>> getAllFeign() {
        return ResponseEntity.ok(employeeService.getAllWithFeign());
    }

    @GetMapping("/feign/employees/{id}")
    public ResponseEntity<Employee> getByIdFeign(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getByIdWithFeign(id));
    }

    @PostMapping("/feign/employees")
    public ResponseEntity<Employee> createFeign(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createWithFeign(employee));
    }

    @PutMapping("/feign/employees/{id}")
    public ResponseEntity<Employee> updateFeign(@PathVariable int id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateWithFeign(id, employee));
    }

    @DeleteMapping("/feign/employees/{id}")
    public ResponseEntity<Void> deleteFeign(@PathVariable int id) {
        employeeService.deleteWithFeign(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/webclient/employees")
    public ResponseEntity<List<Employee>> getAllWebClient() {
        return ResponseEntity.ok(employeeService.getAllWithWebClient());
    }

    @GetMapping("/webclient/employees/{id}")
    public ResponseEntity<Employee> getByIdWebClient(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getByIdWithWebClient(id));
    }

    @PostMapping("/webclient/employees")
    public ResponseEntity<Employee> createWebClient(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createWithWebClient(employee));
    }

    @PutMapping("/webclient/employees/{id}")
    public ResponseEntity<Employee> updateWebClient(@PathVariable int id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateWithWebClient(id, employee));
    }

    @DeleteMapping("/webclient/employees/{id}")
    public ResponseEntity<Void> deleteWebClient(@PathVariable int id) {
        employeeService.deleteWithWebClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/resttemplate/employees")
    public ResponseEntity<List<Employee>> getAllRestTemplate() {
        return ResponseEntity.ok(employeeService.getAllWithRestTemplate());
    }

    @GetMapping("/resttemplate/employees/{id}")
    public ResponseEntity<Employee> getByIdRestTemplate(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getByIdWithRestTemplate(id));
    }

    @PostMapping("/resttemplate/employees")
    public ResponseEntity<Employee> createRestTemplate(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createWithRestTemplate(employee));
    }

    @PutMapping("/resttemplate/employees/{id}")
    public ResponseEntity<Employee> updateRestTemplate(@PathVariable int id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateWithRestTemplate(id, employee));
    }

    @DeleteMapping("/resttemplate/employees/{id}")
    public ResponseEntity<Void> deleteRestTemplate(@PathVariable int id) {
        employeeService.deleteWithRestTemplate(id);
        return ResponseEntity.noContent().build();
    }
}
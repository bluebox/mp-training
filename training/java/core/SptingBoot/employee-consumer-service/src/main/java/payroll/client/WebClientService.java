package payroll.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import payroll.Employee;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebClientService {
    private final WebClient webClient;

    public List<Employee> getAll() {
        Employee[] arr = webClient.get().uri("/employees").retrieve().bodyToMono(Employee[].class).block();
        return arr == null ? List.of() : Arrays.asList(arr);
    }

    public Employee getById(int id) {
        return webClient.get().uri("/employees/{id}", id).retrieve().bodyToMono(Employee.class).block();
    }

    public Employee create(Employee employee) {
        return webClient.post().uri("/employees").body(Mono.just(employee), Employee.class).retrieve().bodyToMono(Employee.class).block();
    }

    public Employee update(int id, Employee employee) {
        return webClient.put().uri("/employees/{id}", id).body(Mono.just(employee), Employee.class).retrieve().bodyToMono(Employee.class).block();
    }

    public void delete(int id) {
        webClient.delete().uri("/employees/{id}", id).retrieve().toBodilessEntity().block();
    }
}
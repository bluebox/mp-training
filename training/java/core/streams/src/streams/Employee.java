package streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    String department;
    double salary;
    List<String> skills;

    public Employee(int id, String name, String department, double salary, List<String> skills) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + department + " | $" + salary + " | " + skills;
    }
}

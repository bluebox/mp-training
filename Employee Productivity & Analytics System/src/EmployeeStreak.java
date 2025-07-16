import java.time.LocalDate;
import java.util.List;

class EmployeeStreak {
    String employeeId;
    List<LocalDate> dates;

    EmployeeStreak(String id, List<LocalDate> d) {
        this.employeeId = id;
        this.dates = d;
    }
}

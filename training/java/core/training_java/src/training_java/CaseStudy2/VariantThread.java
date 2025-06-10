import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VariantThread extends Thread {
	        private final List<Employee> dataBatch;
	        private final Map<String, Long> q32Remarks = new HashMap<>();
	        private final Map<List<String>, Double> q6Time = new HashMap<>();
	        private final Map<String, Map<YearMonth, List<Double>>> q20Drop = new HashMap<>();
	        private final Map<String, List<LocalDate>> q18ConsecDates = new HashMap<>();

	        public VariantThread(List<Employee> batch) {
	            this.dataBatch = batch;
	        }

	        public Map<String, Long> getQ32Remarks() { return q32Remarks; }
	        public Map<List<String>, Double> getQ6Time() { return q6Time; }
	        public Map<String, Map<YearMonth, List<Double>>> getQ20Drop() { return q20Drop; }
	        public Map<String, List<LocalDate>> getQ18ConsecDates() { return q18ConsecDates; }
	        public void run() {
	            // Q32: Count remarks
	            q32Remarks.putAll(dataBatch.stream()
	                .collect(Collectors.groupingBy(Employee::getRemarks, Collectors.counting())));

	            // Q6: Time per Department & Project
	            Map<List<String>, Double> timeMap = dataBatch.stream()
	                .collect(Collectors.groupingBy(emp -> List.of(emp.getDepartment(), emp.getProjectId()),
	                        Collectors.summingDouble(Employee::getHoursWorked)));
	            timeMap.forEach((k, v) -> q6Time.merge(k, v, Double::sum));
	            
	         // Q20: Drop > 40%
	            Map<String, Map<YearMonth, Double>> empMonthlyHours = dataBatch.stream()
	                .collect(Collectors.groupingBy(Employee::getEmployeeId,
	                    Collectors.groupingBy(emp -> YearMonth.from(emp.getDate()),
	                        Collectors.summingDouble(Employee::getHoursWorked))));
	            empMonthlyHours.forEach((empId, monthMap) -> {
	    			var sortedMonthMap = monthMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
	    					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	    			double prev = 0;
	    			double percentage = 0;
	    			for (var key : sortedMonthMap.keySet()) {
	    				double curr = sortedMonthMap.get(key);
	    				if (prev != 0) {
	    					percentage = ((prev - curr) / prev) * 100;
	    				}
	    				if (percentage >= 40) {
	    					q20Drop.putIfAbsent(empId, Map.of(key, List.of(prev, curr)));
	    				}
	    				prev = curr;

	    			}

	    		});
	            
	         // Q18: 3+ Consecutive Dates
	            Map<String, List<LocalDate>> empDateMap = dataBatch.stream()
	                .collect(Collectors.groupingBy(Employee::getEmployeeId,
	                    Collectors.collectingAndThen(Collectors.mapping(Employee::getDate, Collectors.toSet()),
	                        s -> s.stream().sorted().collect(Collectors.toList()))));
	            empDateMap.forEach((empId, dates) -> {
	            	boolean flag=false;
	    			for (int i = 1; i < dates.size(); i++) {
	    				if (dates.get(i).compareTo(dates.get(i - 1))>=3) {
	    					flag=true;
	    					if (flag) {
	    						q18ConsecDates.putIfAbsent(empId, List.of(dates.get(i - 1), dates.get(i)));
	    						break;
	    					}
	    				} else {
	    					flag=false;
	    				}
	    			}
	    		});// 18
	        }
	    }
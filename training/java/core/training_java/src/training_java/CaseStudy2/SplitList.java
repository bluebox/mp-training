import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SplitList {

	public static List<List<Employee>> splitList(List<Employee> list) {

		List<List<Employee>> batches = new ArrayList<>();
		var pieces = list.stream().collect(Collectors.groupingBy(Employee::getEmployeeId));
		int i = 0;
		for (var key : pieces.keySet()) {
			batches.addAll(i % 3, List.of(pieces.get(key)));
			i = i + 1;
		}
		return batches;
	}

	public static void main(String[] args) throws Exception {
		ExcelReader getContentFromExcelSheets = new ExcelReader();
		List<Employee> data = new ArrayList<Employee>();
		try {
			data = getContentFromExcelSheets.readRecordsFromExcelFile(
					"/home/developer/eclipse-workspace/training_java/src/CaseStudy2/Sample_Employee_WorkLogs.xlsx");
		} catch (IOException e) {

			e.printStackTrace();
		}
		int numThreads = 4;
		List<List<Employee>> batches = splitList(data);
		List<VariantThread> threads = new ArrayList<>();

		for (List<Employee> batch : batches) {
			VariantThread t = new VariantThread(batch);
			threads.add(t);
			t.start();
		}

		for (Thread t : threads)
			t.join();

		// Combine results
		Map<String, Long> q32 = new HashMap<>();
		Map<List<String>, Double> q6 = new HashMap<>();
		Map<String, Map<YearMonth, List<Double>>> q20 = new HashMap<>();
		Map<String, List<LocalDate>> q18 = new HashMap<>();
		for (VariantThread t : threads) {
			t.getQ32Remarks().forEach((k, v) -> q32.merge(k, v, Long::sum));
			t.getQ6Time().forEach((k, v) -> q6.merge(k, v, Double::sum));
			q20.putAll(t.getQ20Drop());
			q18.putAll(t.getQ18ConsecDates());
		}

		System.out.println("--- Q32 REMARK-->COUNTS ---");
		q32.forEach((k, v) -> System.out.println(k + ": " + v));

		System.out.println("--- Q6 DEPTP&ROJECT-->HOURS ---");
		q6.forEach((k, v) -> System.out.println(k + ": " + v));

		System.out.println("--- Q20 DROP IN HOURS>=40% ---");
		q20.forEach((k, v) -> System.out.println(k + ": " + v));

		System.out.println("--- Q18 3+ Zero Hour CONSECUTIVE DATES ---");
		q18.forEach((k, v) -> System.out.println(k + ": " + v));
	}
}

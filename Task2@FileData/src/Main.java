import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		List<EmployeeData>Data=CSVReader.getData();
		ProblemNo3.getData(Data);
		ProblemNo4.getData(Data);
		ProblemNo7.getData(Data);
		ProblemNo9.getData(Data);
	}

}

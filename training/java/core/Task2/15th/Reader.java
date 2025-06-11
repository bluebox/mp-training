import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public static List<EmployeeDetails> readCSV(String filePath) {
        List<EmployeeDetails> details = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int i=0;

            while ((line = br.readLine()) != null) {
                if (i==0) {
                    i++;
                    continue;
                }

                String[] rows = line.split(",", -1); 

                if (rows.length < 4)
                    continue; 

                String empId = rows[0].trim();
                //System.out.println(rows[0]+"\n**");
                String name = rows[1].trim();
                LocalDate date = LocalDate.parse(rows[2].trim());
                double hours = Double.parseDouble(rows[3].trim());

                details.add(new EmployeeDetails(empId, name,date,hours));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return details;
    }
}
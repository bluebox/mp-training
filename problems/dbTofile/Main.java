package dbTofile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        int count = 0;

        String excelPath = "C:\\Users\\DELL\\Downloads\\Employee_Timesheet_May_to_July_2025_FormattedDate.xlsx";
        String csvOutputPath = "output.csv";

        // Replace with your DB info
        String jdbcURL = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            System.out.println("Database connected...");

            FileInputStream file = new FileInputStream(new File(excelPath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                ArrayList<String> record = new ArrayList<>();
                int ind = 0;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC && ind != 4) {
                        record.add(Double.toString(cell.getNumericCellValue()));
                    } else {
                        record.add(cell.toString()); // safer for general cell content
                    }
                    ind++;
                }

                count++;
                if (count != 1) { // Skip header row
                    // Write to DB (replace with your actual INSERT SQL)
                    String sql = "INSERT INTO employee_timesheet (col1, col2, col3, col4, col5) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                        for (int i = 0; i < 5; i++) {
                            stmt.setString(i + 1, i < record.size() ? record.get(i) : "");
                        }
                        stmt.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            file.close();
            workbook.close();

            // Read from DB and write to CSV
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM employee_timesheet");
                 FileWriter csvWriter = new FileWriter(csvOutputPath)) {

                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                // Write header
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.append(meta.getColumnName(i));
                    if (i < columnCount) csvWriter.append(",");
                }
                csvWriter.append("\n");

                // Write rows
                while (rs.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        csvWriter.append(rs.getString(i));
                        if (i < columnCount) csvWriter.append(",");
                    }
                    csvWriter.append("\n");
                }

                System.out.println("Data written to output.csv");

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

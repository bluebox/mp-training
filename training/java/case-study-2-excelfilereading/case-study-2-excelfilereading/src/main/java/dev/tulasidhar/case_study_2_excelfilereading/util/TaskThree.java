package dev.tulasidhar.case_study_2_excelfilereading.util;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.tulasidhar.case_study_2_excelfilereading.model.EmployeeWorkLog;

public class TaskThree {
    public static void taskThreeProcess(List<EmployeeWorkLog> employees) throws Exception {
        Map<String, List<Double>> hoursPerProject = new HashMap<>();
        
        for (EmployeeWorkLog employee : employees) {
            String projectId = employee.getProjectId();
            hoursPerProject.computeIfAbsent(projectId, k -> new ArrayList<>())
                          .add(employee.getHoursWorked());
        }
        
        
        Map<String, Double> stdDevByProject = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : hoursPerProject.entrySet()) {
            double stdDev = calculateStdDev(entry.getValue());
            stdDevByProject.put(entry.getKey(), stdDev);
        }
        
        writeStdDevToCSV(stdDevByProject, "task3_stddev.csv");
    }
    
    private static double calculateStdDev(List<Double> numbers) {
        //average
        double sum = 0.0;
        for (double num : numbers) {
            sum += num;
        }
        double mean = sum / numbers.size();
        
        //sum of squaredifferences
        double squaredDiffSum = 0.0;
        for (double num : numbers) {
            squaredDiffSum += Math.pow(num - mean, 2);
        }
        
        return Math.sqrt(squaredDiffSum / numbers.size());
    }
    
    private static void writeStdDevToCSV(Map<String, Double> data, String fileName) throws Exception {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("ProjectId,StandardDeviation\n");
            for (Map.Entry<String, Double> entry : data.entrySet()) {
                writer.write(String.format("%s,%.2f\n", entry.getKey(), entry.getValue()));
            }
        }
    }
}

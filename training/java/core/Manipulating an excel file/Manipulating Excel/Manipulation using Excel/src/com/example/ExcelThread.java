package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelThread implements Runnable{
	List<Employee> e=new ArrayList<>();
	int tCount;
	public ExcelThread(List<Employee> list,int tCount) {
		this.e=list;
		this.tCount=tCount;
	}
	@Override
	public void run() {
		Map<String, Map<String, Double>> hpm = e.stream().collect(Collectors.groupingBy(s1->s1.employeeId,Collectors.groupingBy(s1->s1.date.getMonth().toString(),Collectors.averagingDouble(s1->s1.hoursWorked))));
		File f1=new File("/home/developer/eclipse-workspace/Bhanu/mp-training/training/java/core/Manipulating an excel file/Manipulating Excel/Manipulation using Excel/Output/Task1.xlsx");
		try(PrintWriter p1=new PrintWriter(f1)){
			p1.println("Employee ID,Month,Average Hours Worked");
			for(String i:hpm.keySet()) {
				for(String j:hpm.get(i).keySet()) {
					p1.printf("%s,%s,%.2f\n",i,j,hpm.get(i).get(j));
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
		System.out.println("Thread"+tCount+" is executed");
		List<Employee> l=e.stream().filter(s1->(s1.remarks.toLowerCase().trim().equals("urgent")||s1.remarks.toLowerCase().trim().equals("critical")))
				.sorted((x,y)->x.name.compareToIgnoreCase(y.name)).collect(Collectors.toList());
		System.out.println(l.size());
		File f2=new File("/home/developer/eclipse-workspace/Bhanu/mp-training/training/java/core/Manipulating an excel file/Manipulating Excel/Manipulation using Excel/Output/Task2.xlsx");
		try(PrintWriter p2=new PrintWriter(f2)){
			p2.println("Employee ID,Name,Department,Project ID,Date,Task Category,Hours Worked,Remarks");
			for(Employee i:l) {
				p2.printf("%s,%s,%s,%s,%s,%s,%.2f,%s\n",i.employeeId,i.name,i.department,i.projectId,i.date.toString(),i.taskCategory,i.hoursWorked,i.remarks);
				
			}
		} catch (FileNotFoundException e4) {
			e4.printStackTrace();
		}
		Map<String, Map<String, List<Employee>>> l1 = e.stream().collect(Collectors.groupingBy(s1->s1.name,Collectors.groupingBy(s1->s1.date.getMonth().toString())));
		File f3=new File("/home/developer/eclipse-workspace/Bhanu/mp-training/training/java/core/Manipulating an excel file/Manipulating Excel/Manipulation using Excel/Output/Task3.xlsx");
		try(PrintWriter p3=new PrintWriter(f3)){
			p3.println("Employee ID,Name,month,no of departments changed");
			for(String i:l1.keySet()) {
				for(String j:l1.get(i).keySet()) {
					if(l1.get(i).get(j).size()>1) {
						p3.printf("%s,%s,%s,%d\n",i,l1.get(i).get(j).get(0).name,j,l1.get(i).get(j).size());
					}
				}
			}
		} catch (FileNotFoundException e3) {
			e3.printStackTrace();
		}
		Map<String, List<Employee>> l2 = e.stream()
			    .collect(Collectors.groupingBy(emp -> emp.department,
			        Collectors.collectingAndThen(
			            Collectors.toList(),
			            list -> list.stream()
			                        .sorted((e1, e2) -> Double.compare(e2.hoursWorked, e1.hoursWorked))
			                        .limit(2)
			                        .collect(Collectors.toList())
			        )
			    ));
		File f4=new File("/home/developer/eclipse-workspace/Bhanu/mp-training/training/java/core/Manipulating an excel file/Manipulating Excel/Manipulation using Excel/Output/Task4.xlsx");
		try(PrintWriter p4=new PrintWriter(f4)){
			p4.println("Employee ID,Name,Department,Hours Worked");
			for(String i:l2.keySet()) {
				for(Employee j:l2.get(i)) {
					p4.printf("%s,%s,%s,%s\n",j.employeeId,j.name,i,j.hoursWorked);
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Map<String, Map<LocalDate, Double>> l3 = e.stream()
			    .filter(emp -> emp.date != null)
			    .collect(Collectors.groupingBy(s1 -> s1.name))
			    .entrySet().stream()
			    .collect(Collectors.toMap(
			        Map.Entry::getKey,
			        entry -> {
			            List<Employee> sortedLogs = entry.getValue().stream()
			                .sorted(Comparator.comparing(emp -> emp.date))
			                .toList();
			            return sortedLogs.stream().collect(Collectors.toMap(
			                emp -> emp.date,emp -> {
			                    LocalDate start = emp.date.minusDays(6);
			                    return sortedLogs.stream()
			                        .filter(s1 -> !s1.date.isBefore(start) && !s1.date.isAfter(emp.date))
			                        .mapToDouble(s1 -> s1.hoursWorked)
			                        .average().orElse(0.0);
			                },
			                (a, b) -> a,LinkedHashMap::new));
			        }
			    ));
		File f5=new File("/home/developer/eclipse-workspace/Bhanu/mp-training/training/java/core/Manipulating an excel file/Manipulating Excel/Manipulation using Excel/Output/Task5.xlsx");
		try(PrintWriter p4=new PrintWriter(f5)){
			p4.println("Name,Date,Average Hours Worked per week");
			for(String i:l3.keySet()) {
				for(LocalDate j:l3.get(i).keySet()) {
					p4.printf("%s,%s,%.2f\n",i,j.toString(),l3.get(i).get(j));
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

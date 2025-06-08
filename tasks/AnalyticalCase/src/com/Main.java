package com;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.compress.archivers.StreamingNotSupportedException;
import org.apache.poi.hdgf.streams.Stream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.domain.EmployeePojo;
import com.service.AnalyseData;
import com.service.DataLoder;
import com.service.EmployeeDataAnalytics;
import com.service.WriteToExcel;

public class Main {
	
    public static void main(String[] args) {
        EmployeeDataAnalytics employeeDataAnalytics = new EmployeeDataAnalytics();
        EmployeePojo employeePojo = null;
        DataLoder dataLoder=new DataLoder();
        AnalyseData analyseData=new AnalyseData();
        dataLoder.dataLoder(employeeDataAnalytics, employeePojo);
        employeeDataAnalytics.printEmployees();
        //Map<String , List<EmployeePojo>> groupedEmployees=timeConsumingWork( employeeDataAnalytics);
        analyseData.calculate7DaySlidingAverage(employeeDataAnalytics.getEmployeeList());
        analyseData.calculateWeeklyEffort(employeeDataAnalytics.getEmployeeList());
        analyseData.calculateProjectProductivity(employeeDataAnalytics.getEmployeeList());
        analyseData.calculateStdDevPerProject(employeeDataAnalytics.getEmployeeList());

    }

}



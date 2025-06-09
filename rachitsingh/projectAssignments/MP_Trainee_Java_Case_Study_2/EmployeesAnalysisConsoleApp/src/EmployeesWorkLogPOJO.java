package EmployeesAnalysisConsoleApp.src;

import java.time.LocalDate;

public class EmployeesWorkLogPOJO 
{
    private String employeeID;
    private String employeeName;
    private String department;
    private String projectID;
    private LocalDate date;
    private String taskCategory;
    private double hoursWorked;
    private String remarks;

    public EmployeesWorkLogPOJO(String employeeID, String employeeName, String department, String projectID,
                                LocalDate date, String taskCategory, double hoursWorked, String remarks) 
    {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.department = department;
        this.projectID = projectID;
        this.date = date;
        this.taskCategory = taskCategory;
        this.hoursWorked = hoursWorked;
        this.remarks = remarks;
    }

    public String getEmployeeID() 
    {
        return employeeID;
    }

    public void setEmployeeId(String employeeID) 
    {
        if (!employeeID.isBlank())
        {
            this.employeeID = employeeID;
        }
    }

    public String getemployeeName() 
    {
        return employeeName;
    }

    public void setemployeeName(String employeeName) 
    {
        if (!employeeID.isBlank())
        {
            this.employeeName = employeeName;
        }
    }

    public String getDepartment() 
    {
        return department;
    }

    public void setDepartment(String department) 
    {
        if (!department.isBlank())
        {
            this.department = department;
        } 
    }

    public String getprojectID() 
    {
        return projectID;
    }

    public void setprojectID(String projectID) 
    {
        if (!projectID.isBlank())
        {
            this.projectID = projectID;
        }
    }

    public LocalDate getDate() 
    {
        return date;
    }

    public void setDate(LocalDate date) 
    {
        this.date = date;
    }

    public String getTaskCategory() 
    {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) 
    {
        if (!taskCategory.isBlank())
        {
            this.taskCategory = taskCategory;
        }
    }

    public double getHoursWorked() 
    {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) 
    {
        if (hoursWorked >= 0.0)
        {
            this.hoursWorked = hoursWorked;
        }
    }

    public String getRemarks() 
    {
        return remarks;
    }

    public void setRemarks(String remarks) 
    {
        if (!remarks.isBlank())
        {
            this.remarks = remarks;
        }
    }

    @Override
    public String toString() 
    {
        return "EmployeesWorkLog{" +
                "employeeID='" + employeeID + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", department='" + department + '\'' +
                ", projectID='" + projectID + '\'' +
                ", date=" + date +
                ", taskCategory='" + taskCategory + '\'' +
                ", hoursWorked=" + hoursWorked +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

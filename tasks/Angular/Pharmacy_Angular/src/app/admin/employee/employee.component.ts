import { Component, OnInit } from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {AfterViewInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {MatDatepicker} from '@angular/material/datepicker';
import {EmployeeService} from 'src/app/services/employee.service';
import { AddEmpComponent } from './add-emp/add-emp.component';


@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  title = 'employee'
  displayedColumns: string[] = ['emp_id', 'first_name','last_name', 'doj', 'designation', 'job_type', 'salary', 'contact', 'email', 'address','action'];
  dataSource !: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private dialog:MatDialog, private api : EmployeeService){

  }
 
  ngOnInit(): void{
    this.getAllEmployee();
  }

  openDialog() {
    this.dialog.open(AddEmpComponent, {
      width:'30%'
    }).afterClosed().subscribe(val=>{
      if(val=='save'){
        this.getAllEmployee();
      }

    })
  }

  getAllEmployee(){
    this.api.getEmployee()
    .subscribe({
      next:(res)=>{
        this.dataSource = new MatTableDataSource(res);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error:(err)=>{
        alert("Error Occured")
      }
    })
  }

  editEmployee(row:any){
    this.dialog.open(AddEmpComponent,{
      width:"30%",
      data : row
    }).afterClosed().subscribe(val=>{
      if (val=='update'){
        this.getAllEmployee();
      }
    })
  }

  deleteEmployee(id:string){
    this.api.deleteEmployee(id)
    .subscribe({
      next:(res)=>{
        alert("Employee Deleted Successfully!")
        this.getAllEmployee();
      },
      error:()=>{
        alert("Error While Deleting the Product!")
      }
    })
  }

  


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
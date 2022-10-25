import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { ServicesService } from '../services/services.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SignupComponent } from '../signup/signup.component';



@Component({
  selector: 'app-showstudent',
  templateUrl: './showstudent.component.html',
  styleUrls: ['./showstudent.component.css']
})
export class ShowstudentComponent implements OnInit {
  allstudents: any;
  filterData = "";

   
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private services:ServicesService,
    private dialog: MatDialog,
    ) { 

    this.services.displayAllStudent()
    .subscribe({
      next:(data: any)=>{
      // this.allstudents = data;
      // console.log(this.allstudents)
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }
    });
  }
  displayedColumns: string[] = ['first_name', 'last_name', 'stud_username', 'gender', 'father_name', 'roll_no', 'date_of_birth', 'class_code', 'user_type', 'action'];
  
  ngOnInit(): void {
  }


  applyFilter() {
    // console.log(this.filterData)
    this.services.searchStudent({data:this.filterData}).subscribe({
      next:(data: any)=>{
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator; 
      }  
    })

    // const filterValue = (event.target as HTMLInputElement).value;
    // this.dataSource.filter = filterValue.trim().toLowerCase();

    // if (this.dataSource.paginator) {
    //   this.dataSource.paginator.firstPage();
    // }
  }

  editStudentDetails(row: any){
    this.dialog.open(SignupComponent, {
      width: '40%',
      height: '70%',
      data: row
    })
  }

  deleteStudentDetails(username: string){
    this.services.deleteStudent(username).subscribe({
      next:(data)=>{
        alert("Record deleted successfully")
        location.reload();
      },  
      error:()=>{
        alert("Error while deleting");
      }    
    })
  
  }
  
}

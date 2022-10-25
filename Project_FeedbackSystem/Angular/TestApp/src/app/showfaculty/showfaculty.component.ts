import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { FacultySignupComponent } from '../faculty-signup/faculty-signup.component';
import { ServicesService } from '../services/services.service';

@Component({
  selector: 'app-showfaculty',
  templateUrl: './showfaculty.component.html',
  styleUrls: ['./showfaculty.component.css']
})
export class ShowfacultyComponent implements OnInit {
  allfaculty: any;
  
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private services:ServicesService,
    private dialog: MatDialog,
    ) { 
    this.services.displayAllFaculty().subscribe({
      next:(data: any)=>{
      this.allfaculty = new MatTableDataSource(data);
      this.allfaculty.paginator = this.paginator;
      this.allfaculty.sort = this.sort;
      // console.log(this.allfaculty)
    }
  });
  }
  displayedColumns: string[] = [
    'first_name',
    'last_name',
    'facul_username',
    'gender',
    'date_of_birth',
    'department',
    'joining_date',
    'qualification',
    'experience',
    'user_type',
    'main_subject',
    'action'
  ];
  ngOnInit(): void {
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.allfaculty.filter = filterValue.trim().toLowerCase();

    if (this.allfaculty.paginator) {
      this.allfaculty.paginator.firstPage();
    }
  }

  editFacultyDetails(row: any){
    this.dialog.open(FacultySignupComponent, {
      width: '40%',
      height: '70%',
      data: row
    })
  }

  deleteFacultyDetails(username: string){
    this.services.deleteFaculty(username).subscribe({
      next:(data)=>{
        alert("Record deleted successfully");
        location.reload();
      },  
      error:()=>{
        alert("Error while deleting");
      }    
    })
  
  }

}
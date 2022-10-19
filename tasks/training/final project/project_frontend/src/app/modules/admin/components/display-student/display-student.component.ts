import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { AdminServiceService } from '../../admin-service.service';

@Component({
  selector: 'app-display-student',
  templateUrl: './display-student.component.html',
  styleUrls: ['./display-student.component.css']
})
export class DisplayStudentComponent implements OnInit {

  students:any
  displayedColumns: string[]=['user', 'username','Action']
  
  constructor(private router:Router, private http:AdminServiceService) { }

  ngOnInit(): void {
    this.http.getStudents().subscribe({
      next:(resp)=>{

       
        this.students=resp
       
       
        console.log(this.students) 
      }
      
    })

  }

}

import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminServiceService } from '../../admin-service.service';

@Component({
  selector: 'app-adminhomepage',
  templateUrl: './adminhomepage.component.html',
  styleUrls: ['./adminhomepage.component.css']
})
export class AdminhomepageComponent implements OnInit {
  students:any
  teachers:any
  i:any
  j:any

  constructor(private router:Router, private http:AdminServiceService) { }

  ngOnInit(): void {
    this.http.getStudents().subscribe({
      next:(resp)=>{

       
        this.students=resp
       
        this.i=this.students.length
        console.log(this.students) 
      }
      
    })
    this.http.getTeachers().subscribe({
      next:(resp)=>{

       
        this.teachers=resp
        this.j=this.teachers.length
       
        console.log(this.teachers) 
      }
      
    })

    
  }



}

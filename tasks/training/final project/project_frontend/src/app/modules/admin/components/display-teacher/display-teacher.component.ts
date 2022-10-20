import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminServiceService } from '../../admin-service.service';

@Component({
  selector: 'app-display-teacher',
  templateUrl: './display-teacher.component.html',
  styleUrls: ['./display-teacher.component.css']
})
export class DisplayTeacherComponent implements OnInit {

  teachers:any
  displayedColumns: string[]=['user', 'user__username','user__first_name','user__last_name','user__email','user__mobile_no','qualification','position','Action']

  constructor(private router:Router, private http:AdminServiceService) { }

  ngOnInit(): void {

    this.http.getTeachers().subscribe({
      next:(resp)=>{

       
        this.teachers=resp
       
       
        console.log(this.teachers) 
      }
      
    })

  
    
  }

}

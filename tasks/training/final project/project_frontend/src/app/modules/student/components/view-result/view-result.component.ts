import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentServiceService } from '../../student-service.service';

@Component({
  selector: 'app-view-result',
  templateUrl: './view-result.component.html',
  styleUrls: ['./view-result.component.css']
})
export class ViewResultComponent implements OnInit {
h:any
i:any
j:any
k:any
course_name:any
present_score:any
percentage:any
  constructor( private router:Router, private http:StudentServiceService, private actRouter:ActivatedRoute) {


    this.actRouter.params.subscribe(data=>{
      console.log(data)
      this.course_name=(data['course_name'])
      this.present_score=(data['score'])
      console.log(this.course_name)
    })

   }

  ngOnInit(): void {
    this.http.getQuestionlength(this.course_name).subscribe({
      next:(resp)=>{
        this.h=resp
        console.log(this.h.length)
        this.k=this.h.length

        this.percentage=(this.present_score/this.k)*100
        console.log(this.percentage)
      }

    })

  }

  exampage(){
    this.router.navigate(['student/showexam'])
  }

}

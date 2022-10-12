import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TeacherServiceService } from '../../teacher-service.service';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {


  formNotValid: boolean =false
  formError?: string =""
  courses:any
  options:string[]=["option1","option2","option3","option4"]

  constructor(private http :TeacherServiceService, private router:Router) { }

  questionAddingForm:FormGroup=new FormGroup({

    question_name: new FormControl('', Validators.required),
    option1: new FormControl('',Validators.required),
    option2: new FormControl('',Validators.required),
    option3: new FormControl('',Validators.required),
    option4: new FormControl('',Validators.required),
    answer: new FormControl('', Validators.required),
    course: new FormControl('',Validators.required)

  })


  ngOnInit(): any {

    this.http.getCourses().subscribe(data=>{
      this.courses=data
      console.log(this.courses)
    })

  }

  addQuestion(){
    if (this.questionAddingForm.valid){
      this.http.addQuestion(this.questionAddingForm.value).subscribe(data=>console.log(data))
    }
    else {
      console.log('fill properly ');
      this.formNotValid = true
      // console.log(this.courseAddingForm.valid);
    }
  }

}

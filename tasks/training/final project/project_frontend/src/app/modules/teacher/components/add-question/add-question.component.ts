import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpServiceService } from 'src/app/modules/users/http-service.service';
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
  id:any
  question:any
  j:any

  constructor(private http :TeacherServiceService, private router:Router, private actRouter:ActivatedRoute, private httpUser:HttpServiceService) {

    this.actRouter.params.subscribe(data => {
      this.id = parseInt(data['id'])
      console.log('rakesh')
    })

    if(this.id){
      this.http.getQuestion(this.id).subscribe({
        next:(resp:any)=>{
          this.question=resp.question
          // this.j=resp.j

          this.questionAddingForm.get('question_name')?.setValue(this.question.question_name)
          this.questionAddingForm.get('option1')?.setValue(this.question.option1)
          this.questionAddingForm.get('option2')?.setValue(this.question.option2)
          this.questionAddingForm.get('option3')?.setValue(this.question.option3)
          this.questionAddingForm.get('option4')?.setValue(this.question.option4)
          this.questionAddingForm.get('answer')?.setValue(this.question.answer)
          this.questionAddingForm.get('course')?.setValue(this.question.course)
        }

      })
    }
    
   }

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

    this.http.getQuestion(this.id).subscribe({
      next:(data:any)=>{
        console.log(data)
        this.question=data.question;
      },
      error:(err)=>{
        console.log(err.data)
      }
    })

  }

  addQuestion(){
    if(this.id){

      this.http.updateQuestion(this.id, this.questionAddingForm.value).subscribe(data=>{
        console.log(data);
      })
      this.router.navigate(['teacher/display-question'])
    }

    // else (this.questionAddingForm.valid){
      else{
      this.http.addQuestion(this.questionAddingForm.value).subscribe(data=>{
        console.log(data)})
        this.router.navigate(['teacher/display-question'])
    }
  
  }

}

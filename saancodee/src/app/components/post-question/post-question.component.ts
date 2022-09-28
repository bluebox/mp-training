import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.css']
})
export class PostQuestionComponent implements OnInit {

  list = [{ id: 1, label: 'array' }, { id: 2, label: 'search' }]
  selectOptions: any = [
    {
      id: 1,
      text: 'array'
    },
    {
      id: 2,
      text: 'search'
    }
  ];

  questionForm = this.fb.group(
    {
      problem_name: ['', Validators.required],
      description: ['', Validators.required],
      hints: ['', Validators.required],
      test_cases: ['', Validators.required],
      selected: ['', Validators.required],
      outputs: ['', Validators.required],
      inbuilt_code: ['', Validators.required]
    }
  )

  constructor(private service: RegisterService, private fb:FormBuilder) { }

    onSubmit() {
      this.service.postQuestion(this.questionForm.value).subscribe((data) => {
        console.log(data)
      })
    }

  ngOnInit(): void {
  }

}

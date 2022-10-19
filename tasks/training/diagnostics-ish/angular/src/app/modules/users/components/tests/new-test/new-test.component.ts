import { Component, OnInit } from '@angular/core';
import { LabsService } from 'src/app/services/labs-service/labs.service';
import { TestsService } from 'src/app/services/tests-service/tests.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
@Component({
  selector: 'app-new-test',
  templateUrl: './new-test.component.html',
  styleUrls: ['./new-test.component.css']
})
export class NewTestComponent implements OnInit {
  labs : any
  responseMessage: string = '';
  formNotValid: boolean = false;
  constructor(private http : TestsService, private labHttp : LabsService ,private router :Router) { 
    this.labHttp.getLabs().subscribe({
      next:(resp :any)=>{
        this.labs = JSON.parse(resp['labs']);
        
      },
      error:(err)=>{
        console.log(err);
        
      }
    })
  }
  testForm: FormGroup = new FormGroup({
    test_id: new FormControl('', Validators.required),
    test_name: new FormControl('', Validators.required),
    test_description: new FormControl('', Validators.required),
    lab: new FormControl('', Validators.required),
  });

  onSubmit() {
    if (this.testForm.valid) {
      this.formNotValid = false
      this.http.addTest({ 'form': this.testForm.value }).subscribe({
        next: (resp) => {
          this.responseMessage = resp.message
          if (resp.action_status == "success") {
            this.router.navigate([''])
          }
        },
        error: (err) => {
          console.log(err);
        }
      })
    }
    else {
      this.formNotValid = true
    }
  }

  ngOnInit(): void {
  }

}

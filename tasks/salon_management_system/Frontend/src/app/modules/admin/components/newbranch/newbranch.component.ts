import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-newbranch',
  templateUrl: './newbranch.component.html',
  styleUrls: ['./newbranch.component.css']
})
export class NewbranchComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY

  formNotValid : boolean = false
  formError ?: string =""
  errorMessage : string = ""

  constructor(private http :HttpserviceService ,private router: Router) { }
  newBranchForm : FormGroup = new FormGroup({
    branch_id : new FormControl("",Validators.required),
    branch_name : new FormControl("",[Validators.required,Validators.maxLength(50)]),
    location : new FormControl("",[Validators.required,Validators.maxLength(500)])
  })
  ngOnInit(): void {
  }
  addBranch(){
    console.log(this.newBranchForm.value)
    if(this.newBranchForm.valid){
      this.http.addBranch(this.newBranchForm.value).subscribe(data => {console.log(data);
        this.errorMessage = data.msg;
      // console.log(this.errorMessage)
        if(this.errorMessage == "successful"){
          console.log("ss")
          alert("branch added successfully");
          this.router.navigate(['admin/branch']);
        }
        else{
          this.errorMessage=data.msg
        }
        }
      )
    }
    else{
      this.formNotValid=true
    }
  }
}

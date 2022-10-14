import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-newservice',
  templateUrl: './newservice.component.html',
  styleUrls: ['./newservice.component.css']
})
export class NewserviceComponent implements OnInit {
  formNotValid : boolean = false
  formError ?: string =""
  errorMessage : string = ""

  constructor(private http :HttpserviceService ,private router: Router) { }
  newServiceForm : FormGroup= new FormGroup({
    service_id : new FormControl("",Validators.required),
    service_name : new FormControl("",[Validators.required,Validators.maxLength(50)]),
    Amount_to_be_paid : new FormControl("",Validators.required)
  })
  ngOnInit(): void {
  }

  addService():void{
    console.log(this.newServiceForm.value)
    if(this.newServiceForm.valid){
    this.http.addService(this.newServiceForm.value).subscribe(data => {console.log(data);alert("branch added successfully");this.router.navigate(['admin/services']);}
   )
    }
    else{
      this.formNotValid=true
    }
  }

  
}

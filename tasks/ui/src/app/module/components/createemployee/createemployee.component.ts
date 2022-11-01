import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ServicesService } from 'src/app/services.service';

@Component({
  selector: 'app-createemployee',
  templateUrl: './createemployee.component.html',
  styleUrls: ['./createemployee.component.css']
})
export class CreateemployeeComponent  {
  createemployeeform : any 


  constructor(private service :ServicesService) { 
  this.createemployeeform =  new FormGroup({
    emp_id : new FormControl(),
    emp_name : new FormControl(),
    emp_mobile : new FormControl(),
      emp_email : new FormControl(),
    emp_role : new FormControl(),
    facility_id : new FormControl(),
    emp_password: new FormControl(),

  })
}
createemployeeprofile(){
  console.log(this.createemployeeform.value);
  this.service.createmployee(this.createemployeeform.value).subscribe(data=> {
    console.log(data);
    
  })
  
}


  

}

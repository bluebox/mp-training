import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-createmanager',
  templateUrl: './createmanager.component.html',
  styleUrls: ['./createmanager.component.css']
})
export class CreatemanagerComponent implements OnInit {
createmanagerform : any 

  constructor(private service : ServicesService) {
    this.createmanagerform =  new FormGroup({
      emp_id : new FormControl('',Validators.required),
      emp_name : new FormControl('',Validators.required),
      emp_mobile : new FormControl('',Validators.required),
        emp_email : new FormControl('',Validators.required),
      emp_role : new FormControl('',Validators.required),
      facility_id : new FormControl('',Validators.required),
      emp_password: new FormControl('',Validators.required),
  
    })
   }
   createmanager(){

   }

  ngOnInit(): void {
  }

}

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.css']
})
export class EditprofileComponent implements OnInit {
  employeeform :any
  user :any 
  
  constructor(private  service :ServicesService) {
    let userdata=sessionStorage.getItem("userdetails")
    if(userdata){
    this.user=JSON.parse(userdata)
      console.log(this.user.emp_id);
      
    }
    this.employeeform =  new FormGroup({
      emp_name : new FormControl(this.user.emp_name),
      emp_mobile : new FormControl(this.user.emp_mobile),
        emp_email : new FormControl(this.user.emp_email),
      emp_role : new FormControl(this.user.emp_role),
      facility_id : new FormControl(this.user.facility_id),
      emp_password: new FormControl(),

    })
   

   }
   editprofile(){
    console.log(this.employeeform.value);
    
    this.service.editprofile(this.employeeform.value).subscribe( data => console.log(data)
    )

   }

  ngOnInit(): void {
  }

}

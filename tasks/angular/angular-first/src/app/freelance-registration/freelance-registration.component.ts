import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {ServiceService} from '../service.service'
@Component({
  selector: 'app-freelance-registration',
  templateUrl: './freelance-registration.component.html',
  styleUrls: ['./freelance-registration.component.css']
})
export class FreelanceRegistrationComponent implements OnInit {
reset() {
  this.freelanceRegister.reset()
}
  receivedData : string = ''
  freelanceRegister!: FormGroup;
  constructor(private fb:FormBuilder,private service :ServiceService) { }

  ngOnInit(): void {
    this.freelanceRegister = this.fb.group({
      'first_name'  : new FormControl ('',Validators.required),
      'last_name'  : new FormControl ('',Validators.required),
      'email_id' : new FormControl ('',[Validators.required,Validators.email]),
      'phone_number'  : new FormControl ('',[Validators.required,Validators.maxLength(12)]),
      'country'  : new FormControl ('',[Validators.required]),
      'password' : new FormControl ('',[Validators.required,Validators.maxLength(25)]),
    })
  }
  registerSubmit(){
    this.service.freelancerRegistration(this.freelanceRegister.value).subscribe((data : any)=> console.log(data)
    )
    // console.log(this.freelanceRegister.value)
   }

}



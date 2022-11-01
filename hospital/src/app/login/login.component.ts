import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BookingService } from '../booking.service';
import { ServercomunicationService } from '../servercomunication.service';
export interface UserFormInterface {
  email:string
  password:string
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers:[ServercomunicationService]
})
export class LoginComponent implements OnInit {
  formlogIn: FormGroup = new FormGroup({});
  userForm:UserFormInterface={
    email: '',
    password: ''
  }
  constructor(private fb: FormBuilder,private api:ServercomunicationService,private router:Router,private book:BookingService) { }

  ngOnInit(): void {

    this.formlogIn = this.fb.group({
      email: [null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: [null, [Validators.required]],
  });
  }
saveDetails(formlogIn: any): void {
  const data= JSON.stringify(formlogIn.value)
  // alert('SUCCESS!! :-)\n\n' +data);
  // console.log(this.formlogIn.value.email)
  this.userForm!.email=this.formlogIn.value.email
  this.userForm!.password=this.formlogIn.value.password
  console.log(this.formlogIn)

  this.api.login(formlogIn.value).subscribe(
    (data: any)=>{
      console.log(data);

        this.api.getuser().subscribe(
          (res:any)=>{
            this.book.setUserData(res);
            console.log(res.type_of_user);

            if(res.type_of_user=="P"){
              this.router.navigate(['/appointment/'])
            }
            else if(res.type_of_user=="D"){
              // this.router.navigate(['/slot/'])
              this.router.navigate(['/doctorHome/'])

            }
          },
          (error:any)=>{
            console.log(error);
          }
        )


    },
    (error: any)=>{

  console.log(error);
})
}


}

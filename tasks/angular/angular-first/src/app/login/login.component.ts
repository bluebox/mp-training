import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ServiceService } from '../service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  receivedData: string = ''
  login!: FormGroup;
  user_type = [
    { id: 1, name: "freelancer" },
    { id: 2, name: "client" },
  ];
  constructor(private fb: FormBuilder, private service: ServiceService, private router: Router) { }

  ngOnInit(): void {
    this.login = this.fb.group({
      'email_id': new FormControl('', [Validators.required, Validators.email]),
      'password': new FormControl('', [Validators.required, Validators.maxLength(25)]),
      'user_type': new FormControl('', Validators.required)
    })
  }
  loginSubmit() {
    console.log(this.login.value.email_id);
    if (this.login.value.user_type == 1) {
      this.service.FreelancerLogin(this.login.value).subscribe((data: any) => {
        
        window.sessionStorage.setItem('token', JSON.stringify(data.access_token));
        alert(data.msg);
        console.log(data.access_token);
        console.log(data);
        

        this.router.navigate(['freelance_login_page'])
      }
        , (err: any) => {
          alert('invalid email or password')
          console.log('error');
        });

    }

    else {
      this.service.ClientLogin(this.login.value.email_id).subscribe((data: any) => {
        window.sessionStorage.setItem('cuser', JSON.stringify(data));
        alert('login successfully');
        this.router.navigate(['client_login_page'])
      }
        , (error: any) => {
          console.log('error')
        }
      )
    }
  }

  get password(){
    return this.login.get('password');
  }

  get email(){ 
     return this.login.get('email_id'); }

  get country(){
    return this.login.get('user_type'); 
  }
  onLoad = false;
}

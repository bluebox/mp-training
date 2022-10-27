import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { MatSnackBarModule } from '@angular/material/snack-bar';

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
  constructor(private fb: FormBuilder, private service: ServiceService, private router: Router,private snackBar : MatSnackBar) { }

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
        // alert(data.msg);

        const config = new MatSnackBarConfig();
        config.panelClass = ['background-red'];
        config.duration = 2500;
        config.horizontalPosition = 'center';
        config.verticalPosition = 'top';
        this.snackBar.open(data.msg,'x',config);
        setTimeout( () => this.router.navigate(['freelance_login_page']),1);
        // this.router.navigate(['freelance_login_page']);
        console.log(data.access_token);
        console.log(data);
        

      }
        , (err: any) => {
          alert('invalid login credentials')
          console.log('error');
        });

    }

    else {
      this.service.ClientLogin(this.login.value.email_id).subscribe((data: any) => {
        window.sessionStorage.setItem('cuser', JSON.stringify(data));
        const config = new MatSnackBarConfig();
        config.panelClass = ['background-red'];
        config.duration = 2500;
        config.horizontalPosition = 'center';
        config.verticalPosition = 'top';
        this.snackBar.open('successfully logged in','x',config);
        setTimeout( () => this.router.navigate(['client_login_page']),1);
        // alert('login successfully');
        // this.router.navigate(['client_login_page'])
      }
        , (error: any) => {
          alert("invalid login credentials");
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

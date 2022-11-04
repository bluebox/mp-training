import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employeelogin',
  templateUrl: './employeelogin.component.html',
  styleUrls: ['./employeelogin.component.css']
})
export class EmployeeloginComponent implements OnInit {
  loginDiv:any;
  register:any;
  constructor() { }

  ngOnInit(): void {
  }


     login(){
      this.loginDiv = document.getElementById('Login');
      this.loginDiv.style.background = 'linear-gradient(to right , #1ae939, #34e7ea)'
      this.register.style.background = "white"
       
    }
     Register(){
      
      this.register = document.getElementById('register');
      this.loginDiv = document.getElementById('Login');
      this.loginDiv.style.background = "white"
      this.register.style.background = 'linear-gradient(to right , #1ae939, #34e7ea)'
      
    }

}

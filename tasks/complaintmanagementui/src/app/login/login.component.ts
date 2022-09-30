import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private service :ServicesService,private route:Router) { }
  userdetails :any 

  ngOnInit(): void {
  }

  signin(){
   let  userid=document.getElementById('id') as HTMLInputElement
   let  password=document.getElementById('password') as HTMLInputElement
   console.log(userid.value);
   this.service.getLogindetails(Number(userid.value)).subscribe(data=>{this.userdetails=data
   sessionStorage.setItem("userdetails",JSON.stringify(this.userdetails)) 
   var user = sessionStorage.getItem('userdetails');
  //  var userdata=JSON.parse(sessionStorage.getItem('userdetails'))
    // JSON.parse(user)
    if (this.userdetails!=undefined){

    
   console.log(sessionStorage.getItem("userdetails"));
      let role=document.getElementById("role") as HTMLInputElement 
      


   if(role!=undefined && role.value=='employee' && password.value == this.userdetails.emp_password){
    console.log("hello");
    this.route.navigate(['./signin'])
    
   }
   if(role!=undefined && role.value=='manager' && password.value == this.userdetails.emp_password && this.userdetails.emp_role=='manager'){
    console.log("hello");
    this.route.navigate(['./manager'])
    
   }
  }})
   
   
  }

}

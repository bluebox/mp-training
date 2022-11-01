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
token={}
user : any 
successmsg : string =""
  ngOnInit(): void {
  }
  signin(){
    let  userid=document.getElementById('id') as HTMLInputElement
    let  password=document.getElementById('password') as HTMLInputElement
    console.log(userid.value);
    this.service.getLogindetails(Number(userid.value)).subscribe(data=>{this.user=data
    sessionStorage.setItem("userdetails",JSON.stringify(this.user.userdetails)) 
    console.log(this.user.token);
    
    var user = sessionStorage.getItem('userdetails');
    this.userdetails=this.user.userdetails
  
     if (this.userdetails!=undefined){

    console.log(sessionStorage.getItem("userdetails"));
 
    if( this.userdetails.emp_role=='Ase' && password.value == this.userdetails.emp_password){
     console.log("hello");
     this.route.navigate(['./home'])
     
    }
    if(  password.value == this.userdetails.emp_password && this.userdetails.emp_role=='manager'){
     console.log("hello");
     this.route.navigate(['./manager'])
     
    }
    if(password.value==this.userdetails.emp_password && this.userdetails.emp_role=='admin'){
      console.log("admin log");
      
      this.route.navigate(['./admin'])
    }
   }})
    
    
   }


}

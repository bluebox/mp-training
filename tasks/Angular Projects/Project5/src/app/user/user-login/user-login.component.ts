import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserServicesService } from 'src/app/services/user-services.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit {
  user_data: any;

// showSpinner: any;
  
//   postdata= {
//     "password": "pbkdf2_sha256$390000$G8VgxOWBpBYaA86MsQaoVY$UXRl8x1gVTH+Q8MI1audaf3JJeAqUZY8oK9ZS3Tt784=",
//     "username": "logee12s3erehhgggaain",
//     "first_name": "logfeg1wwxffxzz23in",
//     "last_name": "kussmffacccr",
//     "email": "logcaaxasswxccicccn@gmail.com",
    
// }
//   login: FormGroup<{ Username: FormControl<string | null>; password: FormControl<string | null>; }>;
// 
//   username : string ="";
//   password : string ="";
//   password1 : any ="";
//   show: boolean= false;
//   Login(){
  // console.log("user name is " + this.username)
  // this.clear();
//   this.userService.getData().subscribe(data => { 
//       this.password1=data
//     console.log(data)},(error)=>{
//     console.log("error haa occured");
// })
// this.userService.sendData(this.postdata).subscribe(data => { 

//   console.log(data)},(error)=>{
  
//   console.log("error haa occured");
// })
//   }
  // clear(){
  // this.username ="";
  // this.password = "";
  // this.show = true;
  // }
  constructor(private userService: UserServicesService,private fb:FormBuilder , private route :Router, private activateRoute: ActivatedRoute){

  }
login!:FormGroup;
message:any={}
ApiMessage:any={}
rememberMe!:boolean;
sending:boolean=false
isAuthorised()
{
  return this.userService.hasAccessToken()
}  ngOnInit(): void {
    this.activateRoute.queryParams
    .subscribe((params) => {
      this.message = params;
    }
  );
  this.login=this.fb.group({
    email:new FormControl('', [Validators.required]),
    password:new FormControl('', [Validators.required]),
  })
  const dump={
    'email':localStorage.getItem('email')||'',
    'password':''
  
  }
  this.login.setValue(dump)
  }
  x={
    "email":"login@gmail.com",
    "password":"1234"
}

onCheckboxChange(event:any){
  if(event){
    this.rememberMe=true
  }
}
is_Authenticated():Boolean {
  return this.userService.hasAccessToken()
 }
  loginSubmitForm1(){
    this.sending=true
    
    if(this.login.valid){
       this.userService.login(this.login.value).subscribe(data => { this.user_data = data;
      console.log(data);
      this.userService.is_Authenticated = true;
      this.userService.userData=this.user_data;
      this.ApiMessage=this.user_data;
      if(this.login.value.email){
        localStorage.setItem('email', this.login.value.email);
      }else{
        localStorage.removeItem('email');
      }
      if (this.rememberMe){
        const x = this.login.value.email
        //store username to browser storage
        localStorage.setItem('email', this.login.value.email);
        localStorage.setItem('remember',`${this.rememberMe}`);
      }
      else{
        localStorage.removeItem('remember');
      }
      if(this.ApiMessage && this.ApiMessage.user_id){
        this.userService.setToken(this.ApiMessage.token.access)
        localStorage.setItem('mytoken', this.ApiMessage.token.access)
        localStorage.setItem('refresh_token', this.ApiMessage.token.refresh)
        this.route.navigate(['/user/Admin']);

      }
     
      
      },err => {
        this.sending=false
         console.log("error has occured while connectiong"); });
    }
    
   console.log(this.login.value)
  }




  responsedata:any={};
  loginSubmitForm(){
    if(this.login.valid){
      this.userService.login(this.login.value).subscribe(user => {
        if (user!=null) {
          this.responsedata=user;
          
          localStorage.setItem('mytoken', this.responsedata.token.access)
          this.userService.is_Authenticated = true
          this.userService.userData=user;
          this.ApiMessage=user;
          if(this.login.value.email){
            localStorage.setItem('email', this.login.value.email);
          }else{
            localStorage.removeItem('email');
          }
          if (this.rememberMe){
            const x = this.login.value.email
            //store username to browser storage
            localStorage.setItem('email', this.login.value.email);
            localStorage.setItem('remember',`${this.rememberMe}`);
          }
          else{
            localStorage.removeItem('remember');
          }
          console.log("hello bro")
          if(this.ApiMessage && this.ApiMessage.user_id){
            this.userService.setToken(this.ApiMessage.token.access)
            localStorage.setItem('mytoken', this.ApiMessage.token.access)
            localStorage.setItem('refresh_token', this.ApiMessage.token.refresh)
            this.route.navigate(['/user/Admin']);
    
          }
         
  
  
      }
   
    },
    err=>{ 
      console.log("error has occured while connectiong");
    })
    }
}

}

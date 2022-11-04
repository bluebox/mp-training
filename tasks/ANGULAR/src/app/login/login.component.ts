import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators,FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { SharedService } from '../shared.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username :number | undefined;
  password :string='';
  is_admin:boolean =false;
  profileForm:any=FormGroup;
  details:any=[];
  data:any=[]
  token:any=[]
  // login:any = FormGroup;
  // users:any = [];
  constructor(private fb:FormBuilder, private router:Router, private sharedsrv:SharedService) { }

  ngOnInit(): void {
    // this.login = this.fb.group({
    //   name:['',Validators.required],
    //   email:['',Validators.compose([Validators.required,Validators.email])]
    // })
    this.profileForm = new FormGroup({
      email: new FormControl('',[Validators.email,Validators.required]),
      password: new FormControl('',[Validators.minLength(3),Validators.required]),
      is_admin:new FormControl('')

    });

   this.refreshDemoList();
   console.log(this.details);

  }
  // loginForm(data:any){
  //   console.log(data)
  //   if(data.name){
  //     this.users.forEach((item:any) => {
  //       if(item.username === data.name && item.password === "abc@gmail.com"){
  //         localStorage.setItem("isLoggedIn","true");
  //         this.router.navigate(['demo']);
  //       }
  //       else{
  //         localStorage.clear();
  //       }

  //     });
  //   }


  // goToRegister(){
  //   this.router.navigate(['register'])
  // }

onsubmit()
{
  // console.log(data);
  // this.username = data.username;
  // this.password = data.password;
  // this.sharedsrv.getDemolist().subscribe(val=>{
  //   this.details=val;});


    // this.details.forEach((item:any) => {
    //   console.log(item.passenger_id);

    //   if( item && data.username == item.passenger_id && data.password == item.password)
    //   {

    //   console.log("hello");

    //    sessionStorage.setItem("isLoggedIn","true");
    //    this.router.navigate(['admin'])

    //   }

    // });
    console.log(this.profileForm.value);

    this.sharedsrv.loginpassenger(this.profileForm.value).subscribe(val=>{
      this.data=val;
      localStorage.setItem("isLoggedIn",this.data.access_token);
      localStorage.setItem("name",this.data.user)
      localStorage.setItem("userid",this.data.userid)
      this.token=localStorage.getItem("isLoggedIn")

      console.log(this.token);

      console.log(this.data.is_admin);

      if(this.data.is_admin=="false")
          this.router.navigate(['user'])
      else
      this.router.navigate(['admin'])
    })


  }

  refreshDemoList(){
    this.sharedsrv.getPassengerlist().subscribe(data=>{
     this.details=data;});

    }


    // getCookie(cname:string) {
    //   let name = cname + "=";
    //   let ca = document.cookie.split(';');
    //   for(let i = 0; i <ca.length; i++) {
    //     let c = ca[i];
    //     while (c.charAt(0) == ' ') {
    //       c = c.substring(1);
    //     }
    //     if (c.indexOf(name) == 0) {
    //       return c.substring(name.length, c.length);
    //     }
    //   }
    //   return "";
    // }




    get user()
    {
      return this.profileForm.get("email")
    }
    get pass()
    {
      return this.profileForm.get("password")
    }
    }


import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SharedService } from 'src/app/shared.service';
import { ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-home',
  templateUrl:'./home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  recieved:string='';
  signform:any=FormGroup ;
  signupDetails:any=[];
  passenger_id!:number;
  passenger_name:string="";
  mobile_number:string="";
  passenger_city:string="";
  password:string="";
  email:string="";
  is_admin:string="";
  // userid:Number|undefined;
  // email:string="";
  // mobile:string="";
  // username:string="";
  // password:string="";


  constructor(private fb:FormBuilder, private router:Router, private service:SharedService) {

   }

  ngOnInit(): void {
    //  this.route.params.subscribe(data=>{
    // this.recieved = data['text']

  // })
  this.signform = new FormGroup({
    userid: new FormControl('',[Validators.required]),
    email: new FormControl('', [Validators.email,Validators.required]),
    mobile: new FormControl('',Validators.required),
    city: new FormControl('',Validators.required),
    username:new FormControl('',Validators.required),
    password:new FormControl('',Validators.required)


  });
  }
 onsubmit()
 {

   const val=this.signform.value;

  this.passenger_id=val.userid,
   this.passenger_name=val.username,
    this.mobile_number=val.mobile,
    this.passenger_city=val.city,
    this.email=val.email,
    this.password=val.password
    this.is_admin="false"

 var item={"passenger_id":this.passenger_id,
  "passenger_name":this.passenger_name,
  "mobile_number":this.mobile_number,
  "email":this.passenger_city,
  "passenger_city":this.passenger_city,
  "password":this.password,
  "is_admin":"false"
 }
   var item1={"passenger_id": this.passenger_id, "passenger_name": this.passenger_name, "mobile_number": this.mobile_number, "email": this.email, "passenger_city": this.passenger_city, "password": this.password, "is_admin":this.is_admin}
// var item2={"passenger_id": 78, "passenger_name": "dgd", "mobile_number": "70134", "email": "achyuthchamakuri2459@gmail.com", "passenger_city": "ff", "password": "1234"}
    console.log(item1)
    // console.log(item2)
    this.service.addPassengerlist(item1).subscribe((res =>{
      alert(res.toString());
    }))

    this.router.navigate(['login'])
 }

 get user()
 {
  return this.signform.get("userid")
 }
 get mail()
 {
  return this.signform.get("email")
 }
}

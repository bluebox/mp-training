import { getLocaleNumberFormat } from '@angular/common';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SharedService } from 'src/app/shared.service';
import { LoginComponent } from './login/login.component';


enum digits
{
  one =1,
  two,
  three,
  four,

}



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  hide:boolean=true;
  title = 'ADBMS';
  enumDigits =digits;
  // printMedia = PrintMedia;

  items=["one","two","three"]
user_id:number=0;
Email: string=''
Mobile: string=''
city: string='';
Username: string=''
myprofile: any;
userid:any;
active:any;


  addnewItem(value:string)
  {
    this.items.push(value);
  }


  currentdata="parent data";

constructor(private router:Router,private service:SharedService)
{


}

file: any;


updateFile(e:any){
  this.file = e.target.files[0]
}
updateName(e:any){
  this.user_id= e.target.value
}

ngOnit() :void
{

}

login()
{
  console.log(this.hide);
  this.router.navigate(['login']);
  this.hide=!this.hide;


}

 logout(){
  this.hide=!this.hide
  localStorage.removeItem("name")
  this.router.navigate(['dashboard']);

  // this.service.logout().subscribe(data=>
  //   {
  //     alert(data.toString());
  //     this.router.navigate(['login']);
  //   })

}

onclick()
{
  if(localStorage.getItem("name"))
  this.router.navigate(['user'])
}

upload()
{
  const formData = new FormData();
  formData.append('Username', this.Username);
  formData.append('file', this.file);
  this.service.uploadprofile(formData).subscribe(data=>
    {
      console.log(data);

    })
    var item={"passenger_id":this.user_id,"passenger_name":this.Username,"mobiile_number":this.Mobile,"passenger_city":this.city,
  "email":this.Email, }

 this.service.editprofile(item).subscribe((val:any)=>
 {
   alert("updated successfully");
 })

  }


onchange()
{
  this.active=localStorage.getItem("name")
  if(this.active)
  {
  this.userid=localStorage.getItem("userid")
  var item={"userid":1}
  this.service.mydetails(item).subscribe((data:any)=>
  {
    this.myprofile=JSON.parse(JSON.stringify(data));
    console.log(this.myprofile[0]);


 this.user_id=this.myprofile[0].passenger_id;
 this.Email=this.myprofile[0].email;
 this.Mobile=this.myprofile[0].mobile_number;
 this.city=this.myprofile[0].passenger_city;
 this.Username=this.myprofile[0].passenger_name;

  })

}
}
}


// enum PrintMedia {
//   Newspaper = 1,
//   Newsletter = getPrintMediaCode('newsletter'),
//   Magazine = Newsletter * 3,
//   Book = 10
// }

// function getPrintMediaCode(mediaName: string):number {
//   if (mediaName === 'newsletter') {
//       return 5;
//   }
//   return 0;

// }
// let array = ["apple","banana","mango","guava"]

// let arrayobject:string;

// arrayobject= array[0];
// console.log(arrayobject);







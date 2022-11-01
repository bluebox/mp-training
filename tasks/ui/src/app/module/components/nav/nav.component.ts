import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from 'src/app/services.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {
data :any 
employeelist : any 
  constructor( private router :Router ,private service : ServicesService) { }
  getEmployee(name:string){

      this.service.getEmployee(name).subscribe(data=>{this.data=data;console.log(this.data);
        console.log(this.data.msg);
        if(this.data.msg == "no employee found") {
          alert(" no user found")
          console.log("1234");
          
           
        }
        else this.router.navigate(['../manager/particularemployee',name]);
        
      })
      

   }
   enter(text:string){
    this.getEmployee(text)
    
   }
  onsearch(){
    let name=document.getElementById("inputname") as HTMLInputElement 
      
       console.log(name.value);
       this.getEmployee(name.value)
     }
     displayemployee(event : any ){
      let name=document.getElementById("inputname") as HTMLInputElement 
      console.log(name.value);
      if(name.value.length >1){
        this.service.employeesearch(name.value).subscribe(data => this.employeelist=data
          )
          console.log(this.employeelist);
      }
      else {
        this.employeelist=[]
      }
    }
    logout(){
      sessionStorage.removeItem('userdetails');
      this.router.navigate(['../login'])
     }

  ngOnInit(): void {
  }

  // complaints(){ 
  //   this.router.navigate()
  // }

}

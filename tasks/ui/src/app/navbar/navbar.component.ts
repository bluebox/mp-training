import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { from } from 'rxjs';
import { ServicesService } from '../services.service';
import { Employee } from '../Facility';
import { Router, RouterLink } from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  
  data :any ;
  employeelist : any ;
  name1:string ="";

  @Output() emitter= new EventEmitter();

  constructor(private service: ServicesService,private router : Router) { 
    
  }
  onsearch(){
 let name=document.getElementById("inputname") as HTMLInputElement 
    this.name1=name.value;
    console.log(name.value);
    this.getEmployee(name.value)
  }

  getEmployee(name:string){
   
    this.emitter.emit(name)
    
      this.service.getEmployee(name).subscribe(data=>{this.data=data;console.log(this.data);
        console.log(this.data.msg);
        if(this.data.msg == "no employee found") {
          alert(" no user found")
          console.log("1234");
          
           
        }
        else this.router.navigate(['../home/'+name]);
        
      })
      

   }
   usercomplaints(){
  //  this.service.getComplaints().subscribe( data => )
    
    // this.router.navigate("/complaints")
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
    
    

    
    // console.log();
    
   }
   viewprofile(){
    
   }
   logout(){
    sessionStorage.removeItem('userdetails');
    this.router.navigate(['../login'])
   }

  ngOnInit(): void {
  }

}

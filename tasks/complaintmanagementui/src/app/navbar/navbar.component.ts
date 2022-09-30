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
  
  data !:Employee ;
  employeelist : any ;
  name1:string ="";
  @Output() emitter= new EventEmitter();

  constructor(private service: ServicesService,private router : Router) { 
    
  }

  getEmployee(name:string){
    // let name=document.getElementById("inputname") as HTMLInputElement 
    // this.name1=name.value;
    // console.log(name.value);
    this.emitter.emit(name)
    
      this.service.getEmployee(name).subscribe(data=>{this.data=data;console.log(this.data);
      })
      let profilepic=document.getElementById("profilepic") as HTMLImageElement
      if(this.data){
        profilepic.src="/home/mphs/Downloads"+this.data.emp_pic

      }
   }
   usercomplaints(){
  //  this.service.getComplaints().subscribe( data => )
    
    // this.router.navigate("/complaints")
   }
   displayemployee(event : any ){
    let name=document.getElementById("inputname") as HTMLInputElement 
    console.log(name.value);
    this.service.employeesearch(event.key).subscribe(data => this.employeelist=data
    )
    console.log(this.employeelist);
    
    console.log(event.key);
    
    // console.log();
    
   }

  ngOnInit(): void {
  }

}

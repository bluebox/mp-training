import { Component, OnInit } from '@angular/core';
import { ServicesService } from '../services.service';
import {Complaint } from '../Facility'

@Component({
  selector: 'app-complaints',
  templateUrl: './complaints.component.html',
  styleUrls: ['./complaints.component.css']
})
export class ComplaintsComponent implements OnInit {
  complaints :any
  displaypopup=false
  device: any | undefined
  constructor(private service : ServicesService) {
    this.service.getComplaints().subscribe(data =>this.complaints = data )
    console.log(this.complaints);

    if(this.complaints){
      // console.log(this.complaints.emp_id!=undefined);
      console.log(this.complaints);
      
    }

   }
   displaypopupfun(id:number){
    this.service.getComplaint(id).subscribe(data =>this.device = data )
    console.log(this.device);
    
    if(this.displaypopup==true){
      this.displaypopup=false
    }
    else this.displaypopup=true;
   }

  ngOnInit(): void {

  }

}

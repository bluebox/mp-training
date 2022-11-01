import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-employeecard',
  templateUrl: './employeecard.component.html',
  styleUrls: ['./employeecard.component.css']
})
export class EmployeecardComponent implements OnInit {
 emp_name : any
 data : any
  constructor(private service : ServicesService, private arouter : ActivatedRoute) {
     this.arouter.params.subscribe(data =>
     this.emp_name=data['emp_id'] )
     this.service.getEmployee(this.emp_name).subscribe(data=>{this.data=data;console.log(this.data);
     })
     
   }

  ngOnInit(): void {
  }

}

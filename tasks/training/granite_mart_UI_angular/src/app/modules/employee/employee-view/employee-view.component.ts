import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataServiceService } from 'src/app/services/data-service.service';

@Component({
  selector: 'app-employee-view',
  templateUrl: './employee-view.component.html',
  styleUrls: ['./employee-view.component.css']
})
export class EmployeeViewComponent implements OnInit {

  employee_id:any
  employee_data:any
  constructor(private aroute:ActivatedRoute, private service:DataServiceService) {
    this.aroute.params.subscribe(data=>this.employee_id=data['employee_id'])
    this.service.getEmployee(this.employee_id).subscribe(data=>{this.employee_data=data})
   }

  ngOnInit(): void {
  }

}

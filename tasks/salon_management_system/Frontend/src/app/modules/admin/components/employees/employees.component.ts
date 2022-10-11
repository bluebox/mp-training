import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  subscription : Subscription = Subscription.EMPTY
  employees : any;
  displayedColumns :string[]=["id","username","first_name","last_name","email","employee__emp_id","employee__role","employee__emp_contact_number","employee__branch_id"];

  constructor(private http : HttpserviceService) { }

  ngOnInit(): void {
    this.subscription =this.http.getEmployee().subscribe((data) =>{this.employees = data ;console.log(data)});
  }

}

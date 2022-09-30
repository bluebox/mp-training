import { Component, OnInit } from '@angular/core';
import { Component1Service } from '../component1/service/component1.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.scss'],
  providers:[Component1Service]
})
export class EmployeeComponent implements OnInit {
empName: string="Ramu";
  constructor(private employeservice: Component1Service) { }

  ngOnInit(): void {
  }

}

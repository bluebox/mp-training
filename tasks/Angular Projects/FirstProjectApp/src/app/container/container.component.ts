import { AfterContentInit, Component, ContentChild, ContentChildren, Host, OnInit } from '@angular/core';
import { Component1Service } from '../component1/service/component1.service';
import { EmployeeComponent } from '../employee/employee.component';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.scss'],
  providers: [Component1Service]
})
export class ContainerComponent implements OnInit , AfterContentInit{
@ContentChild(EmployeeComponent) employee!:EmployeeComponent;
constructor(@Host() private employeservice: Component1Service) { }

  ngAfterContentInit(): void {
    console.log(this.employee);
    this.employee.empName = 'Harish';
    console.log(this.employee);

  }

  ngOnInit(): void {
  }

}

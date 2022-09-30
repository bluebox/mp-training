import { AfterContentInit, Component, ContentChild, OnInit } from '@angular/core';
import { EmployesComponent } from '../employes/employes.component';

@Component({
  selector: 'app-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.scss']
})
export class ContainerComponent implements OnInit , AfterContentInit   {

  constructor() { }
  ngAfterContentInit(): void {
console.log(this.employee)
this.employee.empName='shyam_k'
  }
@ContentChild(EmployesComponent) employee!: EmployesComponent;
  ngOnInit(): void {
  }

}

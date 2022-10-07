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
  users : any;
  displayedColumns :string[]=["id","username","first_name","last_name","email", "is_staff"];

  constructor(private http : HttpserviceService) { }

  ngOnInit(): void {
    this.subscription =this.http.getUser().subscribe((data) =>{this.users = data ;console.log(data)});
  }

}

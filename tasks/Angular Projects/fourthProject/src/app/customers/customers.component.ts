import { Component, OnInit } from '@angular/core';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
users:any;
  constructor(private userService:UsersService) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(data=>{
      console.log(data)
      this.users=data;
    },(error)=>{
      console.log('unable to proceed ',error)
    })
  }

}

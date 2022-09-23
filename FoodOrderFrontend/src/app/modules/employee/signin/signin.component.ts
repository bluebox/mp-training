import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/interface';
import { Customer } from 'src/app/interfaces/customer';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  public customer:Customer[]=[];

  constructor(private user:UserService) { }

  ngOnInit(): void {
    this.user.getUser().subscribe((data)=>{this.customer=data;
      console.log(this.customer)
    })
   


  }

  

}

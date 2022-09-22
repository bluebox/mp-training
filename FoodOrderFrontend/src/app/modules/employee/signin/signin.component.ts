import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/interface';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  public student:Student[]=[];

  constructor(private user:UserService) { }

  ngOnInit(): void {
    this.user.getUser().subscribe(data=>this.student=data)
   

  }

}

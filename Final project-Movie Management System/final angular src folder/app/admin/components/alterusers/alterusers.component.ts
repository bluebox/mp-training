import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UserInterface } from 'src/app/interface/user';
import { UserdataService } from 'src/app/services/userdata.service';
import { SignupComponent } from 'src/app/signup/signup.component';
import { AddAdminComponent } from '../add-admin/add-admin.component';

@Component({
  selector: 'app-alterusers',
  templateUrl: './alterusers.component.html',
  styleUrls: ['./alterusers.component.css']
})
export class AlterusersComponent implements OnInit {
  public users:UserInterface[]=[]

  constructor(private user:UserdataService,private dialog:MatDialog) { }

  ngOnInit(): void {
    this.user.getUsers().subscribe(data=>this.users=data)
  }
  openSignup(){
    this.dialog.open(AddAdminComponent)
  }

}

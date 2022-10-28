import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/user.service';
import { EditUserComponent } from '../edit-user/edit-user.component';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  message="";
  name=""
  add1=""
  add2=""
  city=""
  state=""
  code=""
  username=""
  phone=""
  public user:any;
  constructor(private route:Router,private cusSer:UserService,private loginService:LoginService,public dialog:MatDialog) { }

  ngOnInit(): void {
    this.loginCheck()
  }


  loginCheck(){
    
    this.loginService.loginCheck().subscribe((data)=>{
      console.log(data)
      this.user=data.body
      
      this.message=`Hello, ${data.body.customer_name}`
      this.name=`${data.body.customer_name}`
      this.add1=`${data.body.customer_address1}`
      this.add2=`${data.body.customer_address2}`
      this.city=`${data.body.customer_city}`
      this.state=`${data.body.customer_state}`
      this.code=`${data.body.customer_code}`
      this.username=`${data.body.customer_username}`
      this.phone=`${data.body.customer_phn}`
    })
     }

     editProfile()
    {
      this.dialog.open(EditUserComponent, {
        autoFocus: false,
        maxHeight: '90vh'
        
  })
 
    }

    deleteProfile()
    {
      this.cusSer.delCus(this.user.customer_id).subscribe(()=>{
      
        console.log("data deleted")
        })

        alert("Deleted!")
        this.route.navigate([''])
   
    }
}


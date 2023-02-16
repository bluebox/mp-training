import { Component, OnInit } from '@angular/core';
import { throwToolbarMixedModesError } from '@angular/material/toolbar';
import { ServercomunicationService } from '../servercomunication.service';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  user!: any;

  constructor(private api:ServercomunicationService) { }

  ngOnInit(): void {
    // this.getUserdetails();
    // this.loggedin();
    // this.isDoctor();
    // this.isPatient();
  }

  // isPatient(): any {
  //  if(this.user.value.type_of_user=='P')
  //  return true
  //  else return false
  //   }
  //   isDoctor(): any {
  //     if(this.user.value.type_of_user=='P')
  //     return true
  //     else return false
  //   }
  //   loggedin(): any {
  //   if(this.user!=null)
  //   return true
  //   else return false
  //   }

  // getUserdetails(){
  //   this.api.getuser().subscribe(
  //     (res:any)=>{
  //       this.user=res.email;
  //       console.log(this.user);
  //     },
  //     (error:any)=>{
  //       console.log(error);
  //       console.log("userdetails");
  //     }
    // )
  // }
}

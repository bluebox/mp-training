import { ThisReceiver } from '@angular/compiler';
import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { map, Observable, startWith } from 'rxjs';
import { ViewComponent } from './info/components/view/view.component';
import { MovieInterface } from './interface/movie';
import { LoginComponent } from './login/login.component';
import { MoviedataService } from './services/moviedata.service';
import { UserdataService } from './services/userdata.service';
import { SignupComponent } from './signup/signup.component';
import {MatTableDataSource} from '@angular/material/table';
import { UserInterface } from './interface/user';


ViewComponent
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  // public dataSource:any
  // myControl = new FormControl('');
  // options: string[] = ['One', 'Two', 'Three'];
  // filteredOptions!: Observable<string[]>;
  // public nav:FormGroup=new FormGroup({
  // })
  public loggedIn:boolean=false
  public movies:MovieInterface[]=[]
  public role:string=""
  title = 'movie';
  public currentUser!:UserInterface
  constructor(public dialog: MatDialog,public movie:MoviedataService,public user:UserdataService,public router:Router) {}
  ngOnInit(): void {
    this.role=this.user.getrole()
  
  this.user.getuser().subscribe(data=>{this.currentUser=data,console.log(data);
    this.user.setUser(data)
  if(data.User_id>0){
    this.user.setstatus(true)
    this.loggedIn=this.user.status
    this.user.setrole(data.Role)
    console.log(this.user.role)
  }
  else{
    this.user.setstatus(false)
    this.loggedIn=this.user.status
  };
  })
  this.movie.getMovies().subscribe(data=>this.movies=data)
}
// private _filter(value: string): string[] {
//   const filterValue = value.toLowerCase();

//   return this.options.filter(option => option.toLowerCase().includes(filterValue));
// }
openview(mid:number,mname:string,mlang:string,mdetails:string,mreleasedate:Date,mcat:string,mcast:string){
  let dialogRef = this.dialog.open(ViewComponent, {
    data: { id:mid,name:mname,lang:mlang,det:mdetails,date:mreleasedate,category:mcat,cast:mcast },
  });
}
  openDialog() {
    this.dialog.open(SignupComponent);
  }
  openLogin() {
    this.dialog.open(LoginComponent);
  }
  Logout(){
    alert("Thank you, you are being logged out.")
    this.user.permit=false
    this.router.navigate([''])
    this.user.setstatus(false)
    console.log(this.user.status)
    this.user.logout().subscribe(data=>{console.log(data)})
  }
  search(){
    console.log("searching!")
  }
  cancelTicket(){
    this.router.navigate(['booking/ticket/',this.currentUser.User_id])
  }
}

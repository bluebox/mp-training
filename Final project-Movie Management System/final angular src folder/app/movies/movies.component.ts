import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewComponent } from '../info/components/view/view.component';
import { MovieInterface } from '../interface/movie';
import { MoviedataService } from '../services/moviedata.service';
import { UserdataService } from '../services/userdata.service';
import { SignupComponent } from '../signup/signup.component';

ViewComponent
ActivatedRoute
@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  public movies:MovieInterface[]=[]
  public text:string=""
  public filter:string[]=[]
  public count:number=1
  public stoploader:boolean=false

  constructor(private movie:MoviedataService,private router:Router,private route:ActivatedRoute,private dialog:MatDialog,private user:UserdataService) { }

  ngOnInit(): void {
    this.user.getuser().subscribe(data=>{console.log(data);
      if(data.User_id>0){
        this.user.setstatus(true)
        console.log(this.user.status)
      }
    else{
      this.user.setstatus(false)
      console.log(this.user.status)
    }})
    this.movie.getTop()
    .subscribe((data)=>this.movies=data)
  }


  openview(mname:string,mlang:string,mdetails:string,mreleasedate:Date,mcat:string,mcast:string){
    console.log()
    let dialogRef = this.dialog.open(ViewComponent, {
      data: { name:mname,lang:mlang,det:mdetails,date:mreleasedate,category:mcat,cast:mcast },
    });
  }

  search(){
    console.log(this.filter.length,this.text)
    if(this.text===""){
      this.movie.getTop()
    .subscribe((data)=>this.movies=data)
    }
    if(this.filter.length==0){
      this.movie.filter("Empty",this.text).subscribe(data=>this.movies=data)
    }
    let st=this.filter.toString()
    this.movie.filter(st,this.text).subscribe(data=>this.movies=data)
  }
  
  openBooking(id:number){
    if(this.user){
      this.router.navigate(['booking/theatrelist',id])}
    else{
      this.dialog.open(SignupComponent)
    }
  }

  selectedfilter(value:string){
    if(this.filter.includes(value)){
      let index=this.filter.indexOf(value)
      this.filter.splice(index,1)
      console.log(this.filter)
    }
    else{
      this.filter.push(value)
      console.log(this.filter)
    }
    this.getfiltermovies(this.filter)
  }

  getfiltermovies(list:string[]){
    let st=list.toString()
    if(st!==""){
      console.log(st)
      if(this.text===""){
        console.log("text",this.text)
        this.movie.filter(st,"empty").subscribe(data=>this.movies=data)
      }
      this.movie.filter(st,this.text).subscribe(data=>this.movies=data)
    }
    else{
      this.search()
      // this.movie.paginator(1).subscribe(data=>this.movies=data)
    }
  }

  remove(fruit:string){
    let index=this.filter.indexOf(fruit)
    this.filter.splice(index,1)
    console.log("remove",this.filter)
    this.getfiltermovies(this.filter)
  }
  // onPaginateChange(event:any){
  //   this.movie.paginator(Number(event.pageIndex)+1).subscribe(data=>this.movies=data)
  //   console.log(("Current page index: " + event.pageIndex));
  // }
  
  load(input:string){
    if(input==="more"){
    this.count+=1
    }
    if(input==="less"){
      this.count-=1
      this.stoploader=false
      if(this.count<1){
        this.count=1
      }
    }
    this.movie.paginator(this.count).subscribe(data=>{console.log(data)
        this.movies=data;
    },
    (err) => {
      this.stoploader = true;
      console.log(err.error.detail,this.stoploader)
    })
  }
}

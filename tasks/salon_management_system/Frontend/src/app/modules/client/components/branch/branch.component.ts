import { Component, OnInit } from '@angular/core';
import { HttpserviceService } from 'src/app/httpservice.service';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.css']
})
export class BranchComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  branches: any;
  text:any;
  result:any;
  msg : boolean = false;
  displayedColumns :string[]=['spa','branch_id','branch_name','location'];
  constructor(private http : HttpserviceService) { }

  ngOnInit(): void {
    this.subscription =this.http.getBranch().subscribe((data) =>{this.branches = data ;console.log(data)});
  }
 
  onSearchText(text: any,event:any) {
    this.text = event.target.value;
    console.log(text);
    this.http.getSearchBranches(text).subscribe({
      next:(res)=>{
        this.result= res;
        this.branches=res;
        if((this.result).length == 0){
          console.log("notfound");
          this.msg = true;
        }
        else{
          this.msg=false;
        }
        console.log(this.result)
      }
    })
  }
  refresh(){
    this.msg = false;
    this.subscription =this.http.getBranch().subscribe((data) =>{this.branches = data ;console.log(data)});
  }
}

import { Component, OnInit } from '@angular/core';
import { HttpserviceService } from 'src/app/httpservice.service';
import { Subscription } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
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
  displayedColumns :string[]=['spa','branch_id','branch_name','location','update','delete'];
  constructor(private http : HttpserviceService) { }

  // newBranchForm : FormGroup = new FormGroup({
  //   branch_id : new FormControl("",Validators.required),
  //   branch_name : new FormControl("",Validators.required),
  //   location : new FormControl("",Validators.required)
  // })

  ngOnInit(): void {
    this.subscription =this.http.getBranch().subscribe((data) =>{this.branches = data ;console.log(data)});
  }
  deleteBranch(arg : any){
    if(confirm("do u want to delete")){
        var val = {"branch_id": arg}
        console.log(val)
        this.http.deleteBranches(val).subscribe(res=>{
          alert(res.toString());
          console.log(val);
          window.location.reload();
        })
    }
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

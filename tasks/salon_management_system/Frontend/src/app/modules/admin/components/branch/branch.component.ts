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

  
}

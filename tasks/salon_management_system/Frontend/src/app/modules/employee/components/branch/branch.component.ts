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
  displayedColumns :string[]=['spa','branch_id','branch_name','location'];
  constructor(private http : HttpserviceService) { }

  ngOnInit(): void {
    this.subscription =this.http.getBranch().subscribe((data) =>{this.branches = data ;console.log(data)});
  }

}

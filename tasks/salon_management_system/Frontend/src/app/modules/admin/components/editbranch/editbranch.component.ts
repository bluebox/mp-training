import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { HttpserviceService } from 'src/app/httpservice.service';

@Component({
  selector: 'app-editbranch',
  templateUrl: './editbranch.component.html',
  styleUrls: ['./editbranch.component.css']
})
export class EditbranchComponent implements OnInit {
  subscription : Subscription = Subscription.EMPTY
  id:any;

  constructor(private http:HttpserviceService,private router:ActivatedRoute,private route:Router) { }

  editBranchForm : FormGroup = new FormGroup({
    branch_id : new FormControl("",Validators.required),
    branch_name : new FormControl("",[Validators.required,Validators.maxLength(50)]),
    location : new FormControl("",[Validators.required,Validators.maxLength(500)])
  })
  ngOnInit(): void {
  //  console.log(this.router.snapshot.params.id);
   this.http.getCurrentBranch(this.router.snapshot.params.id).subscribe((result:any) => {
   this.editBranchForm = new FormGroup({
    branch_id : new FormControl(result['branch_id'],Validators.required),
    branch_name : new FormControl(result['branch_name'],[Validators.required,Validators.maxLength(50)]),
    location : new FormControl(result['location'],[Validators.required,Validators.maxLength(500)])
   })
  });
  }

  updateBranch(){
    console.log(this.editBranchForm.value)
    this.http.updateBranches(this.router.snapshot.params.id,this.editBranchForm.value).subscribe((result)=>{
      console.log(result);alert("updated successfully");this.route.navigate(['admin/branch'])
    })
  }
  
  cancel(){
    this.route.navigate(['admin/branch']);
  }
}

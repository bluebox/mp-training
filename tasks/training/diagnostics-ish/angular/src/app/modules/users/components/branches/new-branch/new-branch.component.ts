import { Component, OnInit } from '@angular/core';
import { BranchesService } from 'src/app/services/branches-service/branches.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-new-branch',
  templateUrl: './new-branch.component.html',
  styleUrls: ['./new-branch.component.css']
})
export class NewBranchComponent implements OnInit {
  responseMessage: string = '';
  formNotValid: boolean = false;
  formError?: string = ""
  branchId !: string 
  constructor(private actRouter: ActivatedRoute, private http: BranchesService, private router: Router) {
    this.actRouter.params.subscribe(data => {
      this.branchId = data['branch_id']
    })
    if (this.branchId) {
      this.http.getBranch(this.branchId).subscribe({
        next: (resp: any) => {
          this.branchForm.get('branch_id')?.setValue(resp.branch.branch_id)
          this.branchForm.get('branch_name')?.setValue(resp.branch.branch_name)
          this.branchForm.get('location')?.setValue(resp.branch.location)
        }
      })
    }
  }


  branchForm: FormGroup = new FormGroup({
    branch_id: new FormControl('', Validators.required),
    branch_name: new FormControl('', Validators.required),
    location: new FormControl('', Validators.required),
  });

  onSubmit() {
    this.branchForm.get('branch_id')?.setValue(this.branchForm.get('branch_id')?.value.trim())
    this.branchForm.get('branch_name')?.setValue(this.branchForm.get('branch_name')?.value.trim())
    this.branchForm.get('location')?.setValue(this.branchForm.get('location')?.value.trim())
    if (this.branchForm.valid) {
      this.formNotValid = false
      if (this.branchId){
        console.log('update');
        this.http.updateBranch(this.branchId, { 'form': this.branchForm.value }).subscribe({
          next: (resp) => {
            this.responseMessage = resp.message
            if (resp.action_status == "success") {
              this.router.navigate(['admin/display-branches'])
            }
          },
          error: (err) => {
            console.log(err);
          }
        })
      }
      else{
        this.http.addBranch({ 'form': this.branchForm.value }).subscribe({
          next: (resp) => {
            this.responseMessage = resp.message
            if (resp.action_status == "success") {
              this.router.navigate(['admin/display-branches'])
            }
          },
          error: (err) => {
            console.log(err);
          }
        })
      }
    }
    else {
      this.formNotValid = true
    }
  }
  ngOnInit(): void {
  }
}

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BranchesService } from 'src/app/services/branches-service/branches.service';
import { LabsService } from 'src/app/services/labs-service/labs.service';

@Component({
  selector: 'app-new-lab',
  templateUrl: './new-lab.component.html',
  styleUrls: ['./new-lab.component.css']
})
export class NewLabComponent implements OnInit {

  responseMessage: string = '';
  formNotValid: boolean = false;
  formError?: string = ""
  branches : any
  constructor(private http : LabsService, private router: Router , private branchHttp : BranchesService) {
    this.branchHttp.getBranches().subscribe({
      next : (resp)=>{
        this.branches = resp
      },
      error:(err)=>{
        console.log(err);
        
      }
    })
   }
    labForm: FormGroup = new FormGroup({
      lab_id: new FormControl('', Validators.required),
      lab_name: new FormControl('', Validators.required),
      branch: new FormControl('', Validators.required),
    });

    onSubmit() {
      this.labForm.get('lab_id')?.setValue(this.labForm.get('lab_id')?.value.trim())
      this.labForm.get('lab_name')?.setValue(this.labForm.get('lab_name')?.value.trim())
      if (this.labForm.valid) {
        this.formNotValid = false
        this.http.createLab({ 'form': this.labForm.value }).subscribe({
          next: (resp) => {
            this.responseMessage = resp.message
            if (resp.action_status == "success") {
              this.router.navigate([''])
            }
          },
          error: (err) => {
            console.log(err);
          }
        })
      }
      else {
        this.formNotValid = true
      }
    } 
  ngOnInit(): void {
  }

}

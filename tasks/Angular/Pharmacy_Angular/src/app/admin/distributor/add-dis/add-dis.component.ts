import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog'
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {DistributorService} from "src/app/services/distributor.service";


@Component({
  selector: 'app-add-dis',
  templateUrl: './add-dis.component.html',
  styleUrls: ['./add-dis.component.css']
})
export class AddDisComponent implements OnInit {
  distributorForm !: FormGroup;
  actionBtn : string = "Save";


  constructor(private formBuilder : FormBuilder, 
    private api : DistributorService,
    @Inject(MAT_DIALOG_DATA) public editData:any,
    private dialogRef : MatDialogRef<AddDisComponent>) { }

  ngOnInit(): void {
    this.distributorForm = this.formBuilder.group({
      dist_id : ['',Validators.required],
      dist_name : ['',Validators.required],
      contact : ['',Validators.required],
      email : ['',Validators.required],
      address : ['',Validators.required]
    })

    if(this.editData){
      this.actionBtn = "Update";
      this.distributorForm.controls['dist_id'].setValue(this.editData.dist_id);
      this.distributorForm.controls['dist_name'].setValue(this.editData.dist_name);
      this.distributorForm.controls['contact'].setValue(this.editData.contact);
      this.distributorForm.controls['email'].setValue(this.editData.email);
      this.distributorForm.controls['address'].setValue(this.editData.address);
    }
  }

  addDistributor(){
    if(!this.editData){
      if(this.distributorForm.valid){
        this.api.postDistributor(this.distributorForm.value)
        .subscribe({
          next:(res)=>{
            alert("Distributor Added Successfully!");
            this.distributorForm.reset();
            this.dialogRef.close('save');
          },
          error:()=>{
            alert("Error Distributor Not Added!")
          }
        })
      }

    }
    else{
      this.updateDistributor()
      }
  }

  updateDistributor(){
    this.api.putDistributor(this.distributorForm.value, this.editData.id)
    .subscribe({
      next:(res)=>{
        alert("Distributor updated Successfully!");
        this.distributorForm.reset();
        this.dialogRef.close("update");
      },
      error:()=>{
        alert("Error While updating the record!");
      }
    })
  }

}
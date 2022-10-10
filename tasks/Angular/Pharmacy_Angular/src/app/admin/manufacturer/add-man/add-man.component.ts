import { Component,Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog'
import {ManufacturerService} from 'src/app/services/manufacturer.service';


@Component({
  selector: 'app-add-man',
  templateUrl: './add-man.component.html',
  styleUrls: ['./add-man.component.css']
})
export class AddManComponent implements OnInit {

 
  manufacturerForm !: FormGroup;
  actionBtn : string = "Save";

  constructor( private formBuilder : FormBuilder, 
    private api : ManufacturerService,
    @Inject(MAT_DIALOG_DATA) public editData:any,
    private dialogRef : MatDialogRef<AddManComponent>) { }

  ngOnInit(): void {
    this.manufacturerForm = this.formBuilder.group({
      company_id : ['',Validators.required],
      company_name : ['',Validators.required],
      contact : ['',Validators.required],
      email : ['',Validators.required],
      address : ['',Validators.required]
    })

    if(this.editData){
      this.actionBtn = "Update";
      this.manufacturerForm.controls['company_id'].setValue(this.editData.company_id);
      this.manufacturerForm.controls['company_name'].setValue(this.editData.company_name);
      this.manufacturerForm.controls['contact'].setValue(this.editData.contact);
      this.manufacturerForm.controls['email'].setValue(this.editData.email);
      this.manufacturerForm.controls['address'].setValue(this.editData.address);
    }
  }


  addManufacturer(){
    if(!this.editData){
      if(this.manufacturerForm.valid){
        this.api.postManufacturer(this.manufacturerForm.value)
        .subscribe({
          next:(res)=>{
            console.log(res)
            alert("Manufacturer Added Successfully!");
            this.manufacturerForm.reset();
            this.dialogRef.close('save');
          },
          error:()=>{
            alert("Error Manufacturer Not Added!")
          }
        })
      }

    }
    else{
      this.updateManufacturer()
      }
  }

  updateManufacturer(){
    this.api.putManufacturer(this.manufacturerForm.value, this.editData.id)
    .subscribe({
      next:(res)=>{
        alert("Manufacturer updated Successfully!");
        this.manufacturerForm.reset();
        this.dialogRef.close("update");
      },
      error:()=>{
        alert("Error While updating the record!");
      }
    })
  }

}


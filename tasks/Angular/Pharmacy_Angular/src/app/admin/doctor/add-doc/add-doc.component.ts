import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {DoctorService} from 'src/app/services/doctor.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog'

@Component({
  selector: 'app-add-doc',
  templateUrl: './add-doc.component.html',
  styleUrls: ['./add-doc.component.css']
})
export class AddDocComponent implements OnInit {

  doctorForm !: FormGroup;
  actionBtn : string = "Save";
 
  constructor( private formBuilder : FormBuilder, 
    private api : DoctorService,
    @Inject(MAT_DIALOG_DATA) public editData:any,
    private dialogRef : MatDialogRef<AddDocComponent>) { }

  ngOnInit(): void {
    this.doctorForm = this.formBuilder.group({
      doc_id : ['',Validators.required],
      first_name : ['',Validators.required],
      last_name : [''],
      specialization : ['',Validators.required],
      contact : ['',Validators.required],
      email : ['',Validators.required],
      address : ['',Validators.required]
    })

    if(this.editData){
      this.actionBtn = "Update";
      this.doctorForm.controls['doc_id'].setValue(this.editData.doc_id);
      this.doctorForm.controls['first_name'].setValue(this.editData.first_name);
      this.doctorForm.controls['last_name'].setValue(this.editData.last_name);
      this.doctorForm.controls['specialization'].setValue(this.editData.specialization);
      this.doctorForm.controls['contact'].setValue(this.editData.contact);
      this.doctorForm.controls['email'].setValue(this.editData.email);
      this.doctorForm.controls['address'].setValue(this.editData.address);
    }
  }


  addDoctor(){
    if(!this.editData){
      if(this.doctorForm.valid){
        this.api.postDoctor(this.doctorForm.value)
        .subscribe({
          next:(res)=>{
            console.log(res)
            alert("Doctor Added Successfully!");
            this.doctorForm.reset();
            this.dialogRef.close('save');
          },
          error:()=>{
            alert("Error Doctor Not Added!")
          }
        })
      }

    }
    else{
      this.updateDoctor()
      }
  }

  updateDoctor(){
    this.api.putDoctor(this.doctorForm.value, this.editData.id)
    .subscribe({
      next:(res)=>{
        alert("Doctor updated Successfully!");
        this.doctorForm.reset();
        this.dialogRef.close("update");
      },
      error:()=>{
        alert("Error While updating the record!");
      }
    })
  }

}



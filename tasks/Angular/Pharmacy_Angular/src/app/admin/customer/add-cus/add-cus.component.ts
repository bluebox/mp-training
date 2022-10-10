import { Component,Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {CustomerService} from "src/app/services/customer.service"



@Component({
  selector: 'app-add-cus',
  templateUrl: './add-cus.component.html',
  styleUrls: ['./add-cus.component.css']
})
export class AddCusComponent implements OnInit {
  
  customerForm !: FormGroup;
  actionBtn : string = "Save";
  constructor(private formBuilder : FormBuilder, 
    private api : CustomerService,
    @Inject(MAT_DIALOG_DATA) public editData:any,
    private dialogRef : MatDialogRef<AddCusComponent>) { }

    ngOnInit(): void {
      this.customerForm = this.formBuilder.group({
        cust_id : ['',Validators.required],
        first_name : ['',Validators.required],
        last_name : [''],
        contact : ['',Validators.required],
        email : ['',Validators.required],
        address : ['',Validators.required]
      })
  
      if(this.editData){
        this.actionBtn = "Update";
        this.customerForm.controls['cust_id'].setValue(this.editData.cust_id);
        this.customerForm.controls['first_name'].setValue(this.editData.first_name);
        this.customerForm.controls['last_name'].setValue(this.editData.last_name);
        this.customerForm.controls['contact'].setValue(this.editData.contact);
        this.customerForm.controls['email'].setValue(this.editData.email);
        this.customerForm.controls['address'].setValue(this.editData.address);
      }
    }
  
    addCustomer(){
      if(!this.editData){
        if(this.customerForm.valid){
          this.api.postCustomer(this.customerForm.value)
          .subscribe({
            next:(res)=>{
              alert("Customer Added Successfully!");
              this.customerForm.reset();
              this.dialogRef.close('save');
            },
            error:()=>{
              alert("Error Product Not Added!")
            }
          })
        }
  
      }
      else{
        this.updateCustomer()
        }
    }
  
    updateCustomer(){
      this.api.putCustomer(this.customerForm.value, this.editData.id)
      .subscribe({
        next:(res)=>{
          console.log(this.customerForm.value);
          alert("Customer updated Successfully!");
          this.customerForm.reset();
          this.dialogRef.close("update");
        },
        error:()=>{
          alert("Error While updating the record!");
        }
      })
    }
  
  }
  
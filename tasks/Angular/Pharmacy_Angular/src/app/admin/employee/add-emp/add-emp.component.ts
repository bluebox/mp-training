import { Component,Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {EmployeeService} from 'src/app/services/employee.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog'

@Component({
  selector: 'app-add-emp',
  templateUrl: './add-emp.component.html',
  styleUrls: ['./add-emp.component.css']
})
export class AddEmpComponent implements OnInit {

  jobList=["Permanent", "Contract"]
  employeeForm !: FormGroup;
  actionBtn : string = "Save";
  selected : any;

  constructor( private formBuilder : FormBuilder, 
    private api : EmployeeService,
    @Inject(MAT_DIALOG_DATA) public editData:any,
    private dialogRef : MatDialogRef<AddEmpComponent>) { }

  ngOnInit(): void {
    this.employeeForm = this.formBuilder.group({
      emp_id : ['',Validators.required],
      first_name : ['',Validators.required],
      last_name : [''],
      doj : ['',Validators.required],
      designation : ['',Validators.required],
      job_type : ['',Validators.required],
      salary : ['',Validators.required],
      contact : ['',Validators.required],
      email : ['',Validators.required],
      address : ['',Validators.required]
    })

    if(this.editData){
      this.actionBtn = "Update";
      this.employeeForm.controls['emp_id'].setValue(this.editData.emp_id);
      this.employeeForm.controls['first_name'].setValue(this.editData.first_name);
      this.employeeForm.controls['last_name'].setValue(this.editData.last_name);
      this.employeeForm.controls['doj'].setValue(this.editData.doj);
      this.employeeForm.controls['designation'].setValue(this.editData.designation);
      this.employeeForm.controls['job_type'].setValue(this.editData.job_type);
      this.employeeForm.controls['salary'].setValue(this.editData.salary);
      this.employeeForm.controls['contact'].setValue(this.editData.contact);
      this.employeeForm.controls['email'].setValue(this.editData.email);
      this.employeeForm.controls['address'].setValue(this.editData.address);
    }
  }


  addEmployee(){
    if(!this.editData){
      if(this.employeeForm.valid){
        this.api.postEmployee(this.employeeForm.value)
        .subscribe({
          next:(res)=>{
            console.log(res)
            alert("Employee Added Successfully!");
            this.employeeForm.reset();
            this.dialogRef.close('save');
          },
          error:()=>{
            alert("Error Employee Not Added!")
          }
        })
      }

    }
    else{
      this.updateEmployee()
      }
  }

  updateEmployee(){
    this.api.putEmployee(this.employeeForm.value, this.editData.id)
    .subscribe({
      next:(res)=>{
        alert("Employee updated Successfully!");
        this.employeeForm.reset();
        this.dialogRef.close("update");
      },
      error:()=>{
        alert("Error While updating the record!");
      }
    })
  }

}



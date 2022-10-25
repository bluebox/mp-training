import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ServicesService } from '../services/services.service';
import { DatePipe } from '@angular/common';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserLoginComponent } from '../user-login/user-login.component';

@Component({
  selector: 'app-faculty-signup',
  templateUrl: './faculty-signup.component.html',
  styleUrls: ['./faculty-signup.component.css']
})
export class FacultySignupComponent implements OnInit {

  loginformcontrols: any;
  convertDate = new DatePipe("en-US");
  allClasses: any;
  allSubjects: any;
  allDepartments: any;
  data:any;
  updateBtn: string = "Create user";
  isReadOnly = false;

  constructor(
    private services:ServicesService,
    private formbuild: FormBuilder,
    private dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public editData: any,
  ) { 
    this.services.allclasses().subscribe((data)=>{
      this.allClasses = data;
    });

    this.services.allsubjects().subscribe((data)=>{
      this.allSubjects = data;
      console.log(this.allSubjects)
    });

    this.services.alldepartments().subscribe((data)=>{
      this.allDepartments = data;
      console.log(this.allDepartments)
    });
  }

  ngOnInit(): void {

    if(this.editData){
      this.updateBtn = "Update user";
      this.isReadOnly = true;
      this.facultySignupFormModel.controls['first_name'].setValue(this.editData.first_name);
      this.facultySignupFormModel.controls['last_name'].setValue(this.editData.last_name);
      this.facultySignupFormModel.controls['facul_username'].setValue(this.editData.facul_username);
      this.facultySignupFormModel.controls['password'].setValue(this.editData.password);
      this.facultySignupFormModel.controls['gender'].setValue(this.editData.gender);
      this.facultySignupFormModel.controls['date_of_birth'].setValue(this.editData.date_of_birth);
      this.facultySignupFormModel.controls['main_subject'].setValue(this.editData.main_subject);
      this.facultySignupFormModel.controls['department'].setValue(this.editData.department);
      this.facultySignupFormModel.controls['joining_date'].setValue(this.editData.joining_date);
      this.facultySignupFormModel.controls['qualification'].setValue(this.editData.qualification);
      this.facultySignupFormModel.controls['experience'].setValue(this.editData.experience);
    }

  }

  gender: Gender[] = [
    {value: 'male', viewValue: 'Male'},
    {value: 'female', viewValue: 'Female'},
  ];

  // classnames: ClassName[] = [
  //   {classname : 'MM1004'},
  // ];

  usersList: Users[] = [
    {users : 'Faculty', userValue: 'faculty'},
  ];

  facultySignupFormModel = this.formbuild.group({
    first_name: ['', Validators.required],
    last_name: ['', Validators.required],
    facul_username: ['', Validators.required],
    password: ['', Validators.required], // Validators.pattern('[a-zA-Z0-9@#$%^&*]+$')],
    gender: ['', Validators.required],
    date_of_birth: ['', Validators.required],
    main_subject: ['',Validators.required],
    department: ['', Validators.required],
    joining_date: ['', Validators.required],
    qualification: ['', Validators.required],
    experience: [, Validators.required],
    user_type: ['faculty', Validators.required],
  })

  registerNewFaculty(){

    if(!this.editData)
    {
      if(this.facultySignupFormModel.value.user_type == "Faculty" || this.facultySignupFormModel.value.user_type == "faculty")
      {
        // this.facultySignupFormModel.value.date_of_birth = this.convertDate.transform(this.facultySignupFormModel.value.date_of_birth, "dd-MMM-yyyy");
        // this.facultySignupFormModel.value.joining_date = this.convertDate.transform(this.facultySignupFormModel.value.joining_date, "dd-MMM-yyyy");
        this.services.registerNewFaculty(this.facultySignupFormModel.value).subscribe(
          response => {
            alert(response);
            location.reload();
          },
          error => console.log("Error ",error)
        );
        // this.closeDialog();
        // this.openSignupDialog();
        // this.facultySignupFormModel.reset();
        // alert(this.facultySignupFormModel.value.user_type);
      }
      else
      {
        alert("No user type selected");
      }
  }
  else{
    this.updateFacultyDetails();
  }
}

  updateFacultyDetails(){
    this.services.updateFaculty(this.facultySignupFormModel.value, this.editData.facul_username).subscribe(
      response => {
        alert("Record updated successfully");
        location.reload();
        // this.facultySignupFormModel.reset();
      },
      error => console.log("Error ",error)
      
    );
  }

  openLoginDialog() {
    this.dialog.open(UserLoginComponent, {
      width: '40%',
      height: '70%',
    });
  }

}

interface Gender {
  value: string;
  viewValue: string;
}

interface Users {
  users:string;
  userValue:string
}
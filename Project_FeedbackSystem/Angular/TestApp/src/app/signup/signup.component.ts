import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ServicesService } from '../services/services.service';
import { DatePipe } from '@angular/common';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserLoginComponent } from '../user-login/user-login.component';
import { ShowstudentComponent } from '../showstudent/showstudent.component';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
  providers: [ServicesService]
})

export class SignupComponent implements OnInit {
  loginformcontrols: any;
  convertDate = new DatePipe("en-US");
  allClasses: any;
  allSubjects: any;
  allDepartments: any;
  data:any;
  updateBtn: string = "Create user";
  isReadOnly = false;
  btn = "";

// costructor method
  constructor(
    private services:ServicesService,
    private formbuild: FormBuilder,
    private dialog: MatDialog,
    @Inject(MAT_DIALOG_DATA) public editData: any
    ) {

    this.services.allclasses().subscribe((data)=>{
      this.allClasses = data;
    });

    this.services.allsubjects().subscribe((data)=>{
      this.allSubjects = data;
      // console.log(this.allSubjects)
    });

    this.services.alldepartments().subscribe((data)=>{
      this.allDepartments = data;
      // console.log(this.allDepartments)
    });

    console.log(editData)
   }
  
  gender: Gender[] = [
    {value: 'male', viewValue: 'Male'},
    {value: 'female', viewValue: 'Female'},
  ];

  usersList: Users[] = [
    {users : 'Student', userValue: 'student'},
  ];


  studentSignupFormModel = this.formbuild.group({
    first_name: ['', Validators.required],
    last_name: ['', Validators.required],
    stud_username: ['', Validators.required],
    password: ['', Validators.required], //, Validators.pattern('[a-zA-Z0-9]+$')],
    gender: ['', Validators.required],
    date_of_birth: ['', Validators.required],
    roll_no: [,Validators.required],
    father_name: ['', Validators.required],
    user_type: ['student', Validators.required],
    class_code: ['', Validators.required]
  })


  // facultySignupFormModel = this.formbuild.group({
  //   first_name: ['', Validators.required],
  //   last_name: ['', Validators.required],
  //   facul_username: ['', Validators.required, Validators.email],
  //   password: ['', Validators.required, Validators.pattern('[a-zA-Z0-9@#$%^&*]+$')],
  //   gender: ['', Validators.required],
  //   date_of_birth: ['', Validators.required],
  //   main_subject: ['',Validators.required],
  //   department: ['', Validators.required],
  //   joining_date: ['', Validators.required],
  //   qualification: ['', Validators.required],
  //   experience: [, Validators.required],
  //   user_type: ['', Validators.required],
  // })


  // studentSignupFormModel = new FormGroup({
  //   first_name:new FormControl(''),
  //   last_name:new FormControl(''),
  //   stud_username:new FormControl(''),
  //   password:new FormControl(''),
  //   gender:new FormControl(''),
  //   date_of_birth:new FormControl(''),
  //   father_name:new FormControl(''),
  //   roll_no:new FormControl(),
  //   user_type:new FormControl(''),
  //   class_code:new FormControl(''),
    
  // })

  ngOnInit(): void {

  if(this.editData){
    this.updateBtn = "Update user";
    this.isReadOnly = true;
    this.studentSignupFormModel.controls['first_name'].setValue(this.editData.first_name);
    this.studentSignupFormModel.controls['last_name'].setValue(this.editData.last_name);
    this.studentSignupFormModel.controls['stud_username'].setValue(this.editData.stud_username);
    this.studentSignupFormModel.controls['password'].setValue(this.editData.password);
    this.studentSignupFormModel.controls['gender'].setValue(this.editData.gender);
    this.studentSignupFormModel.controls['date_of_birth'].setValue(this.editData.date_of_birth);
    this.studentSignupFormModel.controls['father_name'].setValue(this.editData.father_name);
    this.studentSignupFormModel.controls['class_code'].setValue(this.editData.class_code);
    this.studentSignupFormModel.controls['roll_no'].setValue(this.editData.roll_no);
  }
  }
registerNewStudent(){
  
  if(!this.editData){
    if(this.studentSignupFormModel.value.user_type == "Student" || this.studentSignupFormModel.value.user_type == "student")
  {
    if(!(this.studentSignupFormModel.value.first_name &&
      this.studentSignupFormModel.value.last_name &&
      this.studentSignupFormModel.value.stud_username &&
      this.studentSignupFormModel.value.gender &&
      this.studentSignupFormModel.value.date_of_birth &&
      this.studentSignupFormModel.value.class_code &&
      this.studentSignupFormModel.value.father_name &&
      this.studentSignupFormModel.value.roll_no
      ))
    {

      alert("all fields required");
      return;
    }
    // this.studentSignupFormModel.value.date_of_birth = this.convertDate.transform(this.studentSignupFormModel.value.date_of_birth, "MM/dd/yyyy");
    this.services.registerNewStudent(this.studentSignupFormModel.value).subscribe(
      response => {
        alert(response);
        this.studentSignupFormModel.reset();
      },
      error => console.log("Error ",error)
      
    );
    
    // alert(this.studentSignupFormModel.value.date_of_birth);
  }
  else
  {
    alert("Invalid user type");
  }
  }

  else{
    this.updateStudentDetails();
  }
}

updateStudentDetails(){
  this.services.updateStudent(this.studentSignupFormModel.value, this.editData.stud_username).subscribe(
    response => {
      alert("Record updated successfully");
      location.reload();
      // this.studentSignupFormModel.reset();
    },
    error => console.log("Error ",error)
    
  );
}


// registerNewFaculty(){
//   if(this.facultySignupFormModel.value.user_type == "Faculty" || this.facultySignupFormModel.value.user_type == "faculty")
//   {
//     this.facultySignupFormModel.value.date_of_birth = this.convertDate.transform(this.facultySignupFormModel.value.date_of_birth, "dd-MMM-yyyy");
//     this.facultySignupFormModel.value.joining_date = this.convertDate.transform(this.facultySignupFormModel.value.joining_date, "dd-MMM-yyyy");
//     this.services.registerNewFaculty(this.facultySignupFormModel.value).subscribe(
//       response => {
//         alert(response);
//       },
//       error => console.log("Error ",error)
//     );
//     // this.closeDialog();
//     // this.openSignupDialog();
//     this.facultySignupFormModel.reset();
//     // alert(this.facultySignupFormModel.value.user_type);
//   }
//   else
//   {
//     alert("No user type selected");
//   }
// }

openLoginDialog() {
  this.dialog.open(UserLoginComponent, {
    width: '40%',
    height: '70%',
  });
}

// openSignupDialog() {
//   this.dialog.open(SignupComponent, {
//     width: '35%',
//     height: '70%',
//   });
// }

 
}


//Some interfaces

interface Gender {
  value: string;
  viewValue: string;
}

interface Users {
  users:string;
  userValue:string
}


import { Component } from '@angular/core';
import { Home } from './Home';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { SignupComponent } from './signup/signup.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { FacultySignupComponent } from './faculty-signup/faculty-signup.component';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'TestApp';

  constructor(private signupDialog: MatDialog, private signinDialog: MatDialog){ }

  openStudentSignup() {
    this.signupDialog.open(SignupComponent, {
      width: '40%',
      height: '70%',
    });
  }

  openFacultySignup() {
    this.signupDialog.open(FacultySignupComponent, {
      width: '40%',
      height: '70%',
    });
  }

  openSigninDialog() {
    this.signinDialog.open(UserLoginComponent, {
      width: '40%',
      height: '70%',
    });
  }

}

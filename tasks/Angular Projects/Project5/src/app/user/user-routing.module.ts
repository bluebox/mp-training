import { NgModule } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuardGuard } from '../Auth/auth-guard.guard';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { ErrorUserComponent } from './error-user/error-user.component';
import { UserForgotpasswordComponent } from './user-forgotpassword/user-forgotpassword.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserLogoutComponent } from './user-logout/user-logout.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { UserComponent } from './user.component';

const routes: Routes = [
{ path: 'Admin', component: UserComponent, canActivate:[AuthGuardGuard] },
{path: 'login', component: UserLoginComponent},
{path: '', redirectTo:('login'), pathMatch:'full'},
{path: 'register', component: UserRegisterComponent},
{path: 'error', component: ErrorUserComponent},
{path: 'changepassword', component: ChangepasswordComponent},
{path: 'logout', component: UserLogoutComponent},
{path: 'forgotpassword', component: UserForgotpasswordComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { 
  register!:FormGroup
}

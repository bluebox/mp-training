import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { SuccessPageComponent } from './success-page/success-page.component';
import { UserAuthComponent } from './user-auth.component';

const routes: Routes = [
  { path: '', component: UserAuthComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'success', component: SuccessPageComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserAuthRoutingModule { }

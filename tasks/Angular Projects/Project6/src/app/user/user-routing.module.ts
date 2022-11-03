import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { UserComponent } from './user.component';

const routes: Routes = [
  { path: '', component: UserComponent },
  { path: 'login', component: SignInComponent },
  { path: 'register', component: SignUpComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }

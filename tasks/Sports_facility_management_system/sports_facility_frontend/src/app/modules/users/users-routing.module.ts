import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from 'src/app/component/error/error.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserhomeComponent } from './components/userhome/userhome.component';
import { UserindexComponent } from './components/userindex/userindex.component';

const routes: Routes = [
  {
    path: '',
    component: UserindexComponent,
    children: [
      {path:'login',component: LoginComponent},
      { path: 'signup', component: SignupComponent },
      { path: 'booking/:id', component:UserhomeComponent },
      { path: '**', component: ErrorComponent },
    ],
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }

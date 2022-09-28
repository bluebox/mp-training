import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { RegisterStudentComponent } from './modules/users/components/register-student/register-student.component';

const routes: Routes = [
  {path:"",loadChildren:()=>import('./modules/users/users.module').then(m=>m.UsersModule)}
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

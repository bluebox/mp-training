import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import { RegisterStudentComponent } from './modules/users/components/register-student/register-student.component';

const routes: Routes = [
  {path:"",loadChildren:()=>import('./modules/users/users.module').then(m=>m.UsersModule)},
  {path:"",loadChildren:()=>import('./modules/teacher/teacher.module').then(m=>m.TeacherModule)}
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

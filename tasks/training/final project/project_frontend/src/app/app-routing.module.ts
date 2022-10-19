import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/components/home/home.component';
import { NavbarComponent } from './modules/components/navbar/navbar.component';

const routes: Routes = [
 
  // {path:"", component:HomeComponent},
  {path:"",loadChildren:()=>import('./modules/users/users.module').then(m=>m.UsersModule)},
  {path:"teacher",loadChildren:()=>import('./modules/teacher/teacher.module').then(m=>m.TeacherModule)},
  {path:"student",loadChildren:()=>import('./modules/student/student.module').then(m=>m.StudentModule)},
  {path:"admin", loadChildren:()=>import('./modules/admin/admin.module').then(m=>m.AdminModule)}

 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

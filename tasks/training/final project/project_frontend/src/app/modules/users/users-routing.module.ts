import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RegisterStudentComponent } from './components/register-student/register-student.component';
import { RegisterTeacherComponent } from './components/register-teacher/register-teacher.component';

const routes: Routes = [
  // {path:"registerstudent", component: RegisterStudentComponent},
  // {path: '',redirectTo:'registerstudent', pathMatch:"full"}
  {path: "" , component:DashboardComponent , children :[
    {path: "registerstudent", component: RegisterStudentComponent},
    {path: "registerteacher", component:RegisterTeacherComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }

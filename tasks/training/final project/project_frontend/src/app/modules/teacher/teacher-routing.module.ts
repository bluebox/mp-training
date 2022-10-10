import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../users/components/login/login.component';
import { RegisterStudentComponent } from '../users/components/register-student/register-student.component';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { AddQuestionComponent } from './components/add-question/add-question.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DisplayCourseComponent } from './components/display-course/display-course.component';
import { DisplayQuestionsComponent } from './components/display-questions/display-questions.component';

const routes: Routes = [
  {
    path:"", component:DashboardComponent,children:[
      { path:'courseregister',component:AddCourseComponent},
      { path:'questionregister', component:AddQuestionComponent},
      { path:'display-course', component:DisplayCourseComponent},
      { path:'display-question', component:DisplayQuestionsComponent},
      { path:'',redirectTo:'courseregister',pathMatch:"full"},
    ]

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }

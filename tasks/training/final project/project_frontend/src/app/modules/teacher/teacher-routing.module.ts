import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { AddQuestionComponent } from './components/add-question/add-question.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  {
    path:"", component:DashboardComponent,children:[
      {
        path:'courseregister',component:AddCourseComponent
      },
      {
        path:'',redirectTo:'courseregister',pathMatch:"full"
      },
      {
        path:'questionregister', component:AddQuestionComponent
      }
    ]

  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeacherRoutingModule { }

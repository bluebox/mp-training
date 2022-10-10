import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AttemptExamComponent } from './components/attempt-exam/attempt-exam.component';
import { DashboardcomponentComponent } from './components/dashboardcomponent/dashboardcomponent.component';

const routes: Routes = [
  {
    path:"", component:DashboardcomponentComponent,children:[
      { path:'attemptexam',component:AttemptExamComponent},
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AttemptExamComponent } from './components/attempt-exam/attempt-exam.component';
import { DashboardcomponentComponent } from './components/dashboardcomponent/dashboardcomponent.component';
import { ShowExamComponent } from './components/show-exam/show-exam.component';
import { TakeExamComponent } from './components/take-exam/take-exam.component';

const routes: Routes = [
  {
    path:"", component:DashboardcomponentComponent,children:[
      { path:'attemptexam',component:AttemptExamComponent},
      { path:'showexam', component:ShowExamComponent},
      { path:'takeexam',component:TakeExamComponent}
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }

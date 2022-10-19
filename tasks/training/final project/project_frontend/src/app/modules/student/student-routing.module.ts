import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AttemptExamComponent } from './components/attempt-exam/attempt-exam.component';
import { DashboardcomponentComponent } from './components/dashboardcomponent/dashboardcomponent.component';
import { MyMarksComponent } from './components/my-marks/my-marks.component';
import { ShowExamComponent } from './components/show-exam/show-exam.component';
import { StudentHomepageComponent } from './components/student-homepage/student-homepage.component';
import { StudentNavbarComponent } from './components/student-navbar/student-navbar.component';
import { TakeExamComponent } from './components/take-exam/take-exam.component';
import { TotalAttemptsComponent } from './components/total-attempts/total-attempts.component';
import { ViewResultComponent } from './components/view-result/view-result.component';

const routes: Routes = [
  {
    path:"", component:DashboardcomponentComponent,children:[
      { path:"", component:StudentHomepageComponent},
      { path:'attemptexam',component:AttemptExamComponent},
      { path:'showexam', component:ShowExamComponent},
      { path:'takeexam',component:TakeExamComponent},
      { path:'viewresult', component:ViewResultComponent},
      { path:"mymarks",component:MyMarksComponent},
      { path:'checkmarks', component:TotalAttemptsComponent},
      {path:'navbar', component:StudentNavbarComponent},
      {path:'checkmarks/:course_name', component:TotalAttemptsComponent}
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { DashboardcomponentComponent } from './components/dashboardcomponent/dashboardcomponent.component';
import { AttemptExamComponent } from './components/attempt-exam/attempt-exam.component';


@NgModule({
  declarations: [
    DashboardcomponentComponent,
    AttemptExamComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule
  ]
})
export class StudentModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { DashboardcomponentComponent } from './components/dashboardcomponent/dashboardcomponent.component';
import { AttemptExamComponent } from './components/attempt-exam/attempt-exam.component';
import { ShowExamComponent } from './components/show-exam/show-exam.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { TakeExamComponent } from './components/take-exam/take-exam.component';
import { MatRadioModule } from '@angular/material/radio';


@NgModule({
  declarations: [
    DashboardcomponentComponent,
    AttemptExamComponent,
    ShowExamComponent,
    TakeExamComponent
  ],
  imports: [
    CommonModule,
    StudentRoutingModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatRadioModule
  ]
})
export class StudentModule { }

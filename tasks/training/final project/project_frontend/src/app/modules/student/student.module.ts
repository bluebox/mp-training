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
import { ViewResultComponent } from './components/view-result/view-result.component';
import { MatCardModule } from '@angular/material/card';
import { MyMarksComponent } from './components/my-marks/my-marks.component';
import { TotalAttemptsComponent } from './components/total-attempts/total-attempts.component';
import { StudentNavbarComponent } from './components/student-navbar/student-navbar.component';
import {MatListModule} from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { StudentHomepageComponent } from './components/student-homepage/student-homepage.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { CloseComponent } from './components/close/close.component';
import { MatDialogModule } from '@angular/material/dialog';


@NgModule({
  declarations: [
    DashboardcomponentComponent,
    AttemptExamComponent,
    ShowExamComponent,
    TakeExamComponent,
    ViewResultComponent,
    MyMarksComponent,
    TotalAttemptsComponent,
    StudentNavbarComponent,
    StudentHomepageComponent,
    CloseComponent
  ],
  entryComponents:[CloseComponent],
  imports: [
    CommonModule,
    StudentRoutingModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatRadioModule,
    MatCardModule,
    MatToolbarModule,
    MatListModule,
    MatSidenavModule,
    MatProgressBarModule,
    MatDialogModule 
  ]
})
export class StudentModule { }

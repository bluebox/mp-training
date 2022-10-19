import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdmindashboardcomponentComponent } from './components/admindashboardcomponent/admindashboardcomponent.component';
import { AddTeacherComponent } from './components/add-teacher/add-teacher.component';
import { AddStudentComponent } from './components/add-student/add-student.component';
import { DisplayStudentComponent } from './components/display-student/display-student.component';
import { DisplayTeacherComponent } from './components/display-teacher/display-teacher.component';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';


@NgModule({
  declarations: [
    AdmindashboardcomponentComponent,
    AddTeacherComponent,
    AddStudentComponent,
    DisplayStudentComponent,
    DisplayTeacherComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatTableModule,
    MatIconModule
  ]
})
export class AdminModule { }

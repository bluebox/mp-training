import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdmindashboardcomponentComponent } from './components/admindashboardcomponent/admindashboardcomponent.component';
import { AddTeacherComponent } from './components/add-teacher/add-teacher.component';
import { AddStudentComponent } from './components/add-student/add-student.component';


@NgModule({
  declarations: [
    AdmindashboardcomponentComponent,
    AddTeacherComponent,
    AddStudentComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }

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
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { AdminhomepageComponent } from './components/adminhomepage/adminhomepage.component';



@NgModule({
  declarations: [
    AdmindashboardcomponentComponent,
    AddTeacherComponent,
    AddStudentComponent,
    DisplayStudentComponent,
    DisplayTeacherComponent,
    AdminNavbarComponent,
    AdminhomepageComponent,
   
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatTableModule,
    MatIconModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatInputModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule
  ]
})
export class AdminModule { }

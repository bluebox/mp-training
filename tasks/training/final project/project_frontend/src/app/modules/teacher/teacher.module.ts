import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeacherRoutingModule } from './teacher-routing.module';
import { AddCourseComponent } from './components/add-course/add-course.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AddQuestionComponent } from './components/add-question/add-question.component';
import { DisplayCourseComponent } from './components/display-course/display-course.component';
import { MatIconModule } from '@angular/material/icon';
import { DisplayQuestionsComponent } from './components/display-questions/display-questions.component';
import { UpdateCourseComponent } from './components/update-course/update-course.component';
import { UsersModule } from '../users/users.module';
import { LoginComponent } from '../users/components/login/login.component';
import { NavbarComponent } from '../components/navbar/navbar.component';
import { MatPaginatorModule } from '@angular/material/paginator';


@NgModule({
  declarations: [
    AddCourseComponent,
    DashboardComponent,
    AddQuestionComponent,
    DisplayCourseComponent,
    DisplayQuestionsComponent,
    UpdateCourseComponent,
    
    
  ],

  imports: [
    CommonModule,
    TeacherRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatIconModule,
    MatPaginatorModule, 
    
    
  ]
})
export class TeacherModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RegisterStudentComponent } from './components/register-student/register-student.component';
import { RegisterTeacherComponent } from './components/register-teacher/register-teacher.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { LoginComponent } from './components/login/login.component';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltip, MatTooltipModule } from '@angular/material/tooltip';
import { AppComponent } from 'src/app/app.component';
import { AppModule } from 'src/app/app.module';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { NavbarComponent } from '../components/navbar/navbar.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { StudentLoginhomepageComponent } from './components/student-loginhomepage/student-loginhomepage.component';
import { TeacherLoginhomepageComponent } from './components/teacher-loginhomepage/teacher-loginhomepage.component';
import { UsershomepageComponent } from './components/usershomepage/usershomepage.component';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import {MatDividerModule} from '@angular/material/divider';
import { FooterComponent } from '../components/footer/footer.component';

@NgModule({
  declarations: [
    DashboardComponent,
    RegisterStudentComponent,
    RegisterTeacherComponent,
    LoginComponent,
    NavbarComponent,
    StudentLoginhomepageComponent,
    TeacherLoginhomepageComponent,
    UsershomepageComponent,
    FooterComponent
   
    
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    FormsModule,ReactiveFormsModule,
    MatFormFieldModule,
    MatCardModule,
    MatRadioModule,
    MatSelectModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatProgressBarModule,
    MatDividerModule
        
  ]
})
export class UsersModule { }

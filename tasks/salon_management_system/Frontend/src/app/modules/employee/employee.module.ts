import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeRoutingModule } from './employee-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { ReviewsComponent } from './components/reviews/reviews.component';
import { ServicesComponent } from './components/services/services.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HomeComponent } from './components/home/home.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { BranchComponent } from './components/branch/branch.component';
import {MatTableModule} from '@angular/material/table';



@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    FooterComponent,
    AppointmentsComponent,
    ReviewsComponent,
    ServicesComponent,
    ProfileComponent,
    HomeComponent,
    BranchComponent
  ],
  imports: [
    CommonModule,
    EmployeeRoutingModule,
    MatToolbarModule,
    MatIconModule,
    MatTableModule
  ]
})
export class EmployeeModule { }

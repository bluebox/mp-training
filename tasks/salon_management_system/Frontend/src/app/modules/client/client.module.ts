import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { ServicesComponent } from './components/services/services.component';
import { BookappointmentComponent } from './components/bookappointment/bookappointment.component';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { ReviewComponent } from './components/review/review.component';
import { HomeComponent } from './components/home/home.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';



@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    FooterComponent,
    ProfileComponent,
    AppointmentsComponent,
    ServicesComponent,
    BookappointmentComponent,
    TransactionsComponent,
    ReviewComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    ClientRoutingModule,
    MatToolbarModule,
    MatIconModule
  ]
})
export class ClientModule { }
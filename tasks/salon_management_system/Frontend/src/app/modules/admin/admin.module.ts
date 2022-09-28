import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ClientsComponent } from './components/clients/clients.component';
import { EmployeesComponent } from './components/employees/employees.component';
import { AppointmentComponent } from './components/appointment/appointment.component';
import { ServicesComponent } from './components/services/services.component';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { HomeComponent } from './components/home/home.component';
import {MatToolbarModule} from '@angular/material/toolbar';


@NgModule({
  declarations: [
    DashboardComponent,
    HeaderComponent,
    FooterComponent,
    ClientsComponent,
    EmployeesComponent,
    AppointmentComponent,
    ServicesComponent,
    TransactionsComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatToolbarModule
  ]
})
export class AdminModule { }

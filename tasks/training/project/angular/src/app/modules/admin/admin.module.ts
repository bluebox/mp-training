import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DisplayAppointmentsComponent } from './components/display-appointments/display-appointments.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

// material
import { MatTableModule } from '@angular/material/table';

@NgModule({
  declarations: [
    DisplayAppointmentsComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatTableModule
  ]
})
export class AdminModule { }

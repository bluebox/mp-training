import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DisplayAppointmentsComponent } from './components/display-appointments/display-appointments.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

// material
import { MatTableModule } from '@angular/material/table';
import { AdminNavigationComponent } from './components/admin-navigation/admin-navigation.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [
    DisplayAppointmentsComponent,
    DashboardComponent,
    AdminNavigationComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatTableModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule
  ]
})
export class AdminModule { }

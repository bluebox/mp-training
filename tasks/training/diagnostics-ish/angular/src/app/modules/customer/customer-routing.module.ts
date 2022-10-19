import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { CustomerBookingComponent } from './components/customer-booking/customer-booking.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  {
    path: "", component: DashboardComponent, children: [
      { path: 'booking', component: CustomerBookingComponent },
      { path: 'view-appointments', component: AppointmentsComponent },
      { path: '', redirectTo: 'book-appointment', pathMatch: "full" }
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }

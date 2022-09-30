import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookappointmentComponent } from './components/bookappointment/bookappointment.component';
import { CustomerBookingComponent } from './components/customer-booking/customer-booking.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';

const routes: Routes = [
  {
    path: "", component: DashboardComponent, children: [
      { path: 'book-appointment', component: BookappointmentComponent },
      { path: 'booking', component: CustomerBookingComponent },
      { path: '', redirectTo: 'book-appointment', pathMatch: "full" }
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }

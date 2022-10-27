import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PaymentComponent } from './components/payment/payment.component';
import { SeatsComponent } from './components/seats/seats.component';
import { TheatrelistComponent } from './components/theatrelist/theatrelist.component';
import { TicketComponent } from './components/ticket/ticket.component';

const routes: Routes = [
  {path:"theatrelist/:id",component:TheatrelistComponent},
  {path:"seating/:hallid",component:SeatsComponent},
  {path:"payment",component:PaymentComponent},
  {path:"ticket/:User_id",component:TicketComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookingRoutingModule { }

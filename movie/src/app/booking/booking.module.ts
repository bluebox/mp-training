import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookingRoutingModule } from './booking-routing.module';
import { TheatrelistComponent } from './components/theatrelist/theatrelist.component';
import {MatButtonModule} from '@angular/material/button';
import { GetSeatNoComponent } from './components/get-seat-no/get-seat-no.component';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { ReactiveFormsModule } from '@angular/forms';
import { SeatsComponent } from './components/seats/seats.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { PaymentComponent } from './components/payment/payment.component';
import { TicketComponent } from './components/ticket/ticket.component';

@NgModule({
  declarations: [
    TheatrelistComponent,
    GetSeatNoComponent,
    SeatsComponent,
    ConfirmationComponent,
    PaymentComponent,
    TicketComponent,
  ],
  imports: [
    CommonModule,
    BookingRoutingModule,
    MatButtonModule,
    MatSelectModule,
    MatDatepickerModule,
    ReactiveFormsModule
  ]
})
export class BookingModule { }

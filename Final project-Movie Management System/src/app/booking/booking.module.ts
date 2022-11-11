import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookingRoutingModule } from './booking-routing.module';
import { TheatrelistComponent } from './components/theatrelist/theatrelist.component';
import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { ReactiveFormsModule } from '@angular/forms';
import { SeatsComponent } from './components/seats/seats.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { PaymentComponent } from './components/payment/payment.component';
import { TicketComponent } from './components/ticket/ticket.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { ConfirmComponent } from './components/confirm/confirm.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { FormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    TheatrelistComponent,
    SeatsComponent,
    ConfirmationComponent,
    PaymentComponent,
    TicketComponent,
    ConfirmComponent,
  ],
  imports: [
    CommonModule,
    BookingRoutingModule,
    MatButtonModule,
    MatSelectModule,
    MatDatepickerModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    FormsModule
  ]
})

export class BookingModule { }

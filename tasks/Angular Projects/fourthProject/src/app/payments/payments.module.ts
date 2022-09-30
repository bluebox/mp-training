import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaymentsRoutingModule } from './payments-routing.module';
import { PaymentsComponent } from './payments.component';
import { CashPaymentComponent } from './cash-payment/cash-payment.component';


@NgModule({
  declarations: [
    PaymentsComponent,
    CashPaymentComponent
  ],
  imports: [
    CommonModule,
    PaymentsRoutingModule
  ]
})
export class PaymentsModule { }

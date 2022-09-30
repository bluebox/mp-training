import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomersRoutingModule } from './customers-routing.module';
import { CustomersComponent } from './customers.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    CustomersComponent,
    AddCustomerComponent
  ],
  imports: [
    CommonModule,
    CustomersRoutingModule,
    FormsModule
  ]
})
export class CustomersModule { }

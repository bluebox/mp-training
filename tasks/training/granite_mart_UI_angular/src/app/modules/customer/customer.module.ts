import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerLoginComponent } from './customer-login/customer-login.component';
import { CustomerDashboardComponent } from './customer-dashboard/customer-dashboard.component';



@NgModule({
  declarations: [
    CustomerLoginComponent,
    CustomerDashboardComponent
  ],
  imports: [
    CommonModule
  ]
})
export class CustomerModule { }

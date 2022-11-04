import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdersListComponent } from './orders-list/orders-list.component';
import { OrdersViewComponent } from './orders-view/orders-view.component';
import { OrdersRegisterComponent } from './orders-register/orders-register.component';

import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { CustomerDashboardComponent } from './customer-dashboard/customer-dashboard.component';



@NgModule({
  declarations: [
    OrdersListComponent,
    OrdersViewComponent,
    OrdersRegisterComponent,
    AdminDashboardComponent,
    CustomerDashboardComponent
  ],
  imports: [
    CommonModule,
  ]
})
export class OrdersModule { }

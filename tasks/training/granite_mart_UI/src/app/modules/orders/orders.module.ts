import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdersListComponent } from './orders-list/orders-list.component';
import { OrdersViewComponent } from './orders-view/orders-view.component';
import { OrdersRegisterComponent } from './orders-register/orders-register.component';



@NgModule({
  declarations: [
    OrdersListComponent,
    OrdersViewComponent,
    OrdersRegisterComponent
  ],
  imports: [
    CommonModule
  ]
})
export class OrdersModule { }

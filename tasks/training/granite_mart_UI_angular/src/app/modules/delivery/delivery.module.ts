import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DeliveryRoutingModule } from './delivery-routing.module';
import { DeliveryRegisterComponent } from './delivery-register/delivery-register.component';
import { DeliveryViewComponent } from './delivery-view/delivery-view.component';


@NgModule({
  declarations: [
    DeliveryRegisterComponent,
    DeliveryViewComponent
  ],
  imports: [
    CommonModule,
    DeliveryRoutingModule
  ]
})
export class DeliveryModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FacilitiesRoutingModule } from './facilities-routing.module';
import { FacilityComponent } from './components/facility/facility.component';
import { ParticularfacilityComponent } from './components/particularfacility/particularfacility.component';
import { IndexComponent } from './components/index/index.component';
import { BookingComponent } from './components/booking/booking.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { PaymentpageComponent } from './components/paymentpage/paymentpage.component';
import { ParticularsportComponent } from './components/particularsport/particularsport.component';

@NgModule({
  declarations: [
    FacilityComponent,
    ParticularfacilityComponent,
    IndexComponent,
    BookingComponent,
    HeaderComponent,
    FooterComponent,
    PaymentpageComponent,
    ParticularsportComponent
  ],
  imports: [
    CommonModule,
    FacilitiesRoutingModule,
    ReactiveFormsModule,
  ],
  exports:[
    FooterComponent,
    HeaderComponent,


  ]
})
export class FacilitiesModule { }
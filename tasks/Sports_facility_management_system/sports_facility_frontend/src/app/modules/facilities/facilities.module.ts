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
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    FacilityComponent,
    ParticularfacilityComponent,
    IndexComponent,
    BookingComponent,
    HeaderComponent,
    FooterComponent,
    PaymentpageComponent,
    
  ],
  imports: [
    CommonModule,
    FacilitiesRoutingModule,
    ReactiveFormsModule,
    FormsModule
  ],
  exports:[
    FooterComponent,
    HeaderComponent,


  ]
})
export class FacilitiesModule { }

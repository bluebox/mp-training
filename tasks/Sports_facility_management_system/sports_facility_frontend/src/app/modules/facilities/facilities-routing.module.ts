import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from 'src/app/component/error/error.component';
import { BookingComponent } from './components/booking/booking.component';
import { FacilityComponent } from './components/facility/facility.component';
import { IndexComponent } from './components/index/index.component';
import { ParticularfacilityComponent } from './components/particularfacility/particularfacility.component';
import { ParticularsportComponent } from './components/particularsport/particularsport.component';
import { PaymentpageComponent } from './components/paymentpage/paymentpage.component';

const routes: Routes = [
  {
    path: '',
    component: IndexComponent,
    children: [
      {path:'hyderabad',component: FacilityComponent},
      { path: 'hyderabad/:id', component: ParticularfacilityComponent },
      {path:'hyderabad/sport/:id',component: ParticularsportComponent},
      { path: 'booking/:id', component: BookingComponent },
      {path:'paymentpage',component: PaymentpageComponent},
      { path: '**', component: ErrorComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FacilitiesRoutingModule {}

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FacilitycontrolRoutingModule } from './facilitycontrol-routing.module';
import { FacilityloginComponent } from './components/facilitylogin/facilitylogin.component';
import { FacilityhomeComponent } from './components/facilityhome/facilityhome.component';
import { FacilityindexComponent } from './components/facilityindex/facilityindex.component';


@NgModule({
  declarations: [
    FacilityloginComponent,
    FacilityhomeComponent,
    FacilityindexComponent
  ],
  imports: [
    CommonModule,
    FacilitycontrolRoutingModule
  ]
})
export class FacilitycontrolModule { }

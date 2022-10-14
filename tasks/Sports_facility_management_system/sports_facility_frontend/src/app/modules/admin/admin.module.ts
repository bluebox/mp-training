import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminhomeComponent } from './components/adminhome/adminhome.component';
import { ViewfacilitiesComponent } from './components/viewfacilities/viewfacilities.component';
import { MatGridListModule } from '@angular/material/grid-list';
import { CreatefacilityComponent } from './components/createfacility/createfacility.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../../angular-material.module';
import { AdminloginComponent } from './components/adminlogin/adminlogin.component';
import { AddsportsComponent } from './components/addsports/addsports.component';
import { AddslotsComponent } from './components/addslots/addslots.component';
@NgModule({
  declarations: [
    AdminhomeComponent,
    ViewfacilitiesComponent,
    CreatefacilityComponent,
    AdminloginComponent,
    AddsportsComponent,
    AddslotsComponent,
    
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatGridListModule,
    ReactiveFormsModule,
    AngularMaterialModule
    
  ]
})
export class AdminModule { }

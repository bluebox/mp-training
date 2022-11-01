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
import { AdminheaderComponent } from './components/adminheader/adminheader.component';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
@NgModule({
  declarations: [
    AdminhomeComponent,
    ViewfacilitiesComponent,
    CreatefacilityComponent,
    AdminloginComponent,
    AddsportsComponent,
    AddslotsComponent,
    AdminheaderComponent,
    
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatGridListModule,
    ReactiveFormsModule,
    AngularMaterialModule,
    FormsModule,
    MatTableModule
    
  ]
})
export class AdminModule { }

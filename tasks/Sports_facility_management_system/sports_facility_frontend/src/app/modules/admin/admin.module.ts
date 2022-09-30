import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminhomeComponent } from './components/adminhome/adminhome.component';
import { ViewfacilitiesComponent } from './components/viewfacilities/viewfacilities.component';
import { IndexComponent } from './components/index/index.component';

import { MatGridListModule } from '@angular/material/grid-list';
import { CreatefacilityComponent } from './components/createfacility/createfacility.component';
import { ReactiveFormsModule } from '@angular/forms';
@NgModule({
  declarations: [
    AdminhomeComponent,
    ViewfacilitiesComponent,
    IndexComponent,
    CreatefacilityComponent,
    
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatGridListModule,
    ReactiveFormsModule
    
  ]
})
export class AdminModule { }

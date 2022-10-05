import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { AddTourComponent } from './components/Tours/add-tour/add-tour.component';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';
import { HttpClientModule } from '@angular/common/http';
import { AddCouponComponent } from './components/Coupons/add-coupon/add-coupon.component';
import { ToursListComponent } from './components/Tours/tours-list/tours-list.component';
import { CouponsListComponent } from './components/Coupons/coupons-list/coupons-list.component';
import { CouponsComponent } from './components/Coupons/coupons/coupons.component';
import { ToursComponent } from './components/Tours/tours/tours.component';
import { VehiclesComponent } from './components/Vehicles/vehicles/vehicles.component';
import { AddVehicleComponent } from './components/Vehicles/add-vehicle/add-vehicle.component';
import { VehicleListComponent } from './components/Vehicles/vehicle-list/vehicle-list.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { AddPlacesComponent } from './components/Places/add-places/add-places.component';
import { PlacesComponent } from './components/Places/places/places.component';
import { PlacesListComponent } from './components/Places/places-list/places-list.component';
import { EnquiryListComponent } from './components/Enquiry/enquiry-list/enquiry-list.component';
import { ViewAndEditEnquiryComponent } from './components/Enquiry/view-and-edit-enquiry/view-and-edit-enquiry.component';
import { EnquiriesComponent } from './components/Enquiry/enquiries/enquiries.component';


@NgModule({
  declarations: [
    DashboardComponent,
    HomeComponent,
    AddTourComponent,
    AddCouponComponent,
    ToursListComponent,
    CouponsListComponent,
    CouponsComponent,
    ToursComponent,
    VehiclesComponent,
    AddVehicleComponent,
    VehicleListComponent,
    AddPlacesComponent,
    PlacesComponent,
    PlacesListComponent,
    EnquiryListComponent,
    ViewAndEditEnquiryComponent,
    EnquiriesComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatInputModule,
    ReactiveFormsModule,
    MatSelectModule,
    HttpClientModule,
    MatCheckboxModule,
  ]
})
export class AdminModule { }

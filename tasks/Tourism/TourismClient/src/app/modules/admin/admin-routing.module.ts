import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCouponComponent } from './components/Coupons/add-coupon/add-coupon.component';
import { AddTourComponent } from './components/Tours/add-tour/add-tour.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { ToursListComponent } from './components/Tours/tours-list/tours-list.component';
import { CouponsListComponent } from './components/Coupons/coupons-list/coupons-list.component';
import { CouponsComponent } from './components/Coupons/coupons/coupons.component';
import { ToursComponent } from './components/Tours/tours/tours.component';
import { AddVehicleComponent } from './components/Vehicles/add-vehicle/add-vehicle.component';
import { VehicleListComponent } from './components/Vehicles/vehicle-list/vehicle-list.component';
import { VehiclesComponent } from './components/Vehicles/vehicles/vehicles.component';
import { PlacesComponent } from './components/Places/places/places.component';
import { AddPlacesComponent } from './components/Places/add-places/add-places.component';
import { PlacesListComponent } from './components/Places/places-list/places-list.component';
import { EnquiriesComponent } from './components/Enquiry/enquiries/enquiries.component';
import { ViewAndEditEnquiryComponent } from './components/Enquiry/view-and-edit-enquiry/view-and-edit-enquiry.component';
import { EnquiryListComponent } from './components/Enquiry/enquiry-list/enquiry-list.component';

const routes: Routes = [
  {path:"", component: DashboardComponent, children: [
    {path:"", redirectTo:'home', pathMatch:'full'},
    {path:"home", component: HomeComponent},
    {path:"tours", component: ToursComponent, children: [
      {path:"", redirectTo:'tourList', pathMatch:'full'},
      {path:"addTour", component: AddTourComponent},
      {path:"addTour/:id", component: AddTourComponent},
      {path:"tourList", component: ToursListComponent},
    ]},
    {path:"coupons", component: CouponsComponent, children: [
      {path:"", redirectTo:'couponList', pathMatch:'full'},
      {path:"addCoupon", component: AddCouponComponent},
      {path:"addCoupon/:id", component: AddCouponComponent},
      {path:"couponList", component: CouponsListComponent},
    ]},
    {path:"vehicles", component: VehiclesComponent, children: [
      {path:"", redirectTo:'vehicleList', pathMatch:'full'},
      {path:"addVehicle", component: AddVehicleComponent},
      {path:"addVehicle/:id", component: AddVehicleComponent},
      {path:"vehicleList", component: VehicleListComponent},
    ]},
    {path:"places", component: PlacesComponent, children: [
      {path:"", redirectTo:'placeList', pathMatch:'full'},
      {path:"addPlace", component: AddPlacesComponent},
      {path:"addPlace/:id", component: AddPlacesComponent},
      {path:"placeList", component: PlacesListComponent},
    ]},
    {path:"enquiries", component: EnquiriesComponent, children: [
      {path:"", redirectTo:'enquiryList', pathMatch:'full'},
      {path:"viewEnquiry", component: ViewAndEditEnquiryComponent},
      {path:"viewEnquiry/:id", component: ViewAndEditEnquiryComponent},
      {path:"enquiryList", component: EnquiryListComponent},
    ]},
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

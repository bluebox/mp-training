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
import { BookingListComponent } from './components/Bookings/booking-list/booking-list.component';
import { BookingsComponent } from './components/Bookings/bookings/bookings.component';
import { UsersListComponent } from './components/Users/users-list/users-list.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import { CancellationListComponent } from './components/Cancellation/cancellation-list/cancellation-list.component';
import { EditCancellationComponent } from './components/Cancellation/edit-cancellation/edit-cancellation.component';
import { CancellationsComponent } from './components/Cancellation/cancellations/cancellations.component';
import { FeedbacksComponent } from './components/FeedBacks/feedbacks/feedbacks.component';
import { PackagesComponent } from './components/Packages/packages/packages.component';
import { PackageListComponent } from './components/Packages/package-list/package-list.component';
import { AddPackageComponent } from './components/Packages/add-package/add-package.component';
import { EmployeesComponent } from './components/Employees/employees/employees.component';
import { EmployeeListComponent } from './components/Employees/employee-list/employee-list.component';
import { AddEmlployeeComponent } from './components/Employees/add-emlployee/add-emlployee.component';
import { EditUserComponent } from './components/Users/edit-user/edit-user.component';
import { EditBookingComponent } from './components/Bookings/edit-booking/edit-booking.component';


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
    EnquiriesComponent,
    BookingListComponent,
    BookingsComponent,
    UsersListComponent,
    CancellationListComponent,
    EditCancellationComponent,
    CancellationsComponent,
    FeedbacksComponent,
    PackagesComponent,
    PackageListComponent,
    AddPackageComponent,
    EmployeesComponent,
    EmployeeListComponent,
    AddEmlployeeComponent,
    EditUserComponent,
    EditBookingComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatInputModule,
    ReactiveFormsModule,
    MatSelectModule,
    HttpClientModule,
    MatCheckboxModule,
    MatPaginatorModule,
  ]
})
export class AdminModule { }

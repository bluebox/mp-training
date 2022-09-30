import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { OwnerloginComponent } from './component/ownerlogin/ownerlogin.component';
import { OwnerregisterComponent } from './component/ownerregister/ownerregister.component';
import { CustomerloginComponent } from './component/customerlogin/customerlogin.component';
import { CustomerregisterComponent } from './component/customerregister/customerregister.component';
import { GeneralService } from './general.service';
import { ReactiveFormsModule } from '@angular/forms';
import { PagenotfoundComponent } from './component/pagenotfound/pagenotfound.component';
import { HttpClientModule } from '@angular/common/http';
import { AddvehicleComponent } from './component/owner/addvehicle/addvehicle.component';
import { ProfileComponent } from './component/owner/profile/profile.component';
import { PreviousvehiclesComponent } from './component/owner/previousvehicles/previousvehicles.component';
import { AvailableVehicleComponent } from './component/customer/available-vehicle/available-vehicle.component';
import { OrderHistoryComponent } from './component/customer/order-history/order-history.component';
import { HomepageComponent } from './component/homepage/homepage.component';
import { CustomerProfileComponent } from './component/customer/customer-profile/customer-profile.component';
import { NavbarComponent } from './component/customer/navbar/navbar.component';
import { ONavbarComponent } from './component/owner/o-navbar/o-navbar.component';
import { BookComponent } from './component/customer/book/book.component';
// import {MatDatepickerModule} from '@angular/material/datepicker';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {MatButtonModule} from '@angular/material/button';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';





@NgModule({
  declarations: [
    AppComponent,
    OwnerloginComponent,
    OwnerregisterComponent,
    CustomerloginComponent,
    CustomerregisterComponent,
    PagenotfoundComponent,
    AddvehicleComponent,
    ProfileComponent,
    PreviousvehiclesComponent,
    AvailableVehicleComponent,
    OrderHistoryComponent,
    HomepageComponent,
    CustomerProfileComponent,
    NavbarComponent,
    ONavbarComponent,
    BookComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    // MatDatepickerModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatDatepickerModule,
    MatInputModule,
    MatFormFieldModule
  ],
  providers: [GeneralService],
  bootstrap: [AppComponent]
})
export class AppModule { }

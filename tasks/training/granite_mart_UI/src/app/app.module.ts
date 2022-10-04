import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerListComponent } from './modules/customer/customer-list/customer-list.component';
import { CustomerViewComponent } from './modules/customer/customer-view/customer-view.component';
import { CustomerUpdateComponent } from './modules/customer/customer-update/customer-update.component';
import { StoreListComponent } from './modules/granite-store/store-list/store-list.component';
import {HttpClientModule} from '@angular/common/http';
import { EmployeeListComponent } from './modules/employee/employee-list/employee-list.component';
import { CustomerRegisterComponent } from './modules/customer/customer-register/customer-register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CustomerLoginComponent } from './modules/customer/customer-login/customer-login.component';
import { EmployeeRegisterComponent } from './modules/employee/employee-register/employee-register.component';
import { EmployeeUpdateComponent } from './modules/employee/employee-update/employee-update.component';
import { VehiclesRegisterComponent } from './modules/vehicles/vehicles-register/vehicles-register.component';
import { VehiclesUpdateComponent } from './modules/vehicles/vehicles-update/vehicles-update.component';
import { StoreRegisterComponent } from './modules/granite-store/store-register/store-register.component';
import { StoreUpdateComponent } from './modules/granite-store/store-update/store-update.component';
import { ItemListComponent } from './modules/items/item-list/item-list.component';
import { ItemViewComponent } from './modules/items/item-view/item-view.component';
@NgModule({
  declarations: [
    AppComponent,
    CustomerListComponent,
    CustomerViewComponent,
    CustomerRegisterComponent,
    CustomerUpdateComponent,
    StoreListComponent,
    EmployeeListComponent,
    CustomerLoginComponent,
    EmployeeRegisterComponent,
    EmployeeUpdateComponent,
    VehiclesRegisterComponent,
    VehiclesUpdateComponent,
    StoreRegisterComponent,
    StoreUpdateComponent,
    ItemListComponent,
    ItemViewComponent
  ],
  imports: [
    BrowserModule ,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

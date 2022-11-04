import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerListComponent } from './modules/customer/customer-list/customer-list.component';
import { CustomerViewComponent } from './modules/customer/customer-view/customer-view.component';
import { CustomerUpdateComponent } from './modules/customer/customer-update/customer-update.component';
import { StoreListComponent } from './modules/granite-store/store-list/store-list.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
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
import { AdminLoginComponent } from './modules/admin/admin-login/admin-login.component';
import { AdminRegisterComponent } from './modules/admin/admin-register/admin-register.component';
import { AdminUpdateComponent } from './modules/admin/admin-update/admin-update.component';
import { JwtInterceptor } from './modules/jwt.interceptor';
import { AdminDashboardComponent } from './modules/admin/admin-dashboard/admin-dashboard.component';
import { CustomerDashboardComponent } from './modules/customer/customer-dashboard/customer-dashboard.component';
import { CookieService } from 'ngx-cookie-service';
import { ViewCartComponent } from './modules/cart/view-cart/view-cart.component';
import { ItemRegisterComponent } from './modules/items/item-register/item-register.component';
import { OrdersRegisterComponent } from './modules/orders/orders-register/orders-register.component';
import { OrdersListComponent } from './modules/orders/orders-list/orders-list.component';
import { DeliveryRegisterComponent } from './modules/delivery/delivery-register/delivery-register.component';
import { DeliveryViewComponent } from './modules/delivery/delivery-view/delivery-view.component';
import { OrdersViewComponent } from './modules/orders/orders-view/orders-view.component';
import { VehiclesListComponent } from './modules/vehicles/vehicles-list/vehicles-list.component';
import { EmployeeViewComponent } from './modules/employee/employee-view/employee-view.component';
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
    ItemViewComponent,
    VehiclesUpdateComponent,
    VehiclesRegisterComponent,
    AdminLoginComponent,
    AdminRegisterComponent,
    AdminUpdateComponent,
    AdminDashboardComponent,
    CustomerDashboardComponent,
    ViewCartComponent,
    ItemRegisterComponent,
    OrdersRegisterComponent,
    OrdersListComponent,
    DeliveryRegisterComponent,
    DeliveryViewComponent,
    OrdersViewComponent,
    VehiclesListComponent,
    EmployeeViewComponent,
    EmployeeUpdateComponent,
  ],
  imports: [
    BrowserModule ,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    {provide:HTTP_INTERCEPTORS, useClass:JwtInterceptor, multi:true},
    CookieService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

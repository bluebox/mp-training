import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';
import { AdminGuard } from './modules/admin.guard';
import { AdminDashboardComponent } from './modules/admin/admin-dashboard/admin-dashboard.component';
import { AdminLoginComponent } from './modules/admin/admin-login/admin-login.component';
import { AdminRegisterComponent } from './modules/admin/admin-register/admin-register.component';
import { AdminUpdateComponent } from './modules/admin/admin-update/admin-update.component';
import { ViewCartComponent } from './modules/cart/view-cart/view-cart.component';
import { CustomerListComponent } from './modules/customer/customer-list/customer-list.component';
import { CustomerLoginComponent } from './modules/customer/customer-login/customer-login.component';
import { CustomerRegisterComponent } from './modules/customer/customer-register/customer-register.component';
import { CustomerUpdateComponent } from './modules/customer/customer-update/customer-update.component';
import { CustomerViewComponent } from './modules/customer/customer-view/customer-view.component';
import { DeliveryRegisterComponent } from './modules/delivery/delivery-register/delivery-register.component';
import { DeliveryViewComponent } from './modules/delivery/delivery-view/delivery-view.component';
import { EmployeeListComponent } from './modules/employee/employee-list/employee-list.component';
import { EmployeeRegisterComponent } from './modules/employee/employee-register/employee-register.component';
import { EmployeeUpdateComponent } from './modules/employee/employee-update/employee-update.component';
import { EmployeeViewComponent } from './modules/employee/employee-view/employee-view.component';
import { StoreListComponent } from './modules/granite-store/store-list/store-list.component';
import { StoreRegisterComponent } from './modules/granite-store/store-register/store-register.component';
import { StoreUpdateComponent } from './modules/granite-store/store-update/store-update.component';
import { StoreViewComponent } from './modules/granite-store/store-view/store-view.component';
import { ItemListComponent } from './modules/items/item-list/item-list.component';
import { ItemRegisterComponent } from './modules/items/item-register/item-register.component';
import { ItemViewComponent } from './modules/items/item-view/item-view.component';
import { OrdersListComponent } from './modules/orders/orders-list/orders-list.component';
import { OrdersRegisterComponent } from './modules/orders/orders-register/orders-register.component';
import { OrdersViewComponent } from './modules/orders/orders-view/orders-view.component';
import { VehiclesListComponent } from './modules/vehicles/vehicles-list/vehicles-list.component';
import { VehiclesRegisterComponent } from './modules/vehicles/vehicles-register/vehicles-register.component';
import { VehiclesUpdateComponent } from './modules/vehicles/vehicles-update/vehicles-update.component';
import { VehiclesViewComponent } from './modules/vehicles/vehicles-view/vehicles-view.component';
import { RegisterGuard } from './register.guard';

const routes: Routes = [
  {path:'',redirectTo:'customerLogin',pathMatch:'full'},
  {path:'adminLogin',component:AdminLoginComponent,canActivate:[RegisterGuard]},
  // {path:'adminDashboard',component:AdminDashboardComponent},
  {path:'adminRegistration',component:AdminRegisterComponent},
  {path:'adminUpdate',component:AdminUpdateComponent},
  {path:'customersList/:username',component:CustomerListComponent,canActivate:[AuthGuard]},
  {path:'viewCustomer/:customer_id',component:CustomerViewComponent,canActivate:[AuthGuard]},
  {path:'customerRegistration',component:CustomerRegisterComponent},
  {path:'customerLogin',component:CustomerLoginComponent,canActivate:[RegisterGuard]},
  {path:'customerEdit/:username',component:CustomerUpdateComponent,canActivate:[AuthGuard]},
  {path:'employeesList',component:EmployeeListComponent,canActivate:[AuthGuard,AdminGuard]},
  {path:'employeeRegistration',component:EmployeeRegisterComponent,canActivate:[AuthGuard]},
  {path:'viewEmployee/:employee_id',component:EmployeeViewComponent,canActivate:[AuthGuard]},
  {path:'employeeEdit/:employee_id',component:EmployeeUpdateComponent,canActivate:[AuthGuard]},
  {path:'vehiclesList',component:VehiclesListComponent,canActivate:[AuthGuard]},
  {path:'vehicleRegistration',component:VehiclesRegisterComponent,canActivate:[AuthGuard]},
  {path:'vehicleEdit/:vehicle_no',component:VehiclesUpdateComponent,canActivate:[AuthGuard]},
  {path:'viewVehicle/:vehicle_no',component:VehiclesViewComponent,canActivate:[AuthGuard]},
  {path:'storeRegistration',component:StoreRegisterComponent,canActivate:[AuthGuard]},
  {path:'viewStore/:store_id',component:StoreViewComponent,canActivate:[AuthGuard]},
  {path:'storeList',component:StoreListComponent,canActivate:[AuthGuard]},
  {path:'storeEdit/:store_id',component:StoreUpdateComponent,canActivate:[AuthGuard]},
  {path:'itemsList',component:ItemListComponent,canActivate:[AuthGuard]},
  {path:'viewItem/:contains_id',component:ItemViewComponent,canActivate:[AuthGuard]},
  {path:'orderList',component:OrdersListComponent,canActivate:[AuthGuard]},
  {path:'viewCart/:username',component:ViewCartComponent,canActivate:[AuthGuard]},
  {path:'itemRegistration',component:ItemRegisterComponent,canActivate:[AuthGuard]},
  {path:'placeOrder/:username',component:OrdersRegisterComponent,canActivate:[AuthGuard]},
  {path:'viewOrder/:order_id',component:OrdersViewComponent,canActivate:[AuthGuard]},
  {path:'viewDelivery/:order_id',component:DeliveryViewComponent,canActivate:[AuthGuard]},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './modules/admin.guard';
import { AdminDashboardComponent } from './modules/admin/admin-dashboard/admin-dashboard.component';
import { AdminLoginComponent } from './modules/admin/admin-login/admin-login.component';
import { AdminRegisterComponent } from './modules/admin/admin-register/admin-register.component';
import { AdminUpdateComponent } from './modules/admin/admin-update/admin-update.component';
import { CustomerListComponent } from './modules/customer/customer-list/customer-list.component';
import { CustomerLoginComponent } from './modules/customer/customer-login/customer-login.component';
import { CustomerRegisterComponent } from './modules/customer/customer-register/customer-register.component';
import { CustomerUpdateComponent } from './modules/customer/customer-update/customer-update.component';
import { CustomerViewComponent } from './modules/customer/customer-view/customer-view.component';
import { EmployeeListComponent } from './modules/employee/employee-list/employee-list.component';
import { EmployeeRegisterComponent } from './modules/employee/employee-register/employee-register.component';
import { EmployeeUpdateComponent } from './modules/employee/employee-update/employee-update.component';
import { EmployeeViewComponent } from './modules/employee/employee-view/employee-view.component';
import { StoreListComponent } from './modules/granite-store/store-list/store-list.component';
import { StoreRegisterComponent } from './modules/granite-store/store-register/store-register.component';
import { StoreUpdateComponent } from './modules/granite-store/store-update/store-update.component';
import { StoreViewComponent } from './modules/granite-store/store-view/store-view.component';
import { ItemListComponent } from './modules/items/item-list/item-list.component';
import { ItemViewComponent } from './modules/items/item-view/item-view.component';
import { OrdersListComponent } from './modules/orders/orders-list/orders-list.component';
import { VehiclesListComponent } from './modules/vehicles/vehicles-list/vehicles-list.component';
import { VehiclesRegisterComponent } from './modules/vehicles/vehicles-register/vehicles-register.component';
import { VehiclesUpdateComponent } from './modules/vehicles/vehicles-update/vehicles-update.component';
import { VehiclesViewComponent } from './modules/vehicles/vehicles-view/vehicles-view.component';

const routes: Routes = [
  {path:'',redirectTo:'customerLogin',pathMatch:'full'},
  {path:'adminLogin',component:AdminLoginComponent,canActivate:[AdminGuard]},
  // {path:'adminDashboard',component:AdminDashboardComponent},
  {path:'adminRegistration',component:AdminRegisterComponent},
  {path:'adminUpdate',component:AdminUpdateComponent},
  {path:'customersList',component:CustomerListComponent},
  {path:'viewCustomer/:customer_id',component:CustomerViewComponent},
  {path:'customerRegistration',component:CustomerRegisterComponent},
  {path:'customerLogin',component:CustomerLoginComponent},
  {path:'customerEdit/:username',component:CustomerUpdateComponent},
  {path:'employeesList',component:EmployeeListComponent},
  {path:'employeeRegistration',component:EmployeeRegisterComponent},
  {path:'viewEmployee/:employee_id',component:EmployeeViewComponent},
  {path:'employeeEdit/:employee_id',component:EmployeeUpdateComponent},
  {path:'vehiclesList',component:VehiclesListComponent},
  {path:'vehicleRegistration',component:VehiclesRegisterComponent},
  {path:'vehicleEdit/:vehicle_no',component:VehiclesUpdateComponent},
  {path:'viewVehicle/:vehicle_no',component:VehiclesViewComponent},
  {path:'storeRegistration',component:StoreRegisterComponent},
  {path:'viewStore/:store_id',component:StoreViewComponent},
  {path:'storeList',component:StoreListComponent},
  {path:'storeEdit/:store_id',component:StoreUpdateComponent},
  {path:'itemsList',component:ItemListComponent},
  {path:'viewItem/:contains_id',component:ItemViewComponent},
  {path:'orderList',component:OrdersListComponent},
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

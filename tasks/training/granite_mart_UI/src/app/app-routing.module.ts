import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerListComponent } from './modules/customer/customer-list/customer-list.component';
import { CustomerLoginComponent } from './modules/customer/customer-login/customer-login.component';
import { CustomerRegisterComponent } from './modules/customer/customer-register/customer-register.component';
import { CustomerUpdateComponent } from './modules/customer/customer-update/customer-update.component';
import { CustomerViewComponent } from './modules/customer/customer-view/customer-view.component';
import { EmployeeListComponent } from './modules/employee/employee-list/employee-list.component';
import { EmployeeRegisterComponent } from './modules/employee/employee-register/employee-register.component';
import { EmployeeUpdateComponent } from './modules/employee/employee-update/employee-update.component';
import { StoreListComponent } from './modules/granite-store/store-list/store-list.component';
import { OrdersListComponent } from './modules/orders/orders-list/orders-list.component';

const routes: Routes = [
  {path:'customersList',component:CustomerListComponent},
  {path:'viewCustomer/:customer_id',component:CustomerViewComponent},
  {path:'employeesList',component:EmployeeListComponent},
  {path:'employeeRegistration',component:EmployeeRegisterComponent},
  {path:'employeeEdit/:employee_id',component:EmployeeUpdateComponent},
  {path:'storeList',component:StoreListComponent},
  {path:'orderList',component:OrdersListComponent},
  {path:'customerRegistration',component:CustomerRegisterComponent},
  {path:'customerLogin',component:CustomerLoginComponent},
  {path:'customerEdit/:username',component:CustomerUpdateComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

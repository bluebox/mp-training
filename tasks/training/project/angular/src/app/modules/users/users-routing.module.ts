import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { RegisterCustomerComponent } from './components/register-customer/register-customer.component';

const routes: Routes = [
  {path:"",component:DashboardComponent,children:[
    {path:'register-customer' , component : RegisterCustomerComponent },
    {path : '', redirectTo:'register-customer',pathMatch:"full"}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }

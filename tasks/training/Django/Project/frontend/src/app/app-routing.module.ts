import { CustomerGuard } from './customer.guard';
import { ViewProfileComponent } from './component/owner/view-profile/view-profile.component';

import { OrderHistoryComponent } from './component/customer/order-history/order-history.component';
import { ProfileComponent } from './component/owner/profile/profile.component';
import { AvailableVehicleComponent } from './component/customer/available-vehicle/available-vehicle.component';
import { OwnerloginComponent } from './component/ownerlogin/ownerlogin.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerloginComponent } from './component/customerlogin/customerlogin.component';
import { CustomerregisterComponent } from './component/customerregister/customerregister.component';
import { OwnerregisterComponent } from './component/ownerregister/ownerregister.component';
import { PagenotfoundComponent } from './component/pagenotfound/pagenotfound.component';
import { AddvehicleComponent } from './component/owner/addvehicle/addvehicle.component';
import { HomepageComponent } from './component/homepage/homepage.component';
import { CustomerProfileComponent } from './component/customer/customer-profile/customer-profile.component';
import { PreviousvehiclesComponent } from './component/owner/previousvehicles/previousvehicles.component';
import { BookComponent } from './component/customer/book/book.component';
import { OrdersComponent } from './component/owner/orders/orders.component';

import { ViewCustomerProfileComponent } from './component/customer/view-customer-profile/view-customer-profile.component';
import { OwnerGuard } from './owner.guard';
import { OwnBillComponent } from './component/owner/own-bill/own-bill.component';
import { ViewBillComponent } from './component/customer/view-bill/view-bill.component';


const routes: Routes = [
  {path:'', component: HomepageComponent , },

  {path:'view-bill/order-history', redirectTo: 'order-history', pathMatch:'full'},
  {path:'view-bill/c-profile', redirectTo: 'c-profile', pathMatch:'full'},
  {path:'view-bill/available-vehicles', redirectTo: 'available-vehicles', pathMatch:'full'},

  {path:'own-view-bill/orders', redirectTo: 'orders', pathMatch:'full'},
  {path:'own-view-bill/o-profile', redirectTo: 'o-profile', pathMatch:'full'},
  {path:'own-view-bill/previous-vehicles', redirectTo: 'previous-vehicles', pathMatch:'full'},
  {path:'own-view-bill/addvehicle', redirectTo: 'addvehicle', pathMatch:'full'},

  {path:'book-vehicle/available-vehicles', redirectTo: 'available-vehicles', pathMatch:'full'},
  {path:'book-vehicle/c-profile', redirectTo: 'c-profile', pathMatch:'full'},
  {path:'book-vehicle/order-history', redirectTo: 'order-history', pathMatch:'full'},

  {path:'customerlogin', component: CustomerloginComponent },
  {path:'ownerlogin', component: OwnerloginComponent},
  {path:'customerregister', component: CustomerregisterComponent},
  {path:'ownerregister', component: OwnerregisterComponent},
  {path:'addvehicle', component: AddvehicleComponent, canActivate :[OwnerGuard]},
  {path:'available-vehicles', component: AvailableVehicleComponent, canActivate :[CustomerGuard]},
  {path:'customer-profile', component: CustomerProfileComponent, canActivate :[CustomerGuard]},
  {path:'order-history', component: OrderHistoryComponent, canActivate :[CustomerGuard]},
  {path:'o-profile', component: ViewProfileComponent,canActivate :[OwnerGuard]},
  {path:'c-profile', component: ViewCustomerProfileComponent, canActivate :[CustomerGuard]},
  {path:'owner-profile', component:ProfileComponent, canActivate :[OwnerGuard]},
  {path:'previousvehicles', component:PreviousvehiclesComponent, canActivate :[OwnerGuard]},
  {path:'book-vehicle/:id', component: BookComponent, canActivate :[CustomerGuard]},
  {path:'orders', component: OrdersComponent, canActivate :[OwnerGuard]},
  {path:'view-bill/:id', component: ViewBillComponent, canActivate :[CustomerGuard]},
  {path:'own-view-bill/:id', component: OwnBillComponent, canActivate :[OwnerGuard]},

  {path:'**', component: PagenotfoundComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

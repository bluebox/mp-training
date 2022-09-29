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
const routes: Routes = [
  {path:'', component: HomepageComponent},
  {path:'customerlogin', component: CustomerloginComponent},
  {path:'ownerlogin', component: OwnerloginComponent},
  {path:'customerregister', component: CustomerregisterComponent},
  {path:'ownerregister', component: OwnerregisterComponent},
  {path:'addvehicle', component: AddvehicleComponent},
  {path:'available-vehicles', component: AvailableVehicleComponent},
  {path:'customer-profile', component: CustomerProfileComponent},
  {path:'order-history', component: OrderHistoryComponent},
  {path:'owner-profile', component:ProfileComponent},
  {path:'previousvehicles', component:PreviousvehiclesComponent},
  {path:'**', component: PagenotfoundComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

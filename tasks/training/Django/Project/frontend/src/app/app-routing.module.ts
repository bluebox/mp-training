import { OwnerloginComponent } from './component/ownerlogin/ownerlogin.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerloginComponent } from './component/customerlogin/customerlogin.component';
import { CustomerregisterComponent } from './component/customerregister/customerregister.component';
import { OwnerregisterComponent } from './component/ownerregister/ownerregister.component';
import { PagenotfoundComponent } from './component/pagenotfound/pagenotfound.component';
import { AddvehicleComponent } from './component/owner/addvehicle/addvehicle.component';
import { HomepageComponent } from './component/homepage/homepage.component';

const routes: Routes = [
  {path:'', component: HomepageComponent},
  {path:'customerlogin', component: CustomerloginComponent},
  {path:'ownerlogin', component: OwnerloginComponent},
  {path:'customerregister', component: CustomerregisterComponent},
  {path:'ownerregister', component: OwnerregisterComponent},
  {path:'addvehicle', component: AddvehicleComponent},
  {path:'**', component: PagenotfoundComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

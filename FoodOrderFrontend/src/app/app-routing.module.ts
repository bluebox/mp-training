import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { RestaurantGuard } from './restaurant.guard';

const routes: Routes = [
  {path:'',component:IndexComponent},
  {path:'customer',loadChildren:()=>import('./modules/customer/customer.module').then(m=>m.CustomerModule)},
  {path:'restaurants',canActivate:[RestaurantGuard],loadChildren:()=>import('./modules/restaurants/restaurants.module').then(m=>m.RestaurantsModule)},
  {path:'employee',loadChildren:()=>import('./modules/employee/employee.module').then(n=>n.EmployeeModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

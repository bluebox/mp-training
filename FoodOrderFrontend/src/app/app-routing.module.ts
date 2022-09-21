import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RestaurantGuard } from './restaurant.guard';
import { SigninComponent } from './signin/signin.component';

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'signin',component:SigninComponent},
  {path:'home',component:HomeComponent},
  {path:'restaurants',canActivate:[RestaurantGuard],loadChildren:()=>import('./modules/restaurants/restaurants.module').then(m=>m.RestaurantsModule)},
  {path:'employee',loadChildren:()=>import('./modules/employee/employee.module').then(n=>n.EmployeeModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingsComponent } from './components/bookings/bookings.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { TourPageComponent } from './components/tour-page/tour-page.component';
import { TourComponent } from './components/tour/tour.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  {path:'', redirectTo:'user', pathMatch:'full'},
  {path:'login', component: LoginComponent},
  {path:'signup', component: SignupComponent},
  {path:'contact', component: ContactusComponent},
  {path:'bookings', component: BookingsComponent},
  {path:'checkOut/:id', component: CheckoutComponent},
  {path:'user', component: UserDashboardComponent, children:[
    {path:'', redirectTo:'home', pathMatch:'full'},
    {path:'home', component: HomeComponent},
    {path:'tours/:tourType', component: TourComponent},
    {path:'tour/:id', component: TourPageComponent},
  ]},
  {path:'admin', canActivate:[AuthGuard], loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule)}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

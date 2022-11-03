import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookingsComponent } from './modules/user/components/bookings/bookings.component';
import { CheckoutComponent } from './modules/user/components/checkout/checkout.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { HomeComponent } from './modules/user/components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { TourPageComponent } from './modules/user/components/tour-page/tour-page.component';
import { TourComponent } from './modules/user/components/tour/tour.component';
import { UserDashboardComponent } from './modules/user/components/user-dashboard/user-dashboard.component';
import { AuthGuard } from './guards/auth.guard';
import { AdminGuard } from './guards/admin.guard';
import { LoginGuard } from './guards/login.guard';
import { UserGuard } from './guards/user.guard';

const routes: Routes = [
  {path:'', redirectTo:'login', pathMatch:'full'},
  {path:'login',canActivate:[LoginGuard], component: LoginComponent},
  {path:'signup', component: SignupComponent},
  {path:'contact', component: ContactusComponent},
  {path:'user', canActivate:[AuthGuard], loadChildren:() => import('./modules/user/user.module').then(m=> m.UserModule)},
  {path:'admin', canActivate:[AuthGuard, AdminGuard], loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule)}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

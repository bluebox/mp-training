import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllFeedbacksComponent } from './components/all-feedbacks/all-feedbacks.component';
import { BookingPageComponent } from './components/booking-page/booking-page.component';
import { BookingsComponent } from './components/bookings/bookings.component';
import { CancelledBookingsComponent } from './components/cancelled-bookings/cancelled-bookings.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { HomeComponent } from './components/home/home.component';
import { PackageToursComponent } from './components/package-tours/package-tours.component';
import { PreviousBookingsComponent } from './components/previous-bookings/previous-bookings.component';
import { TourPageComponent } from './components/tour-page/tour-page.component';
import { TourComponent } from './components/tour/tour.component';
import { UpcomingBookingsComponent } from './components/upcoming-bookings/upcoming-bookings.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';

const routes: Routes = [
  {path:'', component: UserDashboardComponent, children:[
    {path:'', redirectTo:'home', pathMatch:'full'},
    {path:'home', component: HomeComponent},
    {path:'editProfile', component: EditProfileComponent},
    {path:'tours/:tourType', component: TourComponent},
    {path:'tours', component: TourComponent},
    {path:'package/:id', component: PackageToursComponent},
    {path:'tour/:id', component: TourPageComponent},
    {path:'bookings', component: BookingsComponent, children:[
      {path: '', redirectTo: 'upcoming', pathMatch: 'full'},
      {path: 'upcoming', component: UpcomingBookingsComponent},
      {path: 'previous', component: PreviousBookingsComponent},
      {path: 'cancelled', component: CancelledBookingsComponent},
    ]},
    {path:'bookings/:id', component: BookingPageComponent},
    {path:'feedbacks', component: AllFeedbacksComponent},
    {path:'checkOut/:id', component: CheckoutComponent}
  ]},];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }

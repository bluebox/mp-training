import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ReviewsComponent } from './components/reviews/reviews.component';
import { ServicesComponent } from './components/services/services.component';

const routes: Routes = [
  {path:'',component : DashboardComponent,children:[
    {path : '' , redirectTo : 'home' , pathMatch : 'full'},
    {path : 'home',component : HomeComponent},
    {path : 'header',component : HeaderComponent},
    {path : 'footer',component : FooterComponent},
    {path : 'profile',component : ProfileComponent},
    {path : 'appointments',component :AppointmentsComponent },
    {path : 'services',component : ServicesComponent},
    {path : 'reviews',component : ReviewsComponent}
]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppointmentComponent } from '../admin/components/appointment/appointment.component';
import { ClientsComponent } from '../admin/components/clients/clients.component';
import { ServicesComponent } from '../client/components/services/services.component';
import { AppointmentsComponent } from './components/appointments/appointments.component';
import { BookappointmentComponent } from './components/bookappointment/bookappointment.component';
import { BranchComponent } from './components/branch/branch.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ReviewComponent } from './components/review/review.component';
import { TransactionsComponent } from './components/transactions/transactions.component';

const routes: Routes = [
  {path:'',component : DashboardComponent,children:[
    {path : '' , redirectTo : 'home' , pathMatch : 'full'},
    {path : 'home',component : HomeComponent},
    {path : 'header',component : HeaderComponent},
    {path : 'footer',component : FooterComponent},
    {path : 'profile',component : ProfileComponent},
    {path : 'appointments',component :AppointmentsComponent },
    {path : 'services',component : ServicesComponent},
    {path : 'branch',component : BranchComponent},
    {path : 'transactions',component :TransactionsComponent },
    {path : 'bookappointment',component :BookappointmentComponent },
    {path : 'review',component : ReviewComponent}
]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }

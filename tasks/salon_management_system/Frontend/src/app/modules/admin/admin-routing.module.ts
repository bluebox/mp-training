import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from 'src/app/components/register/register.component';
import { ProfileComponent } from '../employee/components/profile/profile.component';
import { AppointmentComponent } from './components/appointment/appointment.component';
import { BranchComponent } from './components/branch/branch.component';
import { ClientsComponent } from './components/clients/clients.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { EditbranchComponent } from './components/editbranch/editbranch.component';
import { EditserviceComponent } from './components/editservice/editservice.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { NewbranchComponent } from './components/newbranch/newbranch.component';
import { NewserviceComponent } from './components/newservice/newservice.component';
import { RegisteremployeeComponent } from './components/registeremployee/registeremployee.component';
import { ServicesComponent } from './components/services/services.component';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { UpdateappointmentComponent } from './components/updateappointment/updateappointment.component';

const routes: Routes = [
  {path:'',component : DashboardComponent,children:[
    {path : '' , redirectTo : 'home' , pathMatch : 'full'},
    {path : 'home',component : HomeComponent},
    {path : 'header',component : HeaderComponent},
    {path : 'footer',component : FooterComponent},
    {path : 'profile',component : ProfileComponent},
    {path : 'clients',component : ClientsComponent},
    {path : 'appointment',component : AppointmentComponent},
    {path : 'services',component : ServicesComponent},
    {path : 'branch',component : BranchComponent},
    {path : 'transactions',component : TransactionsComponent},
    {path : 'registeremployee',component : RegisteremployeeComponent},
    {path : 'newbranch',component : NewbranchComponent},
    {path : 'newservice',component : NewserviceComponent},
    {path:'updatebranch/:id',component : EditbranchComponent},
    {path : 'updateservice/:id',component : EditserviceComponent},
    {path : 'updateappointment/:id',component:UpdateappointmentComponent}

]}
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

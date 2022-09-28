import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from '../employee/components/profile/profile.component';
import { ClientsComponent } from './components/clients/clients.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  {path:'',component : DashboardComponent,children:[
    {path : '' , redirectTo : 'home' , pathMatch : 'full'},
    {path : 'home',component : HomeComponent},
    {path : 'header',component : HeaderComponent},
    {path : 'footer',component : FooterComponent},
    {path : 'profile',component : ProfileComponent},
    {path : 'clients',component : ClientsComponent},
    {path : 'appointment',component : FooterComponent},
    {path : 'services',component : ProfileComponent},
    {path : 'transactions',component : ClientsComponent},
]}
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

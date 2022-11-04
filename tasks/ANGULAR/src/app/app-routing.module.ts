import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DemoComponent } from './demo/demo.component';
import { LoginComponent } from './login/login.component';
import { MyguardGuard } from './myguard.guard';
import { ShowDemoComponent } from './demo/show-demo/show-demo.component';
import { TerminalComponent } from './terminal/terminal.component';
import { HomeComponent } from './custommodule/signup/home.component';
import { AirlinesComponent } from './airlines/airlines.component';
import { FlightComponent } from './flight/flight.component';
import { TicketComponent } from './ticket/ticket.component';
import { AdminComponent } from './admin/admin.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { StaffComponent } from './staff/staff.component';
import { StaffShiftsComponent } from './staff-shifts/staff-shifts.component';
import { LuggageComponent } from './luggage/luggage.component';
import { ShowTicketComponent } from './ticket/show-ticket/show-ticket.component';
import { DisplayticketComponent } from './displayticket/displayticket.component';
import { BookingsComponent } from './bookings/bookings.component';


const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch:'full'},
  {path:'admin', component:DemoComponent},
  {path:'login',component:LoginComponent},
  {path:'show',component:ShowDemoComponent,canActivate:[MyguardGuard]},
  {path:'terminal',component:TerminalComponent,canActivate:[MyguardGuard]},
  {path:'airlines',component:AirlinesComponent,canActivate:[MyguardGuard]},
  {path:'flight',component:FlightComponent,canActivate:[MyguardGuard]},
  {path:'ticket',component:TicketComponent,canActivate:[MyguardGuard]},
  {path:'user',component:AdminComponent,canActivate:[MyguardGuard]},
  {path:'schedule',component:ScheduleComponent,canActivate:[MyguardGuard]},
  {path:'staff',component:StaffComponent,canActivate:[MyguardGuard]},
  {path:'staff-shifts',component:StaffShiftsComponent},
  {path:'luggage',component:LuggageComponent,canActivate:[MyguardGuard]},
  {path:'show-ticket',component:ShowTicketComponent,canActivate:[MyguardGuard]},
  {path:'displayticket',component:DisplayticketComponent,canActivate:[MyguardGuard]},
  {path:'mybookings',component:BookingsComponent},
  {path: '', loadChildren: () => import('./custommodule/custommodule.module').then(m => m.CustommoduleModule) }
  // {path:'**',component: PageNotFoundComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }

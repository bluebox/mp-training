import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DisplayAppointmentsComponent } from './components/display-appointments/display-appointments.component';

const routes: Routes = [
  {
    path: "", component: DashboardComponent, children: [
      { path: 'display-appointmets', component: DisplayAppointmentsComponent },
      { path: '', redirectTo: 'display-appointmets', pathMatch: "full" }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

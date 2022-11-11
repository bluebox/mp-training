import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from '../admin.guard';
import { AlterHallsComponent } from './components/alter-halls/alter-halls.component';
import { AltermoviesComponent } from './components/altermovies/altermovies.component';
import { AltertheatresComponent } from './components/altertheatres/altertheatres.component';
import { AlterusersComponent } from './components/alterusers/alterusers.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';


const routes: Routes = [{path:"",component:DashboardComponent},
{path:"altermovies",component:AltermoviesComponent},
{path:"alterusers",component:AlterusersComponent,canActivate:[AdminGuard]},
{path:"altertheatres",component:AltertheatresComponent},
{path:"alterhalls",component:AlterHallsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

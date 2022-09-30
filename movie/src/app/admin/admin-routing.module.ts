import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AltermoviesComponent } from './components/altermovies/altermovies.component';
import { AlterusersComponent } from './components/alterusers/alterusers.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';


const routes: Routes = [{path:"",component:DashboardComponent},
{path:"altermovies",component:AltermoviesComponent},
{path:"alterusers",component:AlterusersComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeGuard } from '../home.guard';
import { RegisterComponent } from '../register/register.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FirstPageComponent } from './first-page/first-page.component';


const routes: Routes = [
  {
    path: '', canActivate: [HomeGuard] ,children: [
      { path : 'admin' ,component : AdminPageComponent},
      { path: 'dashboard', component: DashboardComponent },
      { path: 'firstpage', component: FirstPageComponent }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModuleRoutingModule { }

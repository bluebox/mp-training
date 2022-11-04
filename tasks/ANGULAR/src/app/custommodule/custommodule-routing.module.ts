import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustommoduleComponent } from './custommodule.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './signup/home.component';


const routes: Routes = [
  {path: '',children: [
      {path:'signup', component:HomeComponent},
      {path: 'dashboard', component:DashboardComponent},
      {path: 'home/:text', component: HomeComponent },
    ]}


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  // import { HomeComponent } from './home/home.component';
exports: [RouterModule]
})
export class CustommoduleRoutingModule { }

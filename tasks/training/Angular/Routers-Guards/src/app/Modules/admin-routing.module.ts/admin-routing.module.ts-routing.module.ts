import { ContactComponent } from './../contact/contact.component';
import { HomeComponent } from './../home/home.component';
import { DashboardComponent } from './../dashboard/dashboard.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from '../about/about.component';
import { AdminguardGuard } from 'src/app/adminguard.guard';

const routes: Routes = [
  {
    path: '',component: DashboardComponent, children:[
      {path:'home/:id' , component:HomeComponent},
      {path:'home' , component:HomeComponent},

      {path:'about' , component: AboutComponent},
      {path:'contact' , component:ContactComponent},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminModuleTsRoutingModule { }

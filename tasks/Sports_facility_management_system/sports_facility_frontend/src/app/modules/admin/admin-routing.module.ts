import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from 'src/app/component/error/error.component';
import { AdminhomeComponent } from './components/adminhome/adminhome.component';
import { CreatefacilityComponent } from './components/createfacility/createfacility.component';
import { IndexComponent } from './components/index/index.component';
import { ViewfacilitiesComponent } from './components/viewfacilities/viewfacilities.component';

const routes: Routes = [
  {
    path: '',
    component:IndexComponent ,
    children: [
      {path:'login',component:AdminhomeComponent},
      {path:'login/createfacility',component:CreatefacilityComponent},
      { path: 'login/viewfacility', component: ViewfacilitiesComponent },
      { path: '**', component: ErrorComponent },
    ],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

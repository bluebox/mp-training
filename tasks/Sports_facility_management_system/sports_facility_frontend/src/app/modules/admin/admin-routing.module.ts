import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from 'src/app/component/error/error.component';
import { AuthgaurdGuard } from './authgaurd.guard';
import { AddsportsComponent } from './components/addsports/addsports.component';
import { AdminheaderComponent } from './components/adminheader/adminheader.component';
import { AdminhomeComponent } from './components/adminhome/adminhome.component';
import { AdminloginComponent } from './components/adminlogin/adminlogin.component';
import { CreatefacilityComponent } from './components/createfacility/createfacility.component';

import { ViewfacilitiesComponent } from './components/viewfacilities/viewfacilities.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'home',
        component: AdminhomeComponent,
        children: [
          { path: 'createfacility', component: CreatefacilityComponent },
          { path: 'viewfacility', component: ViewfacilitiesComponent },
          {path:'addsports/:fid',component:AddsportsComponent},
        ],
      },

      { path: '**', component: ErrorComponent },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}

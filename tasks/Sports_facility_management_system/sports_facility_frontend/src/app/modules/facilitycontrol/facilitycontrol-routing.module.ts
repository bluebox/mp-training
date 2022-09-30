import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from 'src/app/component/error/error.component';
import { FacilityhomeComponent } from './components/facilityhome/facilityhome.component';
import { FacilityindexComponent } from './components/facilityindex/facilityindex.component';
import { FacilityloginComponent } from './components/facilitylogin/facilitylogin.component';

const routes: Routes = [
  {
    path: '',
    component: FacilityindexComponent,
    children: [
      {path:'login',component:FacilityloginComponent},
      { path: 'home', component: FacilityhomeComponent },
      { path: '**', component: ErrorComponent },
    ],
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FacilitycontrolRoutingModule { }

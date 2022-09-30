import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ComplaintsComponent } from 'src/app/complaints/complaints.component';
import { HomeComponent } from 'src/app/module/components/home/home.component';
import { ManagerhomeComponent } from '../components/managerhome/managerhome.component';
import { ManagertaskComponent } from '../components/managertask/managertask.component';

const routes: Routes = [
  {path:'',component:HomeComponent,
  children:[
    {path:'managerpage',component :ManagerhomeComponent },
    {path:'managercomplaints',component :ManagertaskComponent },

  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagerRoutingModule { }

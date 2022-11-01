import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ComplaintsComponent } from 'src/app/complaints/complaints.component';
import { HomeComponent } from 'src/app/module/components/home/home.component';
import { CreateadeviceComponent } from '../components/createadevice/createadevice.component';
import { CreateemployeeComponent } from '../components/createemployee/createemployee.component';
import { ManagerhomeComponent } from '../components/managerhome/managerhome.component';
import { ManagertaskComponent } from '../components/managertask/managertask.component';
import { ParticularemployeeComponent } from '../components/particularemployee/particularemployee.component';

const routes: Routes = [
  {path:'',component:ManagerhomeComponent,
  children:[
    {path:'',component :HomeComponent },
    {path:'managercomplaints',component :ManagertaskComponent },
    {path:'createemployee',component :CreateemployeeComponent },
    {path:'createadevice',component :CreateadeviceComponent },
    {path:'particularemployee/:emp_id',component :ParticularemployeeComponent },




  ]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagerRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { AppComponent } from './app.component';
import { AuthGuard } from './auth.guard';
import { ComplaintsComponent } from './complaints/complaints.component';
import { CreatefacilityComponent } from './createfacility/createfacility.component';
import { CreatemanagerComponent } from './createmanager/createmanager.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { EmployeecardComponent } from './employeecard/employeecard.component';
import { EmployeetasksComponent } from './employeetasks/employeetasks.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CreateadeviceComponent } from './module/components/createadevice/createadevice.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ProfileComponent } from './profile/profile.component';
import { RaiseComplaintComponent } from './raise-complaint/raise-complaint.component';
import { RegisterComponent } from './register/register.component';
import { ViewFacilitiesComponent } from './view-facilities/view-facilities.component';

const routes: Routes = [
  {path :'' , component :LoginComponent },
  {path :'login' , component :LoginComponent },
  {path :'home' ,canActivate:[AuthGuard], component :HomeComponent },
  {path :'home/:emp_id' , component :EmployeecardComponent },
  {path :'Raiseacomplaint' , component :RaiseComplaintComponent },
  {path :'profile' , component :ProfileComponent },
  {path :'admin' , component :AdminpageComponent ,children:[
    { path :'createfacility',component:CreatefacilityComponent},
    { path :'createmanager',component:CreatemanagerComponent},
    { path :'device',component:CreateadeviceComponent},

    { path :'viewfacilities',component:ViewFacilitiesComponent},
    
  ]},
  {path :'register' , component :RegisterComponent },
  {path :'complaints',component : ComplaintsComponent},
  {path :'employeetasks',component : EmployeetasksComponent},
  {path :'editprofile',component : EditprofileComponent},
  {path: 'manager',loadChildren : ()=>import('./module/manager/manager.module').then(m=> m.ManagerModule)}
  




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

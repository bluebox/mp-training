import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { ComplaintsComponent } from './complaints/complaints.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { EmployeetasksComponent } from './employeetasks/employeetasks.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {path :'' , component :LoginComponent },
  {path :'signin' , component :HomeComponent },
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

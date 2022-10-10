import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent} from './admin/admin.component';
import { CustomerComponent} from './admin/customer/customer.component';
import { EmployeeComponent} from './admin/employee/employee.component';
import { DistributorComponent} from './admin/distributor/distributor.component';
import { DoctorComponent} from './admin/doctor/doctor.component';
import { ManufacturerComponent} from './admin/manufacturer/manufacturer.component';
import { LoginComponent} from './login/login.component';
import { SignupComponent} from './signup/signup.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { UserComponent } from './user/user.component';
import { DrugComponent } from './admin/drug/drug.component'



const routes: Routes = [
  {path:'admin', component:AdminComponent},
  {path:'employee', component:EmployeeComponent},
  {path:'customer', component:CustomerComponent},
  {path:'distributor', component:DistributorComponent},
  {path:'login', component:LoginComponent},
  {path:'signup', component:SignupComponent},
  {path:'doctor', component:DoctorComponent},
  {path:'manufacturer', component:ManufacturerComponent},
  {path:'homepage', component:AdminComponent},
  {path:'about', component:AboutComponent},
  {path:'drug', component:DrugComponent},
  {path:'contact', component:ContactComponent},
  {path:'user', component:UserComponent},
  {path:'', redirectTo:'admin', pathMatch:'full'},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
exports: [RouterModule]
})
export class AppRoutingModule { }

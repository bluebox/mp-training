import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DoctorComponent } from './doctor/doctor.component';
import { ListofpatientComponent } from './listofpatient/listofpatient.component';
import { LoginComponent } from './login/login.component';
import { NewuserComponent } from './newuser/newuser.component';
import { PatientComponent } from './patient/patient.component';
import { StaffComponent } from './staff/staff.component';
import { AppointmentComponent } from './appointment/appointment.component';
// import { SlotsComponent } from './slots/slots.component';
import { DoctorDetailsComponent } from './doctor-details/doctor-details.component';
import { DoctorHomeComponent } from './doctor-home/doctor-home.component';
import { LogoutComponent } from './logout/logout.component';
import { SlotBookingComponent } from './slot-booking/slot-booking.component';
const routes: Routes = [
  {path: '', redirectTo: 'newuser', pathMatch: 'full'},
  {path: 'doctor', component: DoctorComponent},
  {path:'patient', component:PatientComponent},
  {path: 'staff',component: StaffComponent},
  {path:'login', component:LoginComponent},
  {path:'newuser',component:NewuserComponent},
  {path: 'listofpatient',component:ListofpatientComponent},
  {path:'appointment',component:AppointmentComponent},
  {path:'slot',component:SlotBookingComponent},
  {path:'doc-details',component:DoctorDetailsComponent},
  {path:'doctorHome',component:DoctorHomeComponent},
  {path:'logout',component:LogoutComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

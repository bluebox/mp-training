import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookAppointmentComponent } from './components/appointments/book-appointment/book-appointment.component';
import { DisplayAppointmentsComponent } from './components/appointments/display-appointments/display-appointments.component';
import { DisplayBillsComponent } from './components/bills/display-bills/display-bills.component';
import { DisplayBranchesComponent } from './components/branches/display-branches/display-branches.component';
import { NewBranchComponent } from './components/branches/new-branch/new-branch.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DisplayCustomerComponent } from './components/display-customer/display-customer.component';
import { DisplayCustomersComponent } from './components/display-customers/display-customers.component';
import { DisplayEmployeesComponent } from './components/display-employees/display-employees.component';
import { DisplayLabsComponent } from './components/labs/display-labs/display-labs.component';
import { NewLabComponent } from './components/labs/new-lab/new-lab.component';
import { RegisterCustomerComponent } from './components/register-customer/register-customer.component';
import { RegisterEmployeeComponent } from './components/register-employee/register-employee.component';
import { NewTestComponent } from './components/tests/new-test/new-test.component';
import { WelcomeAdminComponent } from './components/welcome-admin/welcome-admin.component';

const routes: Routes = [
  {path:"",component:DashboardComponent,children:[
    {path:'register-customer' , component : RegisterCustomerComponent },
    {path:'update-customer/:customer_id' , component : RegisterCustomerComponent },
    { path: 'register-employee', component: RegisterEmployeeComponent },
    { path: 'book-appointment', component: BookAppointmentComponent },
    { path: 'edit-appointment/:id', component: BookAppointmentComponent },
    { path: 'display-appointments', component: DisplayAppointmentsComponent },
    { path: 'display-bills', component: DisplayBillsComponent},
    { path: 'display-branches', component: DisplayBranchesComponent},
    { path: 'display-labs', component: DisplayLabsComponent},
    { path: 'create-branch', component: NewBranchComponent},
    { path: 'update-branch/:branch_id', component: NewBranchComponent},
    { path: 'create-lab', component: NewLabComponent},
    { path: 'create-test', component: NewTestComponent},
    { path: 'welcome', component: WelcomeAdminComponent},
    { path: 'dislplay-employees', component: DisplayEmployeesComponent},
    { path: 'display-customers', component: DisplayCustomersComponent},
    { path: 'display-customer/:cutomer_id', component: DisplayCustomerComponent},
    { path: '', redirectTo:'welcome',pathMatch:"full"}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }

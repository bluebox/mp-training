import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { RegisterCustomerComponent } from './components/register-customer/register-customer.component';
import { RegisterEmployeeComponent } from './components/register-employee/register-employee.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
// material
import { MatRadioModule } from '@angular/material/radio';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import {MatExpansionModule} from '@angular/material/expansion'

// components
import { BookAppointmentComponent } from './components/appointments/book-appointment/book-appointment.component';
import { DisplayAppointmentComponent } from './components/appointments/display-appointment/display-appointment.component';
import { DisplayAppointmentsComponent } from './components/appointments/display-appointments/display-appointments.component';
import { EditAppointmentComponent } from './components/appointments/edit-appointment/edit-appointment.component';
import { DisplayBillComponent } from './components/bills/display-bill/display-bill.component';
import { DisplayBillsComponent } from './components/bills/display-bills/display-bills.component';
import { NewBillComponent } from './components/bills/new-bill/new-bill.component';
import { NewBranchComponent } from './components/branches/new-branch/new-branch.component';
import { DisplayBranchesComponent } from './components/branches/display-branches/display-branches.component';
import { DisplayLabsComponent } from './components/labs/display-labs/display-labs.component';
import { NewLabComponent } from './components/labs/new-lab/new-lab.component';
import { NewReportComponent } from './components/reports/new-report/new-report.component';
import { DisplayReportComponent } from './components/reports/display-report/display-report.component';
import { DisplayReportsComponent } from './components/reports/display-reports/display-reports.component';
import { NewTestComponent } from './components/tests/new-test/new-test.component';
import { DisplayTestsComponent } from './components/tests/display-tests/display-tests.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialogModule } from '@angular/material/dialog';
import { CloseDialogComponent } from './components/close-dialog/close-dialog.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { MatTabsModule } from '@angular/material/tabs';

import { MatSidenavModule } from '@angular/material/sidenav';
import { WelcomeAdminComponent } from './components/welcome-admin/welcome-admin.component';
import { DisplayCustomersComponent } from './components/display-customers/display-customers.component';
import { DisplayEmployeesComponent } from './components/display-employees/display-employees.component';
import { DisplayCustomerComponent } from './components/display-customer/display-customer.component';
import { SearchComponent } from './components/search/search.component';
@NgModule({
  declarations: [
    RegisterCustomerComponent,
    RegisterEmployeeComponent,
    DashboardComponent,
    BookAppointmentComponent,
    DisplayAppointmentComponent,
    DisplayAppointmentsComponent,
    EditAppointmentComponent,
    DisplayBillComponent,
    DisplayBillsComponent,
    NewBillComponent,
    NewBranchComponent,
    DisplayBranchesComponent,
    DisplayLabsComponent,
    NewLabComponent,
    NewReportComponent,
    DisplayReportComponent,
    DisplayReportsComponent,
   
    NewTestComponent,
    DisplayTestsComponent,
    CloseDialogComponent,
    SidenavComponent,
    WelcomeAdminComponent,
    DisplayCustomersComponent,
    DisplayEmployeesComponent,
    DisplayCustomerComponent,
    SearchComponent,
  ],
  entryComponents:[CloseDialogComponent],
  imports: [
    CommonModule,
    UsersRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    
    // angular material
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatIconModule,
    MatPaginatorModule,
    MatSortModule,
    MatTableModule,
    MatExpansionModule,
    MatToolbarModule,
    MatDialogModule,
    MatSidenavModule,
    MatTabsModule
  ],
  bootstrap: [
  ]
})
export class UsersModule { }

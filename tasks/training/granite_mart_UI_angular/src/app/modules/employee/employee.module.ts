import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule, NgIf } from '@angular/common';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeViewComponent } from './employee-view/employee-view.component';
import { EmployeeRegisterComponent } from './employee-register/employee-register.component';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';
import { JsonPipe } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { AdminDashboardComponent } from '../admin/admin-dashboard/admin-dashboard.component';




@NgModule({
  declarations: [
    EmployeeListComponent,
    EmployeeViewComponent,
    EmployeeRegisterComponent,
    EmployeeUpdateComponent,
    AdminDashboardComponent
  ],
  imports: [
    CommonModule,
    JsonPipe,
    BrowserModule,
    ReactiveFormsModule,
  ]
})
export class EmployeeModule { }

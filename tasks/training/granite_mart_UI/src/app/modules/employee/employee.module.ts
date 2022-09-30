import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { CommonModule, NgIf } from '@angular/common';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeViewComponent } from './employee-view/employee-view.component';
import { EmployeeRegisterComponent } from './employee-register/employee-register.component';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';
import { JsonPipe } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    EmployeeListComponent,
    EmployeeViewComponent,
    EmployeeRegisterComponent,
    EmployeeUpdateComponent,
  ],
  imports: [
    CommonModule,
    JsonPipe,
    BrowserModule,
    ReactiveFormsModule
  ]
})
export class EmployeeModule { }

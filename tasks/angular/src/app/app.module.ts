import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './homepage/homepage.component';
import { EmployeepageComponent } from './employeeloginpage/employeepage.component';
import { CustomerpageComponent } from './customerloginpage/customerpage.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { EmpolyeeregisterpageComponent } from './empolyeeregisterpage/empolyeeregisterpage.component';
import { EmployeeDashboardComponent } from './employee-dashboard/employee-dashboard.component';
import { HeadersectionComponent } from './headersection/headersection.component';
import { FootersectionComponent } from './footersection/footersection.component';
import { CreatecustomeraccountComponent } from './createcustomeraccount/createcustomeraccount.component';
import { CustomerdashboardComponent } from './customerdashboard/customerdashboard.component';
import { CustomerbalacecheckComponent } from './customerbalacecheck/customerbalacecheck.component';
import { MoneytransferComponent } from './moneytransfer/moneytransfer.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap'
@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    EmployeepageComponent,
    CustomerpageComponent,
    PagenotfoundComponent,
    EmpolyeeregisterpageComponent,
    EmployeeDashboardComponent,
    HeadersectionComponent,
    FootersectionComponent,
    CreatecustomeraccountComponent,
    CustomerdashboardComponent,
    CustomerbalacecheckComponent,
    MoneytransferComponent,
    TransactionsComponent
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

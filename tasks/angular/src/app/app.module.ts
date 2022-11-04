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
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CardsComponent } from './cards/cards.component';
import { EmployeeloginComponent } from './employeelogin/employeelogin.component';
import { CustomerrequestComponent } from './customerrequest/customerrequest.component';
import { EmployeerequestpageComponent } from './employeerequestpage/employeerequestpage.component';
import { CreditcardsComponent } from './creditcards/creditcards.component';
import { DebitcardsComponent } from './debitcards/debitcards.component';
import { PersonalloaneligibilitycehckComponent } from './personalloaneligibilitycehck/personalloaneligibilitycehck.component';
import { CustomerlistComponent } from './customerlist/customerlist.component';
import { CustomerloansComponent } from './customerloans/customerloans.component';
import { CustomerhomepageComponent } from './customerhomepage/customerhomepage.component';
import { EmployeemaindashboardComponent } from './employeemaindashboard/employeemaindashboard.component';
import { PhonenumberupdateComponent } from './phonenumberupdate/phonenumberupdate.component';
import { EmailupdationComponent } from './emailupdation/emailupdation.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
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
    TransactionsComponent,
    CardsComponent,
    EmployeeloginComponent,
    CustomerrequestComponent,
    EmployeerequestpageComponent,
    CreditcardsComponent,
    DebitcardsComponent,
    PersonalloaneligibilitycehckComponent,
    CustomerlistComponent,
    CustomerloansComponent,
    CustomerhomepageComponent,
    EmployeemaindashboardComponent,
    PhonenumberupdateComponent,
    EmailupdationComponent
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

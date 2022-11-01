import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CardsComponent } from './cards/cards.component';
import { CreatecustomeraccountComponent } from './createcustomeraccount/createcustomeraccount.component';
import { CustomerbalacecheckComponent } from './customerbalacecheck/customerbalacecheck.component';
import { CustomerdashboardComponent } from './customerdashboard/customerdashboard.component';
import { CustomerpageComponent } from './customerloginpage/customerpage.component';
import { EmployeeDashboardComponent } from './employee-dashboard/employee-dashboard.component';
import { EmployeepageComponent } from './employeeloginpage/employeepage.component';
import { EmpolyeeregisterpageComponent } from './empolyeeregisterpage/empolyeeregisterpage.component'
import { HomepageComponent } from './homepage/homepage.component';
import { MoneytransferComponent } from './moneytransfer/moneytransfer.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { TransactionsComponent } from './transactions/transactions.component';
import {SecurityguardGuard} from "./securityguard.guard"
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
// import {PagenotfoundComponent} from './pagenotfound/pagenotfound.component';

const routes: Routes = [
  {path:'', redirectTo:'home', pathMatch:'full'},
  {path:'home', component:HomepageComponent},
  
  {path:'emppage', component:EmployeeloginComponent, children :[
    {path:'', redirectTo:'emplogin', pathMatch:'full'},
    {path:'emplogin', component:EmployeepageComponent},
    {path: 'empregister', component:EmpolyeeregisterpageComponent},
  ]},
  {path: 'custlogin', component:CustomerpageComponent},
  
  {path: 'empdashBoard', component:EmployeeDashboardComponent, children : [
    {path:'', redirectTo:'employeemaindash', pathMatch:'full'},
    {path :'createCusAcc', component:CreatecustomeraccountComponent},
    {path :'employeerequest', component:EmployeerequestpageComponent, children:[
      
      {path: "creditcard/:email", component: CreditcardsComponent},
      {path: "creditcard", component: CreditcardsComponent},
      {path: "debitcard/:email", component: DebitcardsComponent},
      {path: "debitcard", component: DebitcardsComponent},
      {path: "changePhoneNumber", component: PhonenumberupdateComponent},
      {path: "changePhoneNumber/:email", component: PhonenumberupdateComponent},
      {path: "changeEmail", component: EmailupdationComponent},
      {path: "changeEmail/:email", component: EmailupdationComponent}
      
      
    ]},
    {path :'customerlist', component:CustomerlistComponent},
    {path :'customerloans', component:CustomerloansComponent},
    {path: "employeemaindash", component: EmployeemaindashboardComponent}

    
  ]},
  {path:"cusdashBoard",canActivate:[SecurityguardGuard], component:CustomerdashboardComponent, children: [
    {path: '', redirectTo:"customerhome", pathMatch :'full'},
    {path: "customerhome", component: CustomerhomepageComponent},
    {path: "cusbalcheck", component: CustomerbalacecheckComponent},
    {path: "moneytransfer", component: MoneytransferComponent},
    {path: "transactionlist", component: TransactionsComponent},
    {path: "cards", component: CardsComponent},
    {path: "customerrequest", component: CustomerrequestComponent},
    {path: "personalLoanEligibility", component: PersonalloaneligibilitycehckComponent}
    
  ]},
  {path:'**', component: PagenotfoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
// import {PagenotfoundComponent} from './pagenotfound/pagenotfound.component';

const routes: Routes = [
  {path:'', redirectTo:'home', pathMatch:'full'},
  {path:'home', component:HomepageComponent},
  {path:'emplogin', component:EmployeepageComponent},
  {path: 'custlogin', component:CustomerpageComponent},
  {path: 'empregister', component:EmpolyeeregisterpageComponent},
  {path: 'empdashBoard', component:EmployeeDashboardComponent, children : [
    {path :'createCusAcc', component:CreatecustomeraccountComponent}
    
  ]},
  {path:"cusdashBoard", component:CustomerdashboardComponent, children: [
    {path: "cusbalcheck", component: CustomerbalacecheckComponent},
    {path: "moneytransfer", component: MoneytransferComponent},
    {path: "transactionlist", component: TransactionsComponent}
  ]},
  {path:'**', component: PagenotfoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

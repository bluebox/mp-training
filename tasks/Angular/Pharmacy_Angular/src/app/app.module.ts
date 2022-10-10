import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { CustomerComponent } from './admin/customer/customer.component';
import { EmployeeComponent } from './admin/employee/employee.component';
import { DistributorComponent } from './admin/distributor/distributor.component';
import { AddDisComponent } from './admin/distributor/add-dis/add-dis.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatRadioModule} from '@angular/material/radio';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AddCusComponent } from './admin/customer/add-cus/add-cus.component';
import { DoctorComponent } from './admin/doctor/doctor.component';
import { AddDocComponent } from './admin/doctor/add-doc/add-doc.component';
import { ManufacturerComponent } from './admin/manufacturer/manufacturer.component';
import { AddEmpComponent } from './admin/employee/add-emp/add-emp.component';
import { AddManComponent } from './admin/manufacturer/add-man/add-man.component';
import { DrugComponent } from './admin/drug/drug.component';
import { InvoiceComponent } from './admin/invoice/invoice.component';
import { OrderComponent } from './admin/order/order.component';
import { PrescribeComponent } from './admin/prescribe/prescribe.component';
import { PurchaseComponent } from './admin/purchase/purchase.component';
import { SalesComponent } from './admin/sales/sales.component';
import { SupplyComponent } from './admin/supply/supply.component';
import { AddDrugComponent } from './admin/drug/add-drug/add-drug.component';
import { AddInvComponent } from './admin/invoice/add-inv/add-inv.component';
import { AddOrdComponent } from './admin/order/add-ord/add-ord.component';
import { AddPreComponent } from './admin/prescribe/add-pre/add-pre.component';
import { AddPurComponent } from './admin/purchase/add-pur/add-pur.component';
import { AddSalComponent } from './admin/sales/add-sal/add-sal.component';
import { AddSupComponent } from './admin/supply/add-sup/add-sup.component';
import { SignupComponent } from './signup/signup.component';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import { UserComponent } from './user/user.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AboutComponent } from './about/about.component';
import { ContactComponent } from './contact/contact.component';
import { OrdersComponent } from './user/orders/orders.component';
import { CartComponent } from './user/cart/cart.component'; 
import {MatGridListModule} from '@angular/material/grid-list';
import {MatSelectModule} from '@angular/material/select';







@NgModule({
  declarations: [
    AppComponent,
    AdminComponent,
    LoginComponent,
    HeaderComponent,
    CustomerComponent,
    EmployeeComponent,
    DistributorComponent,
    AddDisComponent,
    AddCusComponent,
    DoctorComponent,
    AddDocComponent,
    ManufacturerComponent,
    AddEmpComponent,
    AddManComponent,
    DrugComponent,
    InvoiceComponent,
    OrderComponent,
    PrescribeComponent,
    PurchaseComponent,
    SalesComponent,
    SupplyComponent,
    AddDrugComponent,
    AddInvComponent,
    AddOrdComponent,
    AddPreComponent,
    AddPurComponent,
    AddSalComponent,
    AddSupComponent,
    SignupComponent,
    UserComponent,
    HomepageComponent,
    AboutComponent,
    ContactComponent,
    OrdersComponent,
    CartComponent,

    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatCardModule,
    MatTabsModule,
    FormsModule,
    MatGridListModule,
    MatSelectModule,


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupComponent } from './signup/signup.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { RegisterRoutingModule } from './register-routing.module';
import { HomeComponent } from './home/home.component';
import {MatCardModule} from '@angular/material/card';
import { ProductsComponent } from './products/products.component';
import { LoginComponent } from './login/login.component';
import {MatIconModule} from '@angular/material/icon';
import { BrowserModule } from '@angular/platform-browser';
import { CustCartComponent } from './cust-cart/cust-cart.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Routes } from '@angular/router';
import { RouterGuardGuard } from '../router-guard.guard';


@NgModule({
  declarations: [
    SignupComponent,
    DashboardComponent,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
    ProductsComponent,
    LoginComponent,
    CustCartComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    ReactiveFormsModule,
    RegisterRoutingModule,
    MatCardModule, FormsModule,
    MatIconModule,
    MatCardModule,    
    BrowserAnimationsModule,
    MatCardModule,
    MatSliderModule,
    MatSidenavModule,
    MatToolbarModule,
    
  ]
})
export class RegisterModule { 
  
}

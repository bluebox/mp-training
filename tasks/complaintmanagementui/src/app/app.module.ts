import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ComplaintsComponent } from './complaints/complaints.component';
import { HomeComponent } from './home/home.component';
import { EmployeetasksComponent } from './employeetasks/employeetasks.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCard, MatCardModule } from "@angular/material/card";
import { MatButtonModule } from "@angular/material/button";
import { PopupcardComponent } from './popupcard/popupcard.component';
import { OncreateDirective } from './oncreate.directive';
// import {OncreateDirective} from './oncreate.directive'
// import { ManagertaskComponent } from './module/components/managertask/managertask.component';
// import { NavComponent } from './module/components/nav/nav.component';
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    ComplaintsComponent,
    HomeComponent,
    EmployeetasksComponent,
    EditprofileComponent,
    PopupcardComponent,
    OncreateDirective,
    // ManagertaskComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCardModule
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

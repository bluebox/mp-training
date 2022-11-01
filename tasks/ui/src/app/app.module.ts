import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
// import {AngularMaterialModule}
import {MatFormFieldModule} from '@angular/material/form-field';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ComplaintsComponent } from './complaints/complaints.component';
import { HomeComponent } from './home/home.component';
import { EmployeetasksComponent } from './employeetasks/employeetasks.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCard, MatCardModule } from "@angular/material/card";
import { MatDialogModule } from '@angular/material/dialog';
// import { NgModel } from '@angular/forms';
import { MatButtonModule } from "@angular/material/button";
import { PopupcardComponent } from './popupcard/popupcard.component';
import { OncreateDirective } from './oncreate.directive';
import { CreateemployeeComponent } from './module/components/createemployee/createemployee.component';
import { EmployeecardComponent } from './employeecard/employeecard.component';
import { FooterComponent } from './footer/footer.component';
import { RaiseComplaintComponent } from './raise-complaint/raise-complaint.component';
import { CreateadeviceComponent } from './module/components/createadevice/createadevice.component';
import { ParticularemployeeComponent } from './module/components/particularemployee/particularemployee.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { CreatefacilityComponent } from './createfacility/createfacility.component';
import { CreatemanagerComponent } from './createmanager/createmanager.component';
import { ViewFacilitiesComponent } from './view-facilities/view-facilities.component';
import { ProfileComponent } from './profile/profile.component';
// import {DialogOverviewExampleDialog} from './employeetasks/employeetasks.component'
// import {MaterialExampleModule} from '../material.module';
// import {DialogOverviewExample, DialogOverviewExampleDialog} from './dialog-overview-example';
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
    CreateemployeeComponent,
    EmployeecardComponent,
    FooterComponent,
    RaiseComplaintComponent,
    CreateadeviceComponent,
    ParticularemployeeComponent,
    AdminpageComponent,
    CreatefacilityComponent,
    CreatemanagerComponent,
    ViewFacilitiesComponent,
    ProfileComponent,
    // DialogOverviewExampleDialog,
    // ManagertaskComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatDialogModule,
    MatFormFieldModule,
    FormsModule
    // NgModel
  
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

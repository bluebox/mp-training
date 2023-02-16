import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PatientComponent } from './patient/patient.component';
import { DoctorComponent } from './doctor/doctor.component';
import { StaffComponent } from './staff/staff.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgMaterialModule } from './ng-material/ng-material.module';
import { LoginComponent } from './login/login.component';
import { ListofpatientComponent } from './listofpatient/listofpatient.component';
import { ServercomunicationService } from './servercomunication.service';
import { NewuserComponent } from './newuser/newuser.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { SalaryComponent } from './salary/salary.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { TestComponent } from './test/test.component';
import { BillComponent } from './bill/bill.component';
import { DiagnosisComponent } from './diagnosis/diagnosis.component';
// import { BookAppointmentComponent } from './book-appointment/book-appointment.component';
// import { SlotsComponent } from './slots/slots.component';
import { DoctorDetailsComponent } from './doctor-details/doctor-details.component';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import { BookingService } from './booking.service';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { DatePipe } from '@angular/common';
import { PatientHomeComponent } from './patient-home/patient-home.component';
import { LogoutComponent } from './logout/logout.component';
import { SlotBookingComponent } from './slot-booking/slot-booking.component';
import { DoctorHomeComponent } from './doctor-home/doctor-home.component';
import {MatTableModule} from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';

@NgModule({
  declarations: [
    AppComponent,
    PatientComponent,
    DoctorComponent,
    StaffComponent,
    LoginComponent,
    ListofpatientComponent,
    NewuserComponent,
    NavbarComponent,
    FooterComponent,
    SalaryComponent,
    // AppointmentComponent,
    TestComponent,
    BillComponent,
    DiagnosisComponent,
    // BookAppointmentComponent,
    AppointmentComponent,
    // UpdateSlotsComponent,
    // SlotsComponent,
    DoctorDetailsComponent,
    PatientHomeComponent,
    LogoutComponent,
    SlotBookingComponent,
    DoctorHomeComponent,

    // ServercomunicationService,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    NgMaterialModule,
    HttpClientModule,
    MatBottomSheetModule,
    MatCheckboxModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,


  ],
  providers: [ServercomunicationService,BookingService,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }

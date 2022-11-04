import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DemoComponent } from './demo/demo.component';
import { ShowDemoComponent } from './demo/show-demo/show-demo.component';
import { EditDemoComponent } from './demo/edit-demo/edit-demo.component';
import { SharedService } from './shared.service';

import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { CustommoduleModule } from './custommodule/custommodule.module';
import { TerminalComponent } from './terminal/terminal.component';
import { ShowTerminalComponent } from './terminal/show-terminal/show-terminal.component';
import { AirlinesComponent } from './airlines/airlines.component';
import { FlightComponent } from './flight/flight.component';
import { TicketComponent } from './ticket/ticket.component';
import { AdminComponent } from './admin/admin.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import { ScheduleComponent } from './schedule/schedule.component';
import { StaffComponent } from './staff/staff.component';
import { ShowStaffComponent } from './staff/show-staff/show-staff.component';
import { StaffShiftsComponent } from './staff-shifts/staff-shifts.component';
import { LuggageComponent } from './luggage/luggage.component';
import { ShowTicketComponent } from './ticket/show-ticket/show-ticket.component';
import { DisplayticketComponent } from './displayticket/displayticket.component';
import { BookingsComponent } from './bookings/bookings.component';
@NgModule({
  declarations: [
    AppComponent,
    DemoComponent,
    ShowDemoComponent,
    EditDemoComponent,
    LoginComponent,
    TerminalComponent,
    ShowTerminalComponent,
    AirlinesComponent,
    FlightComponent,
    TicketComponent,
    AdminComponent,
    ScheduleComponent,
    StaffComponent,
    ShowStaffComponent,
    StaffShiftsComponent,
    LuggageComponent,
    ShowTicketComponent,
    DisplayticketComponent,
    BookingsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCardModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

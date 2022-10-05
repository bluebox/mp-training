import { NgModule } from '@angular/core';


import { FormsModule, ReactiveFormsModule,  } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';


import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import { SignupComponent } from './components/signup/signup.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { UploadFileComponent } from './components/upload-file/upload-file.component';
import { HeaderComponent } from './components/header/header.component';
import {MatTabsModule} from '@angular/material/tabs';
import {MatMenuModule} from '@angular/material/menu';
import { FooterComponent } from './components/footer/footer.component';
import { CorouselComponent } from './components/corousel/corousel.component';
import { ContactusComponent } from './components/contactus/contactus.component';
import { HomeComponent } from './components/home/home.component';
import { CardComponent } from './components/card/card.component';
import { TourPageComponent } from './components/tour-page/tour-page.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TourComponent } from './components/tour/tour.component';
import { UniversalAppInterceptor } from './services/HttpInterceptor';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { BookingsComponent } from './components/bookings/bookings.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import {MatStepperModule} from '@angular/material/stepper';
import {MatSelectModule} from '@angular/material/select';
import {MatRadioModule} from '@angular/material/radio';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    UserDashboardComponent,
    UploadFileComponent,
    HeaderComponent,
    FooterComponent,
    CorouselComponent,
    ContactusComponent,
    HomeComponent,
    CardComponent,
    TourPageComponent,
    FeedbackComponent,
    TourComponent,
    BookingsComponent,
    CheckoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatButtonModule,
    HttpClientModule,
    MatTabsModule,
    MatMenuModule,
    NgbModule,
    FormsModule,
    MatStepperModule,
    MatSelectModule,
    MatRadioModule,
  ],
  providers: [
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS }, JwtHelperService

    // { provide: HTTP_INTERCEPTORS, useClass: UniversalAppInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

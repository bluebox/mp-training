import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { BookingsComponent } from './components/bookings/bookings.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { FeedbackComponent } from './components/feedback/feedback.component';
import { HomeComponent } from './components/home/home.component';
import { TourComponent } from './components/tour/tour.component';
import { TourPageComponent } from './components/tour-page/tour-page.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTabsModule } from '@angular/material/tabs';
import { MatMenuModule } from '@angular/material/menu';
import { MatStepperModule } from '@angular/material/stepper';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CardComponent } from './components/card/card.component';
import { CorouselComponent } from './components/corousel/corousel.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { PackageToursComponent } from './components/package-tours/package-tours.component';
import { AllFeedbacksComponent } from './components/all-feedbacks/all-feedbacks.component';
import { BookingPageComponent } from './components/booking-page/booking-page.component';


@NgModule({
  declarations: [
    BookingsComponent,
    CheckoutComponent,
    FeedbackComponent,
    HomeComponent,
    TourComponent,
    TourPageComponent,
    UserDashboardComponent,
    CardComponent,
    CorouselComponent,
    EditProfileComponent,
    PackageToursComponent,
    AllFeedbacksComponent,
    BookingPageComponent,

  ],

  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
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

  ]
})
export class UserModule { }

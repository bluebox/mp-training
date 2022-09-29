import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSliderModule } from '@angular/material/slider' ;
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { PagenotfouondComponent } from './pagenotfouond/pagenotfouond.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { IndexComponent } from './index/index.component';
import { FreelanceRegistrationComponent } from './freelance-registration/freelance-registration.component';
import { FloginPageComponent } from './flogin_page/flogin-page/flogin-page.component';
import { CloginPageComponent } from './clogin_page/clogin-page/clogin-page.component';
import { JobsComponent } from './flogin_page/jobs/jobs.component';
import { ProposalsComponent } from './flogin_page/proposals/proposals.component';
import { PaymentDetailsComponent } from './flogin_page/payment-details/payment-details.component';
import { FcontractDetailsComponent } from './flogin_page/fcontract-details/fcontract-details.component';
import { PostAJobComponent } from './clogin_page/post-a-job/post-a-job.component';
import { FreelancerProposalsComponent } from './clogin_page/freelancer-proposals/freelancer-proposals.component';
import { FeePaymentDetailsComponent } from './clogin_page/fee-payment-details/fee-payment-details.component';
import { CcontractDetailsComponent } from './clogin_page/ccontract-details/ccontract-details.component';
import { SendProposalPageComponent } from './flogin_page/send-proposal-page/send-proposal-page.component';
import { ProposalDetailsComponent } from './clogin_page/proposal-details/proposal-details.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    PagenotfouondComponent,
    IndexComponent,
    FreelanceRegistrationComponent,
    FloginPageComponent,
    CloginPageComponent,
    JobsComponent,
    ProposalsComponent,
    PaymentDetailsComponent,
    FcontractDetailsComponent,
    PostAJobComponent,
    FreelancerProposalsComponent,
    FeePaymentDetailsComponent,
    CcontractDetailsComponent,
    SendProposalPageComponent,
    ProposalDetailsComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MatSliderModule,
    NgbModule,
    BrowserAnimationsModule,
    ReactiveFormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

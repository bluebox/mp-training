import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AdminPageComponent } from './module/admin-page/admin-page.component';
import { PagenotfouondComponent } from './pagenotfouond/pagenotfouond.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
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

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'login/:id', component: LoginComponent },
  { path: 'admin', loadChildren: () => import('./module/module.module').then(m => m.ModuleModule) },
  { path: 'client-register', component: RegisterComponent },
  {
    path: 'freelance_login_page', component: FloginPageComponent, children: [
      { path: 'jobs', component: JobsComponent },
      { path: 'proposals', component: ProposalsComponent},
      { path: 'payment_details', component: PaymentDetailsComponent},
      { path: 'fcontract_details', component: FcontractDetailsComponent }

    ]
  },
  {
    path: 'client_login_page', component: CloginPageComponent, children: [
      { path: 'post_a_job', component: PostAJobComponent},
      { path: 'freelancer_proposals', component: FreelancerProposalsComponent },
      { path: 'fee_payment_details', component: FeePaymentDetailsComponent },
      { path: 'ccontract_details', component: CcontractDetailsComponent }

    ]
  },
  { path: 'freelance-registration', component: FreelanceRegistrationComponent },
  { path: '**', component: PagenotfouondComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

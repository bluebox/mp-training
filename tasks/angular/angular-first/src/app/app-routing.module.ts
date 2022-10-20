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
import { SendProposalPageComponent } from './flogin_page/send-proposal-page/send-proposal-page.component';
import { ProposalDetailsComponent } from './clogin_page/proposal-details/proposal-details.component';
import { EditProfilecComponent } from './clogin_page/edit-profilec/edit-profilec.component';
import { EditProfileComponent } from './flogin_page/edit-profile/edit-profile.component';
import { FguardGuard } from './fguard.guard';
import { EditProposalComponent } from './flogin_page/edit-proposal/edit-proposal.component';
import { EditJobComponent } from './clogin_page/edit-job/edit-job.component';
import { CguardGuard } from './cguard.guard';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  // { path: 'login/:id', component: LoginComponent },
  { path: 'admin', loadChildren: () => import('./module/module.module').then(m => m.ModuleModule) },
  { path: 'client-register', component: RegisterComponent },
  { path: 'freelance-registration', component: FreelanceRegistrationComponent },
  {
    path: 'freelance_login_page', component: FloginPageComponent ,canActivate: [FguardGuard]
  },
  { path: 'freelance_login_page/jobs', component: JobsComponent ,canActivate: [FguardGuard] },
  { path: 'freelance_login_page/jobs/send_proposal_page/:job_id', component: SendProposalPageComponent ,canActivate: [FguardGuard] },

  { path: 'freelance_login_page/proposals', component: ProposalsComponent ,canActivate: [FguardGuard]},
  { path: 'freelance_login_page/payment_details', component: PaymentDetailsComponent,canActivate: [FguardGuard] },
  { path: 'freelance_login_page/fcontract_details', component: FcontractDetailsComponent,canActivate: [FguardGuard] },
  { path: 'freelance_login_page/edit_profile', component: EditProfileComponent ,canActivate: [FguardGuard]},
  { path: 'freelance_login_page/edit_proposal/:id/:job_id', component: EditProposalComponent ,canActivate: [FguardGuard]},


  {
    path: 'client_login_page', component: CloginPageComponent ,canActivate: [CguardGuard]
  },
  { path: 'client_login_page/post_a_job', component: PostAJobComponent ,canActivate: [CguardGuard] },
  { path: 'client_login_page/freelancer_proposals', component: FreelancerProposalsComponent,canActivate: [CguardGuard] },
  { path: 'client_login_page/fee_payment_details', component: FeePaymentDetailsComponent,canActivate: [CguardGuard] },
  { path: 'client_login_page/ccontract_details', component: CcontractDetailsComponent,canActivate: [CguardGuard]},
  { path: 'client_login_page/edit_profilec', component: EditProfilecComponent ,canActivate: [CguardGuard]},
  { path: 'client_login_page/freelancer_proposals/proposal_details/:id', component: ProposalDetailsComponent,canActivate: [CguardGuard]},
  { path: 'client_login_page/freelancer_proposals/edit_job/:id', component: EditJobComponent ,canActivate: [CguardGuard]},



  { path: '**', component: PagenotfouondComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

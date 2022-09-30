import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddReviewComponent } from './add-review/add-review.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EmployeeSignUpComponent } from './employee-sign-up/employee-sign-up.component';
import { ReviewsComponent } from './reviews/reviews.component';
import { ShowBillsComponent } from './show-bills/show-bills.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { UserhomeComponent } from './userhome/userhome.component';


const routes: Routes = [

    {
        path: '', component: DashboardComponent, children: [
            { path: 'myProfile', component: UserhomeComponent },
            { path: 'emp', component: EmployeeSignUpComponent },
            { path: '', component: ReviewsComponent },
            { path: 'updateprofile', component: UpdateProfileComponent },
            { path: 'addreview', component: AddReviewComponent },
            { path: 'showbills', component: ShowBillsComponent },
        ]
    },

];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule { }

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';

import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserindexComponent } from './components/userindex/userindex.component';
import { DialogOverviewExampleDialog, UserhomeComponent } from './components/userhome/userhome.component';
import { FacilitiesModule } from '../facilities/facilities.module';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FeedbackmodalComponent } from './components/feedbackmodal/feedbackmodal.component';
import {MatFormFieldModule, MatLabel} from '@angular/material/form-field';
import { AngularMaterialModule } from '../../angular-material.module';

import { MatInputModule } from '@angular/material/input';
import {MatDialogModule} from '@angular/material/dialog';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent,
    UserindexComponent,
    UserhomeComponent,
    FeedbackmodalComponent,
    DialogOverviewExampleDialog,
    // HeaderComponent
    
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    FacilitiesModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    AngularMaterialModule,
    MatInputModule,
    MatDialogModule,
    FormsModule,
    NgbModule,
  ],
  exports: [
    MatFormFieldModule,
  
  ]
})
export class UsersModule { }

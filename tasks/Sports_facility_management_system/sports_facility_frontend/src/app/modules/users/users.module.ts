import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';

import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserindexComponent } from './components/userindex/userindex.component';
import { UserhomeComponent } from './components/userhome/userhome.component';

import { FacilitiesModule } from '../facilities/facilities.module';


@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent,
    UserindexComponent,
    UserhomeComponent,
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    FacilitiesModule
    
  ]
})
export class UsersModule { }

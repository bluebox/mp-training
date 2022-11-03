import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserAuthRoutingModule } from './user-auth-routing.module';
import { UserAuthComponent } from './user-auth.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { SuccessPageComponent } from './success-page/success-page.component';


@NgModule({
  declarations: [
    UserAuthComponent,
    LoginComponent,
    RegisterComponent,
    SuccessPageComponent
  ],
  imports: [
    CommonModule,
    UserAuthRoutingModule
  ]
})
export class UserAuthModule { }

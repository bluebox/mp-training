import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { UserLoginComponent } from './user-login/user-login.component';
import {MatToolbarModule} from  '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormGroup, FormsModule } from '@angular/forms';
import { UserRegisterComponent } from './user-register/user-register.component';
import {MatCardModule} from  '@angular/material/card';
import {MatRadioModule} from '@angular/material/radio';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule, MatPseudoCheckbox } from '@angular/material/core';
import { MatSliderModule } from '@angular/material/slider';

import {MatInputModule} from '@angular/material/input';
import { CustomMaterialModule } from './user.material';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatTabsModule} from '@angular/material/tabs';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {MatSelectModule} from '@angular/material/select';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import { ErrorUserComponent } from './error-user/error-user.component';
import { ChangepasswordComponent } from './changepassword/changepassword.component';
import { UserLogoutComponent } from './user-logout/user-logout.component';
import { UserForgotpasswordComponent } from './user-forgotpassword/user-forgotpassword.component';
import { TokenInterceptorService } from '../services/token-interceptor.service';

@NgModule({
  declarations: [
    UserComponent,
    UserLoginComponent,
    UserRegisterComponent,
    ErrorUserComponent,
    ChangepasswordComponent,
    UserLogoutComponent,
    UserForgotpasswordComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    UserRoutingModule,
    MatToolbarModule,
    // MatButtonModule,
    MatFormFieldModule,
    MatCardModule,
    MatRadioModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatRadioModule,
    MatSliderModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatCardModule,
    MatButtonModule,
    MatRadioModule,
    MatDatepickerModule,
    CustomMaterialModule,
    MatCheckboxModule,
    MatTabsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSelectModule,
    MatGridListModule,
    MatInputModule,
    MatIconModule
    
  ],
  providers: [
    {provide:HTTP_INTERCEPTORS, useClass: TokenInterceptorService,multi:true},

  ],
  exports: [
    MatButtonModule,
    MatInputModule,
    ] ,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    

})
export class UserModule {
  hide = true;

 }

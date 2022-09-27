import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RestaurantsRoutingModule } from './restaurants-routing.module';
import { HomeComponent } from './home/home.component';
import { SigninComponent } from './signin/signin.component';
import { LoginComponent } from './login/login.component';
import {MatFormFieldModule} from '@angular/material/form-field'; 
import {MatInputModule} from '@angular/material/input'; 
import {MatToolbarModule} from '@angular/material/toolbar'; 
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import { AddFoodComponent } from './add-food/add-food.component';
import {MatCheckboxModule} from '@angular/material/checkbox'; 
import { FormsModule } from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
@NgModule({
  declarations: [
    HomeComponent,
    SigninComponent,
    LoginComponent,
    AddFoodComponent
  ],
  imports: [
    CommonModule,
    RestaurantsRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    FormsModule,
    MatDialogModule

  ]
})
export class RestaurantsModule { }

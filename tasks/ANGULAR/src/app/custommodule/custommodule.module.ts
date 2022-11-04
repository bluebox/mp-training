import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustommoduleRoutingModule } from './custommodule-routing.module';
import { CustommoduleComponent } from './custommodule.component';
import { HomeComponent } from './signup/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ReactiveFormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';


@NgModule({
  declarations: [
    CustommoduleComponent,
    HomeComponent,
    DashboardComponent
  ],
  imports: [
    CommonModule,
    CustommoduleRoutingModule,
   ReactiveFormsModule,
   MatCardModule



  ]
})
export class CustommoduleModule { }

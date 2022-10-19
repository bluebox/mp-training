import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import { AltermoviesComponent } from './components/altermovies/altermovies.component';
import {MatTableModule} from '@angular/material/table'
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { MovieFormComponent } from './components/movie-form/movie-form.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AlterusersComponent } from './components/alterusers/alterusers.component';
import { AltertheatresComponent } from './components/altertheatres/altertheatres.component';
import { TheatreFormComponent } from './components/theatre-form/theatre-form.component';
import { AddAdminComponent } from './components/add-admin/add-admin.component';
import { AlterHallsComponent } from './components/alter-halls/alter-halls.component';
import { HallFormComponent } from './components/hall-form/hall-form.component';
@NgModule({
  declarations: [
    DashboardComponent,
    AltermoviesComponent,
    MovieFormComponent,
    AlterusersComponent,
    AltertheatresComponent,
    TheatreFormComponent,
    AddAdminComponent,
    AlterHallsComponent,
    HallFormComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MatListModule,
    MatMenuModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule

  ]
})
export class AdminModule { }

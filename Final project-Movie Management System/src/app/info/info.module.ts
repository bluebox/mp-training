import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InfoRoutingModule } from './info-routing.module';
import { InfoComponent } from './info.component';
import { HeaderComponent } from './components/header/header.component';
import { BollywoodComponent } from './components/bollywood/bollywood.component';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatSliderModule } from '@angular/material/slider';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
// import { HollywoodComponent } from './components/hollywood/hollywood.component';
import { ViewComponent } from './components/view/view.component';
// import { TollywoodComponent } from './components/tollywood/tollywood.component';



 

@NgModule({
  declarations: [
    InfoComponent,
    HeaderComponent,
    BollywoodComponent,
    // HollywoodComponent,
    ViewComponent,
    // TollywoodComponent
  ],
  imports: [
    CommonModule,
    InfoRoutingModule,
    MatFormFieldModule,
    MatSelectModule,
    MatSliderModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule,
    MatDividerModule

  ]
})
export class InfoModule { }

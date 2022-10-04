import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './Indexpage/header/header.component';
import {MatCardModule} from '@angular/material/card';
import {MatCardHarness} from '@angular/material/card/testing';
import {MatSliderModule} from '@angular/material/slider';
import {RegisterModule} from './register/register.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import{MatSidenavModule} from '@angular/material/sidenav';
import { RouterGuardGuard } from './router-guard.guard';
import { DataserveService } from './dataserve.service';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RegisterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    

   
  ],
  providers: [RouterGuardGuard,DataserveService],
  bootstrap: [AppComponent]
})
export class AppModule { }

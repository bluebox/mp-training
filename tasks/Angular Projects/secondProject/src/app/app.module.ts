import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Component1Component } from './component1/component1.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { Childcomponent1Component } from './component1/childcomponent1/childcomponent1.component';
import { HeaderComponent } from './header/header.component';
<<<<<<< HEAD
import { ContainerComponent } from './container/container.component';
import { EmployesComponent } from './employes/employes.component';
=======
import { APP_CONFIG, APP_SERVICE_CONFIG } from './AppConfig/appconfig.service';
>>>>>>> f3a83ba9a9807ebb4ad43bf59adc8b5d50690aed

@NgModule({
  declarations: [
    AppComponent,
    Component1Component,
    Childcomponent1Component,
    HeaderComponent,
    ContainerComponent,
    EmployesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule
  ],
  providers: [
    provide: APP_SERVICE_CONFIG,
    useValue:APP_CONFIG,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

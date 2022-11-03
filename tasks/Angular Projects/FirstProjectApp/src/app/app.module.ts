import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Component1Component } from './component1/component1.component';
import { ChildcomponentComponent } from './component1/childcomponent/childcomponent.component';
import { ContainerComponent } from './container/container.component';
import { HeaderComponent } from './header/header.component';
import { EmployeeComponent } from './employee/employee.component';
import { APP_CONFIG, APP_SERVICE_CONFIG } from './AppConfig/appconfig.service';

@NgModule({
  declarations: [
    AppComponent,
    Component1Component,
    ChildcomponentComponent,
    ContainerComponent,
    HeaderComponent,
    EmployeeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [{
    provide:APP_SERVICE_CONFIG,
    useValue:APP_CONFIG
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpServiceService } from './modules/users/http-service.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './modules/components/navbar/navbar.component';
import { HomeComponent } from './modules/components/home/home.component';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatButtonModule } from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { FooterComponent } from './modules/components/footer/footer.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    // FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatIconModule,
    MatTooltipModule,
    MatButtonModule,
    MatToolbarModule
  ],
  // exports:[
  //   NavbarComponent

  // ],

  providers: [HttpServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }


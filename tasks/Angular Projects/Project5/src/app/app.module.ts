import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule,  } from '@angular/material/slider';
import {Component} from '@angular/core';
import { MatToolbar, MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { PAGENOTFOUNDComponent } from './pagenotfound/pagenotfound.component';
import {MatCardModule} from  '@angular/material/card';
import { FormsModule } from '@angular/forms';
import {MatRadioModule} from '@angular/material/radio';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CustomMaterialModule } from './user/user.material';
import { MatMenuModule } from '@angular/material/menu';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptorService } from './services/token-interceptor.service';


@NgModule({
  declarations: [
    AppComponent,
    PAGENOTFOUNDComponent,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatCardModule,
    MatButtonModule,
    MatRadioModule,
    MatDatepickerModule,
    FormsModule,
    MatMenuModule,
    MatIconModule,
    HttpClientModule,
   



    
  ],
  providers: [
    {provide:HTTP_INTERCEPTORS, useClass: TokenInterceptorService,multi:true},

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

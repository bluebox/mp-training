import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { Product1Component } from './product1/product1.component';
import { Product2Component } from './product2/product2.component';
import { ProductComponent } from './product/product.component';
import { ClientComponent } from './client/client.component';
import { SearchComponent } from './search/search.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AdminComponent } from './admin/admin.component';
import { AdminEditComponent } from './admin-edit/admin-edit.component';
import { AdminDeleteComponent } from './admin-delete/admin-delete.component';
import { AdminManageComponent } from './admin-manage/admin-manage.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoansComponent } from './loans/loans.component';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { CommonInterceptorInterceptor } from './common-interceptor.interceptor';
import { UserComponent } from './user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    Product1Component,
    Product2Component,
    ProductComponent,
    LoansComponent,
    ClientComponent,
    SearchComponent,
    PageNotFoundComponent,
    AdminComponent,
    AdminEditComponent,
    AdminDeleteComponent,
    AdminManageComponent,
    UserComponent,
   
  ],
  imports: [
    HttpClientModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    ReactiveFormsModule,
  ],
  providers: [
    {provide:HTTP_INTERCEPTORS, useClass: CommonInterceptorInterceptor,multi:true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

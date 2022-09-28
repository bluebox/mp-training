import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { ProblemsComponent } from './components/problems/problems.component';
import { ProfileComponent } from './components/profile/profile.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { RegisterService } from './services/register.service';
import { AuthGuard } from './shared/auth.guard';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { ProblemComponent } from './components/problem/problem.component';
import { ProblemdiscussionComponent } from './components/problemdiscussion/problemdiscussion.component';
import { HomeComponent } from './components/home/home.component';
import { CategoryComponent } from './components/category/category.component';
import { PostQuestionComponent } from './components/post-question/post-question.component';
@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    NavbarComponent,
    LoginComponent,
    ProblemsComponent,
    ProfileComponent,
    EditProfileComponent,
    ProblemComponent,
    ProblemdiscussionComponent,
    HomeComponent,
    CategoryComponent,
    PostQuestionComponent,  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ]
,  providers: [RegisterService, AuthGuard, {
  provide: HTTP_INTERCEPTORS,
  useClass: TokenInterceptorService,
  multi: true
}],
  bootstrap: [AppComponent]
})
export class AppModule { }

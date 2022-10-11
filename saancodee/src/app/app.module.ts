import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { DiscussionComponent } from './components/discussion/discussion.component';
import { SubmissionComponent } from './components/submission/submission.component';
import { PostDiscussionComponent } from './components/post-discussion/post-discussion.component';
import { BlogComponent } from './components/blog/blog.component';
import { BlogItemComponent } from './components/blog-item/blog-item.component';
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
    DiscussionComponent,
    SubmissionComponent,
    PostDiscussionComponent,
    BlogComponent,
    BlogItemComponent,  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ]
,  providers: [RegisterService, AuthGuard, {
  provide: HTTP_INTERCEPTORS,
  useClass: TokenInterceptorService,
  multi: true
}],
  bootstrap: [AppComponent]
})
export class AppModule { }

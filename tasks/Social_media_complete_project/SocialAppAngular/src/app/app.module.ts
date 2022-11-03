import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatMenuModule } from '@angular/material/menu';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatChipsModule } from '@angular/material/chips';
import { MatBadgeModule } from '@angular/material/badge';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatRippleModule, MAT_DATE_FORMATS } from '@angular/material/core';
import { MatTabsModule } from '@angular/material/tabs';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatMomentDateModule } from "@angular/material-moment-adapter";
import { MatSliderModule } from '@angular/material/slider';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HeaderComponent } from './component/header/header.component';
import { LoginComponent } from './component/login/login.component';
import { SignupComponent } from './component/signup/signup.component';
import { ProfileComponent } from './component/profile/profile.component';
import { SettingsComponent } from './component/settings/settings.component';
import { ExampleComponent } from './component/example/example.component';
import { SnakebarComponent } from './component/snakebar/snakebar.component';
import { ForgotPasswordDialogComponent } from './component/forgot-password-dialog/forgot-password-dialog.component';
import { LogoutComponent } from './component/logout/logout.component';
import { MessageComponent } from './component/message/message.component';
import { ResetPasswordComponent } from './component/reset-password/reset-password.component';
import { VerifyEmailComponent } from './component/verify-email/verify-email.component';
import { TimelineComponent } from './component/timeline/timeline.component';
import { PostComponent } from './component/post/post.component';
import { PostLikeDialogComponent } from './component/post-like-dialog/post-like-dialog.component';
import { PostCommentDialogComponent } from './component/post-comment-dialog/post-comment-dialog.component';
import { APP_DATE_FORMATS } from './common/app-date-formats';
import { PostShareDialogComponent } from './component/post-share-dialog/post-share-dialog.component';
import { ShareConfirmDialogComponent } from './component/share-confirm-dialog/share-confirm-dialog.component';
import { PostDialogComponent } from './component/post-dialog/post-dialog.component';
import { ConfirmationDialogComponent } from './component/confirmation-dialog/confirmation-dialog.component';
import { WaitingDialogComponent } from './component/waiting-dialog/waiting-dialog.component';
import { CommentLikeDialogComponent } from './component/comment-like-dialog/comment-like-dialog.component';
import { TagDialogComponent } from './component/tag-dialog/tag-dialog.component';
import { SearchDialogComponent } from './component/search-dialog/search-dialog.component';
import { PostDetailComponent } from './component/post-detail/post-detail.component';
import { PhotoUploadDialogComponent } from './component/photo-upload-dialog/photo-upload-dialog.component';
import { FollowingFollowerListDialogComponent } from './component/following-follower-list-dialog/following-follower-list-dialog.component';
import { AuthInterceptor } from './interceptor/auth.interceptor';
import { ViewPhotoDialogComponent } from './component/view-photo-dialog/view-photo-dialog.component';
import { AuthGuard } from './guard/auth.guard';
import { HomeComponent } from './component/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    SignupComponent,
    ProfileComponent,
    SettingsComponent,
    ExampleComponent,
    SnakebarComponent,
    ForgotPasswordDialogComponent,
    LogoutComponent,
    MessageComponent,
    ResetPasswordComponent,
    VerifyEmailComponent,
    TimelineComponent,
    PostComponent,
    PostLikeDialogComponent,
    PostCommentDialogComponent,
    PostShareDialogComponent,
    ShareConfirmDialogComponent,
    PostDialogComponent,
    ConfirmationDialogComponent,
    WaitingDialogComponent,
    HomeComponent,
    CommentLikeDialogComponent,
    TagDialogComponent,
    SearchDialogComponent,
    PostDetailComponent,
    PhotoUploadDialogComponent,
    FollowingFollowerListDialogComponent,
    ViewPhotoDialogComponent,

  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatDividerModule,
    MatListModule,
    MatTooltipModule,
    MatChipsModule,
    MatBadgeModule,
    MatDialogModule,
    MatSnackBarModule,
    MatRippleModule,
    MatTabsModule,
    MatSelectModule,
    MatRadioModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatMomentDateModule,
    MatSliderModule,
    MatAutocompleteModule,
    MatDividerModule,
    MatSlideToggleModule
  ],
  providers: [
    AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: MAT_DATE_FORMATS, useValue: APP_DATE_FORMATS }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

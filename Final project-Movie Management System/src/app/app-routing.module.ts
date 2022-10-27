import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './admin.guard';
import { AuthGuard } from './auth.guard';
import { CancelledComponent } from './cancelled/cancelled.component';
import { ConcertsComponent } from './concerts/concerts.component';
import { LoginComponent } from './login/login.component';
import { MoviesComponent } from './movies/movies.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SignupComponent } from './signup/signup.component';


const routes: Routes = [
  {path:"",redirectTo:"movies",pathMatch:'full'},
  {path:'movies',component:MoviesComponent,canActivate:[]},
  {path:'cancelled',component:CancelledComponent,canActivate:[AuthGuard,]},
  {path:'movieInfo',
  loadChildren: () => import('./info/info.module').then(m => m.InfoModule)},
  {path:'admin',
  loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),canActivate:[AuthGuard]},
  {path:'booking',
  loadChildren: () => import('./booking/booking.module').then(m => m.BookingModule),
  canActivate:[AuthGuard,]},
  {path:'concerts',component:ConcertsComponent,canActivate:[AuthGuard,]},
  {path:'signup',component:SignupComponent},
  { path: 'info', loadChildren: () => import('./info/info.module').then(m => m.InfoModule) },
  // {path:'login',component:LoginComponent},
  {path:"**",component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

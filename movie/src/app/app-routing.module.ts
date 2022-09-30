import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConcertsComponent } from './concerts/concerts.component';
// import { MoviedescriptionComponent } from './moviedescription/moviedescription.component';
import { MoviesComponent } from './movies/movies.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  {path:"",redirectTo:"movies",pathMatch:'full'},
  {path:'movies',component:MoviesComponent,children:[{ path:'moviename' , component:MoviesComponent}]},
  {path:'movieInfo',
  loadChildren: () => import('./info/info.module').then(m => m.InfoModule)},
  {path:'admin',
  loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)},
  {path:'booking',
  loadChildren: () => import('./booking/booking.module').then(m => m.BookingModule)},
  {path:'concerts',component:ConcertsComponent},
  {path:'signup',component:SignupComponent},
  { path: 'info', loadChildren: () => import('./info/info.module').then(m => m.InfoModule) },
  {path:"**",component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

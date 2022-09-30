import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ErrorComponent } from './component/error/error.component';
import { HomeComponent } from './component/home/home.component';

const routes: Routes = [
  
  {path: 'home', component:HomeComponent},
  {path: 'admin', loadChildren:  () => import('./modules/admin/admin.module').then(m => m.AdminModule)},
  {path: 'user', loadChildren:  () => import('./modules/users/users.module').then(m => m.UsersModule)},
  {path: 'facilities', loadChildren:  () => import('./modules/facilities/facilities.module').then(m => m.FacilitiesModule)},
  {path:'**',component:ErrorComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

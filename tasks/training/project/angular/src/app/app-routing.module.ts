import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AdminGaurdGuard } from './modules/admin/admin-gaurd.guard';

const routes: Routes = [
  { path: "", component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'users', loadChildren: () => import('./modules/users/users.module').then(m => m.UsersModule) },
  { path: 'customer', loadChildren: () => import('./modules/customer/customer.module').then(m => m.CustomerModule) },
  { path: 'doctor', loadChildren: () => import('./modules/doctor/doctor.module').then(m => m.DoctorModule) },
  { path: 'admin',
  canActivate :[AdminGaurdGuard]
  , loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule) },
  { path: "**", component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

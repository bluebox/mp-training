import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterCustomerComponent } from './components/register-customer/register-customer.component';
import { AdminGaurdGuard } from './gaurds/admin-gaurd.guard';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterCustomerComponent},
  { path: 'update-customer/:customer_id', component: RegisterCustomerComponent},
  {
    path: 'admin',
    // canActivate: [AdminGaurdGuard],
    loadChildren: () =>
      import('./modules/users/users.module').then((m) => m.UsersModule),
  },
  {
    path: 'customer',
    loadChildren: () =>
      import('./modules/customer/customer.module').then(
        (m) => m.CustomerModule
      ),
  },  

  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

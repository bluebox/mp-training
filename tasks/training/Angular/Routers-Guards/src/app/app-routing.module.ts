import { LoginComponent } from './Components/login/login.component';
import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './Components/register/register.component';
import { AdminComponent } from './Components/admin/admin.component';
import { ContactComponent } from './Components/contact/contact.component';
import { AboutComponent } from './Components/about/about.component';
import { NotFoundComponent } from './Components/not-found/not-found.component';
import { AdminguardGuard } from './adminguard.guard';
import { FormComponent } from './Components/form/form.component';

const routes: Routes = [
  { path:"login", component: LoginComponent },
  { path:"", redirectTo: 'login', pathMatch: "full" },
  { path:"register", component: RegisterComponent },
  { path:"admin",canActivate:[AdminguardGuard], loadChildren: () => import('./Modules/admin-routing.module.ts/admin-routing.module.ts-routing.module').then(m => m.AdminModuleTsRoutingModule) },
  { path:"contact", component: ContactComponent },
  { path:"about", component: AboutComponent },
  { path:"form", component: FormComponent },
  { path:"**", component: NotFoundComponent },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

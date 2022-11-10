import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegisterComponent } from './register/register.component';

// include route paths via Routes module
const routes: Routes = [
  {path:'register', component:RegisterComponent, pathMatch:"full"},
  {path:'login', component:LoginComponent, pathMatch:"full"},
  {path:'', loadChildren:() => import("./admin/admin.module").then(res => res.AdminModule)},
  {path:'**', component:PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BranchComponent } from './components/branch/branch.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RegisterComponent } from './components/register/register.component';
import { ServicesComponent } from './components/services/services.component';

const routes: Routes = [
  {path:'',redirectTo : 'home', pathMatch:'full'},
  {path:'home',component: HomeComponent},
  {path:'login',component: LoginComponent},
  {path:'register',component: RegisterComponent},
  {path:'branch',component: BranchComponent},
  {path:'services',component: ServicesComponent},
  {path:'admin',loadChildren:() => import('./modules/admin/admin.module').then(m => m.AdminModule)},
  {path:'employee',loadChildren:() => import('./modules/employee/employee.module').then(m => m.EmployeeModule)},
  {path:'client',loadChildren:() => import('./modules/client/client.module').then(m => m.ClientModule)},
  {path:'**',component:NotFoundComponent}  //dont keep notfound component first it will always give not found html text
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

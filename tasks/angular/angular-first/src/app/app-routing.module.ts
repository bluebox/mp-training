import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChildComponentComponent } from './child-component/child-component.component';
import { LoginComponent } from './login/login.component';
import { AdminPageComponent } from './module/admin-page/admin-page.component';
import { PagenotfouondComponent } from './pagenotfouond/pagenotfouond.component';
import { RegisterComponent } from './register/register.component';
import { ReactiveFormsModule } from '@angular/forms';
import { IndexComponent } from './index/index.component';
import { FreelanceRegistrationComponent } from './freelance-registration/freelance-registration.component';

const routes: Routes = [
  {path : 'childcomponent' ,component: ChildComponentComponent},
  {path : '' ,component : IndexComponent },
  {path : 'login', component: LoginComponent},
  {path : 'login/:id' , component: LoginComponent},
  {path : 'admin', loadChildren : () => import('./module/module.module').then( m => m.ModuleModule)},
  {path : 'register', component : RegisterComponent},
  {path : '**', component : PagenotfouondComponent },
  {path : 'freelance-registration', component: FreelanceRegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

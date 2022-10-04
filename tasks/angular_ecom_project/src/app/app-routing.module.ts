import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './Indexpage/header/header.component';
import { RegisterRoutingModule } from './register/register-routing.module';

const routes: Routes = [
  {path: 'signup',loadChildren:() => import('../app/register/register.module').then(m => m.RegisterModule)},
            // {path:'',redirectTo:'signup',pathMatch:"full"},
           
            
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    RegisterRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }

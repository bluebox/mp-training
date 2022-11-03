import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';

const routes: Routes = [
  { path: 'user', loadChildren: () => import('./user/user.module').then(m => m.UserModule) },
  {path:'home', component: HomeComponent},
  {path:'**', component: PagenotfoundComponent, pathMatch:'full'}
]
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
